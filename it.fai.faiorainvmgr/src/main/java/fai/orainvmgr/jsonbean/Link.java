package fai.orainvmgr.jsonbean;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 
 * Generato contestualmente alla classe {@link GetInventoryQuantitiesRes}
 * cui si rimanda
 *  
 * @author Gianni Radice
 *
 */
@Generated("jsonschema2pojo")
public class Link {

  @SerializedName("rel")
  @Expose
  private String rel;
  @SerializedName("href")
  @Expose
  private String href;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("kind")
  @Expose
  private String kind;

  public String getRel() {
    return rel;
  }

  public void setRel(String rel) {
    this.rel = rel;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

}