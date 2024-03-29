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
    Customer(int id ,String userName, String password, String phoneNumber) {
        super(id,userName, password, phoneNumber);
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


    public void addOrder(int bookId, int quantity) {
        orders.add(new Order(bookId, quantity));
    }

    public void addOrder(Order order){
        addOrder(order.getBookId(),order.getQuantity());
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

    public  ArrayList<Book> arrayOFOrderBooks(){
        ArrayList<Book> orderBooksArrayList=new ArrayList<Book>();
        for(int i=0;i<orders.size();i++) {
            orderBooksArrayList.add(Library.searchBookById(orders.get(i).getBookId()));
        }
        return orderBooksArrayList;
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }
}
