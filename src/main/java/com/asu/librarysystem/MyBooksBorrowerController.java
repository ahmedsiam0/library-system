package com.asu.librarysystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

import static com.asu.librarysystem.Library.searchBookById;

public class MyBooksCustomerController {
    @FXML
    private Label userName;
    @FXML
    private GridPane bookContenerCustomer;

    public void startMyBooksCustomerController(Customer customer){
        displayUserName(customer);
        putsBooks(customer);
    }
    public void displayUserName(Customer customer){
        userName.setText(customer.getUserName());
    }

    public void putsBooks(Customer customer){
        int colm=0;
        int row=1;
        ArrayList<Order>orderArrayList=customer.copyElementOfArrayList();
        try {
            for(int i=0;i<orderArrayList.size();i++){
                FXMLLoader fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Book-View-Card.fxml"));
                VBox CardOfBook=fxmlLoader.load();
                BooksViewCard booksCard= fxmlLoader.getController();
                booksCard.setData(searchBookById(orderArrayList.get(i).getBookId()));
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
