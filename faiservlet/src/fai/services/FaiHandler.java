package fai.services;

import it.swdes.database.SqlStatementManager;
import it.swdes.models.AbstractModel;
import it.swdes.models.AbstractModelResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import fai.common.db.SqlUtilities;
import fai.models.FaiFarmaco;
import fai.services.util.Utility;

public class FaiHandler {

  static Logger logger = Logger.getLogger(FaiHandler.class);

  // **********************************************************************************
  // SERVIZIO SEARCH
  // **********************************************************************************
  public static AbstractModelResult search(FaiFarmaco faiFarmaco,  String tableName, Connection conn) {

    AbstractModelResult result = new AbstractModelResult();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      Properties parameters = new Properties();
      String wherecondition = faiFarmaco.getCodMinsan() != null && !faiFarmaco.getCodMinsan().trim().equals("") ? (" AND COD_MINSAN  like " + SqlUtilities.getAsStringFieldValue(faiFarmaco.getCodMinsan()) + "") : " ";
      wherecondition += faiFarmaco.getCodEan() != null && !faiFarmaco.getCodEan().trim().equals("") ? (" AND COD_EAN like " + SqlUtilities.getAsStringFieldValue(faiFarmaco.getCodEan()) + "") : " ";
      wherecondition += faiFarmaco.getDescrProd() != null && !faiFarmaco.getDescrProd().trim().equals("") ? (" AND DESCR_PROD like " + SqlUtilities.getAsStringFieldValue(faiFarmaco.getDescrProd()) + "") : " ";
      wherecondition += faiFarmaco.getOid() != null ? (" AND OID =  " + faiFarmaco.getOid() + "") : " ";
      
      parameters.setProperty("wherecondition", wherecondition);
      
      parameters.setProperty("tableName", tableName.equals("") ? "V_FAI_FARMACO" : tableName );


      String sql = SqlStatementManager.getSqlStatement("/fai/services/sql/search/searchFaiFarmaco.sql", parameters);
      logger.debug("Query searchFaiFarmaco:\n" + sql);
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      ArrayList listFaiFarmaco = new ArrayList();

      while (rs.next()) {
        FaiFarmaco currFaiFarmaco = new FaiFarmaco();
        if (rs.getString("OID") != null)
          currFaiFarmaco.setOid(new Long(rs.getString("OID")));
        currFaiFarmaco.setIsDeleted(rs.getString("IS_DELETED") != null && rs.getString("IS_DELETED").equals("S") ? Boolean.TRUE : Boolean.FALSE);
        currFaiFarmaco.setCodMinsan(rs.getString("COD_MINSAN"));
        currFaiFarmaco.setCodEan(rs.getString("COD_EAN"));
        currFaiFarmaco.setDescrProd(rs.getString("DESCR_PROD"));
        currFaiFarmaco.setDescrTipoProd(rs.getString("DESCR_TIPO_PROD"));
        currFaiFarmaco.setDescrEstesoTipoProd(rs.getString("DESCR_EStesa_TIPO_PROD"));

        listFaiFarmaco.add(currFaiFarmaco);
      }

      result.setElements((AbstractModel[])listFaiFarmaco.toArray(new AbstractModel[listFaiFarmaco.size()]));
      result.setCode(AbstractModelResult.CODE_OK);
      result.setSeverity(AbstractModelResult.SEVERITY_OK);

    }
    catch (Exception ex) {
      Utility.setExceptionResult(result, ex, "FAI_FAMRACO_SEARCH_EXCEPTION");
    }
    finally {
      try {
        rs.close();
      }
      catch (Exception ex) {
      }
      try {
        pstmt.close();
      }
      catch (Exception ex) {
      }
    }
    return result;
  }

  // **********************************************************************************
  // SERVIZIO UPDATE
  // **********************************************************************************
  public static AbstractModelResult update(FaiFarmaco oldFaiFarmaco, FaiFarmaco newFaiFarmaco, Connection conn) {

    AbstractModelResult result = new AbstractModelResult();

    try {
      if (updateFaiFarmaco(oldFaiFarmaco, newFaiFarmaco, conn) == 1) {
        conn.commit();
        result.setElements(new AbstractModel[] {newFaiFarmaco});
        result.setCode(AbstractModelResult.CODE_OK);
        result.setSeverity(AbstractModelResult.SEVERITY_OK);
      }
      else {
        conn.rollback();
        result.setCode("NESSUN_UPD_FAI_FARMACO_EFFETTUATO");
        result.setSeverity(AbstractModelResult.SEVERITY_ERROR);
      }
    }
    catch (Exception ex) {
      try {
        conn.rollback();
      }
      catch (Exception e) {
      }
      logger.error(ex);
      result.setCode("UPD_FAI_FARMACO_EXCEPTION");
      result.setSeverity(AbstractModelResult.SEVERITY_ERROR);
      result.setDescr(ex.getMessage());
    }

    return result;
  }

  public static int updateFaiFarmaco(FaiFarmaco oldFaiFarmaco, FaiFarmaco newFaiFarmaco, Connection conn) {
    logger.debug("updateFaiFarmaco");
    Statement st = null;
    int nrow = 0;

    try {

      Properties parameters = new Properties();

      // old
      parameters.setProperty("oldOid", oldFaiFarmaco.getOid() != null  ? " AND OID = " +  oldFaiFarmaco.getOid().toString()  : "");
      parameters.setProperty("oldIsDeleted", oldFaiFarmaco.getIsDeleted().equals(Boolean.TRUE) ? "AND is_DELETED = 'S'" :  " AND IS_DELETED = 'N'");
      parameters.setProperty("oldCodMinsan", oldFaiFarmaco.getCodMinsan() != null && !oldFaiFarmaco.getCodMinsan().equals("") ? " AND COD_MINSAN =  " + SqlUtilities.getAsStringFieldValue(oldFaiFarmaco.getCodMinsan())+"" : "");
      parameters.setProperty("oldCodEan", oldFaiFarmaco.getCodEan() != null && !oldFaiFarmaco.getCodEan().equals("") ? "AND COD_EAN = " + SqlUtilities.getAsStringFieldValue(oldFaiFarmaco.getCodEan())+"" : "");
      parameters.setProperty("oldDescrProd", oldFaiFarmaco.getDescrProd() != null && !oldFaiFarmaco.getDescrProd().equals("") ? "AND DESCR_PROD = " + SqlUtilities.getAsStringFieldValue(oldFaiFarmaco.getDescrProd())+"" : "");
      parameters.setProperty("oldAnagrafica", oldFaiFarmaco.getAnagrafica() != null && !oldFaiFarmaco.getAnagrafica().equals("") ? "AND ANAGRAFICA = " + SqlUtilities.getAsStringFieldValue(oldFaiFarmaco.getAnagrafica())+"" : "");
      parameters.setProperty("oldDescrTipoProd", oldFaiFarmaco.getDescrTipoProd() != null && !oldFaiFarmaco.getDescrTipoProd().equals("") ? "AND DESCR_TIPO_PROD = " + SqlUtilities.getAsStringFieldValue(oldFaiFarmaco.getDescrTipoProd())+"" : "");
      parameters.setProperty("oldDescrEstesaTipoProd", oldFaiFarmaco.getDescrEstesaTipoProd() != null && !oldFaiFarmaco.getDescrEstesaTipoProd().equals("") ? "AND DESCR_ESTESA_TIPO_PROD = " + SqlUtilities.getAsStringFieldValue(oldFaiFarmaco.getDescrEstesaTipoProd())+"" : "");
            
      // new
      parameters.setProperty("newIsDeleted", newFaiFarmaco.getIsDeleted().equals(Boolean.TRUE) ? ", is_DELETED = 'S'" :  "  , IS_DELETED = 'N'");
      parameters.setProperty("newCodMinsan", newFaiFarmaco.getCodMinsan() != null && !newFaiFarmaco.getCodMinsan().equals("") ? " , COD_MINSAN =  " + SqlUtilities.getAsStringFieldValue(newFaiFarmaco.getCodMinsan())+"" : "");
      parameters.setProperty("newCodEan", newFaiFarmaco.getCodEan() != null && !newFaiFarmaco.getCodEan().equals("") ? ", COD_EAN = '" + SqlUtilities.getAsStringFieldValue(newFaiFarmaco.getCodEan())+"" : "");
      parameters.setProperty("newDescrProd", newFaiFarmaco.getDescrProd() != null && !newFaiFarmaco.getDescrProd().equals("") ? ", DESCR_PROD = " + SqlUtilities.getAsStringFieldValue(newFaiFarmaco.getDescrProd())+"" : "");
      parameters.setProperty("newAnagrafica", newFaiFarmaco.getAnagrafica() != null && !newFaiFarmaco.getAnagrafica().equals("") ? ", ANAGRAFICA = " + SqlUtilities.getAsStringFieldValue(newFaiFarmaco.getAnagrafica())+"" : "");
      parameters.setProperty("newDescrTipoProd", newFaiFarmaco.getDescrTipoProd() != null && !newFaiFarmaco.getDescrTipoProd().equals("") ? ", DESCR_TIPO_PROD = " + SqlUtilities.getAsStringFieldValue(newFaiFarmaco.getDescrTipoProd())+"" : "");
      parameters.setProperty("newDescrEstesaTipoProd", newFaiFarmaco.getDescrEstesaTipoProd() != null && !newFaiFarmaco.getDescrEstesaTipoProd().equals("") ? ", DESCR_ESTESA_TIPO_PROD = " + SqlUtilities.getAsStringFieldValue(newFaiFarmaco.getDescrEstesaTipoProd())+"" : "");

      st = conn.createStatement();

      String theQuery = SqlStatementManager.getSqlStatement("/fai/services/sql/update/updateFaiFarmaco.sql", parameters);
      logger.trace("Query update: " + theQuery);

      nrow = st.executeUpdate(theQuery);
      logger.debug("Righe updatate: " + nrow);
    }
    catch (Exception ex) {
      try {
        conn.rollback();
      }
      catch (Exception e) {
      }
      logger.error(ex);
    }
    finally {
      try {
        st.close();
      }
      catch (Exception ex) {
      }
    }
    return nrow;
  }
  
  // **********************************************************************************
  // SERVIZIO INSERT
  // **********************************************************************************
  public static AbstractModelResult insertFaiFarmaco(FaiFarmaco faiFarmacoModel, Connection conn) {
    logger.debug("Eseguo insertFaiFarmaco");
    AbstractModelResult result = new AbstractModelResult();

    PreparedStatement pstmt = null;
    Properties parameters = new Properties();

    try {
      Long oid = Utility.getOidSequence(conn);
      faiFarmacoModel.setOid(oid);

      parameters.setProperty("oid", faiFarmacoModel.getOid().toString());
      parameters.setProperty("codMinsan", faiFarmacoModel.getCodMinsan());
      parameters.setProperty("codEan", faiFarmacoModel.getCodEan());
      parameters.setProperty("descrProd", faiFarmacoModel.getDescrProd());
      parameters.setProperty("descrTipoProd", faiFarmacoModel.getDescrTipoProd());
      parameters.setProperty("descrEstesaTipoProd", faiFarmacoModel.getDescrEstesaTipoProd());

      parameters.setProperty("anagrafica", faiFarmacoModel.getAnagrafica());
      parameters.setProperty("isDeleted", faiFarmacoModel.getIsDeleted().equals(Boolean.FALSE) ? "N" : "S");

      String sql = SqlStatementManager.getSqlStatement("/fai/services/sql/insert/insertFaiFarmaco.sql", parameters);

      logger.debug("   sql: " + sql);
      pstmt = conn.prepareStatement(sql);

      if (pstmt.executeUpdate() > 0) {
        result.setElements(new AbstractModel[] {faiFarmacoModel});
        result.setCode(AbstractModelResult.CODE_OK);
        result.setSeverity(AbstractModelResult.SEVERITY_OK);
      }
      else {
        result.setElements(new AbstractModel[] {faiFarmacoModel});
        result.setCode("INSERT_FAI_FARMACO_NON_APPLICABILE");
        result.setSeverity(AbstractModelResult.SEVERITY_OK);
      }

    }
    catch (Throwable ex) {
      logger.error("insertFaiFarmaco failure", ex);
      ex.printStackTrace();
      result.setCode("INSERT_FAI_FARMACO_EXCEPTION");
      result.setSeverity(AbstractModelResult.SEVERITY_ERROR);
      result.setDescr(ex.getMessage());
    }
    finally {
      try {
        pstmt.close();
      }
      catch (Exception ex1) {
      }
    }
    return result;
  }

}
