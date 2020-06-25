import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BasicBackendControllerIntegrationTest implements Runnable {

	public static void main(String[] args) {
		new BasicBackendControllerIntegrationTest().run();
	}

	private void log(Object o) {
		System.out.println(o);
	}

	@Override
	public void run() {
		final WebTarget okWebTarget = ClientBuilder.newClient()
				.target("http://localhost:18080/slag-basic-backend3/demo/ok");
		Builder request = okWebTarget.request(MediaType.TEXT_PLAIN);
		Response response = request.get();
		String readEntity = response.readEntity(String.class);
		if (!readEntity.startsWith("ok")) {
			throw new RuntimeException();
		}
		log("test 'OK' successful.");
	}
}
