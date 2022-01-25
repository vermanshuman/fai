package fai.orainvmgr.jsonbean;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Classe generata per mezzo di 
 * <a href="http://www.jsonschema2pojo.org/">http://www.jsonschema2pojo.org/</a>
 * a partire dal JSON di esempio (documentato in  
 * <a href="https://docs.oracle.com/en/cloud/saas/supply-chain-management/21b/fasrp/use_case_Logistics_Get_Inventory_Quantities.html">
 * https://docs.oracle.com/en/cloud/saas/supply-chain-management/21b/fasrp/use_case_Logistics_Get_Inventory_Quantities.html</a>:<br/>
 * <pre>
 * {
 *   "OrganizationCode" : "M1",
 *   "ItemNumber" : "VC110"
 * }il
 * </pre>
 * 
 * @author Gianni Radice
 *
 */
@Generated("jsonschema2pojo")
public class GetInventoryQuantitiesReq {

  @SerializedName("OrganizationCode")
  @Expose
  private String organizationCode;
  
  @SerializedName("ItemNumber")
  @Expose
  private String itemNumber;

  public String getOrganizationCode() {
    return organizationCode;
  }

  public void setOrganizationCode(String organizationCode) {
    this.organizationCode = organizationCode;
  }

  public String getItemNumber() {
    return itemNumber;
  }

  public void setItemNumber(String itemNumber) {
    this.itemNumber = itemNumber;
  }

}