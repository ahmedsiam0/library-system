package com.asu.librarysystem;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Book {
    private static int idCounter = 0;
    private final int id;
    private String title;
    private String author;
    private int publicationYear;
    private int price;
    private int rating;
    private int quantity;
    private File cover;

    public Book(String title, String author, int publicationYear, int price, int rating,int quantity, String coverPath){
        this.id = ++idCounter;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.price = price;
        this.rating = rating;
        this.quantity=quantity;
        setCover(coverPath);
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

    public boolean isAvailable() {
        if (quantity>0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCover(String path) {
        File newCover = new File(path);

        if (!newCover.exists()) {
            System.out.println("Can't find file with path: " + path);
        }
        cover = new File("data/covers/" + id + ".jpg");
        try {
            if (!newCover.toPath().equals(cover.toPath()))
                Files.copy(newCover.toPath(), cover.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Faild to copy this file: " + path);
            System.out.println("Book title: " + title);
            System.out.println("Book id: " + id);
        }
    }
    public String getCoverPath() {
        return cover.getAbsolutePath();
    }
}
