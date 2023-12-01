package com.asu.librarysystem;

public class Book {
    private static int idCounter=0;
    private final int id;
    private String title;
    private String author;
    private int publicationYear;
    private boolean status;
    private int price;
    private int rating;

    public Book(String title, String author, int publicationYear, boolean status, int price, int rating) {
        idCounter++;
        id=idCounter;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.status = status;
        this.price = price;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isStatus() {
        return status;
    }

    public int getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }

}
