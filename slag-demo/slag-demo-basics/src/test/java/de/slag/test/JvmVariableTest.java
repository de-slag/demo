package de.slag.test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JvmVariableTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		assumeTrue(Boolean.getBoolean("advanced-tests"));
		System.out.println("OK");
	}

}
