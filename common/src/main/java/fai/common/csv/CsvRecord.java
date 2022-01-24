package fai.common.csv;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class CsvRecord {
  
  private List<String> headers = new ArrayList<String>();
  private Hashtable<String, Integer> headersIndexes = new Hashtable<String, Integer>(); 
  private List<String> values = new ArrayList<String>();
  private NumberFormat intNumberFormat = null;
  private NumberFormat doubleNumberFormat = null;
  private SimpleDateFormat timestampFormat = null;
  private SimpleDateFormat timeFormat = null;
  private SimpleDateFormat dateFormat = null;
  
  private CsvLineParser csvLineParser = new CsvLineParser();

  
  public boolean isTrimValueSpeaces() {
    return csvLineParser.isTrimValueSpeaces();
  }
  public void setTrimValueSpeaces(boolean trimValueSpeaces) {
    csvLineParser.setTrimValueSpeaces(trimValueSpeaces);
  }
  public String getFieldSeparator() {
    return csvLineParser.getFieldSeparator();
  }
  public void setFieldSeparator(String fieldSeparator) {
    if (fieldSeparator != null && fieldSeparator.length() == 1) {
        csvLineParser.setFieldSeparator(fieldSeparator.charAt(0));
    }
  }

  

  public char getStringDelimiter() {
    return csvLineParser.getStringDeimiterChar().charAt(0);
  }
  public char getStringDelimiterEscapeChar() {
    return csvLineParser.getStringDeimiterChar().charAt(0);
  }
  
  
  public boolean isStringMultiline() {
    return csvLineParser.isMultiline();
  }
  public void setStringMultiline(boolean stringMultiline) {
      csvLineParser.setMultiline(stringMultiline);
  }
  
  public void setStringFormat(char stringDelimiter, char stringDelimiterEscapeChar) {
    if (stringDelimiter != stringDelimiterEscapeChar) throw new IllegalArgumentException("al momento il carattere di escape del delimitatore di stringa ("+stringDelimiterEscapeChar+") deve essere uguale al delimitatore di stringa stesso ("+stringDelimiter+")");
    csvLineParser.setStringDeimiterChar(stringDelimiter);
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
  public SimpleDateFormat getTimeFormat() {
    return timeFormat;
  }
  public void setTimeFormat(SimpleDateFormat timeFormat) {
    this.timeFormat = timeFormat;
  }
  public SimpleDateFormat getDateFormat() {
    return dateFormat;
  }
  public void setDateFormat(SimpleDateFormat dateFormat) {
    this.dateFormat = dateFormat;
  }
  
  
  
  public List<String> getHeaders() {
      List<String> headersToReturn = new ArrayList<String>();
      for (String h : headers) {
        headersToReturn.add(h);
      }
      return headersToReturn;
  }
  
  
  public void parseHeaders(String recordAsText) throws Exception {
    headers = parseRecord(recordAsText);
    headersIndexes = new Hashtable<String, Integer>();
    for (int i = 0; i < headers.size(); i++) {
      headersIndexes.put(headers.get(i), i);
    }
  }
  
  public void parseValues(String recordAsText) throws Exception {
    values = parseRecord(recordAsText);
  }  
  
  public int getHeaderIndex(String fieldName) {
    return getHeaderIndex(fieldName, false);
  }
  
  public int getHeaderIndex(String fieldName, boolean mandatory) {
    Integer idx = headersIndexes.get(fieldName);
    if (idx == null && mandatory) {
      throw new CsvException("campo "+fieldName+" inesistente");
    }
    return idx == null ? -1 : idx;
  }
  
  public String getString(int fieldIndex) throws Exception {
    return fieldIndex > values.size() - 1 ? null : values.get(fieldIndex);
  }

  public String getString(String fieldName, boolean mandatoryField, boolean mandatoryValue) throws Exception {
    int idx = getHeaderIndex(fieldName, mandatoryField);
    if (idx == -1) {
        return null;
    }
    String s = idx >= values.size() ? null : values.get(idx);
    if (s == null && mandatoryValue) throw new CsvException("campo "+fieldName+" presente, ma non valorizzato");
    return s;
  }
  
  public Integer getInteger(String fieldName, boolean mandatoryField, boolean mandatoryValue) throws Exception {
    int fieldIndex = getHeaderIndex(fieldName, mandatoryField);
    if (fieldIndex == -1) {
      if (mandatoryValue) throw new CsvException("campo "+mandatoryField+" presente, ma non valorizzato");
      return null;
    }
    return getInteger(fieldIndex, mandatoryValue);
  }

  
  public Integer getInteger(int fieldIndex, boolean mandatoryValue) throws Exception {
    String s = getString(fieldIndex);
    if (s == null || "".equals(s.trim())) {
      if (mandatoryValue) throw new CsvException("campo di indice "+fieldIndex+" ("+headers.get(fieldIndex)+") presente, ma non valorizzato");
      return null;
    }
    //
    return intNumberFormat != null ? intNumberFormat.parse(s).intValue() : Integer.parseInt(s);
  }

  public Long getLong(String fieldName, boolean mandatoryField, boolean mandatoryValue) throws Exception {
    int fieldIndex = getHeaderIndex(fieldName, mandatoryField);
    if (fieldIndex == -1) {
      if (mandatoryValue) throw new CsvException("campo "+mandatoryField+" presente, ma non valorizzato");
      return null;
    }
    return getLong(fieldIndex, mandatoryValue);
  }

  
  public Long getLong(int fieldIndex, boolean mandatoryValue) throws Exception {
    String s = getString(fieldIndex);
    if (s == null || "".equals(s.trim())) {
      if (mandatoryValue) throw new CsvException("campo di indice "+fieldIndex+" ("+headers.get(fieldIndex)+") presente, ma non valorizzato");
      return null;
    }
    //
    return intNumberFormat != null ? intNumberFormat.parse(s).intValue() : Long.parseLong(s);
  }


  public Double getDouble(String fieldName, boolean mandatoryField, boolean mandatoryValue) throws Exception {
    int fieldIndex = getHeaderIndex(fieldName, mandatoryField);
    if (fieldIndex == -1) {
      if (mandatoryValue) throw new CsvException("campo "+mandatoryField+" presente, ma non valorizzato");
      return null;
    }
    return getDouble(fieldIndex, mandatoryValue);
  }

  
  public Double getDouble(int fieldIndex, boolean mandatoryValue) throws Exception {
    String s = getString(fieldIndex);
    if (s == null || "".equals(s.trim())) {
      if (mandatoryValue) throw new CsvException("campo di indice "+fieldIndex+" ("+headers.get(fieldIndex)+") presente, ma non valorizzato");
      return null;
    }
    //
    return doubleNumberFormat != null ? doubleNumberFormat.parse(s).doubleValue() : Double.parseDouble(s);
  }


  private Calendar getCalendar(String formatted, SimpleDateFormat sdf) throws Exception {
    Date dt = timestampFormat.parse(formatted);
    Calendar c= Calendar.getInstance();
    c.setTimeInMillis(dt.getTime());
    return c;
  }
  
  
  private Calendar getCalendar(String fieldName, SimpleDateFormat sdf, boolean mandatoryField, boolean mandatoryValue) throws Exception {
    int fieldIndex = getHeaderIndex(fieldName, mandatoryField);
    if (fieldIndex == -1) {
      if (mandatoryValue) throw new CsvException("campo "+mandatoryField+" presente, ma non valorizzato");
      return null;
    }
    return getCalendar(fieldIndex, sdf, mandatoryValue);
  }

  
  private Calendar getCalendar(int fieldIndex, SimpleDateFormat sdf, boolean mandatoryValue) throws Exception {
    String s = getString(fieldIndex);
    if (s == null || "".equals(s.trim())) {
      if (mandatoryValue) throw new CsvException("campo di indice "+fieldIndex+" ("+headers.get(fieldIndex)+") presente, ma non valorizzato");
      return null;
    }
    //
    Date dt;
    dt = sdf.parse(s);
    Calendar c= Calendar.getInstance();
    c.setTimeInMillis(dt.getTime());
    return c;
  }


  public Calendar getTimestamp(int fieldIndex, boolean mandatoryValue) throws Exception {
    return getCalendar(fieldIndex, timestampFormat, mandatoryValue);
  }
    
  public Calendar getTimestamp(String fieldName, boolean mandatoryField, boolean mandatoryValue) throws Exception {
    return getCalendar(fieldName, timestampFormat, mandatoryField, mandatoryValue);
  }

  public Calendar getTime(int fieldIndex, boolean mandatoryValue) throws Exception {
    return getCalendar(fieldIndex, timeFormat, mandatoryValue);
  }
    
  public Calendar getTime(String fieldName, boolean mandatoryField, boolean mandatoryValue) throws Exception {
    return getCalendar(fieldName, timeFormat, mandatoryField, mandatoryValue);
  }

  public Calendar getDate(int fieldIndex, boolean mandatoryValue) throws Exception {
    return getCalendar(fieldIndex, dateFormat, mandatoryValue);
  }
    
  public Calendar getDate(String fieldName, boolean mandatoryField, boolean mandatoryValue) throws Exception {
    return getCalendar(fieldName, dateFormat, mandatoryField, mandatoryValue);
  }

  private List<String> parseRecord(String recordAsText) throws Exception {
      csvLineParser.parse(recordAsText);
      return csvLineParser.getAllTokens();
  }
  
  public boolean hasValues() {
      return csvLineParser.hasTokenList();
  }
   
  

}
