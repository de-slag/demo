package de.slag.demo.persist;

import org.springframework.stereotype.Repository;

import de.slag.common.model.EntityBean;

@Repository
public class TestEntity42DaoImpl extends PersistAndLoadDemoDao<TestEntity42> implements TestEntity42Dao {

	@Override
	protected Class<? extends EntityBean> getType() {
		return TestEntity42.class;
	}

}
