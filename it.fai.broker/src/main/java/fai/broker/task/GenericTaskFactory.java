package fai.broker.task;

import fai.common.db.SqlQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;

public class GenericTaskFactory {

	public static GenericTask newGenericTask(String acronym, Calendar nowReference, Connection conn) throws Exception {
		String className = SqlQueries.getGenericTaskConfigClassName(acronym, conn);
		if (className == null || "".equals(className))
			throw new IllegalArgumentException(
					"inammissibile, nessun " + GenericTask.class.getName() + " per l'ACRONYM " + acronym);
		//
		GenericTask genericTask = (GenericTask) Class.forName(className).newInstance();
		genericTask.setup(acronym, nowReference, conn);
		return genericTask;
	}

	public static class Test {

		public static void main(String args[]) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "FAI", "india123");
				con.setAutoCommit(false);

				// step 1
				 GenericTaskFactory.newGenericTask("IMP_ORDINE_IN", Calendar.getInstance(),
				con).doJob();

				// step 2
				 GenericTaskFactory.newGenericTask("FABBISOGNO_CALCULATOR",
				 Calendar.getInstance(), con).doJob();

				// setp 3
				 GenericTask gt = GenericTaskFactory.newGenericTask("APPROVVIGIONAMENTO_MGR",
				 Calendar.getInstance(), con);
				 gt.doJob();
				con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
