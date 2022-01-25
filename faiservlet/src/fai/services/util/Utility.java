package fai.services.util;

import it.swdes.database.SqlStatementManager;

import it.swdes.database.Support;
import it.swdes.database.UndeclaredThrowableException;
import it.swdes.models.AbstractModelResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class Utility {

  public static Logger logger = Logger.getLogger(Utility.class);

  public static Long getOidSequence(Connection conn) {

    Statement st = null;
    ResultSet rs = null;

    try {
      st = conn.createStatement();
      rs = st.executeQuery(SqlStatementManager.getSqlStatement("/fai/services/sql/nextval.sql"));
      if (rs.next()) {
        return Support.stringToLong(rs.getString("OID"));
      }
      else {
        return Support.stringToLong(null);
      }
    }
    catch (Exception ex) {
      throw new UndeclaredThrowableException(ex);
    }
    finally {
      try {
        rs.close();
      }
      catch (Exception ex) {
      }
      try {
        st.close();
      }
      catch (Exception ex) {
      }
    }
  }

  public static AbstractModelResult setExceptionResult(AbstractModelResult result, Throwable t, String codeError) {
    if (result != null) {
      result.setCode(codeError);
      result.setSeverity(AbstractModelResult.SEVERITY_ERROR);
      if (t instanceof UndeclaredThrowableException) {
        result.setDescr(((UndeclaredThrowableException) t).getUndeclaredThrowable() != null ? ((UndeclaredThrowableException) t).getUndeclaredThrowable().getMessage() : t.getMessage());
      }
      else {
        result.setDescr(t.getMessage() != null ? t.getMessage() : (t.getCause() != null ? t.getCause().getMessage() : ""));
      }

      logger.error(t.getMessage(), t);

    }
    return result;
  }

  public static String calendarToString(Calendar c) {
    String dataStr = null;
    if (c != null) {
      SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      dataStr = data.format(c.getTime());
    }
    return dataStr;
  }

  public static String calendarToString2(Calendar c) {
    String dataStr = null;
    if (c != null) {
      SimpleDateFormat data = new SimpleDateFormat("yyyyMMddHHmmss");
      dataStr = data.format(c.getTime());
    }
    return dataStr;
  }

  public static String calendarToString3(Calendar c) {
    String dataStr = null;
    if (c != null) {
      SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
      dataStr = data.format(c.getTime());
    }
    return dataStr;
  }

  

  public static String getSafeString(final String input) {
    if (input == null) {
      return "null";
    }
    else {
      return input.replace("'", "''");
    }
  }

  public static String getDbString(final Object input) {
    return getDbString(input, true);
  }

  public static String getDbString(final Object input, boolean nullable) {
    if (input == null || !(input instanceof String)) {
      return (nullable) ? "null" : "''";
    }
    StringBuilder sb = new StringBuilder();
    sb.append('\'');
    sb.append(((String) input).replace("'", "''"));
    sb.append('\'');
    return sb.toString();
  }

  public static String ClobToString(Clob cl) throws SQLException, IOException {

    if (cl == null) {
      return "";
    }

    StringBuffer strOut = new StringBuffer();
    String buff;
    BufferedReader br = new BufferedReader(cl.getCharacterStream());

    while ((buff = br.readLine()) != null) {
      strOut.append(buff);
    }
    //
    String s = strOut.toString();
    //
    return s;
  }

  public static String getStackTrace(Throwable t) {
    StringWriter sw = new StringWriter();
    // t.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }

  
  private static String fillDataAiMinuti(String data) {
    // ddmmyyyyhhmm
    if (data.length() >= 14) {
      return data.substring(0, 14);
    }

    for (int i = data.length(); i < 14; i++) {
      data += "0";
    }
    return data;
  }

  public static void apexPropertyHandler(Properties params) {
    String key = null;
    String value = null;

    Set propKeys = params.keySet(); // get all keys
    Iterator itr = propKeys.iterator();
    while (itr.hasNext()) {
      key = (String) itr.next();
      value = params.getProperty(key);
      params.setProperty(key, value.replace("'", "''"));
    }
  }

  public static Double getDoubleFromString(String d) {
    try {
      Double ret = new Double(d);
      return ret;
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static Integer getIntFromString(String i) {
    try {
      Integer ret = new Integer(i);
      return ret;
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static Long getLongFromString(String l) {
    try {
      Long ret = new Long(l);
      return ret;
    }
    catch (Exception ex) {
      return null;
    }
  }

  
  public static Calendar getCalendar(Date date) {
    Calendar cal = Calendar.getInstance(Locale.US);
    cal.setTime(date);
    return cal;
  }

 
  public static double round(double value, int places) {
    if (places < 0)
      throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
  }
  
  // Riempie con degli zeri i caratteri mancanti alla stringa per raggiungere la
  // lunghezza definita dal protocollo (fieldLength)
  public static String getFilledFieldZero(String value, int fieldLength) {
    int start = 0;
    int end = fieldLength - value.length();
    String charToAdd = "";

    for (int i = start; i < end; i++) {
      charToAdd += "0";
    }
    return charToAdd + value;
  }

}
