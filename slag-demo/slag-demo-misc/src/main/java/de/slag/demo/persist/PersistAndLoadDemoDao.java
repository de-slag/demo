package de.slag.demo.persist;

import java.util.Collection;
import java.util.stream.Collectors;

import de.slag.common.context.SubClassesUtils;
import de.slag.common.core.dao.AbstractDao;
import de.slag.common.model.EntityBean;

@SuppressWarnings("unchecked")
public abstract class PersistAndLoadDemoDao<E extends EntityBean> extends AbstractDao<E> {
	static {
		registeredEntitiesSupplier = () -> {
			Collection<Class<?>> findAllSubclassesOf = SubClassesUtils.findAllSubclassesOf(EntityBean.class);
			return findAllSubclassesOf.stream().map(type -> {
				return (Class<EntityBean>) type;
			}).collect(Collectors.toList());
		};
	}
}
