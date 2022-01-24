package fai.imp.comifar.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Esito {
	@XmlElement(name="sigla")
	private String outcome;
	
	@XmlElement(name="messaggio")
	private String outcomeDescription;

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
}
