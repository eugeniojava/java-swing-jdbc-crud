package com.eugeniojava.javaswingjdbccrud.dao.impl;

import com.eugeniojava.javaswingjdbccrud.dao.AbstractDao;
import com.eugeniojava.javaswingjdbccrud.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDaoImpl extends AbstractDao<Product> {
    @Override
    public String getTableName() {
        return "products";
    }

    @Override
    public String getColumnNamesCommaSeparated() {
        return "name, price, quantity";
    }

    @Override
    public Product instantiateAndSetAllFields(ResultSet resultSet) {
        Product customer = new Product();
        try {
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setPrice(resultSet.getDouble("price"));
            customer.setQuantity(resultSet.getInt("quantity"));
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PreparedStatement setColumnValuesInOrderExceptId(Product product, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PreparedStatement setColumnValuesInOrder(Product product, PreparedStatement preparedStatement) {
        setColumnValuesInOrderExceptId(product, preparedStatement);
        try {
            preparedStatement.setInt(4, product.getId());
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getColumnNamesWithInterpolationMarkAssignedEachExceptIdCommaSeparated() {
        return "name = ?, price = ?, quantity = ?";
    }
}
