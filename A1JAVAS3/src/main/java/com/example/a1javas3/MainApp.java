package com.example.a1javas3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class MainApp extends Application {
    private ComboBox<String> dayComboBox;
    private TableView<DishItem> tableView;

    @Override
    public void start(Stage stage) {
        // Initialize GUI components
        dayComboBox = new ComboBox<>();
        tableView = new TableView<>();

        // Populate ComboBox with sample days (you can fetch these from the database)
        ObservableList<String> days = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        dayComboBox.setItems(days);

        // Create columns for the TableView
        TableColumn<DishItem, String> dishNameColumn = new TableColumn<>("Dish Name");
        dishNameColumn.setCellValueFactory(new PropertyValueFactory<>("dishName"));

        TableColumn<DishItem, Integer> ordersCountColumn = new TableColumn<>("Orders Count");
        ordersCountColumn.setCellValueFactory(new PropertyValueFactory<>("ordersCount"));

        // Add columns to the TableView
        tableView.getColumns().addAll(dishNameColumn, ordersCountColumn);

        // Create a button to fetch and display data
        Button showDataButton = new Button("Show Most Ordered Dishes");
        showDataButton.setOnAction(e -> displayData());

        // Create the main layout
        VBox root = new VBox(10);
        root.getChildren().addAll(dayComboBox, showDataButton, tableView);

        // Set up the scene and show the stage
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Restaurant Statistics");
        stage.setScene(scene);
        stage.show();
    }

    private void displayData() {
        String selectedDay = dayComboBox.getValue();
        if (selectedDay != null) {
            List<DishItem> dishData = DataReader.getMostOrderedDishes(selectedDay);

            // Update TableView with the fetched data
            tableView.getItems().clear();
            tableView.getItems().addAll(dishData);
        } else {
            // Handle case where no day is selected
            System.out.println("Please select a day.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

