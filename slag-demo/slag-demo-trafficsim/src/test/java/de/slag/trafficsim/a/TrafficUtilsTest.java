package de.slag.trafficsim.a;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrafficUtilsTest {

	@Test
	void test() {
		int inKmh = TrafficUtils.inKmh(5);
		assertEquals(18, inKmh);
	}
	
	@Test
	void test2() {
		assertEquals(5, TrafficUtils.inMeternPerSecond(18));
	}

}
