package de.slag.invest.a4j;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.Builder;
import org.patriques.output.timeseries.data.StockData;

import de.slag.staging.model.SecurityCsv;

public class CsvLineBuilder implements Builder<List<String>> {

	private StockData stockData;

	private String symbol;

	private Date fetchTimestamp;

	private String wknIsin;

	public CsvLineBuilder withSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	public CsvLineBuilder withWknIsin(String wknIsin) {
		this.wknIsin = wknIsin;
		return this;
	}

	public CsvLineBuilder withFetchTimestamp(Date fetchTimestamp) {
		this.fetchTimestamp = fetchTimestamp;
		return this;
	}

	public CsvLineBuilder withStockData(StockData stockData) {
		this.stockData = stockData;
		return this;
	}

	@Override
	public List<String> build() {
		Objects.requireNonNull(symbol, "symbol not setted");
		Objects.requireNonNull(fetchTimestamp, "fetchTimestamp not setted");
		Objects.requireNonNull(wknIsin, "wknIsin not setted");
		Objects.requireNonNull(stockData, "stockData not setted");

		List<String> result = new ArrayList<String>();

		result.add(new SimpleDateFormat(SecurityCsv.FETCH_TS_FORMAT).format(fetchTimestamp));
		result.add(symbol);
		result.add(wknIsin);
		result.add(new SimpleDateFormat(SecurityCsv.DATE_FORMAT).format(of(stockData.getDateTime())));
		result.add(Double.toString(stockData.getOpen()));
		result.add(Double.toString(stockData.getClose()));
		result.add(Double.toString(stockData.getHigh()));
		result.add(Double.toString(stockData.getLow()));
		result.add(Long.toString(stockData.getVolume()));
		result.add("(DEFAULT)");
		return result;
	}

	private Date of(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		long epoch = localDateTime.atZone(zoneId).toEpochSecond();

		return new Date(epoch * 1000);
	}

}
