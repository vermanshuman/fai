package fai.imp.base.bean;

public class ProcessedOrderBean {
	
	private String productCode;
	
	private Integer orderedQuanity;
	
	private Boolean orderFailed;
	
	private String errorCode;
	
	private String errorDescription;
	
	public ProcessedOrderBean(String productCode, Integer orderedQuanity, Boolean orderStatus, String errorCode,
			String errorDescription) {
		this.productCode = productCode;
		this.orderedQuanity = orderedQuanity;
		this.orderFailed = orderStatus;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	
	public ProcessedOrderBean(String productCode, Integer orderedQuanity, Boolean orderStatus) {
		this.productCode = productCode;
		this.orderedQuanity = orderedQuanity;
		this.orderFailed = orderStatus;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getOrderedQuanity() {
		return orderedQuanity;
	}

	public void setOrderedQuanity(Integer orderedQuanity) {
		this.orderedQuanity = orderedQuanity;
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
}
