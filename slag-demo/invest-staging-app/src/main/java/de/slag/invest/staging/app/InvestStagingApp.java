package de.slag.invest.staging.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.slag.invest.a4j.InvestA4JRunner;
import de.slag.invest.a4j.InvestA4JRunnerBuilder;

public class InvestStagingApp implements Runnable {
	
	private static final Log LOG = LogFactory.getLog(InvestStagingApp.class);

	private Properties properties;

	InvestStagingApp(String propertiesFilename) throws IOException {
		if (!new File(propertiesFilename).exists()) {
			throw new RuntimeException("file does not exists: " + propertiesFilename);
		}
		properties = new Properties();
		properties.load(new FileInputStream(propertiesFilename));
	}

	@Override
	public void run() {
		LOG.info(properties.getProperty(InvestStagingAppProperties.SYMBOL_WKN_ISIN_MAPPING));;
		
		List<String> symbols = Arrays.asList(properties.getProperty(InvestStagingAppProperties.SYMBOLS).split(";"));

		InvestA4JRunner investA4JRunner = new InvestA4JRunnerBuilder()
				.withApiKey(properties.getProperty(InvestStagingAppProperties.API_KEY))
				.withOutputFolder(properties.getProperty(InvestStagingAppProperties.OUTPUT_FOLDER))
				.withSymbolWknIsinMapping(properties.getProperty(InvestStagingAppProperties.SYMBOL_WKN_ISIN_MAPPING))
				.withSymbols(symbols).build();
		investA4JRunner.run();
	}

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			throw new RuntimeException("1st arg has to be the properties file");
		}
		InvestStagingApp investStagingApp = new InvestStagingApp(args[0]);
		investStagingApp.run();

		System.out.println("all done.");
	}

}
