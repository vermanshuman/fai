package it.fai.be.service.impl;

import fai.broker.models.*;
import fai.broker.task.GenericTask;
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

import javax.swing.text.html.Option;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<UploadTaskDTO> findAll(String startDate, String endDate, Connection conn) throws Exception {
        log.debug("Find All Upload Tasks");
        List<UploadTaskConfig> uploadTasks = fai.broker.db.SqlQueries.getAllUploadTask(conn);
        List<Magazzino> magazzini = fai.broker.db.SqlQueries.getAllMagazzino(ValueConstant.MAGAZZINO_CONTEXT, conn);
        return uploadTasks
                .stream()
                .map(uploadTask -> setUploadTask(uploadTask,magazzini))
                .map(uploadTaskDTO -> setOrderCount(uploadTaskDTO, conn))
                .collect(Collectors.toList());
    }

    @Override
    public UploadTaskDTO executeImportTask(Long taskOID, Connection conn) throws Exception {
        log.debug("Execute Task" , taskOID);
        UploadTaskDTO uploadTaskDTO = null;
        UploadTaskConfig uploadTask = fai.broker.db.SqlQueries.findUploadTask(taskOID, conn);
        if(uploadTask != null){
            uploadTaskDTO = setUploadTask(uploadTask);

            String className = SqlQueries.getGenericTaskConfigClassName(ValueConstant.IMPORT_ACRONYM, conn);
            if (className == null || "".equals(className))
                throw new IllegalArgumentException(
                        "inammissibile, nessun " + GenericTask.class.getName() + " per l'ACRONYM IMP_ORDINE_IN");
            //
            GenericTask genericTask = (GenericTask) Class.forName(className).newInstance();
            genericTask.setup(ValueConstant.IMPORT_ACRONYM + "@" + taskOID,  Calendar.getInstance(), conn);
            String error = genericTask.doJob();
            uploadTaskDTO.setMessage(error);
        }
        return uploadTaskDTO;
    }

    @Override
    public UploadTaskDTO calculatorTask(Long taskOID, Connection conn) throws Exception {
        log.debug("Calculate Task" , taskOID);
        UploadTaskDTO uploadTaskDTO = null;
        UploadTaskConfig uploadTask = fai.broker.db.SqlQueries.findUploadTask(taskOID, conn);
        if(uploadTask != null){
            uploadTaskDTO = setUploadTask(uploadTask);

            String className = SqlQueries.getGenericTaskConfigClassName(ValueConstant.CALCULATOR_ACRONYM, conn);
            if (className == null || "".equals(className))
                throw new IllegalArgumentException(
                        "inammissibile, nessun " + GenericTask.class.getName() + " per l'ACRONYM IMP_ORDINE_IN");
            //
            GenericTask genericTask = (GenericTask) Class.forName(className).newInstance();
            genericTask.setup(ValueConstant.CALCULATOR_ACRONYM + "@" + taskOID,  Calendar.getInstance(), conn);
            String error = genericTask.doJob();
            uploadTaskDTO.setMessage(error);
        }
        return uploadTaskDTO;
    }

    @Override
    public UploadTaskDTO procurementManagerTask(Long taskOID, Connection conn) throws Exception {
        log.debug("Procurement Task" , taskOID);
        UploadTaskDTO uploadTaskDTO = null;
        UploadTaskConfig uploadTask = fai.broker.db.SqlQueries.findUploadTask(taskOID, conn);
        if(uploadTask != null){


            String className = SqlQueries.getGenericTaskConfigClassName(ValueConstant.PROCUREMENT_ACRONYM, conn);
            if (className == null || "".equals(className))
                throw new IllegalArgumentException(
                        "inammissibile, nessun " + GenericTask.class.getName() + " per l'ACRONYM IMP_ORDINE_IN");
            //
            GenericTask genericTask = (GenericTask) Class.forName(className).newInstance();
            genericTask.setup(ValueConstant.PROCUREMENT_ACRONYM + "@" + taskOID,  Calendar.getInstance(), conn);

            String error = genericTask.doJob();
            if(error == null){
                fai.broker.db.SqlQueries.setUploadTaskStatus(taskOID, UploadCSVStatus.VALUE_PROCESSED.getOid(),null , null, conn);
            }
            uploadTaskDTO = setUploadTask(uploadTask);
            uploadTaskDTO.setMessage(error);
        }
        return uploadTaskDTO;
    }

    private UploadTaskDTO setUploadTask(UploadTaskConfig uploadTaskConfig) {
        return setUploadTask(uploadTaskConfig, null);
    }

    private UploadTaskDTO setUploadTask(UploadTaskConfig uploadTaskConfig, List<Magazzino> magazzini) {
        UploadTaskDTO uploadTaskDTO = new UploadTaskDTO();
        uploadTaskDTO.setOid(uploadTaskConfig.getOid());
        if(uploadTaskConfig.getCreationTs() != null)
            uploadTaskDTO.setCreationDate(uploadTaskConfig.getCreationTs().getTime());
        uploadTaskDTO.setCsvFileName(uploadTaskConfig.getCsvFileName());
        uploadTaskDTO.setExecutionStatus(uploadTaskConfig.getStatus().getStatusDescr());
        uploadTaskDTO.setMagazzinoAcronym(uploadTaskConfig.getMagazzinoAcronym());
        uploadTaskDTO.setOrderStatus(uploadTaskConfig.getOrderStatus());
        uploadTaskDTO.setFulFilledOrderCount(1);
        uploadTaskDTO.setProcessedOrderCount(0);
        uploadTaskDTO.setMissingOrderCount(0);
        uploadTaskDTO.setMagazzinoAcronym(uploadTaskConfig.getMagazzinoAcronym());
        if(magazzini != null && !magazzini.isEmpty() &&
                StringUtils.isNotBlank(uploadTaskConfig.getMagazzinoAcronym())){
            Optional<Magazzino> warehouse = magazzini
                    .stream()
                    .filter(m -> m.getAcronym().equalsIgnoreCase(uploadTaskConfig.getMagazzinoAcronym()))
                    .findFirst();
            if(warehouse.isPresent())
                uploadTaskDTO.setMagazzinoAcronym(warehouse.get().getDescr());
        }
        return uploadTaskDTO;
    }

    private UploadTaskDTO setOrderCount(UploadTaskDTO uploadTaskDTO, Connection conn){
        try {
            List<OrdineInCollection> ordineInCollections = fai.broker.db.SqlQueries.findOrdineInCollectionByInputResource(
                    uploadTaskDTO.getCsvFileName(), conn);
            if (ordineInCollections != null && ordineInCollections.size() > 0) {
                Long ordineInCollectionId = ordineInCollections.get(0).getOid();
                uploadTaskDTO.setOrderCount(fai.broker.db.SqlQueries.countOrdineInByCollectionID(ordineInCollectionId, conn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadTaskDTO;
    }

}
