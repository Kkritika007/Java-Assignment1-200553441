// DBUtility.java
package com.example.a1javas3;

import java.sql.*;

public class DBUtility {
    private static final String JDBC_URL = "jdbc:mysql://172.31.22.43:3306/Kritika200553441";
    private static final String USERNAME = "Kritika200553441";
    private static final String PASSWORD = "QDqjVR-efn";

    // Establishes and returns a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // Closes the given resources (Connection, PreparedStatement, ResultSet)
    public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
