package fai.imp.base.bean;

public class ProcessedOrderBean {
	
	private String productCode;

	private String susbituteMinsan;
	
	private Integer orderedQuantity;

	private Integer missingQuantity;
	
	private Boolean orderFailed;
	
	private String errorCode;
	
	private String errorDescription;

	private String numeroOrdineFornitore;

	private String idVendita;

	private String requestXML;

	private String responseXML;

	public ProcessedOrderBean(String productCode, Integer orderedQuantity, Boolean orderStatus, String errorCode,
			String errorDescription, String idVendita) {
		this.productCode = productCode;
		this.orderedQuantity = orderedQuantity;
		this.orderFailed = orderStatus;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.idVendita = idVendita;
	}

	public ProcessedOrderBean(String productCode, Integer orderedQuantity, Integer missingQuantity, Boolean orderStatus, String errorCode,
							  String errorDescription, String idVendita) {
		this.productCode = productCode;
		this.orderedQuantity = orderedQuantity;
		this.missingQuantity = missingQuantity;
		this.orderFailed = orderStatus;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.idVendita = idVendita;
	}


	public ProcessedOrderBean(String productCode, String substituteMinsan, Integer orderedQuantity, Integer missingQuantity, Boolean orderStatus, String errorCode,
							  String errorDescription, String numeroOrdineFornitore,String idVendita, String requestXML,
							  String responseXMl) {
		this.productCode = productCode;
		this.susbituteMinsan = substituteMinsan;
		this.orderedQuantity = orderedQuantity;
		this.missingQuantity = missingQuantity;
		this.orderFailed = orderStatus;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.numeroOrdineFornitore = numeroOrdineFornitore;
		this.idVendita = idVendita;
		this.requestXML = requestXML;
		this.responseXML = responseXMl;
	}

	public ProcessedOrderBean(String productCode, String substituteMinsan, Integer orderedQuantity, Integer missingQuantity, Boolean orderStatus, String errorCode,
							  String errorDescription, String idVendita, String requestXML,String responseXMl) {
		this.productCode = productCode;
		this.susbituteMinsan = substituteMinsan;
		this.orderedQuantity = orderedQuantity;
		this.missingQuantity = missingQuantity;
		this.orderFailed = orderStatus;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.idVendita = idVendita;
		this.requestXML = requestXML;
		this.responseXML = responseXMl;
	}
	
	public ProcessedOrderBean(String productCode, Integer orderedQuanity, Boolean orderStatus, String idVendita) {
		this.productCode = productCode;
		this.orderedQuantity = orderedQuanity;
		this.orderFailed = orderStatus;
		this.idVendita = idVendita;
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

	public String getNumeroOrdineFornitore() {
		return numeroOrdineFornitore;
	}

	public void setNumeroOrdineFornitore(String numeroOrdineFornitore) {
		this.numeroOrdineFornitore = numeroOrdineFornitore;
	}

	public String getIdVendita() {
		return idVendita;
	}

	public void setIdVendita(String idVendita) {
		this.idVendita = idVendita;
	}

	public String getSusbituteMinsan() {
		return susbituteMinsan;
	}

	public void setSusbituteMinsan(String susbituteMinsan) {
		this.susbituteMinsan = susbituteMinsan;
	}

	public String getRequestXML() {
		return requestXML;
	}

	public void setRequestXML(String requestXML) {
		this.requestXML = requestXML;
	}

	public String getResponseXML() {
		return responseXML;
	}

	public void setResponseXML(String responseXML) {
		this.responseXML = responseXML;
	}
}
