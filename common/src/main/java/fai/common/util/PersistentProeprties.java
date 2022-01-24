package fai.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PersistentProeprties {
  
  private String propertiesFile = null;
  private Properties props = new Properties();
  
  
  
  public PersistentProeprties(String targetFileName) {
    super();
    this.propertiesFile = targetFileName;
  }

  public PersistentProeprties setProperty(String key, String value) {
    props.setProperty(key, value);
    return this;
  }
  
  public String getProperty(String key) {
    return props.getProperty(key);
  }

  public String getProperty(String key, String defaultValue) {
    return props.getProperty(key, defaultValue);
  }
  

  public PersistentProeprties load() throws Exception {
    if ((new File(propertiesFile)).exists() == false) return this;
    //
    FileInputStream fis = new FileInputStream(propertiesFile);
    props = new Properties();
    props.load(fis);
    fis.close();
    return this;
  }
  
  public PersistentProeprties rollback() throws Exception {
    return load();
  }
  
  public PersistentProeprties commit() throws Exception {
    FileOutputStream os = new FileOutputStream(propertiesFile);
    props.store(os, "");
    os.flush();
    os.close();
    return this;
  }
  

}
