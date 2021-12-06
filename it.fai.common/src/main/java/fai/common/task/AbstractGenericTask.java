package fai.common.task;

import fai.common.db.SqlQueries;
import fai.common.models.GenericTaskConfig;
import fai.common.util.ExceptionsTool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Calendar;

public abstract class AbstractGenericTask implements GenericTask {

    static Logger logger = Logger.getLogger(AbstractGenericTask.class);


    protected String acronym;
    protected Calendar nowReference;
    protected Connection conn;
    protected GenericTaskConfig taskConfig;
    protected RichPropertiesDB params = null;

    @Override
    public void setup(String acronym, Calendar nowReference, Connection conn) throws Exception {
        this.acronym = acronym;
        this.nowReference = nowReference;
        this.conn = conn;
        taskConfig = SqlQueries.getGenericTaskConfig(acronym, conn);
        params = RichPropertiesDB.newRichPropertiesDB(taskConfig.getRichProperties(), acronym, conn);
        completeSetup();
    }

    protected abstract void completeSetup() throws Exception;

    @Override
    public String doJob() throws Exception {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = acronym + ", " + METH_NAME + ": ";
        logger.info(fai.common.db.SqlQueries.logInfo(LOG_PREFIX + "...", null, null, conn));
        conn.commit();
        if (isThisWeekdayExecutionAllowed() == false) {
            logger.info(fai.common.db.SqlQueries.logInfo(LOG_PREFIX + "nessuna operazione da svolgere in base al pattern settimanale", null, null, conn));
            conn.commit();
            return null;
        }
        //
        String error = null;
        SqlQueries.setGenericTaskNewSession(taskConfig.getOid(), conn);
        try {
            error = doJobExecute();
            if (error == null) {
                SqlQueries.setGenericTaskSessionCompleted(taskConfig.getOid(), true, null, conn);
                logger.info(fai.common.db.SqlQueries.logInfo(LOG_PREFIX + "task completato senza errori", null, null, conn));
            } else {
                conn.rollback(); // eventuale rollaback per qualsisi cosa non sia stato commitatto
                logger.error(fai.common.db.SqlQueries.logError(LOG_PREFIX + "il task non sar� portato a termine per il seguente motivo: " + error, null, null, conn));
                SqlQueries.setGenericTaskSessionCompleted(taskConfig.getOid(), false, error, conn);
            }
            conn.commit();
        } catch (Throwable th) {
            th.printStackTrace();
            error = LOG_PREFIX + ExceptionsTool.getExceptionDescription("task interrotto causa eccezione intattesa", th);
            logger.error(fai.common.db.SqlQueries.logError(error, null, th, conn), th);
            SqlQueries.setGenericTaskSessionCompleted(taskConfig.getOid(), false, error, conn);
            conn.commit();
        }
        return error;
    }

    protected abstract String doJobExecute() throws Exception;


    protected boolean isThisWeekdayExecutionAllowed() {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = METH_NAME + ": ";
        logger.info(LOG_PREFIX + "...");
        String smtwtfs = taskConfig.getScheduledSmtwtfs();
        if (smtwtfs == null || "".equals(smtwtfs.trim())) {
            logger.info(LOG_PREFIX + "nessun filtro pattern settimanale e orario impostato per l'esecuzione del task: il task sar� eseguito");
            return true;
        }
        //
        int[] weekday = new int[]{Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY};
        int currDayOfWeek = nowReference.get(Calendar.DAY_OF_WEEK);
        for (int i = 0; i < weekday.length; i++) {
            if (currDayOfWeek == weekday[i]) {
                String type = smtwtfs.substring(i, i + 1);
                boolean mustBeExecuted = "X".equals(type);
                logger.info(LOG_PREFIX + "flag di esecuzione estratto dal pattern settimanale " + smtwtfs + ": " + type + "; il task " + (mustBeExecuted == false ? "non sar�" : "sar�") + " eseguito");
                return mustBeExecuted;
            }
        }
        throw new IllegalStateException(LOG_PREFIX + "configurazione pattern settimanale errata: " + taskConfig.getScheduledSmtwtfs());
    }


}
