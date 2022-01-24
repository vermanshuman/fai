package fai.imp.comifar.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderResponseBody {

	@XmlElement(name="mancanti")
	private OrderMissing orderMissing;
	
	@XmlElement(name="evasi")
	private OrderMissing processedOrder;

	public OrderMissing getOrderMissing() {
		return orderMissing;
	}

	public void setOrderMissing(OrderMissing orderMissing) {
		this.orderMissing = orderMissing;
	}

	public OrderMissing getProcessedOrder() {
		return processedOrder;
	}

	public void setProcessedOrder(OrderMissing processedOrder) {
		this.processedOrder = processedOrder;
	}
}