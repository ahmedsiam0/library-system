package com.asu.librarysystem;

import java.util.ArrayList;

public class Customer extends Account {
    private ArrayList<Order> orders;

    // shopping cart
    public boolean assignBefore;

    Customer(String userName, String password, String phoneNumber) {
        super(userName, password, phoneNumber);
        orders = new ArrayList<Order>();
        assignBefore = false;
    }

    private int findOrder(int orderId) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == orderId) {
                return i;
            }
        }
        return -1;
    }

    public void viewItem(int bookId) {
        System.out.println("Title = ");
        System.out.println("Author = ");
        System.out.println("Release Date = ");
        System.out.println("Availability = ");
        System.out.println("Price = ");
        System.out.println("Rating = ");
    }

    public void addOrder(int bookId, int quantity) {
        orders.add(new Order(bookId, quantity));
    }

    public void deleteOrder(int orderId) {
        int orderIndex = findOrder(orderId);

        if (orderIndex != -1) {
            orders.remove(orderIndex);
        } else {
            System.out.println("Can't find order");
        }
    }

    public void updateQuantity(int orderId, int newQuantity) {
        int orderIndex = findOrder(orderId);

        if (orderIndex != -1) {
            orders.get(orderIndex).setQuantity(newQuantity);
        } else {
            System.out.println("Can't find order");
        }
    }

    public void setAssignBefore(boolean assignBefore) {
        if (!assignBefore)
            this.assignBefore = true;
        else
            this.assignBefore = true;
    }
}
