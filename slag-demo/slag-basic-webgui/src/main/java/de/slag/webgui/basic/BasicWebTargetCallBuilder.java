package de.slag.webgui.basic;

import java.util.Objects;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BasicWebTargetCallBuilder {

	private String acceptedResponseType = MediaType.TEXT_PLAIN;

	private HttpMethod httpMethod = HttpMethod.GET;

	private String target;

	private String endpoint;

	public BasicWebTargetCallBuilder withTarget(String target) {
		this.target = target;
		return this;
	}

	public BasicWebTargetCallBuilder withEndpoint(String endpoint) {
		this.endpoint = endpoint;
		return this;
	}

	public BasicWebTargetCallBuilder withAcceptedResponseType(String acceptedResponseType) {
		this.acceptedResponseType = acceptedResponseType;
		return this;
	}

	public BasicWebTargetCall build() {

		Objects.requireNonNull(target, "target not setted");
		Objects.requireNonNull(endpoint, "endpoint not setted");

		WebTarget webTarget = ClientBuilder.newClient().target(target + endpoint);

		return new BasicWebTargetCall() {

			@Override
			public Response call() throws Exception {
				Invocation.Builder request = webTarget.request(acceptedResponseType);
				switch (httpMethod) {
				case GET:
					return request.get();
				default:
					throw new RuntimeException("not supported: " + httpMethod);
				}
			}
		};
	}

	public enum HttpMethod {
		GET, POST
	}
}

//		return new BasicWebTargetCall() {
//			
//			ClientBuilder.newClient().target(target);
//			
//			
//			
//			@Override
//			public Response call() throws Exception {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		}
