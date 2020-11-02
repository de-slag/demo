package de.slag.invest.a4j;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class InvestA4JRunnerTest {
	
	private static final String API_KEY = "SEOG69AIA6X9PGR2";

	private static final String SYMBOL_WKN_ISIN_MAPPING = "MSFT:US5949181045;IBM:US4592001014";
	
	private static final List<String> SYMBOLS = Arrays.asList("MSFT","IBM");
	
	@Test
	void integrationTest() {
		InvestA4JRunner investA4JRunner = new InvestA4JRunnerBuilder()
			.withApiKey(API_KEY)
			.withSymbols(SYMBOLS)
			.withOutputFolder(System.getProperty("java.io.tmpdir"))
			.withSymbolWknIsinMapping(SYMBOL_WKN_ISIN_MAPPING)
			.build();
		investA4JRunner.run();
	}

}
