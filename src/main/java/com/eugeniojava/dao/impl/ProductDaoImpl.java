package main.java.com.eugeniojava.dao.impl;

import main.java.com.eugeniojava.dao.GenericDao;
import main.java.com.eugeniojava.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDaoImpl extends GenericDao<Product> {
    @Override
    protected String getTableName() {
        return "products";
    }

    @Override
    protected String getColumnNamesCommaSeparated() {
        return "name, price, quantity";
    }

    @Override
    protected Product instantiateAndSetAllFields(ResultSet resultSet) {
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
    protected PreparedStatement setColumnValuesInOrderExceptId(Product product, PreparedStatement preparedStatement) {
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
    protected PreparedStatement setColumnValuesInOrder(Product product, PreparedStatement preparedStatement) {
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
    protected String getColumnNamesWithInterpolationMarkAssignedEachExceptIdCommaSeparated() {
        return "name = ?, price = ?, quantity = ?";
    }
}
