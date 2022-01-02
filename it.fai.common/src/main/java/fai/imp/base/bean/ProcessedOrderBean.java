package fai.imp.base.bean;

public class ProcessedOrderBean {
	
	private String productCode;
	
	private Integer orderedQuantity;

	private Integer missingQuantity;
	
	private Boolean orderFailed;
	
	private String errorCode;
	
	private String errorDescription;
	
	public ProcessedOrderBean(String productCode, Integer orderedQuantity, Boolean orderStatus, String errorCode,
			String errorDescription) {
		this.productCode = productCode;
		this.orderedQuantity = orderedQuantity;
		this.orderFailed = orderStatus;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public ProcessedOrderBean(String productCode, Integer orderedQuantity, Integer missingQuantity, Boolean orderStatus, String errorCode,
							  String errorDescription) {
		this.productCode = productCode;
		this.orderedQuantity = orderedQuantity;
		this.missingQuantity = missingQuantity;
		this.orderFailed = orderStatus;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	
	public ProcessedOrderBean(String productCode, Integer orderedQuanity, Boolean orderStatus) {
		this.productCode = productCode;
		this.orderedQuantity = orderedQuanity;
		this.orderFailed = orderStatus;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(Integer orderedQuanity) {
		this.orderedQuantity = orderedQuanity;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Boolean getOrderFailed() {
		return orderFailed;
	}

	public void setOrderFailed(Boolean orderFailed) {
		this.orderFailed = orderFailed;
	}

	public Integer getMissingQuantity() {
		return missingQuantity;
	}

	public void setMissingQuantity(Integer missingQuantity) {
		this.missingQuantity = missingQuantity;
	}
}
