package main.java.com.eugeniojava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.eugeniojava.util.DatabaseConnection;

import static java.util.Collections.emptyList;

public abstract class GenericDao<T> {

    protected static DatabaseConnection databaseConnection;

    static {
        databaseConnection = DatabaseConnection.getInstance();
        try {
            databaseConnection.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getTableName();

    protected abstract String getColumnNamesCommaSeparated();

    protected abstract T instantiateAndSetAllFields(ResultSet resultSet);

    protected abstract PreparedStatement setColumnValuesInOrderExceptId(T t, PreparedStatement preparedStatement);

    protected abstract PreparedStatement setColumnValuesInOrder(T t, PreparedStatement preparedStatement);

    protected abstract String getColumnNamesWithInterpolationMarkAssignedEachExceptIdCommaSeparated();

    protected T getObjectIfFound(ResultSet resultSet) {
        T t = null;
        boolean check = false;

        try {
            while (resultSet.next()) {
                check = true;
                t = instantiateAndSetAllFields(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return check ? t : null;
    }

    protected PreparedStatement getPreparedStatement(String query) throws SQLException {
        return databaseConnection.getConnection().prepareStatement(query);
    }

    public List<T> findAll() {
        String query = "SELECT * FROM " + getTableName() + " ORDER BY id";
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(instantiateAndSetAllFields(resultSet));
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();

            return emptyList();
        }
    }

    public List<T> findByName(String name) {
        String query = "SELECT * FROM " + getTableName() + " WHERE name LIKE ? ORDER BY id";
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(instantiateAndSetAllFields(resultSet));
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();

            return emptyList();
        }
    }

    public T findLast() {
        String query = "SELECT * FROM " + getTableName() + " WHERE id = (SELECT MAX(id) FROM " + getTableName() + ")";

        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            return getObjectIfFound(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }

    public int create(T t) {
        String query = "INSERT INTO " + getTableName() + "(" + getColumnNamesCommaSeparated() + ") VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            PreparedStatement returnedPreparedStatement = setColumnValuesInOrderExceptId(t, preparedStatement);

            return returnedPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return -1;
        }
    }

    public int update(T t) {
        String query = "UPDATE " + getTableName() + " SET "
                + getColumnNamesWithInterpolationMarkAssignedEachExceptIdCommaSeparated()
                + " WHERE id = ?";

        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            PreparedStatement returnedPreparedStatement = setColumnValuesInOrder(t, preparedStatement);

            return returnedPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return -1;
        }
    }

    public int delete(int id) {
        String query = "DELETE FROM " + getTableName() + " WHERE id = ?";

        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return -1;
        }
    }

    public boolean commitChangesIfTrueElseRollback(boolean flag) {
        try {
            Connection connection = databaseConnection.getConnection();

            if (flag) {
                connection.commit();
            } else {
                connection.rollback();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }
}
