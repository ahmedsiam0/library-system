package com.asu.librarysystem;

public class Order {
    
    static private int idCounter = 0;
    final private int id;
    private int bookId;
    private int quantity;
    private String discountCode;

    public Order(int bookId, int quantity) {
        this.id = ++idCounter;
        this.bookId = bookId;
        this.quantity = quantity;
        this.discountCode = "";
    }
    public Order(int id,int bookId, int quantity) {
        this.id = id;
        this.bookId = bookId;
        this.quantity = quantity;
        this.discountCode = "";
    }

    public int getIdCounter(){
        return idCounter;
    }
    public void setIdCounter(int idCounter){
        this.idCounter= idCounter;
    }

    public int getId() {
        return id; 
    }

    public String getBook() {
        return Library.searchBookById(bookId).getTitle();
    }

    public int getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        double price = Library.searchBookById(bookId).getPrice() * quantity;
        if (discountCode != "") {
            price -= Library.getDiscountHandler().getDiscount(discountCode) * price / 100.0;
        }
        return price;
    }
    
    public void setBook(int bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscountCode(String discountCode) {
        if (Library.getDiscountHandler().getDiscount(discountCode) != -1.0)
            this.discountCode = discountCode;
    }

    public String getDiscountCode() {
        return discountCode;
    }
}
