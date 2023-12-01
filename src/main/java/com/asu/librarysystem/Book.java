package com.asu.librarysystem;

public class Book {
    private int book_ID;
    private String book_Title;
    private String author;
    private int publication_Year;
    private boolean status;
    private int price;
    private int book_Rating;

    public Book(int book_ID, String book_Title, String author, int publication_Year, boolean status, int price, int book_Rating) {
        this.book_ID = book_ID;
        this.book_Title = book_Title;
        this.author = author;
        this.publication_Year = publication_Year;
        this.status = status;
        this.price = price;
        this.book_Rating = book_Rating;
    }

    public int getBook_ID() {
        return book_ID;
    }

    public String getBook_Title() {
        return book_Title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublication_Year() {
        return publication_Year;
    }

    public boolean isStatus() {
        return status;
    }

    public int getPrice() {
        return price;
    }

    public int getBook_Rating() {
        return book_Rating;
    }
}
