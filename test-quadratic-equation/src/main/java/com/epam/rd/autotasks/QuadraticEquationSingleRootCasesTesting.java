package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class QuadraticEquationSingleRootCasesTesting {

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    public static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(1, -2, 1, "1.0"),
                Arguments.of(1, 0, 0, "0.0"),
                Arguments.of(8, 0, 0, "0.0"),
                Arguments.of(-6, 9, 3, "-1.5"),
                Arguments.of(12, 36, -6, "-3.0")
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testSingleRootCases(double a, double b, double c, String expected) {
        assertEquals(expected, quadraticEquation.solve(a, b, c));
    }
}
