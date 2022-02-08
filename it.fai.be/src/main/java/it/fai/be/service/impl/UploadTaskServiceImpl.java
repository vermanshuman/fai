package it.fai.be.service.impl;

import fai.broker.models.*;
import fai.broker.task.GenericTask;
import fai.common.db.SqlQueries;
import fai.common.models.GenericTaskConfig;
import it.fai.be.constant.ValueConstant;
import it.fai.be.dto.CSVFileDTO;
import it.fai.be.dto.UploadTaskDTO;
import it.fai.be.enums.ProcessingStatus;
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
import java.util.*;
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
                uploadTaskConfig.setStatus(StatusInfo.newToProcessInstance(null, null));
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
        List<UploadTaskDTO> uploadTaskDTOS = uploadTasks
                .stream()
                .map(uploadTask -> setUploadTask(uploadTask,magazzini))
                .map(uploadTaskDTO -> setOrderCount(uploadTaskDTO, conn))
                .collect(Collectors.toList());

        boolean isExecute = false;
        boolean inCorso = false;
        for(UploadTaskDTO uploadTaskDTO : uploadTaskDTOS){
            if(!isExecute && StringUtils.isNotBlank(uploadTaskDTO.getExecutionStatus()) &&
                    uploadTaskDTO.getExecutionStatus().equalsIgnoreCase(ProcessingStatus.ESEGUIRE.toString())){
                uploadTaskDTO.setButtonDisabled(Boolean.FALSE);
                isExecute = true;
            }
            if(!inCorso && StringUtils.isNotBlank(uploadTaskDTO.getExecutionStatus()) &&
                    uploadTaskDTO.getExecutionStatus().equalsIgnoreCase(ProcessingStatus.INCORSO.toString())){
                uploadTaskDTO.setButtonDisabled(Boolean.FALSE);
                inCorso = true;
            }
            if(isExecute && inCorso)
                break;
        }

        List<GenericTaskConfig> genericTaskConfigs = fai.broker.db.SqlQueries.getALlGenericTaskConfig(
                "FAI_GENERIC_TASK.CLASS_NAME = 'fai.broker.task.impord.OrdineInImporterTask'", conn);

        List<UploadTaskDTO> scheduledTasks = genericTaskConfigs
                .stream()
                .filter(genericTaskConfig -> genericTaskConfig.getAllProperty() != null
                        && genericTaskConfig.getAllProperty().length > 0)
                .map(genericTaskConfig -> setScheduledTask(genericTaskConfig,magazzini))
                .collect(Collectors.toList());
        uploadTaskDTOS.addAll(scheduledTasks);
        return  uploadTaskDTOS;
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
            if(StringUtils.isBlank(error)){
                fai.broker.db.SqlQueries.seUploadTaskExecutionStatus(taskOID, ExecutionStatus.EXECUTION_SUCCESS.getAcronym(),
                        ExecutionStatus.EXECUTION_SUCCESS.getDescr(), conn);
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
        uploadTaskDTO.setExecutionStatusDescription(uploadTaskConfig.getStatusTechDescr());
        if(StringUtils.isNotBlank(uploadTaskConfig.getStatusDescr())){
            if(uploadTaskConfig.getStatusDescr().equalsIgnoreCase(ExecutionStatus.EXECUTION_FAILED.getAcronym())){
                uploadTaskDTO.setExecutionStatus(ProcessingStatus.ANNULATO.toString());
            }else if(uploadTaskConfig.getStatusDescr().equalsIgnoreCase(ExecutionStatus.EXECUTION_SUCCESS.getAcronym())){
                uploadTaskDTO.setExecutionStatus(ProcessingStatus.COMPLETATO.toString());
            }else {
                uploadTaskDTO.setExecutionStatus(ProcessingStatus.INCORSO.toString());
            }
        }else {
            uploadTaskDTO.setExecutionStatus(ProcessingStatus.ESEGUIRE.toString());
        }
//        if(uploadTaskConfig.getStatus() != null && uploadTaskConfig.getStatus().getStatus() != null){
//            ItemStatus itemStatus = uploadTaskConfig.getStatus().getStatus();
//           if(StringUtils.isNotBlank(itemStatus.getDescr())){
//               if(itemStatus.getDescr().equalsIgnoreCase("TO PROCESS")){
//                   uploadTaskDTO.setExecutionStatus(ProcessingStatus.ESEGUIRE.toString());
//               }else if(itemStatus.getDescr().equalsIgnoreCase("PROCESSING")){
//                   uploadTaskDTO.setExecutionStatus(ProcessingStatus.INCORSO.toString());
//               }else if(itemStatus.getDescr().equalsIgnoreCase("PROCESSED")){
//                   uploadTaskDTO.setExecutionStatus(ProcessingStatus.COMPLETATO.toString());
//               }else if(itemStatus.getDescr().equalsIgnoreCase("ERROR")){
//                   uploadTaskDTO.setExecutionStatus(ProcessingStatus.ANNULATO.toString());
//               }
//           }
//        }
        uploadTaskDTO.setOrderStatus(uploadTaskConfig.getOrderStatus());
        uploadTaskDTO.setFulFilledOrderCount(1);
        uploadTaskDTO.setProcessedOrderCount(0);
        uploadTaskDTO.setMissingOrderCount(0);
        uploadTaskDTO.setMagazzinoAcronym(uploadTaskConfig.getMagazzinoAcronym());
        uploadTaskDTO.setButtonDisabled(Boolean.TRUE);
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

    private UploadTaskDTO setScheduledTask(GenericTaskConfig genericTaskConfig, List<Magazzino> magazzini) {
        UploadTaskDTO uploadTaskDTO = new UploadTaskDTO();
        uploadTaskDTO.setOid(genericTaskConfig.getOid());
        uploadTaskDTO.setIsScheduled(Boolean.TRUE);
        if(genericTaskConfig != null && StringUtils.isNotBlank(genericTaskConfig.getScheduledTimes())){
            List<String> items = Arrays.asList(genericTaskConfig.getScheduledTimes().split(","));
            String schedule = getNextScheduleTime(items);
            uploadTaskDTO.setCreationDate(DateUtil.fromString(schedule, DateUtil.getDatePatternWithMinutes()));
        }

        uploadTaskDTO.setCsvFileName(genericTaskConfig
                .getRichProperties().getProperty("csvInFileName"));
        uploadTaskDTO.setExecutionStatusDescription(null);
        uploadTaskDTO.setExecutionStatus(ProcessingStatus.ESEGUIRE.toString());
        uploadTaskDTO.setMagazzinoAcronym(genericTaskConfig
                .getRichProperties().getProperty("magazzino_acronym"));
        uploadTaskDTO.setOrderStatus(ItemStatus.VALUE_TO_PROCESS.toString());
        uploadTaskDTO.setFulFilledOrderCount(1);
        uploadTaskDTO.setProcessedOrderCount(0);
        uploadTaskDTO.setMissingOrderCount(0);
        uploadTaskDTO.setButtonDisabled(Boolean.FALSE);
        if(magazzini != null && !magazzini.isEmpty() &&
                StringUtils.isNotBlank(uploadTaskDTO.getMagazzinoAcronym())){
            Optional<Magazzino> warehouse = magazzini
                    .stream()
                    .filter(m -> m.getAcronym().equalsIgnoreCase(genericTaskConfig
                            .getRichProperties().getProperty("magazzino_acronym")))
                    .findFirst();
            if(warehouse.isPresent())
                uploadTaskDTO.setMagazzinoAcronym(warehouse.get().getDescr());
        }
        return uploadTaskDTO;
    }

    private  String getNextScheduleTime(List<String> items){
        String nextScheduleTime = null;
        Optional<Date> schedule = items.stream()
                .filter(item -> StringUtils.isNotBlank(item))
                .map(item -> DateUtil.getCurrentDay(DateUtil.fromString(item,
                        DateUtil.getTimePattern())))
                .filter(date -> date.equals(DateUtil.getNow()) || date.after(DateUtil.getNow()))
                .findFirst();

        if(schedule.isPresent()){
            nextScheduleTime = DateUtil.toFormattedString(schedule.get(), DateUtil.getDatePatternWithMinutes(), null);
        }else {
            if(items.size() > 0)
                nextScheduleTime = DateUtil.toFormattedString(DateUtil.getNextDay(DateUtil.fromString(items.get(0),
                    DateUtil.getTimePattern())), DateUtil.getDatePatternWithMinutes(), null);

        }
        return nextScheduleTime;
    }
}
