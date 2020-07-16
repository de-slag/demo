package de.slag.basic.backend.api;

import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.Token;

public interface BasicBackendService {

	Token getLogin(String username, String password);

	BackendState putConfigProperty(String token, ConfigProperty configProperty);

	String runDefault(String token);
	
	public enum BackendState {
		OK,
		
		NOT_OK
	}

}
