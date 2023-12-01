package com.asu.librarysystem;

public class Order {

    static int idCounter = 0;
    private Book book;
    private int quantity;

    private int id;

    public Order(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    // Placeholder
    public Order(int bookId, int quantity){
        id = idCounter++;
        this.quantity = quantity;
    }

    public int getId() { return id; }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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


}
