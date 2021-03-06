package de.slag.demo.slagbasicbackend4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import de.slag.basic.backend.api.BasicBackendService;
import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.EntityDto;
import de.slag.basic.model.Token;

@Service
public class BasicBackend4ServiceImpl implements BasicBackendService {

	private static final List<String> TYPES = Arrays.asList("Galaxy", "StarSystem", "Planet");

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

	@Override
	public Collection<String> getDataTypes() {
		return TYPES;
	}
	
	@Override
	public EntityDto getEntity(String type, Long id) {
		final List<String> fixValues = Arrays.asList("type=" + type, "id=" + id);
		final ArrayList<String> values = new ArrayList<>(fixValues);

		switch (type) {
		case "Galaxy":
			values.add("diameterInLightYears=120_000");
			break;
		case "StarSystem":
			values.add("planetCount=8");
			values.add("galaxy=TYPE:Galaxy:4711");
			values.add("planets|18=TYPE:Planet:18");
			values.add("planets|37=TYPE:Planet:37");
			break;
		case "Planet":
			values.add("distanceToStarInAe=1.25");
			values.add("starSystem=TYPE:StarSystem:98");
			break;

		default:
			break;
		}

		EntityDto entityDto = new EntityDto();
		entityDto.setProperties(new ArrayList<String>(values));
		
		
		return entityDto;
	}
}
