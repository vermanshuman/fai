package fai.common.models;

import it.swdes.models.*;
import it.swdes.models.Attribute;

public class FtpConfig extends AbstractModel {


  public static int FTP_PROTOCOL;
  public static int FTP_HOST;
  public static int FTP_PORT;
  public static int FTP_DIR;
  public static int FTP_LOGIN;
  public static int FTP_PASSWORD;
  public static int FTP_PASSWORD_ENCR;

  public static final Attribute[] attributes = buildAttributes(FtpConfig.class, new Attribute[] { //@formatter:off
    new Attribute("ftpProtocol", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("ftpHost", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("ftpPort", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("ftpDir", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("ftpLogin", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("ftpPassword", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("ftpPasswordEncr", null, Boolean.class, Boolean.FALSE, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public FtpConfig() {
  }


  /** FTP_PROTOCOL **/
  public boolean setFtpProtocol(String ftpProtocol) {
    return setString(FTP_PROTOCOL, ftpProtocol);
  }

  public String getFtpProtocol() {
    return getString(FTP_PROTOCOL);
  }


  /** FTP_HOST **/
  public boolean setFtpHost(String ftpHost) {
    return setString(FTP_HOST, ftpHost);
  }

  public String getFtpHost() {
    return getString(FTP_HOST);
  }


  /** FTP_PORT **/
  public boolean setFtpPort(Integer ftpPort) {
    return setInt(FTP_PORT, ftpPort);
  }

  public Integer getFtpPort() {
    return getInt(FTP_PORT);
  }


  /** FTP_DIR **/
  public boolean setFtpDir(String ftpDir) {
    return setString(FTP_DIR, ftpDir);
  }

  public String getFtpDir() {
    return getString(FTP_DIR);
  }


  /** FTP_LOGIN **/
  public boolean setFtpLogin(String ftpLogin) {
    return setString(FTP_LOGIN, ftpLogin);
  }

  public String getFtpLogin() {
    return getString(FTP_LOGIN);
  }


  /** FTP_PASSWORD **/
  public boolean setFtpPassword(String ftpPassword) {
    return setString(FTP_PASSWORD, ftpPassword);
  }

  public String getFtpPassword() {
    return getString(FTP_PASSWORD);
  }


  /** FTP_PASSWORD_ENCR **/
  public boolean setFtpPasswordEncr(Boolean ftpPasswordEncr) {
    return setBoolean(FTP_PASSWORD_ENCR, ftpPasswordEncr);
  }

  public Boolean getFtpPasswordEncr() {
    return getBoolean(FTP_PASSWORD_ENCR);
  }

  public boolean isFtpPasswordEncr() {
    return isBoolean(FTP_PASSWORD_ENCR);
  }

 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on



}
