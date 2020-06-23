package de.slag.demo.slagbasicbackend3;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XyResponse {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
