package fai.exp.oracloud.db;

import java.util.List;

public class FaiColumnExport {
  
  private long oid;
  private int columnIdx;
  private String columnNameOracle;
  private String columnNameExport;
  private boolean required;
  private String sqlType;
  private int sqlTypeLen;
  private int sqlTypeScale;
  private String dateFormatPattern;
  private String numbFormatPattern;
  private String numbFormatGrpCh;
  private String numbFormatDecCh;
  private Object defaultValue;
  public long getOid() {
    return oid;
  }
  public void setOid(long oid) {
    this.oid = oid;
  }
  public int getColumnIdx() {
    return columnIdx;
  }
  public void setColumnIdx(int columnIdx) {
    this.columnIdx = columnIdx;
  }
  public String getColumnNameOracle() {
    return columnNameOracle;
  }
  public void setColumnNameOracle(String columnNameOracle) {
    this.columnNameOracle = columnNameOracle;
  }
  public String getColumnNameExport() {
    return columnNameExport;
  }
  public void setColumnNameExport(String columnNameExport) {
    this.columnNameExport = columnNameExport;
  }
  public boolean isRequired() {
    return required;
  }
  public void setRequired(boolean required) {
    this.required = required;
  }
  public String getSqlType() {
    return sqlType;
  }
  public void setSqlType(String sqlType) {
    this.sqlType = sqlType;
  }
  public int getSqlTypeLen() {
    return sqlTypeLen;
  }
  public void setSqlTypeLen(int sqlTypeLen) {
    this.sqlTypeLen = sqlTypeLen;
  }
  public int getSqlTypeScale() {
    return sqlTypeScale;
  }
  public void setSqlTypeScale(int sqlTypeScale) {
    this.sqlTypeScale = sqlTypeScale;
  }
  public String getDateFormatPattern() {
    return dateFormatPattern;
  }
  public void setDateFormatPattern(String dateFormatPattern) {
    this.dateFormatPattern = dateFormatPattern;
  }
  public String getNumbFormatPattern() {
    return numbFormatPattern;
  }
  public void setNumbFormatPattern(String numbFormatPattern) {
    this.numbFormatPattern = numbFormatPattern;
  }
  public String getNumbFormatGrpCh() {
    return numbFormatGrpCh;
  }
  public void setNumbFormatGrpCh(String numbFormatGrpCh) {
    this.numbFormatGrpCh = numbFormatGrpCh;
  }
  public String getNumbFormatDecCh() {
    return numbFormatDecCh;
  }
  public void setNumbFormatDecCh(String numbFormatDecCh) {
    this.numbFormatDecCh = numbFormatDecCh;
  }
  public Object getDefaultValue() {
    return defaultValue;
  }
  public void setDefaultValue(Object defaultValue) {
    this.defaultValue = defaultValue;
  }
  
  
  

}
