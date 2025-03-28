package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCsvParametrizedTesting {

    protected Factorial factorial = new Factorial();

    @ParameterizedTest
    @CsvFileSource(resources = "/csvCases.csv", numLinesToSkip = 1)
    void testFactorial(String input, String expected) {
        String actual = factorial.factorial(input);
        assertEquals(expected, actual);
    }
}
