package de.slag.demo.slagbasicbackend3;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

	@Override
	@GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON)
	public Token getLogin(@RequestParam(required = false) String username,
			@RequestParam(required = false) String password) {

		final Token token = new Token();
		String tokenString = String.valueOf(System.currentTimeMillis());

		map.put(tokenString, new Properties());

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
		properties.put(configProperty.getKey(), configProperty.getValue());
		return null;
	}

	@Override
	@GetMapping(path = "/rundefault", produces = MediaType.APPLICATION_JSON)
	public String runDefault(@RequestParam String token) {

		Properties properties = map.get(token);
		properties.keySet().forEach(key -> LOG.info(key + "=" + properties.get(key)));

		// TODO Auto-generated method stub
		return "all done";
	}

	@GetMapping(path = "/demo/ok", produces = MediaType.TEXT_PLAIN)
	public String getOk() {
		return "ok, " + LocalDateTime.now();
	}

}
