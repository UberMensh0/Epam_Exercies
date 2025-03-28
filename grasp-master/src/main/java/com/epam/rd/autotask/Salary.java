package com.epam.rd.autotask;

public class Salary {
    private int salary;
    private int hoursWorked;
    private int hourlyRate;

    public Salary(int salary, int hoursWorked, int hourlyRate) {
        // migrate salary related attrs to Salary
        this.salary = salary;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    // migrate methods from employee
    public int calculateHourlySalary() {
        return hoursWorked * hourlyRate;
    }
    public int calculateTotalSalary() {
        return salary + calculateHourlySalary();
    }
}