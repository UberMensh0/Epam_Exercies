package com.epam.rd.autotasks;

import static com.epam.rd.autotasks.Utils.assertFailuresAreAssertionErrors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

class AlwaysFalseTestingsTest {

	@Test
	void testIllegalArgumentTesting() {
		TestExecutionSummary summary = Utils.runTesting(AlwaysFalseIllegalArgumentTesting.class);

		assertTrue(summary.getTestsStartedCount() > 0,
				"You must implement at least one test method in NaturalIllegalArgumentTesting");
		assertEquals(0, summary.getTestsSucceededCount(), "All tests must fail for this implementation");

		assertFailuresAreAssertionErrors(summary);
	}

	@Test
	void testCsvParametrizedTesting() {
		TestExecutionSummary summary = Utils.runTesting(AlwaysFalseCsvParametrizedTesting.class);

		assertEquals(6, summary.getTestsStartedCount(),
				"You must implement a parametrized method in NaturalCsvParametrizedTesting");
		assertEquals(2, summary.getTestsSucceededCount(), "2 tests must pass for this implementation");

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
		TestExecutionSummary summary = Utils.runTesting(AlwaysFalseRegularInputTesting.class);

		assertTrue(summary.getTestsStartedCount() > 0,
				"You must implement some test methods in NaturalRegularInputTesting");
		assertTrue(summary.getTestsFailedCount() > 0,
				"You must consider stub implementation cases in NaturalRegularInputTesting");

		assertFailuresAreAssertionErrors(summary);
	}

	private static class AlwaysFalse extends Natural {
		@Override
		public boolean isPrime(int number) {
			return false;
		}
	}

	static class AlwaysFalseIllegalArgumentTesting extends NaturalIllegalArgumentTesting {
		{
			natural = new AlwaysFalse();
		}
	}

	static class AlwaysFalseCsvParametrizedTesting extends NaturalCsvParametrizedTesting {
		{
			natural = new AlwaysFalse();
		}
	}

	static class LazyPrimeMethodSourceParametrizedTesting extends NaturalMethodSourceParametrizedTesting {
		{
			natural = new AlwaysFalse();
		}
	}

	static class AlwaysFalseRegularInputTesting extends NaturalRegularInputTesting {
		{
			natural = new AlwaysFalse();
		}
	}
}