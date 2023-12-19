package com.asu.librarysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.ArrayList;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Borrower B = new Borrower("Hossam", "123456", "123456");
        Book b = new Book("", "", 0, true, 1500, 4, "Any");
        Library.addBook(b);
        Book BB = new Book("", "", 0, true, 1500, 4, "Any");
        Library.addBook(BB);
        B.addTransaction(BB, 15, 20);
        B.addTransaction(b, 10, 15);
        Library.addBorrower(B);
        System.out.println(B.getNoOfBooks());
//       Library.logInByUserName("Ahmad" , "1234");


        launch();

        // Order class testing
//        Book book1 = new Book("book1","author1",2023,true,200,4);
//        Book book2 = new Book("book2","author1",2023,true,200,4);
//        Book book3 = new Book("book3","author1",2023,true,200,4);
//        Order o = new Order(book1, 6);
//        Borrower b1 = new Borrower("hossam","hos", "1999999999");
//        Borrower b2 = new Borrower("hossam","hos", "1060606060");
//        b1.addTransaction(book1,20230519,20231103);
//        b1.addTransaction(book2,20230519,20231003);
//        b2.addTransaction(book3,20220815,20231130);
//        b2.addTransaction(book1,20220815,20231130);
//        b1.deleteTransaction(4);
//        System.out.println("Book Price: " + book1.getPrice());
//        System.out.println("Order Price: " + o.getPrice());
//        System.out.println(b1.finesIfLate());
//        ArrayList b = new ArrayList();
//        b.add(1);
//        b.add(2);
//        b.add(888);
//        b.add(4);
//        b.remove(3);

//        System.out.println(b.get(2));

//        // Testing Cover
//        Book book = new Book("The Old Man And The Sea", "Ernest Hemingway", 1952, true, 200, 0, "book-covers/the_old_man_and_the_sea.jpg");
//        System.out.println(book.getCoverPath());
//
//        // Testing ReviewHandler
//        ReviewHandler reviewHandler = new ReviewHandler();
//        reviewHandler.addReview(1, 1, 1, "Nice Book");
//        reviewHandler.addReview(2, 1, 1, "");
//        reviewHandler.addReview(3, 1, 1, "");
//        reviewHandler.addReview(4, 1, 1, "");
//        reviewHandler.addReview(5, 1, 1, "Bad Book");
//        reviewHandler.deleteReview(3, 1);
//        ArrayList<Integer> ratings = reviewHandler.getBookRatings(1);
//        System.out.println(ratings.get(1));
//        System.out.println(reviewHandler.getReviewText(1, 1));
//        System.out.println(reviewHandler.getReviewText(5, 1));
    }
}
