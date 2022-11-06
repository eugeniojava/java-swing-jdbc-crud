package main.java.com.eugeniojava.dao.impl;

import main.java.com.eugeniojava.dao.GenericDao;
import main.java.com.eugeniojava.model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl extends GenericDao<Customer> {
    @Override
    protected String getTableName() {
        return "customers";
    }

    @Override
    protected String getColumnNamesCommaSeparated() {
        return "name, age, segment";
    }

    @Override
    protected Customer instantiateAndSetAllFields(ResultSet resultSet) {
        Customer customer = new Customer();

        try {
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setAge(resultSet.getInt("age"));
            customer.setSegment(resultSet.getString("segment"));

            return customer;
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    protected PreparedStatement setColumnValuesInOrderExceptId(Customer customer, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.setString(3, customer.getSegment());

            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    protected PreparedStatement setColumnValuesInOrder(Customer customer, PreparedStatement preparedStatement) {
        setColumnValuesInOrderExceptId(customer, preparedStatement);
        try {
            preparedStatement.setInt(4, customer.getId());

            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    protected String getColumnNamesWithInterpolationMarkAssignedEachExceptIdCommaSeparated() {
        return "name = ?, age = ?, segment = ?";
    }
}
