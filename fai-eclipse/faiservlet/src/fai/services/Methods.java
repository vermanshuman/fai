package fai.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import fai.models.FaiFarmaco;
import fai.services.util.Environment;
import it.swdes.database.SqlStatementManager;
import it.swdes.models.AbstractModel;
import it.swdes.models.AbstractModelRequest;
import it.swdes.models.AbstractModelResult;

public class Methods {

  public static Environment env = new Environment();
  private static long operationTime = 0;
  static Logger logger = Logger.getLogger(Methods.class);

  public static AbstractModelResult insert(AbstractModelRequest request, Connection conn) {

    AbstractModel theModel = request.getElementAt(0);
    if (theModel instanceof FaiFarmaco) {
      return FaiHandler.insertFaiFarmaco((FaiFarmaco)theModel, conn);
    }
    return null;
  }

  public static AbstractModelResult search(AbstractModel[] args, Connection conn) {

    AbstractModel theModel = args[0];
    if (theModel instanceof FaiFarmaco) {
      return FaiHandler.search((FaiFarmaco)theModel, "", conn);
    }
    return null;
  }

  public static AbstractModelResult update(AbstractModelRequest request, Connection conn) {

    AbstractModel oldModel = request.getOldAbstractModel();
    AbstractModel newModel = request.getNewAbstractModel();

    if ((oldModel instanceof FaiFarmaco) && (newModel instanceof FaiFarmaco)) {
      if (((FaiFarmaco)oldModel).getCodMinsan() != null && !((FaiFarmaco)oldModel).getCodMinsan().equals("") && (((FaiFarmaco)newModel).getCodMinsan() != null && !((FaiFarmaco)newModel).getCodMinsan().equals("")) && ((FaiFarmaco)oldModel).getCodMinsan().equals(((FaiFarmaco)newModel).getCodMinsan())
          || ((FaiFarmaco)oldModel).getCodEan() != null && !((FaiFarmaco)oldModel).getCodEan().equals("") && (((FaiFarmaco)newModel).getCodEan() != null && !((FaiFarmaco)newModel).getCodEan().equals("")) && ((FaiFarmaco)oldModel).getCodEan().equals(((FaiFarmaco)newModel).getCodEan())) {
        return FaiHandler.update((FaiFarmaco)oldModel, (FaiFarmaco)newModel, conn);
        // FACCIAMO UPDATE
      }
      else {
        // FACCIAMO INSERT
        return FaiHandler.insertFaiFarmaco((FaiFarmaco)newModel, conn);
      }

    }

    return null;
  }

  // **********************************************************************************
  // SERVIZIO DELETE
  // **********************************************************************************
  public static AbstractModelResult hardDelete(AbstractModelRequest request, Connection conn) {
    logger.debug("Eseguo deleteFaiFarmaco");
    AbstractModelResult result = new AbstractModelResult();

    PreparedStatement pstmt = null;
    Properties parameters = new Properties();

    AbstractModel[] args = request.getElements();
    AbstractModel faiFarmacoModel = args[0];

    if (faiFarmacoModel instanceof FaiFarmaco) {

      if (((FaiFarmaco)faiFarmacoModel).getOid() != null) {

        try {

          parameters.setProperty("oid", ((FaiFarmaco)faiFarmacoModel).getOid().toString());
          String sql = SqlStatementManager.getSqlStatement("/fai/services/sql/delete/deleteFaiFarmaco.sql", parameters);

          logger.debug("   sql: " + sql);
          pstmt = conn.prepareStatement(sql);

          if (pstmt.executeUpdate() > 0) {
            result.setElements(new AbstractModel[] {faiFarmacoModel});
            result.setCode(AbstractModelResult.CODE_OK);
            result.setSeverity(AbstractModelResult.SEVERITY_OK);
          }
          else {
            result.setElements(new AbstractModel[] {faiFarmacoModel});
            result.setCode("DELETE_FAI_FARMACO_NON_APPLICABILE");
            result.setSeverity(AbstractModelResult.SEVERITY_OK);
          }
        }
        catch (Throwable ex) {
          logger.error("deleteFaiFarmaco failure", ex);
          result.setCode("DELETE_FAI_FARMACO_EXCEPTION");
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
      }
    }
    return result;
  }

  // **********************************************************************************
  // SERVIZIO DELETE
  // **********************************************************************************
  public static AbstractModelResult setDelete(AbstractModelRequest request, Connection conn) {
    logger.debug("Eseguo setDelete");
    AbstractModelResult result = new AbstractModelResult();
    AbstractModel[] args = request.getElements();
    AbstractModel faiFarmacoModel = args[0];

    if (faiFarmacoModel instanceof FaiFarmaco) {

      PreparedStatement pstmt = null;
      Properties parameters = new Properties();

      try {

        result = FaiHandler.search((FaiFarmaco)faiFarmacoModel, "FAI_FARMACO", conn);

        if (result.getElementCount() > 0) {

          FaiFarmaco oldFarmaco = (FaiFarmaco)result.getElementAt(0);
          FaiFarmaco newFarmaco = new FaiFarmaco();
          newFarmaco.copyFrom(oldFarmaco);
          newFarmaco.setIsDeleted(((FaiFarmaco)faiFarmacoModel).getIsDeleted());

          return FaiHandler.update((FaiFarmaco)oldFarmaco, (FaiFarmaco)newFarmaco, conn);
        }

        else {
          return FaiHandler.insertFaiFarmaco((FaiFarmaco)faiFarmacoModel, conn);
        }

      }
      catch (Throwable ex) {
        logger.error("setDelete failure", ex);
        result.setCode("DELETE_FAI_FARMACO_EXCEPTION");
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
    }
    return result;
  }

}
