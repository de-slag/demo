package de.slag.basic.backend.api;

import javax.ws.rs.core.Response;

import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.Token;

public interface BasicBackendController {

	/**
	 * Implement as http-get
	 * 
	 * @param username as request parameter
	 * @param password as request parameter
	 * @return {@link Token}
	 */
	Token getLogin(String username, String password);

	/**
	 * implement as http-get
	 * 
	 * @param token as request parameter
	 * @return messages as string
	 */
	String runDefault(String token);

	/**
	 * Puts a single config Implement as http-put
	 * 
	 * @param token          as request parameter
	 * @param configProperty as request body
	 * @return
	 */
	Response putConfigProperty(String token, ConfigProperty configProperty);

}
