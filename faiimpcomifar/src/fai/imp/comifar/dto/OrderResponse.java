package fai.imp.comifar.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="risposta")
@XmlAccessorType(XmlAccessType.FIELD)

public class OrderResponse {
	
	private Head head;
	
	private OrderResponseBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public OrderResponseBody getBody() {
		return body;
	}

	public void setBody(OrderResponseBody body) {
		this.body = body;
	}
}