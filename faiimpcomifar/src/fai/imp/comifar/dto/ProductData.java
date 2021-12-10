package fai.imp.comifar.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="comifar")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductData {
	private List<Art> art;

	public List<Art> getArt() {
		return art;
	}

	public void setArt(List<Art> art) {
		this.art = art;
	}
	

}
