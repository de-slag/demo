package de.slag.basic.backend.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.EntityDto;
import de.slag.basic.model.Token;

public interface BasicBackendService {

	Token getLogin(String username, String password);

	BackendState putConfigProperty(String token, ConfigProperty configProperty);

	String runDefault(String token);

	public enum BackendState {
		OK,

		NOT_OK
	}

	default Collection<String> getDataTypes() {
		return Collections.emptyList();
	}

	default EntityDto getEntity(String type, Long id) {
		final EntityDto entityDto = new EntityDto();
		entityDto.setType(type);
		entityDto.setId(id);
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

		entityDto.setProperties(new ArrayList<String>(values));
		return entityDto;
	}

}
