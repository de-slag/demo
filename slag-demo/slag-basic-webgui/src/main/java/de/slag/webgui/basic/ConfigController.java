package de.slag.webgui.basic;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ConfigController {

	private String configText;

	private String status = "created";

	public String getConfigText() {
		return configText;
	}

	public void setConfigText(String configText) {
		this.configText = configText;
	}

	public void save() {
		status = "saved";
	}

	public String getStatus() {
		return status;
	}
	
	

}
