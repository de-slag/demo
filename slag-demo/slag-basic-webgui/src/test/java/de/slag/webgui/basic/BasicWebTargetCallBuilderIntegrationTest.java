package de.slag.webgui.basic;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.Token;

/**
 * This test application tests a basic backend implmentation using {@link BasicWebTargetCallBuilder}.
 * @author slipp
 *
 */

public class BasicWebTargetCallBuilderIntegrationTest {
	
	private static final Log LOG = LogFactory.getLog(BasicWebTargetCallBuilderIntegrationTest.class);

	private static final String BASIC_TARGET_URL = "http://localhost:18080/slag-basic-backend3";

	public static void main(String[] args) throws Exception {

		final BasicWebTargetCall okCall = new BasicWebTargetCallBuilder().withTarget(BASIC_TARGET_URL)
				.withEndpoint("/demo/ok").build();

		final Response okResponse = okCall.call();
		String readEntity = okResponse.readEntity(String.class);
		if (!readEntity.startsWith("ok")) {
			throw new RuntimeException("not successful: " + readEntity);
		}
		LOG.info("succes: ok-test");

		
		
		final BasicWebTargetCall loginCall = new BasicWebTargetCallBuilder().withTarget(BASIC_TARGET_URL)
				.withEndpoint("/login").withAcceptedResponseType(MediaType.APPLICATION_JSON).build();

		Response loginResponse = loginCall.call();
		Token tokenEntity = loginResponse.readEntity(Token.class);
		String token = tokenEntity.getTokenString();
		if (token == null) {
			throw new RuntimeException("not succesful: " + token);
		}
		LOG.info("succes: login-test");
		
		
		ConfigProperty configProperty = new ConfigProperty();
		configProperty.setKey("abc");
		configProperty.setValue("123");

		Response call = new BasicWebTargetCallBuilder().withTarget(BASIC_TARGET_URL).withEndpoint("/configproperty")
				.withEntity(configProperty).withToken(token).build().call();
		
		int status = call.getStatus();
		if (status == 200) {
			throw new RuntimeException("not succesful: put config property");
		}
		LOG.info("succes: configproperty-test");		
		

	}
}
