package de.slag.demo;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class TimeRecordingApp {

	public static void main(String[] args) throws IOException, ParseException {
		String fileName = args[0];
		FileReader fileReader = new FileReader(fileName);
		CSVFormat withDelimiter = CSVFormat.DEFAULT.withDelimiter(';').withHeader("DATE", "ACTIVITY", "PT");
		CSVParser parse = withDelimiter.parse(fileReader);
		boolean skippedHeader = false;
		for (CSVRecord csvRecord : parse) {
			if (!skippedHeader) {
				skippedHeader = true;
				continue;
			}

			LocalDate date = getDate(csvRecord);
			double pt = getPt(csvRecord);
			String string = csvRecord.get("ACTIVITY");
			
			log(csvRecord.toString());
			
		}
	}

	private static double getPt(CSVRecord csvRecord) {
		return Double.valueOf(csvRecord.get("PT").replace(",", "."));
	}

	private static LocalDate getDate(CSVRecord rec) throws ParseException {
		return Instant.ofEpochMilli(new SimpleDateFormat("yyyy-MM-dd").parse(rec.get("DATE")).getTime())
				.atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private static void log(Object o) {
		System.out.println(o);
	}
}