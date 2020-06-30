package de.slag.webgui.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Callable;

import de.slag.basic.model.Token;
import de.slag.webgui.basic.call.RunDefaultCall;
import de.slag.webgui.basic.call.builder.ConfigCallBuilder;
import de.slag.webgui.basic.call.builder.LoginCallBuilder;
import de.slag.webgui.basic.call.builder.RunDefaultCallBuilder;

public class BasicWebConcreateCallBuilderIntegrationTest {

	public static void main(String[] args) {
		new Runner().run();
	}
}

class Runner implements Runnable, BasicWebGuiIntegrationTest {

	private Properties properties = new Properties();

	public Runner() {
		properties.putAll(loadDefaultProperties());
	}

	@Override
	public void run() {
		final Token token = call(new LoginCallBuilder(() -> properties).build());

		final String tokenString = token.getTokenString();
		if (tokenString == null) {
			throw new RuntimeException();
		}

		properties.put(PropertiesSupplier.FRONTEND_CURRENT_TOKEN, tokenString);

		final String call = call(new ConfigCallBuilder(() -> properties).build());
		call.getClass();
		

		final String defaultResult = call(new RunDefaultCallBuilder(()->properties).build());
		defaultResult.getClass();
		

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
