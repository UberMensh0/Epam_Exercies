package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class QuadraticEquationNoRootsCasesTesting {

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    public static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(-563, 0, -5, "no roots"),
                Arguments.of(2, 10, 30, "no roots"),
                Arguments.of(-0.5, 1, -50, "no roots"),
                Arguments.of(1, 11, 111, "no roots"),
                Arguments.of(2, 2, 2, "no roots")
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testNoRootsCases(double a, double b, double c, String expected) {
        assertEquals(expected, quadraticEquation.solve(a, b, c));
    }
}
