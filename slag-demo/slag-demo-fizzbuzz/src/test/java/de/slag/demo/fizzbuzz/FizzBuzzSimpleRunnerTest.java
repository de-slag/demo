package de.slag.demo.fizzbuzz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FizzBuzzSimpleRunnerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		List<String> messages = new ArrayList<>();
		final FizzBuzzSimpleRunner fizzBuzzSimpleRunner = new FizzBuzzSimpleRunner(s -> messages.add(s));
		fizzBuzzSimpleRunner.run();
		assertTrue(Integer.valueOf(100).equals(messages.size()));
		assertEquals("1", messages.get(0));
		assertEquals("2", messages.get(1));
		assertEquals("fizz", messages.get(2));
		assertEquals("4", messages.get(3));
		assertEquals("buzz", messages.get(4));
		
		assertEquals("8", messages.get(7));		
		assertEquals("fizz", messages.get(11));
		assertEquals("fizzbuzz", messages.get(14));
		assertEquals("16", messages.get(15));
	}

}
