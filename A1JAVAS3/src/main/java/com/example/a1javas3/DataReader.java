package com.example.a1javas3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<DishItem> getMostOrderedDishes(String selectedDay) {
        List<DishItem> dishData = new ArrayList<>();

        try (Connection connection = DbUtility.connect()) {
            String query = "SELECT dish_name, COUNT(*) AS ordersCount " +
                    "FROM restaurant_orders " +
                    "WHERE order_day = ? " +
                    "GROUP BY dish_name " +
                    "ORDER BY ordersCount DESC";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, selectedDay);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String dishName = resultSet.getString("dish_name");
                        int ordersCount = resultSet.getInt("ordersCount");
                        dishData.add(new DishItem(dishName, ordersCount));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dishData;
    }
}
