package com.eugeniojava.javaswingjdbccrud.dao.impl;

import com.eugeniojava.javaswingjdbccrud.dao.AbstractDao;
import com.eugeniojava.javaswingjdbccrud.model.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl extends AbstractDao<Employee> {
    @Override
    public String getTableName() {
        return "employees";
    }

    @Override
    public String getColumnNamesCommaSeparated() {
        return "name, age, role";
    }

    @Override
    public Employee instantiateAndSetAllFields(ResultSet resultSet) {
        Employee employee = new Employee();
        try {
            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getString("name"));
            employee.setAge(resultSet.getInt("age"));
            employee.setRole(resultSet.getString("role"));
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PreparedStatement setColumnValuesInOrderExceptId(Employee employee, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.setString(3, employee.getRole());
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PreparedStatement setColumnValuesInOrder(Employee employee, PreparedStatement preparedStatement) {
        setColumnValuesInOrderExceptId(employee, preparedStatement);
        try {
            preparedStatement.setInt(4, employee.getId());
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getColumnNamesWithInterpolationMarkAssignedEachExceptIdCommaSeparated() {
        return "name = ?, age = ?, role = ?";
    }
}
