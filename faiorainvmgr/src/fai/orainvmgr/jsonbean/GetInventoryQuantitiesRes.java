package fai.orainvmgr.jsonbean;

import java.util.List;
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
 *   "OrganizationCode": "M1",
 *   "ItemNumber": "VC110",
 *   "PrimaryUnitOfMeasure": "Each",
 *   "QuantityOnhand": 2,
 *   "ReturnStatus": "SUCCESS",
 *   "ReturnMessageName": null,
 *   "ReturnMessageText": null,
 *   "links": [
 *   {
 *     "rel": "self",
 *     "href": "https://servername/fscmRestApi/resources/version/availableQuantityDetails/99999",
 *     "name": "availableQuantityDetails",
 *     "kind": "item"
 *   },
 *   {
 *     "rel": "canonical",
 *     "href": "https://servername/fscmRestApi/resources/version/availableQuantityDetails/99999",
 *     "name": "availableQuantityDetails",
 *     "kind": "item"
 *   }
 *   ]
 * }
 * </pre>
 * 
 * @author Gianni Radice
 *
 */
@Generated("jsonschema2pojo")
public class GetInventoryQuantitiesRes {

  @SerializedName("OrganizationCode")
  @Expose
  private String organizationCode;
  @SerializedName("ItemNumber")
  @Expose
  private String itemNumber;
  @SerializedName("PrimaryUnitOfMeasure")
  @Expose
  private String primaryUnitOfMeasure;
  @SerializedName("QuantityOnhand")
  @Expose
  private Integer quantityOnhand;
  @SerializedName("ReturnStatus")
  @Expose
  private String returnStatus;
  @SerializedName("ReturnMessageName")
  @Expose
  private Object returnMessageName;
  @SerializedName("ReturnMessageText")
  @Expose
  private Object returnMessageText;
  @SerializedName("links")
  @Expose
  private List<Link> links = null;

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

  public String getPrimaryUnitOfMeasure() {
    return primaryUnitOfMeasure;
  }

  public void setPrimaryUnitOfMeasure(String primaryUnitOfMeasure) {
    this.primaryUnitOfMeasure = primaryUnitOfMeasure;
  }

  public Integer getQuantityOnhand() {
    return quantityOnhand;
  }

  public void setQuantityOnhand(Integer quantityOnhand) {
    this.quantityOnhand = quantityOnhand;
  }

  public String getReturnStatus() {
    return returnStatus;
  }

  public void setReturnStatus(String returnStatus) {
    this.returnStatus = returnStatus;
  }

  public Object getReturnMessageName() {
    return returnMessageName;
  }

  public void setReturnMessageName(Object returnMessageName) {
    this.returnMessageName = returnMessageName;
  }

  public Object getReturnMessageText() {
    return returnMessageText;
  }

  public void setReturnMessageText(Object returnMessageText) {
    this.returnMessageText = returnMessageText;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

}
