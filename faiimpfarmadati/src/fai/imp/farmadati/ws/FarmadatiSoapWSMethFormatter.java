package fai.imp.farmadati.ws;

import fai.imp.farmadati.ws.FarmadatiSoapWS.GetDataSetModalita;

/**
 * restituisce una descrizione della call
 * 
 * @author Gianni Radice
 *
 */
public class FarmadatiSoapWSMethFormatter {

  public static String formatGetEnabledDataSet() throws Exception {
    return formatGetEnabledDataSet(false);
  }
  
  public static String formatGetEnabledDataSet(boolean onlyParams) throws Exception {
    return (onlyParams == true ? "" : "GetEnabledDataSet")+"()";
  }

  public static String formatGetSchemaDataSet(String codiceSetDati, boolean tracciatoVar) throws Exception {
    return formatGetSchemaDataSet(codiceSetDati, tracciatoVar, false);
  }
  
  public static String formatGetSchemaDataSet(String codiceSetDati, boolean tracciatoVar, boolean onlyParams) throws Exception {
    return (onlyParams == true ? "" : "GetSchemaDataSet")+"(CodiceSetDati="+codiceSetDati+",SchemaVar="+tracciatoVar+")";
  }

  public static String formatGetDataSet(String codiceSetDati, GetDataSetModalita modalita, int pageN) throws Exception {
    return formatGetDataSet(codiceSetDati, modalita, pageN, false);
  }

  public static String formatGetDataSet(String codiceSetDati, GetDataSetModalita modalita, int pageN, boolean onlyParams) throws Exception {
    return (onlyParams == true ? "" : "GetDataSetUnzippedByteListFile")+"(CodiceSetDati="+codiceSetDati+",Modalita="+modalita+",PageN="+pageN+")";
  }

  public static String formatGetDataSetUnzippedByteListFile(String codiceSetDati, int pageN) throws Exception {
    return formatGetDataSetUnzippedByteListFile(codiceSetDati, pageN, false);
  }

  public static String formatGetDataSetUnzippedByteListFile(String codiceSetDati, int pageN, boolean onlyParams) throws Exception {
    return (onlyParams == true ? "" : "GetDataSetUnzippedByteListFile")+"(ByteListFile=(unzipped),Modalita="+GetDataSetModalita.GETRECORDS+",PageN="+pageN+")";
  }



}
