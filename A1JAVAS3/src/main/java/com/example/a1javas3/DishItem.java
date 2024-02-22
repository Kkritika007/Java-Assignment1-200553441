package com.example.a1javas3;

public class DishItem {
    private String dishName;
    private int ordersCount;

    public DishItem(String dishName, int ordersCount) {
        this.dishName = dishName;
        this.ordersCount = ordersCount;
    }

    public String getDishName() {
        return dishName;
    }

    public int getOrdersCount() {
        return ordersCount;
    }
}
