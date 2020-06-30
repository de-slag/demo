package de.slag.webgui.basic;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

@ManagedBean
@SessionScoped
public class ConfigController {

	private static final String NEWLINE = "\n";

	private PropertiesSupport propertiesSupport = PropertiesSupport.getInstance();

	private String configText;

	private String status = "created";

	public String getConfigText() {
		return configText;
	}

	public void setConfigText(String configText) {
		this.configText = configText;
	}

	public void generateDefaults() {
		configText = "";
		configText += PropertiesSupplier.FRONTEND_BACKEND_URL + NEWLINE;
		configText += PropertiesSupplier.FRONTEND_USER + NEWLINE;
		configText += PropertiesSupplier.FRONTEND_PASSWORD;
	}

	public void save() {
		propertiesSupport.getProperties().clear();
		if (StringUtils.isEmpty(configText)) {
			return;
		}
		final List<String> asList = Arrays.asList(configText.split(NEWLINE));
		asList.forEach(keyValue -> {
			final String[] split = keyValue.split("=");
			final String key = split[0];
			final String value = split[1];
			propertiesSupport.getProperties().put(key, value);
		});

		status = "saved";
	}

	public String getStatus() {
		return status;
	}

}
