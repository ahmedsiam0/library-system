package com.asu.librarysystem;

import java.util.ArrayList;

import static com.asu.librarysystem.Library.searchBookById;

public class Customer extends Account{
    private ArrayList<Order> orders;

    // shopping cart

    Customer(String userName, String password, String phoneNumber){
        super( userName, password, phoneNumber);
        orders = new ArrayList<Order>();
    }

    private int findOrder(int orderId){
        for (int i = 0; i < orders.size(); i++){
            if (orders.get(i).getId() == orderId){
                return i;
            }
        }
        return -1;
    }

    public void viewItem(int bookId){
        System.out.println("Title = ");
        System.out.println("Author = ");
        System.out.println("Release Date = ");
        System.out.println("Availability = ");
        System.out.println("Price = ");
        System.out.println("Rating = ");
    }

    public void addOrder(int bookId, int quantity){
        orders.add( new Order(bookId, quantity) );
    }

    public void deleteOrder(int orderId){
        int orderIndex = findOrder(orderId);

        if(orderIndex != -1){
            orders.remove(orderIndex);
        } else {
            System.out.println("Can't find order");
        }
    }

    public void updateQuantity(int orderId, int newQuantity){
        int orderIndex = findOrder(orderId);

        if(orderIndex != -1){
            orders.get(orderIndex).setQuantity(newQuantity);
        } else {
            System.out.println("Can't find order");
        }
    }

    public ArrayList<Order> copyElementOfArrayList(){
        return orders;
    }
    public  ArrayList<Book> arrayOFOrderBooks(){
        ArrayList<Book> orderBooksArrayList=new ArrayList<Book>();
        for(int i=0;i<orders.size();i++) {
            orderBooksArrayList.add(searchBookById(orders.get(i).getBookId()));
        }
        return orderBooksArrayList;
    }
}
