# Test Quadratic Equation

This exercise is about getting familiar with parametrized unit testing and JUnit 4.12 approach in particular.

1. Design and code a `solve` method in the [QuadraticEquation](src/main/java/com/epam/rd/autotasks/QuadraticEquation.java) class.
   Here are some details:
- the method must solve a quadratic equation specified by three coefficients given as parameters: `a`,`b`,`c`.
- first parameter `a` might not be zero. If it is the case, throw an `IllegalArgumentException`.
- the `solve` method returns a string in three formats:
    - `x1 x2` (two roots in any order separated by space) if there are two roots,
    - `x1` (just the value of the root) if there is the only root,
    - `no roots` (just a string value "no roots") if there is no root.

2. Complete the test classes:
- [QuadraticEquationNoRootsCasesTesting](src/main/java/com/epam/rd/autotasks/QuadraticEquationNoRootsCasesTesting.java)
- [QuadraticEquationSingleRootCasesTesting](src/main/java/com/epam/rd/autotasks/QuadraticEquationSingleRootCasesTesting.java)
- [QuadraticEquationTwoRootsCasesTesting](src/main/java/com/epam/rd/autotasks/QuadraticEquationTwoRootsCasesTesting.java)
- [QuadraticEquationZeroACasesTesting](src/main/java/com/epam/rd/autotasks/QuadraticEquationZeroACasesTesting.java)

Please consider that these are parameterized test classes. You have to provide at least 4 test cases for each class. You are not allowed to change the signature of their constructors. 

To complete the exercise successfully, your tests must correctly detect flaws in some other implementations. Several classes contain special tests that apply your tests to your implementation and any incorrect implementations: 
- [DefaultQuadraticEquationTestingTest](src/test/java/com/epam/rd/autotasks/DefaultQuadraticEquationTestingTest.java)
- [ParamCarefulIncapableQuadraticEquationTestingTest](src/test/java/com/epam/rd/autotasks/ParamCarefulIncapableQuadraticEquationTestingTest.java)
- [ParamCarefulTwoRootsReversedOrderQuadraticEquationTestingTest](src/test/java/com/epam/rd/autotasks/ParamCarefulTwoRootsReversedOrderQuadraticEquationTestingTest.java)
- [ParamCarelessSingleRootOnlyQuadraticEquationTestingTest](src/test/java/com/epam/rd/autotasks/ParamCarelessSingleRootOnlyQuadraticEquationTestingTest.java)
- [ParamCarelessTwoRootsOnlyQuadraticEquationTestingTest](src/test/java/com/epam/rd/autotasks/ParamCarelessTwoRootsOnlyQuadraticEquationTestingTest.java)

Your solution method must pass your tests, while other implementations must fail them. 

Hint: [Quadratic formula reference](https://en.wikipedia.org/wiki/Quadratic_formula)
