package com.epam.rd.autotasks;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class DbManager {
	private DbManager() {
		throw new UnsupportedOperationException();
	}

	// init procedures, configure and execute
	public static int callCountDepartments(Connection connection) throws SQLException {
		try (CallableStatement c = connection.prepareCall("{call COUNT_DEPARTMENTS(?)}")) {
			c.registerOutParameter(1, Types.INTEGER);
			c.execute();

			return c.getInt(1);
		}
	}

	public static int callCountEmployees(Connection connection) throws SQLException {
		try (CallableStatement c = connection.prepareCall("{call COUNT_EMPLOYEES(?)}")) {
			c.registerOutParameter(1, Types.INTEGER);
			c.execute();

			return c.getInt(1);
		}
	}

	public static int callCountEmployeesByDepartmentId(Connection connection, int departmentId) throws SQLException {
		try (CallableStatement c = connection.prepareCall("{call COUNT_EMPLOYEES_BY_DEPARTMENT_ID(?, ?)}")) {
			c.setInt(1, departmentId);
			c.registerOutParameter(2, Types.INTEGER);
			c.execute();

			return c.getInt(2);
		}
	}
}