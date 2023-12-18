package com.asu.librarysystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class BooksCustomer {
    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookName;

    @FXML
    private Label authorName;

    @FXML
    private Label bookRate;

    private void setCover(String path) {
        InputStream fileLocation;

        try {
            fileLocation = new FileInputStream(path);
            Image cover = new Image(fileLocation);
            bookImage.setImage(cover);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void setData(Book book,String path){
        ArrayList<Integer> ratings = Library.getReviewHandler().getBookRatings(book.getId());
        InputStream fileLocation;

        try {
            fileLocation = new FileInputStream(path);
            Image cover = new Image(fileLocation);
            bookImage.setImage(cover);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        bookName.setText(book.getTitle());
        authorName.setText(book.getAuthor());
        bookRate.setText();
    }

}
