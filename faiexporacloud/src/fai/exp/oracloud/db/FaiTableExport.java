package fai.exp.oracloud.db;

import java.util.ArrayList;
import java.util.List;

public class FaiTableExport {

  private long oid;
  private int tableIdx;
  private String tableNameOracle;
  private String tableNameExport;
  private String smtwtfsExportPattern;
  private String sqlQueryIn;
  private String sqlQueryInFile;
  private String sqlQueryOut;
  private String sqlQueryOutFile;
  private String sqlDDL;
  private List<FaiColumnExport> columns = new ArrayList<FaiColumnExport>();
  public long getOid() {
    return oid;
  }
  public void setOid(long oid) {
    this.oid = oid;
  }
  public int getTableIdx() {
    return tableIdx;
  }
  public void setTableIdx(int tableIdx) {
    this.tableIdx = tableIdx;
  }
  public String getTableNameOracle() {
    return tableNameOracle;
  }
  public void setTableNameOracle(String tableNameOracle) {
    this.tableNameOracle = tableNameOracle;
  }
  public String getTableNameExport() {
    return tableNameExport;
  }
  public void setTableNameExport(String tableNameExport) {
    this.tableNameExport = tableNameExport;
  }
  public String getSmtwtfsExportPattern() {
    return smtwtfsExportPattern;
  }
  public void setSmtwtfsExportPattern(String smtwtfsExportPattern) {
    this.smtwtfsExportPattern = smtwtfsExportPattern;
  }
  public String getSqlQueryIn() {
    return sqlQueryIn;
  }
  public void setSqlQueryIn(String sqlQueryIn) {
    this.sqlQueryIn = sqlQueryIn;
  }
  public String getSqlQueryInFile() {
    return sqlQueryInFile;
  }
  public void setSqlQueryInFile(String sqlQueryInFile) {
    this.sqlQueryInFile = sqlQueryInFile;
  }
  public String getSqlQueryOut() {
    return sqlQueryOut;
  }
  public void setSqlQueryOut(String sqlQueryOut) {
    this.sqlQueryOut = sqlQueryOut;
  }
  public String getSqlQueryOutFile() {
    return sqlQueryOutFile;
  }
  public void setSqlQueryOutFile(String sqlQueryOutFile) {
    this.sqlQueryOutFile = sqlQueryOutFile;
  }
  public String getSqlDDL() {
    return sqlDDL;
  }
  public void setSqlDDL(String sqlDDL) {
    this.sqlDDL = sqlDDL;
  }
  public List<FaiColumnExport> getColumns() {
    return columns;
  }
  public void setColumns(List<FaiColumnExport> columns) {
    this.columns = columns;
  }  
  public void addColumn(FaiColumnExport column) {
    this.columns.add(column);
  }
  public FaiColumnExport getColumnAt(int idx) {
    return columns.get(idx);
  }

  public int getMaxColumnIndex() {
    int maxColIdx = Integer.MIN_VALUE;
    for (FaiColumnExport col : columns) {
      if (col.getColumnIdx() > maxColIdx) {
        maxColIdx = col.getColumnIdx();
      }
    }
    return maxColIdx;
  }
  
  public void removeColumnByOracleName(String columnOracleName) {
    for (int i = 0; i < columns.size(); i++) {
      if (columns.get(i).getColumnNameOracle().equals(columnOracleName)) {
        columns.remove(i);
        return;
      }
    }
    return;
  }
}
