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
        Book b = new Book("book1","author1",2023,true,200,4);
        Order o = new Order(b, 6);
        System.out.println("Book Price: " + b.getPrice());
        System.out.println("Order Price: " + o.getPrice());
    }
}
