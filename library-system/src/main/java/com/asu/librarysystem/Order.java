package com.asu.librarysystem;

public class Order {
    
    static private int idCounter = 0;
    final private int id;
    private Book book;
    private int quantity;

    public Order(Book book, int quantity) {
        id = ++idCounter;
        this.book = book;
        this.quantity = quantity;
    }

    // Placeholder
    public Order(int bookId, int quantity){
        id = ++idCounter;
        this.quantity = quantity;
    }

    public int getId() {
        return id; 
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return book.getPrice() * quantity;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
