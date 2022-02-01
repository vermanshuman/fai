package fai.imp.comifar.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItem {
	
	@XmlElement(name="minsan")
	private String productCode;
	
	@XmlElement(name="qta")
	private Integer quantity;
	
	@XmlElement(name="esito")
	private String outcome;
	
	@XmlElement(name="descr_esito")
	private String outcomeDescription;

	@XmlElement(name="sostituito")
	private String substituteMinsan;
	
	public OrderItem(String productCode, Integer quantity) {
		super();
		this.productCode = productCode;
		this.quantity = quantity;
	}
	
	public OrderItem() {
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

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getOutcomeDescription() {
		return outcomeDescription;
	}

	public void setOutcomeDescription(String outcomeDescription) {
		this.outcomeDescription = outcomeDescription;
	}

	public String getSubstituteMinsan() {
		return substituteMinsan;
	}

	public void setSubstituteMinsan(String substituteMinsan) {
		this.substituteMinsan = substituteMinsan;
	}
}
