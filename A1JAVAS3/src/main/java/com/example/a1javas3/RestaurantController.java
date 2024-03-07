// RestaurantController.java
package com.example.a1javas3;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.sql.*;
import java.time.LocalDate;

public class RestaurantController {
    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<OrderData> tableView;

    @FXML
    private PieChart pieChart;

    // JDBC connection details
    private final String jdbcUrl = "jdbc:mysql://172.31.22.43:3306/Kritika200553441";
    private final String username = "Kritika200553441";
    private final String password = "QDqjVR-efn";

    @FXML
    private void initialize() {
        // Add any initialization code here
        fadeInEffect();
    }

    @FXML
    private void showMostOrderedDishes(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();

        // Fetch data from the database based on the selected date
        // Write your SQL query to retrieve the most ordered dishes

        // Example query: SELECT dish_name, COUNT(*) as order_count FROM your_table WHERE order_day = ? GROUP BY dish_name ORDER BY order_count DESC
        String sql = "SELECT dish_name, COUNT(*) as order_count FROM restaurant_orders WHERE order_day = ? GROUP BY dish_name ORDER BY order_count DESC";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDate(1, Date.valueOf(selectedDate));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Populate TableView
                tableView.getItems().clear();
                tableView.getColumns().clear();

                TableColumn<OrderData, String> dishColumn = new TableColumn<>("Dish Name");
                TableColumn<OrderData, Integer> orderCountColumn = new TableColumn<>("Order Count");

                dishColumn.setCellValueFactory(new PropertyValueFactory<>("dishName"));
                orderCountColumn.setCellValueFactory(new PropertyValueFactory<>("orderCount"));

                tableView.getColumns().addAll(dishColumn, orderCountColumn);

                while (resultSet.next()) {
                    String dishName = resultSet.getString("dish_name");
                    int orderCount = resultSet.getInt("order_count");

                    tableView.getItems().add(new OrderData(dishName, orderCount));
                }
            }

            // Populate PieChart
            pieChart.getData().clear();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dishName = resultSet.getString("dish_name");
                    int orderCount = resultSet.getInt("order_count");

                    pieChart.getData().add(new PieChart.Data(dishName, orderCount));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    private void fadeInEffect() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), tableView.getScene().getRoot());
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
}
