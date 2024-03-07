package com.example.a1javas3;

public class OrderData {
    private String dishName;
    private int orderCount;

    public OrderData(String dishName, int orderCount) {
        this.dishName = dishName;
        this.orderCount = orderCount;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    @Override
    public String toString() {
        return "Dish: " + dishName + ", Orders: " + orderCount;
    }


}
