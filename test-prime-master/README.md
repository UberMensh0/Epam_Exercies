# Testing Prime Numbers

The goal of this task is to give you some practice working with JUnit 5.

Estimated workload of this exercise is *45 min.*

### Description

Design and code a `isPrime` method in the [Natural](src/main/java/com/epam/rd/autotasks/Natural.java) class.

### Details

Here are some details:

- This method must check whether the number received as an argument is prime.
- If the number is prime, return `true`, otherwise return `false`.
- If the number is negative, throw an `IllegalArgumentException`.

Complete the test classes:

- [NaturalIllegalArgumentTesting](src/main/java/com/epam/rd/autotasks/NaturalIllegalArgumentTesting.java) tests negative number input cases.

- [NaturalCsvParametrizedTesting](src/main/java/com/epam/rd/autotasks/NaturalCsvParametrizedTesting.java) contains a parameterized test that takes arguments from the [primeTest.csv](src/main/resources/primeTest.csv) file. Do not change that file, only implement the method.

- [NaturalMethodSourceParametrizedTesting](src/main/java/com/epam/rd/autotasks/NaturalMethodSourceParametrizedTesting.java) contains a parameterized test that takes arguments from the `testCases` method. Complete the test method and introduce the method that provides the following cases:
    - `1`, `false`
    - `2`, `true`
    - `3`, `true`
    - `4`, `false`

- [NaturalRegularInputTesting](src/main/java/com/epam/rd/autotasks/NaturalRegularInputTesting.java). It is a test class where you can add regular test cases. Consider covering the cases that are not present in other test classes.

To pass the exercise, your tests must correctly detect flaws of some other implementations.
There are special tests in several classes that apply your tests to your implementation and other problematic ("bad") ones:

- [NaturalTestingsTest](src/test/java/com/epam/rd/autotasks/NaturalTestingsTest.java)
- [AlwaysFalseTestingsTest](src/test/java/com/epam/rd/autotasks/AlwaysFalseTestingsTest.java)
- [AlwaysTrueTestingsTest](src/test/java/com/epam/rd/autotasks/AlwaysTrueTestingsTest.java)

Your solution method must pass your tests while other implementations must fail your tests in some cases.

There are several tests that are hidden. They will be applied to your solution once you submit it to Autocode. So, consider the variety of possible cases.

Hint: [Prime number reference](https://en.wikipedia.org/wiki/Prime_number)

### Examples

An example of calling the `isPrime` method is shown below.

```java
Natural natural = new Natural();
String result = IntStream.range(0, 10)
        .mapToObj(i -> i + ": " + natural.isPrime(i))
        .collect(Collectors.joining(", ", "{", "}"));
```

The result of calling the `isPrime` method is shown below.

```
{0: false, 1: false, 2: true, 3: true, 4: false, 5: true, 6: false, 7: true, 8: false, 9: false}
```
