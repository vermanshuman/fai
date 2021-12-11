package fai.imp.comifar.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)
public class Art {
	
	@XmlElement(name="mins")
	private String minsan;
	
	@XmlElement(name="st")
	private String status;
	
	@XmlElement(name="nv")
	private String note;
	
	@XmlElement(name="vda")
	private String validStartDate;
	
	@XmlElement(name="va")
	private String validEndDate;
	
	@XmlElement(name="ls")
	private List<ListBracket> listBracket;

	
	public String getMinsan() {
		return minsan;
	}

	public void setMinsan(String minsan) {
		this.minsan = minsan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValidStartDate() {
		return validStartDate;
	}

	public void setValidStartDate(String validStartDate) {
		this.validStartDate = validStartDate;
	}

	public String getValidEndDate() {
		return validEndDate;
	}

	public void setValidEndDate(String validEndDate) {
		this.validEndDate = validEndDate;
	}
	
	public List<ListBracket> getListBracket() {
		return listBracket;
	}

	public void setListBracket(List<ListBracket> listBracket) {
		this.listBracket = listBracket;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
