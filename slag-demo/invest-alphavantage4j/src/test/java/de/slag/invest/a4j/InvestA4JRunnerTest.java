package de.slag.invest.a4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvestA4JRunnerTest {

	private static final String API_KEY = "SEOG69AIA6X9PGR2";

	private static final List<String> SYMBOLS = Arrays.asList("MSFT", "IBM", "GOOGL","AAPL","ADBE","AMZN");

	private String symbolWknIsinMapping;

	@BeforeEach
	public void setUp() {
		final InputStream is = getClass().getClassLoader().getResourceAsStream("symbol-wkn-isin-mapping.properties");

		final List<String> collect = new BufferedReader(new InputStreamReader(is)).lines().parallel()
				.collect(Collectors.toList());

		symbolWknIsinMapping = String.join(";", collect).replace("=", ":");
	}

	@Test
	void integrationTest() {
		InvestA4JRunner investA4JRunner = new InvestA4JRunnerBuilder().withApiKey(API_KEY).withSymbols(SYMBOLS)
				.withOutputFolder(System.getProperty("java.io.tmpdir")).withSymbolWknIsinMapping(symbolWknIsinMapping)
				.build();
		investA4JRunner.run();
	}

}
