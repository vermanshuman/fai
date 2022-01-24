package fai.imp.comifar.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Info {
	@XmlElement(name="esito_sigla")
	private String outcome;
	
	@XmlElement(name="esito_descr")
	private String outcomeDescription;
	
	@XmlElement(name="continua")
	private String continueStatus;
	
	@XmlElement(name="articoli")
	private Integer article;
	
	@XmlElement(name="note")
	private String note;
	
	@XmlElement(name="progressivo")
	private Integer progressive;

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

	public String getContinueStatus() {
		return continueStatus;
	}

	public void setContinueStatus(String continueStatus) {
		this.continueStatus = continueStatus;
	}

	public Integer getArticle() {
		return article;
	}

	public void setArticle(Integer article) {
		this.article = article;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getProgressive() {
		return progressive;
	}

	public void setProgressive(Integer progressive) {
		this.progressive = progressive;
	}
}
