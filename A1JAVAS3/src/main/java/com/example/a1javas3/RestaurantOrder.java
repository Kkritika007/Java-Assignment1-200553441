package com.example.a1javas3;

public class RestaurantOrder {
    private String customerName;
    private String dishName;
    private String orderDay;
    private double price;

    public RestaurantOrder(String customerName, String dishName, String orderDay, double price) {
        this.customerName = customerName;
        this.dishName = dishName;
        this.orderDay = orderDay;
        this.price = price;
    }

    // Add getters and setters as needed

    @Override
    public String toString() {
        return "RestaurantOrder{" +
                "customerName='" + customerName + '\'' +
                ", dishName='" + dishName + '\'' +
                ", orderDay='" + orderDay + '\'' +
                ", price=" + price +
                '}';
    }
}

