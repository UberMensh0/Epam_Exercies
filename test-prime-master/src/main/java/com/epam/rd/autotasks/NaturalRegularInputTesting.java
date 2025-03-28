package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NaturalRegularInputTesting {
	Natural natural = new Natural();

	@Test
	void test0() {
		assertFalse(natural.isPrime(0));
	}

	@Test
	void test15() {
		assertFalse(natural.isPrime(15));
	}

	@Test
	void test7() {
		assertTrue(natural.isPrime(7));
	}
}
