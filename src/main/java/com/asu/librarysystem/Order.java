package com.asu.librarysystem;

public class Order {
    private Book book;
    private int quantity;

    public Order(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

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
