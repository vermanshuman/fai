package fai.common.util;

import java.util.Set;

import org.apache.log4j.Logger;

public class RichProperties extends java.util.Properties {
  
  
  static Logger logger = Logger.getLogger(RichProperties.class);
  
  
  public RichProperties() {
    
  }

  public RichProperties(java.util.Properties props) {
    Set keys = props.keySet();
    for (Object key : keys) {
      this.setProperty((String)key, props.getProperty((String)key));
    }
  }
  
  public static RichProperties asRichProperties(java.util.Properties props) {
    return new RichProperties(props);
  }


  public String getProperty(String key) {
    return this.getProperty(key, null, false);
  }

  public String getProperty(String key, String defaultValue) {
    return this.getProperty(key, defaultValue, false);
  }

  public String getProperty(String key, boolean required) {
    return this.getProperty(key, null, required);
  }
  
  public Long getLong(String key) {
    return this.getLong(key, null, false);
  }

  public Long getLong(String key, String defaultValue) {
    return this.getLong(key, defaultValue, false);
  }

  public Long getLong(String key, boolean required) {
    return this.getLong(key, null, required);
  }


  private Long getLong(String key, String defaultValue, boolean required) {
    String s = getProperty(key, defaultValue, required);
    return s == null ? null : new Long(Long.parseLong(s));
  }

  
  public Integer getInteger(String key) {
    return this.getInteger(key, null, false);
  }

  public Integer getInteger(String key, String defaultValue) {
    return this.getInteger(key, defaultValue, false);
  }

  public Integer getInteger(String key, boolean required) {
    return this.getInteger(key, null, required);
  }


  private Integer getInteger(String key, String defaultValue, boolean required) {
    String s = getProperty(key, defaultValue, required);
    return s == null ? null : new Integer(Integer.parseInt(s));
  }

  public Double getDouble(String key) {
    return this.getDouble(key, null, false);
  }

  public Double getDouble(String key, String defaultValue) {
    return this.getDouble(key, defaultValue, false);
  }

  public Double getDouble(String key, boolean required) {
    return this.getDouble(key, null, required);
  }


  private Double getDouble(String key, String defaultValue, boolean required) {
    String s = getProperty(key, defaultValue, required);
    return s == null ? null : new Double(Double.parseDouble(s));
  }


  public Boolean getBoolean(String key) {
    return this.getBoolean(key, null, false);
  }

  public Boolean getBoolean(String key, String defaultValue) {
    return this.getBoolean(key, defaultValue, false);
  }

  public Boolean getBoolean(String key, boolean required) {
    return this.getBoolean(key, null, required);
  }


  private Boolean getBoolean(String key, String defaultValue, boolean required) {
    String s = getProperty(key, defaultValue, required);
    return s == null ? null : new Boolean(Boolean.parseBoolean(s));
  }

  

  private String getProperty(String key, String defaultValue, boolean required) {
    String value = super.getProperty(key);
    if (value != null || (value == null && defaultValue == null)) { 
      logger.info("          "+key +" = "+ ("password".equalsIgnoreCase(key) || "pass".equalsIgnoreCase(key) ? "****" : value));
    }
    else if (value == null && defaultValue != null) {
      value = defaultValue;
      logger.info("(default) "+key +" = "+("password".equalsIgnoreCase(key) || "pass".equalsIgnoreCase(key) ? "****" : value));
    }
    else {
      throw new IllegalStateException("condizione non gestita, "+key+", "+defaultValue+", "+required+" (bug)");
    }
    if (value == null && required) {
      throw new IllegalStateException("nessun valore per la chiave obbligatoria "+key);
    }
    return value;
  }
  
  

}
