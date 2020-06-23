package de.slag.demo.slagbasicbackend3;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

class DemoBackend3ControllerTest {
	
	@Test
	void testOkJson() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:18080/slag-basic-backend3");
		WebTarget okJsonWebTarget = webTarget.path("/ok-json");
		Builder invocationBuilder = okJsonWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		XyResponse readEntity = response.readEntity(XyResponse.class);
		System.out.println(readEntity.getMessage());
		
		
		
	}

	@Test
	void testUser() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:18080/slag-basic-backend3");
		WebTarget userWebTarget = webTarget.path("/user");
		Builder invocationBuilder = userWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		User readEntity = response.readEntity(User.class);
		readEntity.getClass();
	}
}
