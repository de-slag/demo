package de.slag.webgui.basic;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.slag.basic.model.Token;

/**
 * This test application tests a basic backend implmentation using {@link BasicWebTargetCallBuilder}.
 * @author slipp
 *
 */

public class BasicWebTargetCallBuilderIntegrationTest {

	private static final String BASIC_TARGET_URL = "http://localhost:18080/slag-basic-backend3";

	public static void main(String[] args) throws Exception {

		final BasicWebTargetCall okCall = new BasicWebTargetCallBuilder().withTarget(BASIC_TARGET_URL)
				.withEndpoint("/demo/ok").build();

		final Response okResponse = okCall.call();
		String readEntity = okResponse.readEntity(String.class);
		if (!readEntity.startsWith("ok")) {
			throw new RuntimeException("not successful: " + readEntity);
		}

		final BasicWebTargetCall loginCall = new BasicWebTargetCallBuilder().withTarget(BASIC_TARGET_URL)
				.withEndpoint("/login").withAcceptedResponseType(MediaType.APPLICATION_JSON).build();

		Response loginResponse = loginCall.call();
		Token tokenEntity = loginResponse.readEntity(Token.class);
		String token = tokenEntity.getTokenString();
		if (token == null) {
			throw new RuntimeException("not succesful: " + token);
		}

	}
}
