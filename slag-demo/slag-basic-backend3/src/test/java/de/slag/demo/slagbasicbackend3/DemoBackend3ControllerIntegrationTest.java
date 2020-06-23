package de.slag.demo.slagbasicbackend3;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DemoBackend3ControllerIntegrationTest implements Runnable {

	private String URL = "http://localhost:18080";

	private WebTarget loginWebTarget;
	private WebTarget configPropertyWebTarget;
	private WebTarget runDefaultWebTarget;

	public static void main(String[] args) {
		new DemoBackend3ControllerIntegrationTest().run();
	}

	public DemoBackend3ControllerIntegrationTest() {
		Client newClient = ClientBuilder.newClient();

		loginWebTarget = newClient.target(URL + "/login");
		configPropertyWebTarget = newClient.target(URL + "/configproperty");
		runDefaultWebTarget = newClient.target(URL + "/rundefault");
	}

	@Override
	public void run() {
		Builder request = loginWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = request.get();

	}

}
