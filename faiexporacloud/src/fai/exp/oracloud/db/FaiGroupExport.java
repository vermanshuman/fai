package fai.exp.oracloud.db;

import java.util.ArrayList;
import java.util.List;

public class FaiGroupExport {
  
  private long oid;
  private int groupIdx;
  private String groupNameExport;
  private List<FaiTableExport> tables = new ArrayList<FaiTableExport>();
  public long getOid() {
    return oid;
  }
  public void setOid(long oid) {
    this.oid = oid;
  }
  public int getGroupIdx() {
    return groupIdx;
  }
  public void setGroupIdx(int groupIdx) {
    this.groupIdx = groupIdx;
  }
  public String getGroupNameExport() {
    return groupNameExport;
  }
  public void setGroupNameExport(String groupNameExport) {
    this.groupNameExport = groupNameExport;
  }
  public List<FaiTableExport> getTables() {
    return tables;
  }
  public void setTables(List<FaiTableExport> tables) {
    this.tables = tables;
  }
  public void addTable(FaiTableExport table) {
    tables.add(table);
  }    
  public FaiTableExport getTableAt(int idx) {
    return tables.get(idx);
  }
  

}
