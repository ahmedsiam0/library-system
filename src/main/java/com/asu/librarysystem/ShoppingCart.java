package com.asu.librarysystem;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Order> orders;
    private String discount;
    private PaymentMethod paymentMethod;

    public ShoppingCart() {
        orders = new ArrayList<Order>();
        discount = null;
        paymentMethod = null;
    }
    
    public void addBook(int bookId) {
        int index = findBook(bookId);
        if (index == -1)
            orders.add(new Order(bookId, 1));
    }

    public void deleteBook(int bookId) {
        int index = findBook(bookId);
        if (index != -1)
            orders.remove(index);
    }

    private int findBook(int bookId) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getBookId() == bookId) {
                return i;
            }
        }
        return -1;
    }
    public ArrayList<Order> getOrders() {
        return (new ArrayList<Order>(orders));
    }
    public String getDiscount() {
        return discount;
    }
    public void updateOrderQuantity(int bookId, int quantity) {
        int index = findBook(bookId);
        if (index == -1)
            return;
        orders.get(index).setQuantity(quantity);
    }

    public void setDiscount(String discountCode) {
        this.discount = discountCode;
        for (var order : orders) {
            order.setDiscountCode(discountCode);
        }
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (var order : orders) {
            totalPrice += order.getPrice();
        }
        return totalPrice;
    }
    
    public PaymentMethod getpaymentMethod() {
        return paymentMethod;
    }

    public void setpaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void completePurchase() {
        Customer activeAccount = (Customer)Library.getActiveAccount();
        for (var order : orders) {
            activeAccount.addOrder(order.getBookId(), order.getQuantity());
        }
        orders.clear();
    }
}
