package com.example.a1javas3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtility {
    private static final String JDBC_URL = "jdbc:mysql://your-aws-mysql-host:3306/your-database-name";
    private static final String USERNAME = "your-username";
    private static final String PASSWORD = "your-password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static List<RestaurantOrder> getOrders() {
        List<RestaurantOrder> orders = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM restaurant_orders";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String customerName = resultSet.getString("customer_name");
                    String dishName = resultSet.getString("dish_name");
                    String orderDay = resultSet.getString("order_day");
                    double price = resultSet.getDouble("price");

                    // Create RestaurantOrder object
                    RestaurantOrder order = new RestaurantOrder(customerName, dishName, orderDay, price);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
