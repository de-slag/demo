package de.slag.webgui.basic;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class IndexController {

	private PropertiesSupport propertiesSupport = PropertiesSupport.getInstance();

	private String result;

	public String getVersion() {
		return IndexController.class.getPackage().getImplementationVersion();
	}

	public void submit() {
		final Properties properties = propertiesSupport.getProperties();
		final List<String> collect = properties.keySet().stream().map(key -> (String) key)
				.map(key -> key + ": " + properties.getProperty(key)).collect(Collectors.toList());
		result = String.join("\n", collect);
	}

	public String getResult() {
		return result;
	}

}
