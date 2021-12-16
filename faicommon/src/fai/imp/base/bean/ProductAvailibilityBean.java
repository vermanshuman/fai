package fai.imp.base.bean;

public class ProductAvailibilityBean {
	
	private String productCode;
	
	private Boolean availibility;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Boolean getAvailibility() {
		return availibility;
	}

	public void setAvailibility(Boolean availibility) {
		this.availibility = availibility;
	}
	
	@Override
	public String toString() {
		return "Product Code : " + getProductCode() + " - Availibility: " + getAvailibility();
	}
}
