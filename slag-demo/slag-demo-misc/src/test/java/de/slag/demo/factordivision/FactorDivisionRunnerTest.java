package de.slag.demo.factordivision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

class FactorDivisionRunnerTest {

	private static final Function<Integer, List<Integer>> RUNNED_FACTOR_DIVISION_OF = i -> {
		final FactorDivisionRunner factorDivisionRunner = new FactorDivisionRunner(i);
		factorDivisionRunner.run();
		return factorDivisionRunner.getResult();
	};

	@Test
	void test1() {
		assertEquals(Arrays.asList(1), RUNNED_FACTOR_DIVISION_OF.apply(1));
	}

	@Test
	void test2() {
		assertEquals(Arrays.asList(1, 2), RUNNED_FACTOR_DIVISION_OF.apply(2));
	}

	@Test
	void test3() {
		assertEquals(Arrays.asList(1, 3), RUNNED_FACTOR_DIVISION_OF.apply(3));
	}

	@Test
	void test4() {
		assertEquals(Arrays.asList(1, 2, 4), RUNNED_FACTOR_DIVISION_OF.apply(4));
	}

	@Test
	void test5() {
		assertEquals(Arrays.asList(1, 5), RUNNED_FACTOR_DIVISION_OF.apply(5));
	}

	@Test
	void test12() {
		assertEquals(Arrays.asList(1, 2, 3, 4, 6, 12), RUNNED_FACTOR_DIVISION_OF.apply(12));
	}

	@Test
	void test16() {
		assertEquals(Arrays.asList(1, 2, 3, 4, 8, 16), RUNNED_FACTOR_DIVISION_OF.apply(16));
	}

	@Test
	void test64() {
		assertEquals(Arrays.asList(1, 2, 4, 8, 16, 32, 64), RUNNED_FACTOR_DIVISION_OF.apply(64));
	}

	@Test
	void test68() {
		assertEquals(Arrays.asList(1, 2, 4, 17, 34, 68), RUNNED_FACTOR_DIVISION_OF.apply(68));
	}

}
