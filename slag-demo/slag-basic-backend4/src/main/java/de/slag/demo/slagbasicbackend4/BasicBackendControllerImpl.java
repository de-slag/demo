package de.slag.demo.slagbasicbackend4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.slag.basic.backend.api.BasicBackendController;
import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.Token;

@RestController
public class BasicBackendControllerImpl implements BasicBackendController {

	private static final Log LOG = LogFactory.getLog(BasicBackendControllerImpl.class);

	private Map<String, Properties> map = new HashMap<>();

	@PostConstruct
	public void setUp() {
		LOG.debug("created");
	}

	@Override
	@GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON)
	public Token getLogin(@RequestParam(required = false) String username,
			@RequestParam(required = false) String password) {

		LOG.debug(String.format("login with credentials: username %s, password: %s", username, password));

		final Token token = new Token();
		String tokenString = String.valueOf(System.currentTimeMillis());

		map.put(tokenString, new Properties());
		LOG.debug(String.format("created properties for token: %s", tokenString));

		token.setTokenString(tokenString);
		LOG.info("token created: " + tokenString);
		return token;
	}

	@Override
	@PutMapping(path = "/configproperty", produces = MediaType.TEXT_PLAIN)
	public Response putConfigProperty(@RequestParam(required = false) String token,
			@RequestBody ConfigProperty configProperty) {

		LOG.info(configProperty);
		if (token == null) {
			return null;
		}
		Properties properties = map.get(token);
		String key = configProperty.getKey();
		String value = configProperty.getValue();
		properties.put(key, value);
		LOG.info(String.format("token: '%s', putted key: '%s', value: '%s'", token, key, value));
		return null;
	}

	@Override
	@GetMapping(path = "/rundefault", produces = MediaType.APPLICATION_JSON)
	public String runDefault(@RequestParam String token) {

		Properties properties = map.get(token);
		if (properties == null) {
			return "no properties found for token: " + token;
		}
		List<String> configEntries = new ArrayList<>();
		properties.keySet().forEach(key -> {
			String keyValuePair = key + "=" + properties.get(key);
			configEntries.add(keyValuePair);
			LOG.info(keyValuePair);
		});

		// TODO Auto-generated method stub
		return String.join("\n", configEntries) + "\nall done";
	}

	@GetMapping(path = "/demo/ok", produces = MediaType.TEXT_PLAIN)
	public String getOk() {
		return "ok, " + LocalDateTime.now();
	}

}
