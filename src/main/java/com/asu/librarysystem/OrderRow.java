package com.asu.librarysystem;

public class OrderRow {
    private Order order;
    private String owner;

    public OrderRow(Order order, String owner) {
        this.order = order;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public String getBook() {
        return order.getBook();
    }

    public int getQuantity() {
        return order.getQuantity();
    }

    public double getPrice() {
        return order.getPrice();
    }

    public String getDiscountCode() {
        return order.getDiscountCode();
    }
}
