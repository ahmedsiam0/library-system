package com.asu.librarysystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyBooksCustomer {
    @FXML
    private Label userName;
    @FXML
    private GridPane bookContenerCustomer;
    public static 

    public void putsBooks(){

        int colm=0;
        int row=1;
        try {
            for(){
                FXMLLoader fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Book-view.fxml"));
                VBox CardOfBook=fxmlLoader.load();
                BooksViewCard booksCard= fxmlLoader.getController();
                booksCard.setData(book);
                if(colm==6){
                    colm=8;
                    row++;
                }
                bookContenerCustomer.add(CardOfBook,colm++,row);
                GridPane.setMargin(CardOfBook,new Insets(10));

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }



    }
}
