package it.fai.be.controller;

import it.fai.be.dto.ResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class FaiImportController extends AbstractController {

    static final Logger LOG = LogManager.getLogger(FaiImportController.class);

    @PostMapping("/api/fai/import/csv")
    public Object faiImportCsv(@RequestBody MultipartFile file,
                               HttpServletRequest req, HttpServletResponse res,
                               @RequestParam(required = true) String acronym) throws IOException {
        LOG.debug("faiImportCsv starting, acronym : {}", acronym);
        return ResponseDto.builder().success(true).build();
    }
}
