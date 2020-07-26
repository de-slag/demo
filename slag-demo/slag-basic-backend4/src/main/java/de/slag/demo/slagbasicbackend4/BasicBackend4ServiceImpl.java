package de.slag.demo.slagbasicbackend4;

import org.springframework.stereotype.Service;

import de.slag.basic.backend.api.BasicBackendService;
import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.Token;

@Service
public class BasicBackend4ServiceImpl implements BasicBackendService {

	@Override
	public Token getLogin(String username, String password) {
		Token token = new Token();
		token.setTokenString(String.valueOf(System.currentTimeMillis()));
		return token;
	}

	@Override
	public BackendState putConfigProperty(String token, ConfigProperty configProperty) {
		return BackendState.OK;
	}

	@Override
	public String runDefault(String token) {
		return this.getClass().getName() + ", OK!";
	}

}
