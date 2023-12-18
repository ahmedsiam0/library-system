package com.asu.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.asu.librarysystem.Library.searchBookById;

public class AllBooksController {
    @FXML
    private Label userName;
    @FXML
    private GridPane bookContenerCustomer;
    public void startMyBooksBorrowerController(Borrower borrower){
        displayUserName(borrower);
        putsBooks(borrower);

    }
    public void displayUserName(Borrower borrower){
        userName.setText(borrower.getUserName());
    }

    public void backButton(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("name.fxml"));
            Parent root = loader.load();


            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void putsBooks(Borrower borrower){
        int colm=0;
        int row=1;
        ArrayList<Transaction> transactionArrayList=borrower.copyElementOfArrayList();
        try {
            for(int i=0;i<transactionArrayList.size();i++){
                FXMLLoader fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Book-View-Card.fxml"));
                VBox CardOfBook=fxmlLoader.load();
                BooksViewCard booksCard= fxmlLoader.getController();
                booksCard.setData(searchBookById(transactionArrayList.get(i).getBookId()));
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
