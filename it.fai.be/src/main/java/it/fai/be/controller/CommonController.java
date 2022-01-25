package it.fai.be.controller;

import fai.broker.db.SqlQueries;
import fai.broker.models.Magazzino;
import it.fai.be.constant.ValueConstant;
import it.fai.be.dto.MagazzinoDto;
import it.fai.be.dto.ResponseDto;
import it.fai.be.utils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CommonController extends AbstractController {

	static final Logger LOG = LogManager.getLogger(CommonController.class);
	
	@GetMapping("/api/magazzino/all")
	public Object getAllMagazzino(HttpServletRequest request) {
		Connection conn = null;
		try {
			conn = getConnection();
			List<Magazzino> magazzini = SqlQueries.getAllMagazzino(ValueConstant.MAGAZZINO_CONTEXT, conn);
			List<MagazzinoDto> results = magazzini.stream()
					.map(m -> MagazzinoDto.from(m))
					.collect(Collectors.toList());
			return ResponseDto.builder().success(true).response(results).build();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return ResponseDto.builder().success(false).response(e.getMessage()).build();
		} finally {
			DbUtils.closeSilent(conn);
		}
	}
	
}
