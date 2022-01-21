package it.fai.be.service.impl;

import fai.broker.db.SqlQueries;
import fai.broker.models.OrdineIn;
import it.fai.be.dto.OrderDTO;
import it.fai.be.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class OrdererviceImpl implements OrderService {

    @Override
    public List<OrderDTO> findAll(Connection conn) throws Exception {
        log.debug("Find All Orders");
        List<OrdineIn> orders = fai.broker.db.SqlQueries.getAllOrdine(conn);
        return orders
                .stream()
                .map(order -> setOrder(order, conn))
                .collect(Collectors.toList());
    }


    private OrderDTO setOrder(OrdineIn ordineIn, Connection conn) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setIdVendita(ordineIn.getIdVendita());
        orderDTO.setUserName(ordineIn.getNomeCompletoAcquirente());
        orderDTO.setEmail(ordineIn.getEmailAcquirente());
        orderDTO.setNumberOfLines(ordineIn.getOrderCount());
        orderDTO.setStatus(ordineIn.getStatus() != null && ordineIn.getStatus().getStatus() != null
                ? ordineIn.getStatus().getStatus().getDescr() : null);
        orderDTO.setAmount(ordineIn.getPrezzoTotale());
        try {
            orderDTO.setMissingCount(SqlQueries.countOrdineInDataLinkedToMissingOnes(
                    ordineIn.getOid(), false, false, conn));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDTO;
    }
}
