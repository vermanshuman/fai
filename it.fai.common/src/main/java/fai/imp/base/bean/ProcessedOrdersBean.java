package fai.imp.base.bean;

import java.util.List;

public class ProcessedOrdersBean {

	private String requestXML;

	private String responseXMl;

	private List<ProcessedOrderBean> processedOrders;

	public String getRequestXML() {
		return requestXML;
	}

	public void setRequestXML(String requestXML) {
		this.requestXML = requestXML;
	}

	public String getResponseXMl() {
		return responseXMl;
	}

	public void setResponseXMl(String responseXMl) {
		this.responseXMl = responseXMl;
	}

	public List<ProcessedOrderBean> getProcessedOrders() {
		return processedOrders;
	}

	public void setProcessedOrders(List<ProcessedOrderBean> processedOrders) {
		this.processedOrders = processedOrders;
	}
}
