package com.asu.librarysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.ArrayList;

public class MainApplication extends Application {
    public static ArrayList<Book> books  = new ArrayList<Book>();

    public static Stage st;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        st = stage;
        stage.show();
    }

    public static void main(String[] args) {
        Book book1 = new Book("Jane Eyre", "Charlotte Bronte", 1847, true, 8
                , 1, "Orphaned Jane is sent to work as a governess for brooding Mr. Rochester's daughter, Adele. Love begins to grow between Jane and her moody employer, but his mysterious first wife threatens to ruin their chance at happiness."
                , "D:\\Programming\\Repos\\library-system\\book-covers\\jane_eyre.jpg", new Categories[]{Categories.HORROR, Categories.DRAMA});

        books.add(book1);
        Library.addBorrower(new Borrower("arsany", "123", "01277535814"));
        Library.logInByUserName("arsany", "123");

        launch();
    }
}
