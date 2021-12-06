package fai.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimeFormat {

  
  public static String format(Calendar c, String format, String tz) {
    return format(c, format, tz, null);
  }
  
  public static String format(Calendar c, String format, String tz, Locale locale) {
    if (c==null) return null;
    SimpleDateFormat sdf = locale == null ?  new SimpleDateFormat(format) : new SimpleDateFormat(format, locale);
    if (tz!=null)
      sdf.setTimeZone(TimeZone.getTimeZone(tz));
    return sdf.format(c.getTime());
  }
  
  
  

}
