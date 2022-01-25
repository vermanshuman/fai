package fai.imp.farmadati.db;

import org.datacontract.schemas._2004._07.FDIWebServices.GetEnabledDataSet_Output;
import org.datacontract.schemas._2004._07.FDIWebServices.GetSchemaDataSet_Output;
import org.datacontract.schemas._2004._07.FDIWebServices.SetDati;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.datacontract.schemas._2004._07.FDIWebServices.Field;

import java.sql.Connection;


public class FarmadatiDDL {
  
  
  static Logger logger = Logger.getLogger(FarmadatiDDL.class);

  
  private Connection conn = null;
  
  public FarmadatiDDL(Connection conn) {
    super();
    this.conn = conn;
  }
  
  
  private String asDDL(String entityKey, GetSchemaDataSet_Output schemaDataSet) throws Exception {
    StringBuffer sb = new StringBuffer("");
    Field [] f = schemaDataSet.getFields();
    for (int i = 0; i < f.length; i++) {
      sb.append(f[i].getKey());
      sb.append(" ");
      if ("VARCHAR".equals(f[i].getType())) {
        sb.append("VARCHAR2("+f[i].getLength()+")");
      }
      else if ("TEXT".equals(f[i].getType())) {
        sb.append("CLOB");
      }
      else if ("DATE".equals(f[i].getType())) {
        sb.append("DATE");
      }
      else if ("NUMERIC".equals(f[i].getType())) {
        sb.append("NUMBER("+f[i].getLength()+")");
      }
      else {
        throw new IllegalStateException("tipo non gestito: "+f[i].getType());
      }
      if (i < f.length-1) {
        sb.append(", ");
      }
    }
    return "CREATE TABLE "+entityKey+" ("+sb.toString()+")";
  }
  
  public void createTable(SetDati setDati, GetSchemaDataSet_Output schemaDataSet) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + " "+(setDati == null ? "null" : setDati.getKey())+": ";
    logger.info(LOG_PREFIX + "...");

    String entityKey = setDati.getKey();
    String createTable = asDDL(entityKey, schemaDataSet);
    String existingCreateTable = SqlQueries.getCreateTableByTableName(entityKey, conn);
    if (createTable.equals(existingCreateTable)) {
      logger.info(LOG_PREFIX + " la tabella già esiste");
      SqlQueries.updateFarmadatiDdl(entityKey, createTable, conn); // per impostare NO_LONGER_EXISTS_TS a NULL
      conn.commit();
      return;
    }
    //
    if (existingCreateTable != null) {
      logger.info(LOG_PREFIX + " DROP ...");
      SqlQueries.executeUpdate("DROP TABLE "+entityKey+" CASCADE CONSTRAINTS", conn);
    }
    //
    logger.info(LOG_PREFIX + " CREATE ...");
    SqlQueries.executeUpdate(createTable, conn);
    //
    logger.info(LOG_PREFIX + " registrazione dello script di creazione che utilizzato ...");
    if (existingCreateTable == null) {
      SqlQueries.insertFarmadatiDdl(entityKey, createTable, conn);
    }
    else {
      SqlQueries.updateFarmadatiDdl(entityKey, createTable, conn);
    }
    conn.commit();
    //
    logger.info(LOG_PREFIX + " commenti Oracle ...");
    SqlQueries.commentOnTable(setDati.getKey(), setDati.getDescription(), conn);
    Field [] f = schemaDataSet.getFields();
    for (int i = 0; i < f.length; i++) {
      SqlQueries.commentOnColumn(entityKey, f[i].getKey(), f[i].getDescription(), conn);
    }
  }
  

}
