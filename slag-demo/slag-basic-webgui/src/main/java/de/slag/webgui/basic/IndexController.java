package de.slag.webgui.basic;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class IndexController {
	
	public String getVersion() {
		return IndexController.class.getPackage()
				.getImplementationVersion();
	}

}
