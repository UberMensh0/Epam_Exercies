package com.epam.rd.autotasks;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;


class NaturalMethodSourceParametrizedTesting {
	Natural natural = new Natural();

	@ParameterizedTest
	@MethodSource("testCases")
	void testIsPrime(int in, boolean expected) {
		assertEquals(expected, natural.isPrime(in));
	}

	static Stream<Arguments> testCases() {
		return Stream.of(
				Arguments.arguments(1, false),
				Arguments.arguments(2, true),
				Arguments.arguments(3, true),
				Arguments.arguments(4, false)
		);
	}
}