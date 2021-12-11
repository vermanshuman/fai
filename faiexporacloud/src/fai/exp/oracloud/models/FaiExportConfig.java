package fai.exp.oracloud.models;

import it.swdes.models.*;
import fai.common.models.*;

public class FaiExportConfig extends FtpConfig {


  public static int EXPORT_TIME;

  public static final Attribute[] attributes = buildAttributes(FaiExportConfig.class, new Attribute[] { //@formatter:off
    new Attribute("exportTime", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public FaiExportConfig() {
  }


  /** EXPORT_TIME **/
  public boolean setExportTime(java.util.Calendar exportTime) {
    return setCalendar(EXPORT_TIME, exportTime);
  }

  public java.util.Calendar getExportTime() {
    return getCalendar(EXPORT_TIME);
  }

 //@formatter:on



}
