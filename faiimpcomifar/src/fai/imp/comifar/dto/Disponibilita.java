package fai.imp.comifar.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="disponibilita")
@XmlAccessorType(XmlAccessType.FIELD)
public class Disponibilita {
	@XmlElement(name="esito")
	private Esito outcome;
	
	@XmlElement(name="dispon")
	private Dispon avail;

	public Esito getOutcome() {
		return outcome;
	}

	public void setOutcome(Esito outcome) {
		this.outcome = outcome;
	}

	public Dispon getAvail() {
		return avail;
	}

	public void setAvail(Dispon avail) {
		this.avail = avail;
	}
}
