package de.slag.demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DemoController {
	
	public String getVersion() {
		return "0.1.1";
	}
}
