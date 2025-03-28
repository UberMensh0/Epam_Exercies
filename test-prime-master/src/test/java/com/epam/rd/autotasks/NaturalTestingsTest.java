package com.epam.rd.autotasks;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

class NaturalTestingsTest {

	@Test
	void testIllegalArgumentTesting() {
		TestExecutionSummary summary = Utils.runTesting(NaturalIllegalArgumentTesting.class);

		assertTrue(summary.getTestsStartedCount() > 0,
				"You must implement at least one test method in NaturalIllegalArgumentTesting");
		assertEquals(summary.getTestsStartedCount(), summary.getTestsSucceededCount(),
				"All tests must pass for this implementation");
	}

	@Test
	void testCsvParametrizedTesting() throws IOException {
		final Path csvPath = Paths.get("src/main/resources/primeTest.csv");
		assertTrue(Files.exists(csvPath), "You must keep src/main/resources/primeTest.csv unchanged");
		assertEquals("2,true;" + "3,true;" + "4,false;" + "5,true;" + "6,false;" + "7,true",
				Files.lines(csvPath).collect(joining(";")), "You must keep src/main/resources/primeTest.csv unchanged");

		TestExecutionSummary summary = Utils.runTesting(NaturalCsvParametrizedTesting.class);

		assertEquals(6, summary.getTestsStartedCount(),
				"You must implement a parametrized method in NaturalCsvParametrizedTesting");
		assertEquals(summary.getTestsStartedCount(), summary.getTestsSucceededCount(),
				"All tests must pass for this implementation");
	}

	@Test
	void testMethodSourceParametrizedTesting() {
		final String testCases = NaturalMethodSourceParametrizedTesting.testCases()
				.map(arg -> Arrays.stream(arg.get()).map(Object::toString).collect(joining(","))).collect(joining(";"));
		assertEquals("1,false;2,true;3,true;4,false", testCases,
				"You must specify (1,false; 2,true; 3,true; 4,false) cases in NaturalMethodSourceParametrizedTesting.testCases() method");

		TestExecutionSummary summary = Utils.runTesting(NaturalMethodSourceParametrizedTesting.class);

		assertEquals(4, summary.getTestsStartedCount(),
				"You must implement a parametrized method in NaturalMethodSourceParametrizedTesting");
		assertEquals(summary.getTestsStartedCount(), summary.getTestsSucceededCount(),
				"All tests must pass for this implementation");
	}

	@Test
	void testRegularInputTesting() {
		TestExecutionSummary summary = Utils.runTesting(NaturalMethodSourceParametrizedTesting.class);

		assertTrue(summary.getTestsStartedCount() > 0,
				"You must implement some test methods in NaturalRegularInputTesting");
		assertEquals(summary.getTestsStartedCount(), summary.getTestsSucceededCount(),
				"All tests must pass for this implementation");
	}
}