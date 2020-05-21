package de.slag.invest.webgui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DwaConfigController {

	private String key;

	private String value;

	private final Map<String, String> configuration = new HashMap<>();

	private final List<ConfigEntry> configurationEntries = new ArrayList<>();

	@PostConstruct
	public void init() {
		configuration.put("test-key", "test-value");
		resetConfig();
	}

	public void resetConfig() {
		configurationEntries.clear();
		configurationEntries.addAll(configuration.keySet().stream().map(key -> {
			ConfigEntry configEntry = new ConfigEntry();
			configEntry.setKey(key);
			configEntry.setValue(configuration.get(key));
			return configEntry;
		}).collect(Collectors.toList()));
	}

	public Map<String, String> getConfiguration() {
		return configuration;
	}

	public List<ConfigEntry> getConfigurationEntries() {
		return configurationEntries;
	}

	public String getState() {
		return "configController: " + configuration;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void saveKeyValue() {
		configuration.put(key, value);
		key = null;
		value = null;
		resetConfig();
	}

	public class ConfigEntry {
		private String key;
		private String value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
