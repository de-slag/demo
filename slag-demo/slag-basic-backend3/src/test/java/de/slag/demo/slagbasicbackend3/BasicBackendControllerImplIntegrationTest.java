package de.slag.demo.slagbasicbackend3;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.Token;

public class BasicBackendControllerImplIntegrationTest implements Runnable {

	private static final Log LOG = LogFactory.getLog(BasicBackendControllerImplIntegrationTest.class);

	private static final String BASE_URL = "http://localhost:18080/slag-basic-backend3";

	private WebTarget wtLogin;
	private WebTarget wtConfigProperty;
	private WebTarget wtRunDefault;

	public static void main(String[] args) {
		new BasicBackendControllerImplIntegrationTest().run();
	}

	public BasicBackendControllerImplIntegrationTest() {
		Client client = ClientBuilder.newClient();
		wtLogin = client.target(BASE_URL + "/login");
		wtConfigProperty = client.target(BASE_URL + "/configproperty");
		wtRunDefault = client.target(BASE_URL + "/rundefault");
	}

	@Override
	public void run() {
		Token tokenEntity = wtLogin.request(MediaType.APPLICATION_JSON).get().readEntity(Token.class);
		String token = tokenEntity.getTokenString();
		LOG.info("Token: " + token);

		ConfigProperty configProperty = new ConfigProperty();
		configProperty.setKey("abc");
		configProperty.setValue("123");

		Response put = wtConfigProperty.queryParam("token", token).request()
				.put(Entity.entity(configProperty, MediaType.APPLICATION_JSON));

		int status = put.getStatus();
		LOG.info("Status: " + status);

		String runDefaultResult = wtRunDefault.queryParam("token", token).request(MediaType.APPLICATION_JSON).get()
				.readEntity(String.class);
		LOG.info(runDefaultResult);

	}

}
