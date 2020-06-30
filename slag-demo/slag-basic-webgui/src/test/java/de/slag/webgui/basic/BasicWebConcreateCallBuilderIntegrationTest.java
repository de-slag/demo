package de.slag.webgui.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Callable;

import de.slag.basic.model.Token;
import de.slag.webgui.basic.call.builder.LoginCallBuilder;

public class BasicWebConcreateCallBuilderIntegrationTest {

	public static void main(String[] args) {
		new Runner().run();
	}
}

class Runner implements Runnable {

	private Properties properties = new Properties();

	public Runner() {
		final InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("default.properties");
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			throw new IntegrationTestException(e);
		}

	}

	@Override
	public void run() {
		final Token token = call(new LoginCallBuilder(() -> properties).build());
		token.getClass();
		
		// TODO: implement poperty setting call
		
		// TODO: implement run default call

		
	}
	
	private <T> T call(Callable<T> callable) {
		try {
			return callable.call();
		} catch (Exception e) {
			throw new IntegrationTestException(e);
		}
	}

	class IntegrationTestException extends RuntimeException {

		private static final long serialVersionUID = 1L;
		
		public IntegrationTestException(Throwable e) {
			super(e);
		}


	}
}
