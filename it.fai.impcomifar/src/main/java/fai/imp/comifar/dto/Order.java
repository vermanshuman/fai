package fai.imp.comifar.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

	@XmlElement(name="head")
	private Head head;

	@XmlElement(name="body")
	private OrderBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public OrderBody getBody() {
		return body;
	}

	public void setBody(OrderBody body) {
		this.body = body;
	}
}
