package de.slag.invest.a4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.patriques.output.timeseries.data.StockData;

import de.slag.invest.a4j.call.Alphavantage4jCall;
import de.slag.invest.a4j.call.Alphavantage4jCallBuilder;
import de.slag.staging.model.SecurityCsv;

public class InvestA4JRunner implements Runnable {

	private String apiKey;

	private List<String> symbols = new ArrayList<String>();

	private Map<String, String> symbolWknIsinMap = new HashMap<>();

	private String outputFolder;

	private long sleepTimeBetween;

	InvestA4JRunner(String apiKey, List<String> symbols, String outputFolder, Map<String, String> symbolWknIsinMap) {
		this(apiKey, symbols, outputFolder, symbolWknIsinMap, 0);
	}

	InvestA4JRunner(String apiKey, List<String> symbols, String outputFolder, Map<String, String> symbolWknIsinMap,
			long sleepTimeBetween) {
		super();
		this.apiKey = apiKey;
		this.symbols.addAll(symbols);
		this.outputFolder = outputFolder;
		this.symbolWknIsinMap.putAll(symbolWknIsinMap);
		this.sleepTimeBetween = sleepTimeBetween;
	}

	@Override
	public void run() {
		final Date fetchTimestamp = new Date();
		final List<List<String>> csvLines = new ArrayList<>();
		for (String symbol : symbols) {
			Alphavantage4jCall a4jCall = new Alphavantage4jCallBuilder().withApiKey(apiKey).withSymbol(symbol).build();
			List<StockData> stockData;
			try {
				stockData = a4jCall.call();
			} catch (Exception e) {
				throw new RuntimeException("at symbol: " + symbol, e);
			}

			stockData.forEach(data -> {
				final List<String> csvLine = new CsvLineBuilder().withSymbol(symbol).withFetchTimestamp(fetchTimestamp)
						.withWknIsin(symbolWknIsinMap.get(symbol)).withStockData(data).build();
				csvLines.add(csvLine);
			});
			sleepFor(sleepTimeBetween);
		}
		csvLines.forEach(System.out::println);
		writeOut(csvLines);
	}

	private void writeOut(List<List<String>> csvLines) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.join(";", SecurityCsv.STRUCTURE));
		sb.append("\n");
		csvLines.forEach(line -> {
			sb.append(String.join(";", line));
			sb.append("\n");
		});

		try {
			Path path = Paths.get(outputFolder + "/securities-" + System.currentTimeMillis() + ".csv");
			byte[] strToBytes = sb.toString().getBytes();

			Files.write(path, strToBytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void sleepFor(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
