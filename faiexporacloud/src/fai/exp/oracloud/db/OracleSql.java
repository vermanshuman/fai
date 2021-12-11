package fai.exp.oracloud.db;

import java.util.List;

import fai.common.db.SqlUtilities;

public class OracleSql {
  
  private FaiTableExport table = null;

  
  public OracleSql() {
    super();
  }

  public OracleSql(FaiTableExport table) {
    super();
    this.table = table;
  }

  public FaiTableExport getTable() {
    return table;
  }

  public void setTable(FaiTableExport table) {
    this.table = table;
  }
  
  public String getCreateTable() {
    StringBuffer sql = new StringBuffer("CREATE TABLE ");
    sql.append(table.getTableNameOracle().trim().toUpperCase());
    sql.append(" (");
    List<FaiColumnExport> columns = table.getColumns();
    for (int i = 0; i < columns.size(); i++) {
      FaiColumnExport col = columns.get(i);
      sql.append(col.getColumnNameOracle().trim().toUpperCase());
      sql.append(" ");
      String type = col.getSqlType().trim().toUpperCase();
      sql.append(type);
      Object defaultValue = col.getDefaultValue();
      int len = col.getSqlTypeLen();
      int scale = col.getSqlTypeScale();
      if ("VARCHAR2".equals(type)) {
        sql.append("("+len+")");
        if (defaultValue != null) {
          sql.append(" DEFAULT "+SqlUtilities.getAsStringFieldValue((String)defaultValue));
        }
      }
      else if ("INTEGER".equals(type)) {
        if (defaultValue != null) {
          sql.append(" DEFAULT "+""+defaultValue);
        }
      }
      else if ("DATE".equals(type)) {
        if (defaultValue != null) {
          sql.append(" DEFAULT "+""+defaultValue);
        }
      }
      else if ("NUMBER".equals(type)) {
        if (scale > 0) {
          sql.append("("+len+","+scale+")");
        }
        else {
          sql.append("("+len+")");
        }
        if (defaultValue != null) {
          sql.append(" DEFAULT "+""+defaultValue);
        }
      }
      else {
        throw new IllegalArgumentException("tipo non gestito: "+type);
      }
      if (i < columns.size() - 1) {
        sql.append(", ");
      }
    }
    sql.append(")");
    return sql.toString();
  }
  
  
  

}
