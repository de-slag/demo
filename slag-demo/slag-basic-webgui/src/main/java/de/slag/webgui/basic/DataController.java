package de.slag.webgui.basic;

import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DataController {

	private PropertiesSupport propertiesSupport = PropertiesSupport.getInstance();

	private String status;
	
	private String type;
	
	private String id;

	public String getVersion() {
		return DataController.class.getPackage().getImplementationVersion();
	}

	public void init() {
		type = null;
		id = null;
		status = "initialized";
	}
	
	public void submit() {
		status = "submitted";
	}
	

	public String getStatus() {
		return status;
	}

	public List<?> getData() {
		return Collections.emptyList();
	}
	public List<?> getTypes() {
		return Collections.emptyList();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
