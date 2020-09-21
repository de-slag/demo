package de.slag.demo.crypo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AesEncryptionFunctionTest {

	@Test
	void test() {
		AesEncryptionFunction aesEncryptionFunction = new AesEncryptionFunction("abc123xyz");
		String apply = aesEncryptionFunction.apply("string to encrypt");
		assertEquals("C+tUsDT6RVhcQoBzKbjfQh4vKdI5qb6AID90J5nSdDA=", apply);
	}

}
