package com.asu.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.asu.librarysystem.Library.searchBookByTitle;
import static com.asu.librarysystem.Library.searchCustomerByUserName;

public class BooksViewCard {
    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookName, authorName;

    private Book bookObject;

    public void setData(Book book){
        bookObject = book;
        InputStream fileLocation;
        try {
            fileLocation = new FileInputStream(book.getCoverPath());
            Image cover = new Image(fileLocation);
            bookImage.setImage(cover);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        bookName.setText(book.getTitle());
        authorName.setText(book.getAuthor());
    }

    public void showDetailsOfBook(MouseEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Book-View-Details.fxml"));
            Parent root = loader.load();

            BookController sceneController = loader.getController();
            sceneController.setScene(bookObject);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            Library.getPreviousBooks().add(bookObject);


            String rootId = stage.getScene().getRoot().getId();


            if (!rootId.equals("BookViewDetails")) {
                System.out.println("Hello324jwoifjwei wjeifoiwejf wjofijweojf weiojwofi wofeijw woifjowie ifwojfewijwoifw eoewifjoefjoweif ewfjweofjefoiw\n efwjowefjiewf oweiwiojfew owfjwie");
                Library.setLastViewer(rootId);
            }

            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("book-view.fxml"));
//            Parent root = loader.load();
//
//            BookController bookc = loader.getController();
//            bookc.setScene(book);
//
//            Scene newScene = new Scene(root);
//            MainApplication.st.setScene(newScene);
//            MainApplication.st.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
