package de.slag.demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HelloWorldController {
	
	public String getHello() {
		return "Hello World!";
	}

}
