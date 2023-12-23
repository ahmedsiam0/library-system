package com.asu.librarysystem;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.shape.Path;

public class Book {
    private static int idCounter = 0;
    private final int id;
    private String title;
    private String author;
    private int publicationYear;
    private boolean status;
    private int price;
    private int quantity;
    private String description;
    private File cover;
    private ArrayList<Category> categories;




    public Book(int id,String title, String author, int publicationYear, boolean status, int price, int quantity, String descreption, String coverPath, ArrayList<Category> categories){
        this.id=id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
        this.description = descreption;
        setCover(coverPath);
        this.categories=categories;
    }
    public Book(String title, String author, int publicationYear, boolean status, int price, int quantity, String descreption, String coverPath){
        this.id = ++idCounter;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
        this.description = descreption;
        setCover(coverPath);
    }

    public Book(String title, String author, int publicationYear, boolean status, int price, int quantity, String descreption, String coverPath, Category[] categories) {
        this( title,  author,  publicationYear,  status,  price,  quantity,  descreption,  coverPath);
        this.categories = new ArrayList<Category>(Arrays.asList(categories));
    }

    public int getIdCounter() {
        return idCounter;
    }
    public void setIdCounter(int idCounter) {
        this.idCounter = idCounter;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) { this.quantity =  quantity; }

    public int getQuantity() { return quantity; }

    public boolean isAvailable() { return quantity > 0; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }


    public void setCover(String path) {
        File newCover = new File(path);
        cover = new File("data/covers/" + id + ".jpg");

        if (!newCover.exists()) {
            System.out.println("Can't find file with path: " + path);
            System.out.println("Book title: " + title);
            System.out.println("Book id: " + id);
            return;
        }
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
        String rootPath = System.getProperty("user.dir");
        String relativePath = cover.getAbsolutePath().substring(rootPath.length() + 1);
        return relativePath;
    }

    public ArrayList<Category> getCategories() {
        return this.categories;
    }
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }


}
