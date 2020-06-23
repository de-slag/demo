package de.slag.demo.slagbasicbackend3;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.slag.basic.backend.api.BasicBackendController;
import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.Token;

@RestController
public class DemoBackend3Controller implements BasicBackendController {

	private Map<String, Properties> configMap = new HashMap<String, Properties>();

	@GetMapping(path = "/demo/ok")
	public String getOk() {
		return "ok";
	}

	@GetMapping(path = "/demo/ok-json", produces = MediaType.APPLICATION_JSON)
	public Object getOkJson() {
		XyResponse xyResponse = new XyResponse();
		xyResponse.setMessage("ok with json");
		return xyResponse;
	}

	@GetMapping(path = "/demo/user", produces = MediaType.APPLICATION_JSON)
	public Object getUser() {
		User entity = new User();
		entity.setId(4711L);
		entity.setUsername("john-doe");
		return entity;
	}

	@Override
	@GetMapping(path = "/demo/login", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Token getLogin(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password) {

		// TODO: correct implement token

		final Token token = new Token();
		token.setTokenString(String.valueOf(System.currentTimeMillis()));
		return token;
	}

	@Override
	@GetMapping(path = "/demo/rundefault", produces = MediaType.APPLICATION_JSON)
	public String runDefault(String token) {
		Properties properties = configMap.get(token);

		// configure backend application, run and return results as single String

		return "all done";
	}

	@PutMapping(path = "/demo/configproperty", produces = MediaType.APPLICATION_JSON) // produces??
	public Response putConfigProperty(String token, String key, String value) {
		// validate token
		if (!configMap.containsKey(token)) {
			configMap.put(token, new Properties());
		}
		configMap.get(token).put(key, value);
		return Response.ok().build();
	}

	@Override
	public Response putConfigProperty(String token, ConfigProperty configProperty) {
		// TODO Auto-generated method stub
		return null;
	}
}
