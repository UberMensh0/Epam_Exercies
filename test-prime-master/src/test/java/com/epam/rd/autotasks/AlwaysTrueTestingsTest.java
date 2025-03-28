package com.epam.rd.autotasks;

import static com.epam.rd.autotasks.Utils.assertFailuresAreAssertionErrors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

class AlwaysTrueTestingsTest {

	@Test
	void testIllegalArgumentTesting() {
		TestExecutionSummary summary = Utils.runTesting(AlwaysTrueIllegalArgumentTesting.class);

		assertTrue(summary.getTestsStartedCount() > 0,
				"You must implement at least one test method in NaturalIllegalArgumentTesting");
		assertEquals(0, summary.getTestsSucceededCount(), "All tests must fail for this implementation");

		assertFailuresAreAssertionErrors(summary);
	}

	@Test
	void testCsvParametrizedTesting() {
		TestExecutionSummary summary = Utils.runTesting(AlwaysTrueCsvParametrizedTesting.class);

		assertEquals(6, summary.getTestsStartedCount(),
				"You must implement a parametrized method in NaturalCsvParametrizedTesting");
		assertEquals(4, summary.getTestsSucceededCount(), "4 tests must pass for this implementation");

		assertFailuresAreAssertionErrors(summary);
	}

	@Test
	void testMethodSourceParametrizedTesting() {
		TestExecutionSummary summary = Utils.runTesting(LazyPrimeMethodSourceParametrizedTesting.class);

		assertEquals(4, summary.getTestsStartedCount(),
				"You must implement a parametrized method in NaturalMethodSourceParametrizedTesting");
		assertEquals(2, summary.getTestsSucceededCount(), "2 tests must pass for this implementation");

		assertFailuresAreAssertionErrors(summary);
	}

	@Test
	void testRegularInputTesting() {
		TestExecutionSummary summary = Utils.runTesting(AlwaysTrueRegularInputTesting.class);

		assertTrue(summary.getTestsStartedCount() > 0,
				"You must implement some test methods in NaturalRegularInputTesting");
		assertTrue(summary.getTestsFailedCount() > 0,
				"You must consider stub implementation cases in NaturalRegularInputTesting");

		assertFailuresAreAssertionErrors(summary);
	}

	private static class AlwaysTrue extends Natural {
		@Override
		public boolean isPrime(int number) {
			return true;
		}
	}

	static class AlwaysTrueIllegalArgumentTesting extends NaturalIllegalArgumentTesting {
		{
			natural = new AlwaysTrue();
		}
	}

	static class AlwaysTrueCsvParametrizedTesting extends NaturalCsvParametrizedTesting {
		{
			natural = new AlwaysTrue();
		}
	}

	static class LazyPrimeMethodSourceParametrizedTesting extends NaturalMethodSourceParametrizedTesting {
		{
			natural = new AlwaysTrue();
		}
	}

	static class AlwaysTrueRegularInputTesting extends NaturalRegularInputTesting {
		{
			natural = new AlwaysTrue();
		}
	}
}