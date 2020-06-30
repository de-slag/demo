package de.slag.webgui.basic;

import java.util.Objects;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BasicWebTargetCallBuilder {

	private String acceptedResponseType = MediaType.TEXT_PLAIN;
	
	private String requestMediaType = MediaType.APPLICATION_JSON;

	private HttpMethod httpMethod = HttpMethod.GET;

	private String target;

	private String endpoint;
	
	private Object entity;
	
	private String token;

	public BasicWebTargetCallBuilder withToken(String token) {
		this.token = token;
		return this;
	}
	
	public BasicWebTargetCallBuilder withEntity(Object entity) {
		this.entity = entity;
		return this;
	}
	
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
				switch (httpMethod) {
				case GET:
					return get();
				case PUT:
					return put();
					
					
				default:
					throw new RuntimeException("not supported: " + httpMethod);
				}
			}

			private Response put() {
				WebTarget queryParam = webTarget.queryParam("token", token);				
				Invocation.Builder request = queryParam.request(acceptedResponseType);
				return request.put(Entity.entity(entity, requestMediaType));
			}

			private Response get() {
				return webTarget.request(acceptedResponseType).get();
			}
		};
	}

	public enum HttpMethod {
		GET,
		
		PUT
	}
}
