package fai.broker.task;

import fai.broker.models.UploadCSVStatus;
import fai.broker.models.UploadTaskConfig;
import fai.common.db.SqlQueries;
import fai.common.models.GenericTaskConfig;
import fai.common.util.ExceptionsTool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Calendar;

public abstract class UploadGenericTask implements GenericTask {


    static Logger logger = Logger.getLogger(UploadGenericTask.class);


    protected String acronym;
    protected Calendar nowReference;
    protected Connection conn;
    protected GenericTaskConfig taskConfig;
    protected UploadTaskConfig uploadTaskConfig;

    @Override
    public void setup(String acronym, Calendar nowReference, Connection conn) throws Exception {
        String[] tokens = acronym.split("\\@");
        this.acronym = tokens[0];
        this.nowReference = nowReference;
        this.conn = conn;
        taskConfig = SqlQueries.getGenericTaskConfig(acronym, conn);
        uploadTaskConfig = fai.broker.db.SqlQueries.findUploadTask(Long.parseLong(tokens[1]), conn);
        completeSetup();
    }

    protected abstract void completeSetup() throws Exception;

    @Override
    public String doJob() throws Exception {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = acronym + ", " + METH_NAME + ": ";
        logger.info(SqlQueries.logInfo(LOG_PREFIX + "...", null, null, conn));
        conn.commit();
        if (isThisWeekdayExecutionAllowed() == false) {
            logger.info(SqlQueries.logInfo(LOG_PREFIX + "nessuna operazione da svolgere in base al pattern settimanale", null, null, conn));
            conn.commit();
            return null;
        }
        //
        String error;
        fai.broker.db.SqlQueries.setUploadTaskNewSession(uploadTaskConfig.getOid(), conn);
        try {
            error = doJobExecute();
            if (error == null) {
                fai.broker.db.SqlQueries.setUploadTaskSessionCompleted(uploadTaskConfig.getOid(), true,
                        UploadCSVStatus.VALUE_PROCESSED.getOid(), UploadCSVStatus.VALUE_PROCESSED.getDescr(), null, conn);
                logger.info(SqlQueries.logInfo(LOG_PREFIX + "task completato senza errori", null, null, conn));
            } else {
                conn.rollback(); // eventuale rollaback per qualsisi cosa non sia stato commitatto
                logger.error(SqlQueries.logError(LOG_PREFIX + "il task non sarà portato a termine per il seguente motivo: " + error, null, null, conn));
                fai.broker.db.SqlQueries.setUploadTaskSessionCompleted(uploadTaskConfig.getOid(), false,
                        UploadCSVStatus.VALUE_PROCESSED.getOid(), UploadCSVStatus.VALUE_PROCESSED.getDescr(), error, conn);
            }
            conn.commit();
        } catch (Throwable th) {
            th.printStackTrace();
            error = LOG_PREFIX + ExceptionsTool.getExceptionDescription("task interrotto causa eccezione intattesa", th);
            logger.error(SqlQueries.logError(error, null, th, conn), th);
            fai.broker.db.SqlQueries.setUploadTaskSessionCompleted(uploadTaskConfig.getOid(), false,
                    UploadCSVStatus.VALUE_ERROR.getOid(), UploadCSVStatus.VALUE_ERROR.getDescr(), error, conn);
            conn.commit();
        }
        return error;
    }

    protected abstract String doJobExecute() throws Exception;

    protected boolean isThisWeekdayExecutionAllowed() {
        return true;
    }
}
