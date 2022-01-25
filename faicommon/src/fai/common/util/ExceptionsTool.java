package fai.common.util;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;


public class ExceptionsTool {
  
  public static Logger logger = Logger.getLogger(ExceptionsTool.class);

  public static String getStackTraceAsString(Throwable th) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    th.printStackTrace(pw);
    return sw.toString();
  }
  
  public static String getExceptionDescription(Throwable th) {
    return getExceptionDescription(null, th);
  }
  
  public static String getExceptionDescription(String info, Throwable th) {
    Throwable currTh = th; 
    while (currTh != null && currTh.getMessage() == null) {
      currTh = currTh.getCause();
    }
    if (currTh == null) currTh = th;
    return (info == null ? "" : info+"; ") + currTh.getClass().getName()+" «"+currTh.getMessage()+"»";
  }
  
  
  public static Integer getHttpErrorCode(AxisFault af) {
    Object [] o = getHttpError(af);
    return o == null ? -1  : (Integer)o[0];
  }
  
  public static String getHttpErrorDescr(AxisFault af) {
    Object [] o = getHttpError(af);
    return o == null ? "?"  : (String)o[1];
  }
  
  private static Object [] getHttpError(AxisFault af) {
    if (af == null) return null;
    Object []  retval = null;
    //
    Integer httpErrorCode = null;
    String httpErrorDescr = null;
    String msg = af.getMessage();
    try {
      Pattern pattern = Pattern.compile("\\(+[0-9]+\\)+[a-zA-Z0-9 ]+");
      Matcher matcher = pattern.matcher(msg);
      if (matcher.find()) {
        logger.debug("tentativo di estrarre codice ed errore HTTP da: "+msg);
        StringTokenizer st = new StringTokenizer(msg, "()");
        httpErrorCode = Integer.parseInt(st.nextToken());
        httpErrorDescr = st.nextToken();
        retval = new Object [] { httpErrorCode, httpErrorDescr };
      }
      else {
        logger.error("impossibile estrarre codice ed errore HTTP da: "+msg);
      }
    }
    catch (Throwable e) {
      logger.error("eccezione "+e.getClass().getName()+" «"+e.getMessage()+"» tentando di estrarre codice ed errore HTTP da: "+msg, e);
    }
    return retval;
    
  }


}
