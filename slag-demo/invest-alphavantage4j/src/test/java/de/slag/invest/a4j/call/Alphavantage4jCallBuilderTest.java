package de.slag.invest.a4j.call;

import org.junit.jupiter.api.Test;

import de.slag.invest.a4j.call.Alphavantage4jCall;
import de.slag.invest.a4j.call.Alphavantage4jCallBuilder;

class Alphavantage4jCallBuilderTest {

	@Test
	void integrationTest() throws Exception {
		Alphavantage4jCallBuilder alphavantage4jCallBuilder = new Alphavantage4jCallBuilder();
		alphavantage4jCallBuilder.withApiKey("50M3AP1K3Y");
		alphavantage4jCallBuilder.withSymbol("MSFT");
		Alphavantage4jCall a4jCall = alphavantage4jCallBuilder.build();
		Object call = a4jCall.call();
	}

}
