package com.asu.librarysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.paint.*;
public class MainApplication  extends Application {

    public static void main(String[] args) {
        Book book = new Book("The river", "Hossam", 2020, true,50, 5, "");
        Book book1 = new Book("The Sea", "Hossam", 2021, true, 120,5,"");
        Book book3 = new Book("The Land", "Hossam", 2021, true, 120,5,"");
        Library.addBook(book);
        Library.addBook(book1);
        Library.addBook(book3);
        Borrower borrower = new Borrower("Hossam","Ahmed","010");
        Borrower borrower2 = new Borrower("Moez","Ahmed","010");
        Borrower borrower3 = new Borrower("Hazem","Ahmed", "01060609587");
        borrower.addTransaction(book);
        borrower.addTransaction(book3);
        borrower.addTransaction(book1);
        borrower2.addTransaction(book);
        borrower2.addTransaction(book1);
        borrower.addTransaction(book3);
        BorrowerController.setCurrentBorrower(borrower);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BorrowerTransactions.fxml")));
            Parent child = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SideBar.fxml")));
            Scene rootscene = new Scene(root);
            Scene childscene = new Scene(child);
            primaryStage.setScene(childscene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
