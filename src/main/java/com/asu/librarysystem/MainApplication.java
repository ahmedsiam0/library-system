package com.asu.librarysystem;
/*
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
*/
public class MainApplication /* extends Application */ {
    /*
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    */
    public static void main(String[] args) {
        //launch();
        
        // Order class testing
        Book book1 = new Book("book1","author1",2023,true,200,4);
        Book book2 = new Book("book2","author1",2023,true,200,4);
        Book book3 = new Book("book3","author1",2023,true,200,4);
        Order o = new Order(book1, 6);
        Borrower b1 = new Borrower(20,"hossam","hos", 1999999999);
        Borrower b2 = new Borrower(30,"hossam","hos", 1060606060);
        b1.addTransaction(book1,20230519,20231103);
        b1.addTransaction(book2,20230519,20231003);
        b2.addTransaction(book3,20220815,20231130);
        b2.addTransaction(book1,20220815,20231130);
        b1.deleteTransaction(4);
        System.out.println("Book Price: " + book1.getPrice());
        System.out.println("Order Price: " + o.getPrice());
        System.out.println(b1.finesIfLate());
        b2.searchTransaction(4);
    }
}
