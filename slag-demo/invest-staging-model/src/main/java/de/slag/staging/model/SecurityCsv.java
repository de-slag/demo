package de.slag.staging.model;

import java.util.Arrays;
import java.util.List;

public interface SecurityCsv {

	String FETCH_TS = "FETCH_TS";

	String SYMBOL = "SYMBOL";

	String WKN_ISIN = "WKN_ISIN";

	String DATE = "DATE";

	String OPEN = "OPEN";

	String CLOSE = "CLOSE";

	String HIGH = "HIGH";

	String LOW = "LOW";

	String VOLUME = "VOLUME";

	String CURRENCY = "CURRENCY";

	List<String> STRUCTURE = Arrays.asList(FETCH_TS, SYMBOL, WKN_ISIN, DATE, OPEN, CLOSE, HIGH, LOW, VOLUME, CURRENCY);

	String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	
	String FETCH_TS_FORMAT = ISO_8601_DATE_FORMAT;
	
	String DATE_FORMAT = "yyyy-MM-dd";
}
