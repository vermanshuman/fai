package it.fai.be.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.fai.be.controller.mapping.MappingConstants;
import it.fai.be.dto.*;
import it.fai.be.service.OrderService;
import it.fai.be.service.UploadTaskService;
import it.fai.be.utils.DbUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(MappingConstants.ORDER_MAPPING)
public class OrderController extends AbstractController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    /**
     * Find all orders.
     */
    @GetMapping
    @ApiOperation(value = "Find all manual tasks", response = OrderDTO.class)
    public ResponseEntity<OrdersDTO> findAll() {
        Connection conn = null;
        OrdersDTO ordersDTO = new OrdersDTO();
        try {
            conn = getConnection();
            List<OrderDTO> orders = service.findAll(conn);
            ordersDTO.setOrders(orders);
            return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ordersDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(ordersDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }
    }

    /**
     * Find all orders for collection.
     */
    @GetMapping(MappingConstants.TASK_ORDER_MAPPING)
    @ApiOperation(value = "Find all manual tasks for collection", response = OrderDTO.class)
    public ResponseEntity<OrdersDTO> findOrdersByTask(@PathVariable Long taskOID) {
        Connection conn = null;
        OrdersDTO ordersDTO = new OrdersDTO();
        try {
            conn = getConnection();
            List<OrderDTO> orders = service.findOrdersByTask(taskOID, conn);
            ordersDTO.setOrders(orders);
            return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ordersDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(ordersDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }
    }
}
