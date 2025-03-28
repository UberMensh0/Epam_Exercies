package com.epam.rd.autotasks;

import java.util.Arrays;

public class QuadraticEquation {
    public String solve(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("Coefficient 'a' cannot be zero.");
        }

        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            return "no roots";
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            return String.valueOf(root);
        } else {
            double root1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double[] roots = {root1, root2};
            Arrays.sort(roots); // Ensure correct order
            return roots[0] + " " + roots[1];
        }
    }
}
