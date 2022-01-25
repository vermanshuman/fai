package fai.imp.base.bean;

public class ProductBean {
	
	private String productCode;
	
	private Integer quantity;
	
	private Boolean availibility;

	public ProductBean(String productCode, Integer quantity) {
		this.productCode = productCode;
		this.quantity = quantity;
	}

	public ProductBean() {
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getAvailibility() {
		return availibility;
	}

	public void setAvailibility(Boolean availibility) {
		this.availibility = availibility;
	}
}
