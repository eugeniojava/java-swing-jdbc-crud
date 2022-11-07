package com.eugeniojava.javaswingjdbccrud.dao.impl;

import com.eugeniojava.javaswingjdbccrud.dao.AbstractDao;
import com.eugeniojava.javaswingjdbccrud.model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl extends AbstractDao<Customer> {
    @Override
    public String getTableName() {
        return "customers";
    }

    @Override
    public String getColumnNamesCommaSeparated() {
        return "name, age, segment";
    }

    @Override
    public Customer instantiateAndSetAllFields(ResultSet resultSet) {
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
    public PreparedStatement setColumnValuesInOrderExceptId(Customer customer, PreparedStatement preparedStatement) {
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
    public PreparedStatement setColumnValuesInOrder(Customer customer, PreparedStatement preparedStatement) {
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
    public String getColumnNamesWithInterpolationMarkAssignedEachExceptIdCommaSeparated() {
        return "name = ?, age = ?, segment = ?";
    }
}
