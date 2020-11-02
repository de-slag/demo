package de.slag.invest.a4j;

import java.util.Collections;

import org.junit.jupiter.api.Test;

class InvestA4JRunnerTest {

	@Test
	void integrationTest() {
		InvestA4JRunner investA4JRunner = new InvestA4JRunnerBuilder()
			.withApiKey("50M3AP1K3Y")
			.withSymbols(Collections.singletonList("MSFT"))
			.withOutputFolder(System.getProperty("java.io.tmpdir"))
			.withSymbolWknIsinMapping("MSFT=846901;DAX=846900")
			.build();
		investA4JRunner.run();
	}

}
