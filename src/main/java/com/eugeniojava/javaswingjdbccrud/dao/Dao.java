package com.eugeniojava.javaswingjdbccrud.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface Dao<T> {
    String getTableName();

    String getColumnNamesCommaSeparated();

    T instantiateAndSetAllFields(ResultSet resultSet);

    PreparedStatement setColumnValuesInOrderExceptId(T t, PreparedStatement preparedStatement);

    PreparedStatement setColumnValuesInOrder(T t, PreparedStatement preparedStatement);

    String getColumnNamesWithInterpolationMarkAssignedEachExceptIdCommaSeparated();
}
