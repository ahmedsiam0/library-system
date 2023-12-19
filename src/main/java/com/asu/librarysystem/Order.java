package com.asu.librarysystem;

import static com.asu.librarysystem.Library.searchBookById;

public class Order {
    
    static private int idCounter = 0;
    final private int id;
    private int bookId;
    private int quantity;

    public Order(int bookId, int quantity) {
        id = ++idCounter;
        this.bookId = bookId;
        this.quantity = quantity;
    }


    public int getId() {
        return id; 
    }

    public int getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return searchBookById(bookId).getPrice() * quantity;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
