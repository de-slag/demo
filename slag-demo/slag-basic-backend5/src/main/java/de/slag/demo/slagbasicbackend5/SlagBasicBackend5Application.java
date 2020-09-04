package de.slag.demo.slagbasicbackend5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "de.slag" })
public class SlagBasicBackend5Application {

	public static void main(String[] args) {
		SpringApplication.run(SlagBasicBackend5Application.class, args);
	}

}
