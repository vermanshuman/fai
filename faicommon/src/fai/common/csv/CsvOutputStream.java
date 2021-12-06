package fai.common.csv;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class CsvOutputStream {
  
  private List<String> headers = null;
  private Hashtable<String, String> defaults = new Hashtable<String, String>();
  private String fieldSeparator = null;
  private String stringFormat = null;
  private boolean includeHeader = false;
  private boolean headerWritten = false;
  private NumberFormat intNumberFormat = null;
  private NumberFormat doubleNumberFormat = null;
  private SimpleDateFormat timestampFormat = null;
  private Hashtable<String, String> currRecord = null;
  private Set<String> mandatories = new HashSet<String>();
  private Hashtable<String, Object> formattersByHeader = new Hashtable<String, Object>();
  private BufferedWriter writer = null;
  private String crlf = null;
  private char charToEscape = '\0';
  private char escapeChar = '\0';
  private boolean stringFormatOnChartToEscapeOnly = false;
  

  
  
  public boolean isIncludeHeader() {
    return includeHeader;
  }
  public void setIncludeHeader(boolean includeHeader) {
    this.includeHeader = includeHeader;
  }
  public String getFieldSeparator() {
    return fieldSeparator;
  }
  public void setFieldSeparator(String fieldSeparator) {
    this.fieldSeparator = fieldSeparator;
  }

  public List<String> getHeaders() {
    return headers;
  }
  
  public String getCrlf() {
    return crlf;
  }
  public void setCrlf(String crlf) {
    this.crlf = crlf;
  }
  public void setOutputStream(OutputStream os) throws Exception {
    writer = new BufferedWriter(new OutputStreamWriter(os));
  }
  
  public void setHeaders(List<String> headers) {
    this.headers = headers;
  }  

  public void setHeadersWithDefaults(List<String> list) {
    setHeadersWithDefaults(list, fieldSeparator, null);
  }

  public void setHeadersWithDefaults(List<String> list, String tempFieldSeparator, String mandatoryIfStartsWith) {
    headers = new ArrayList<String>();
    for (String keyVal: list) {
      boolean mandatory = false;
      keyVal = keyVal.trim();
      if (mandatoryIfStartsWith != null) {
        if (keyVal.startsWith(mandatoryIfStartsWith)) {
          keyVal = keyVal.substring(mandatoryIfStartsWith.length());
          keyVal = keyVal.trim();
          mandatory = true;
        }
      }
      // System.out.println("****** "+keyVal+" // "+tempFieldSeparator);
      int sepIdx = keyVal.indexOf(tempFieldSeparator);
      String key = keyVal.substring(0, sepIdx).trim();
      String value = keyVal.substring(sepIdx+1, keyVal.length()).trim();
      headers.add(key);
      defaults.put(key, value);
      if (mandatory) {
        setMandatory(key);
      }
    }
  }
  
  public void addHeader(String header, String defaultValue, Object formatter, boolean mandatory) {
    if (headers == null) headers = new ArrayList<String>();
    //
    headers.add(header);
    if (defaultValue != null) {
      defaults.put(header, defaultValue);
    }
    if (formatter != null) {
      formattersByHeader.put(header, formatter);
    }
    if (mandatory) {
      setMandatory(header);
    }
    
  }
  
  public void setDefault(String header, String value) {
    if (value == null)
      defaults.remove(header);
    else
      defaults.put(header, value);
  }
  
  public String getStringFormat() {
    return stringFormat;
  }
  
  public char getCharToEscape() {
    return charToEscape;
  }
  public char getEscapeChar() {
    return escapeChar;
  }
  public void setStringFormat(String stringFormat, char charToEscape, char escapeChar) {
    this.stringFormat = stringFormat;
    this.charToEscape = charToEscape;
    this.escapeChar = escapeChar;
  }
  
  
  public boolean isStringFormatOnChartToEscapeOnly() {
    return stringFormatOnChartToEscapeOnly;
  }
  public void setStringFormatOnChartToEscapeOnly(boolean stringFormatOnChartToEscapeOnly) {
    this.stringFormatOnChartToEscapeOnly = stringFormatOnChartToEscapeOnly;
  }
  public NumberFormat getIntNumberFormat() {
    return intNumberFormat;
  }
  public void setIntNumberFormat(NumberFormat intNumberFormat) {
    this.intNumberFormat = intNumberFormat;
  }
  public NumberFormat getDoubleNumberFormat() {
    return doubleNumberFormat;
  }
  public void setDoubleNumberFormat(NumberFormat doubleNumberFormat) {
    this.doubleNumberFormat = doubleNumberFormat;
  }
  
  public SimpleDateFormat getTimestampFormat() {
    return timestampFormat;
  }
  public void setTimestampFormat(SimpleDateFormat timestampFormat) {
    this.timestampFormat = timestampFormat;
  }
  public void setTimestampFormat(String format) {
    this.timestampFormat = new SimpleDateFormat(format);
  }
  
  
  public void setMandatory(String header) {
    setMandatory(header, true);
  }

  public void setMandatory(String header, boolean value) {
    if (value)
      mandatories.add(header);
    else 
      mandatories.remove(header);
  }
  
  public void setCurrentRecord(String header, Object value) {
    createCurrRecordIfNull();
    //
    if (value == null) {
      currRecord.put(header, "");
      return;
    }
    //
    String valueAsString = "";
    if (value instanceof String) {
      String s = (String)value;
      valueAsString = F(header, s);
    }
    else if (value instanceof Integer) {
      valueAsString = F(header, (Integer)value);
    }
    else if (value instanceof Long) {
      valueAsString = F(header, (Long)value);
    }
    else if (value instanceof Double) {
      valueAsString = F(header, (Double)value);
    }
    else if (value instanceof Float) {
      valueAsString = F(header, (Float)value);
    }
    else if (value instanceof Calendar) {
      valueAsString = F(header, (Calendar)value);
    }
    else {
      throw new IllegalArgumentException("tipo non gestito "+value.getClass().getName()+", campo "+header); 
      
    }
    currRecord.put(header, valueAsString);
  }
  
  private String F(String header, String value) {
    if (value == null) return null;
    //
    if (stringFormatOnChartToEscapeOnly) {
      if (value.indexOf(charToEscape) == -1) {
        return value;
      }
    }
    //
    Object o = formattersByHeader.get(header);
    if (o != null) {
      String expectedClassName = String.class.getName();
      if (o.getClass().getName().equals(expectedClassName)) {
        String formatted = String.format((String)o, value);
        formatted.replace(new String(new char [] { charToEscape }), new String(new char [] { escapeChar, charToEscape }));
        return formatted;
      }
      else {
        throw new IllegalStateException("atteso formatter di tipo "+expectedClassName+", trovato "+o.getClass().getName());
      }
    } 
    else if (stringFormat != null) {
      String formatted = String.format(stringFormat, value);
      formatted.replace(new String(new char [] { charToEscape }), new String(new char [] { escapeChar, charToEscape }));
      return formatted;
    }
    else {
      return value;
    }
  }
  
  private String F(String header, Number value) {
    if (value == null) return null;
    //
    Object o = formattersByHeader.get(header);
    if (o != null) {
      String expectedClassName = DecimalFormat.class.getName();
      if (o.getClass().getName().equals(expectedClassName)) {
        return ((NumberFormat)o).format(value);
      }
      else {
        throw new IllegalStateException("atteso formatter di tipo "+expectedClassName+", trovato "+o.getClass().getName());
      }
    } 
    else if (value instanceof Long && intNumberFormat != null) {
      return intNumberFormat.format(value);
    }
    else if (value instanceof Integer && intNumberFormat != null) {
      return intNumberFormat.format(value);
    }
    else if (value instanceof Double && doubleNumberFormat != null) {
      return doubleNumberFormat.format(value);
    }
    else if (value instanceof Float && doubleNumberFormat != null) {
      return doubleNumberFormat.format(value);
    }
    else {
      return ""+value;
    }  
  }


  private String F(String header, Calendar value) {
    if (value == null) return null;
    //
    Object o = formattersByHeader.get(header);
    if (o != null) {
      String expectedClassName = SimpleDateFormat.class.getName();
      if (o.getClass().getName().equals(expectedClassName)) {
        return ((SimpleDateFormat)o).format(value);
      }
      else {
        throw new IllegalStateException("atteso formatter di tipo "+expectedClassName+", trovato "+o.getClass().getName());
      }
    } 
    else if (timestampFormat != null) {
      return timestampFormat.format(value.getTime());
    }
    else {
      return ""+value;
    }  
  }

  public void setFormatter(String header, Object formatter) {
    formattersByHeader.put(header, formatter);
  }
  
  public void newRecord() {
    currRecord = null;
    createCurrRecordIfNull();
  }

  private void createCurrRecordIfNull() {
    if (currRecord == null) {
      currRecord  = new Hashtable<String, String>();
      Set<String> keys = defaults.keySet();
      for (String key : keys) {
        String defaultValue = defaults.get(key);
        if (defaultValue != null)
          currRecord.put(key, defaultValue);
      }
    }
  }
  
  public String getCurrentRecordValue(String header) throws Exception {
    String value = currRecord.get(header);
    if (value == null) {
      value = defaults.get(header);
    }
    return value;
  }
  
  private void writelnHeader() throws Exception {
    for (int i = 0; i < headers.size(); i++) {
      String header = headers.get(i);
      writer.write(header);
      if (i < headers.size() - 1) {
        writer.write(fieldSeparator);
      }
    }
    writer.write(crlf);
    headerWritten = true;
  }
  
  
  public void flushCurrentRecord() throws Exception {
    createCurrRecordIfNull();
    //
    if (includeHeader == true && headerWritten == false) {
      writelnHeader();    
    }
    for (int i = 0; i < headers.size(); i++) {
      String header = headers.get(i);
      String value = getCurrentRecordValue(header);
      if ("".equals(value) && mandatories.contains(header)) {
        throw new IllegalArgumentException("inammissibile, valore non trovato per il campo obbligatorio "+header);
      }
      writer.write(value);
      if (i < headers.size() - 1) {
        writer.write(fieldSeparator);
      }
    }
    writer.write(crlf);
    writer.flush();
  }
  
  
  
  

}
