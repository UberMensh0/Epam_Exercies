package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialRegularInputTesting {

    protected Factorial factorial = new Factorial();

    @Test
    void testSmallNumbers() {
        assertEquals("6", factorial.factorial("3"));
        assertEquals("24", factorial.factorial("4"));
    }

    @Test
    void testLargeNumber() {
        assertEquals("3628800", factorial.factorial("10"));
    }
}
