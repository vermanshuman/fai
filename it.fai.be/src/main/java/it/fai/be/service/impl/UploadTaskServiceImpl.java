package it.fai.be.service.impl;

import fai.broker.models.UploadStatusInfo;
import fai.broker.models.UploadTaskConfig;
import fai.broker.models.UploadTaskProperty;
import fai.common.db.SqlQueries;
import it.fai.be.constant.ValueConstant;
import it.fai.be.dto.CSVFileDTO;
import it.fai.be.dto.UploadTaskDTO;
import it.fai.be.service.UploadTaskService;
import it.fai.be.utils.DateUtil;
import it.fai.be.utils.FilesUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

@Log4j2
@Service
public class UploadTaskServiceImpl implements UploadTaskService {

    @Override
    public CSVFileDTO uploadCSV(CSVFileDTO csvFileDTO, Connection conn) throws Exception {
        log.debug("Updating CSV File");
        if (csvFileDTO != null) {
            List<UploadTaskProperty> uploadTaskProperties = fai.broker.db.SqlQueries.findUploadTaskProperty(
                    ValueConstant.CSV_UPLOAD_ROOT_DIR, conn);
            if (uploadTaskProperties != null && uploadTaskProperties.size() > 0) {
                UploadTaskProperty uploadTaskProperty = uploadTaskProperties.get(0);
                String uploadDirectory = uploadTaskProperty.getValue() + ValueConstant.BSLASH + csvFileDTO.getMagazzinoAcronym();
                if (Files.notExists(Paths.get(uploadDirectory))) {
                    Files.createDirectories(Paths.get(uploadDirectory));
                }
                String base64Content = csvFileDTO.getCsvFileContent();
                base64Content = base64Content.replaceAll("data:application.*base64,", "");
                base64Content = base64Content.replaceAll("data:image.*base64,", "");
                String filename = csvFileDTO.getCsvFileName().replace(" ", ValueConstant.DASH);
                String fileNameWithOutExt = FilenameUtils.removeExtension(filename);
                String extension = FilenameUtils.getExtension(filename);
                filename = fileNameWithOutExt + ValueConstant.DASH + DateUtil.currentDateForFilename() + "." + extension;
                FilesUtils.writeBase64File(uploadDirectory + ValueConstant.BSLASH + filename, base64Content);

                UploadTaskConfig uploadTaskConfig = new UploadTaskConfig();
                uploadTaskConfig.setOid(null);
                uploadTaskConfig.setDescr("Ordini CSV");
                uploadTaskConfig.setCsvFileName(filename);
                uploadTaskConfig.setMagazzinoAcronym(csvFileDTO.getMagazzinoAcronym());
                uploadTaskConfig.setStatus(UploadStatusInfo.newToProcessInstance(null, null));
                uploadTaskConfig.setCreationTs(Calendar.getInstance());
                fai.broker.db.SqlQueries.insertUploadTaskConfig(uploadTaskConfig, conn);
            }
        }


        return csvFileDTO;
    }

    @Override
    public List<UploadTaskDTO> findAll(String startDate, String endDate) {

        return null;
    }

}
