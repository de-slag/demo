package de.slag.demo.persist;

import java.util.Objects;
import java.util.Optional;

import de.slag.common.context.SlagContext;

public class PersistAndLoadDemoApp implements Runnable {

	public static void main(String[] args) {

		PersistAndLoadDemoApp persistAndLoadDemoApp = new PersistAndLoadDemoApp();
		persistAndLoadDemoApp.init();
		persistAndLoadDemoApp.run();

		out("all done");
		System.exit(0);
	}

	private TestEntity42Dao dao;

	public void init() {
		SlagContext.init();
		dao = SlagContext.getBean(TestEntity42Dao.class);
		Objects.requireNonNull(dao, "dao is NULL");
	}

	@Override
	public void run() {
		long createAndSave = createAndSave();
		Optional<TestEntity42> load = dao.load(createAndSave);
		TestEntity42 e = load.get();
		out(String.format("%s: '%s'", e.getId(),e.getName()));
	}

	private long createAndSave() {
		TestEntity42 testEntity42 = new TestEntity42();
		testEntity42.setName("los namos");
		dao.save(testEntity42);
		return testEntity42.getId();
	}

	private static void out(Object o) {
		System.out.println(o);
	}

}
