package com.epam.rd.autotask;

public class Demo {

	private static final System.Logger LOGGER = System.getLogger(Demo.class.getName());

	public static void main(String[] args) {

		{
			// replace dummy class with implemented method
			Person alice = new Person("Alice", 20);
			boolean isAliceEligible = alice.isEligibleForDrivingLicense();
			LOGGER.log(System.Logger.Level.INFO, "Is Alice eligible for driving license? " + isAliceEligible);

			Person john = new Person("John", 17);
			boolean isJohnEligible = john.isEligibleForDrivingLicense();
			LOGGER.log(System.Logger.Level.INFO, "Is John eligible for driving license? " + isJohnEligible);
		}

		{
			// divide empoloyee as two independent-job classes Employee & Salary
			Employee employee = new Employee("John Doe");
			Salary salary = new Salary(3000, 10, 20);

			String[] nameParts = employee.splitName();    // get tuple of fname lname
			LOGGER.log(System.Logger.Level.INFO, "First Name: " + nameParts[0]);
			LOGGER.log(System.Logger.Level.INFO, "Last Name: " + nameParts[1]);

			LOGGER.log(System.Logger.Level.INFO, "Total Salary:  " + salary.calculateTotalSalary());
			LOGGER.log(System.Logger.Level.INFO, "Hourly Salary: " + salary.calculateHourlySalary());
		}
	}
}