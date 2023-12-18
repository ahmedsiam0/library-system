package com.asu.librarysystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

import static com.asu.librarysystem.Library.*;

public class MainApplication  extends Application  {

    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("hello-view.fxml")));

        Image icon =new Image("E:\\ibrahem\\oop\\progect1\\library-system\\src\\main\\resources\\icon\\icons8-library-64.png");
        stage.getIcons().add(icon);

        stage.setTitle("library");
        stage.setScene(scene);
        stage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
//        Customer customer=new Customer("Ibrahem","111","111");
//        addCustomer(customer);
//        Borrower borrower = new Borrower("Ibrahem","111","111");
//        addBorrower(borrower);
//        Book book1 = new Book("Harry Potter and the Prisoner of Azkaban","J. K. Rowling",2023,true,200,4,"E:\\ibrahem\\oop\\progect1\\library-system\\src\\main\\resources\\image\\Harry_Potter_and_the_Prisoner_of_Azkaban.jpg");
//        Book book2 = new Book("Harry Potter and the Chamber of Secrets","J. K. Rowling",2020,true,200,3,"E:\\ibrahem\\oop\\progect1\\library-system\\src\\main\\resources\\image\\Harry_Potter_and_the_Chamber_of_Secrets.jpg");
//        Book book3 = new Book("The Alchemist ","Paulo Coelho ",2021,true,200,2,"E:\\ibrahem\\oop\\progect1\\library-system\\src\\main\\resources\\image\\TheAlchemist.jpg");
//        Book book4 = new Book("A Tale of Two Cities","Charles Dickens",2023,true,200,4,"E:\\ibrahem\\oop\\progect1\\library-system\\src\\main\\resources\\image\\Tales_serial.jpg");
//        Book book5 = new Book("The Little Prince ","Antoine de Saint-Exupéry",2020,true,200,3,"E:\\ibrahem\\oop\\progect1\\library-system\\src\\main\\resources\\image\\Littleprince.jpeg");
//        Book book6 = new Book("Harry Potter and the Philosopher's Stone","J. K. Rowling",2021,true,200,2,"E:\\ibrahem\\oop\\progect1\\library-system\\src\\main\\resources\\image\\Harry_Potter_and_the_Philosopher's_Stone_Book_Cover.jpg");
//        Book book7 = new Book("And Then There Were None","Agatha Christie",2023,true,200,4,"E:\\ibrahem\\oop\\progect1\\library-system\\src\\main\\resources\\image\\And_Then_There_Were_None_First_Edition_Cover_1939.jpg");
//        Book book8 = new Book("The Hobbit","J. R. R. Tolkien",2020,true,200,3,"E:\\ibrahem\\oop\\progect1\\library-system\\src\\main\\resources\\image\\TheHobbit_FirstEdition.jpg");
//        Book book9 = new Book("She: A History of Adventure","H. Rider Haggard",2021,true,200,2,"E:\\ibrahem\\oop\\progect1\\library-system\\src\\main\\resources\\image\\330px-SHE,_A_History_of_Adventure_(1st_Edition_Cover),_by_H._Rider_Haggard.jpg");
//        Book book10 = new Book("The Da Vinci Code","Dan Brown",2021,true,200,2,"E:\\ibrahem\\oop\\progect1\\library-system\\src\\main\\resources\\image\\DaVinciCode.jpg");
//        addBook(book1);
//        addBook(book2);
//        addBook(book3);
//        addBook(book4);
//        addBook(book5);
//        addBook(book6);
//        addBook(book7);
//        addBook(book8);
//        addBook(book9);
//        addBook(book10);
//        customer.addOrder(book1.getId(),2);
//        customer.addOrder(book2.getId(),2);
//        customer.addOrder(book3.getId(),1);
//        customer.addOrder(book5.getId(),2);
//        customer.addOrder(book7.getId(),2);
//
//        borrower.addTransaction(book3,2020,2022);
//        borrower.addTransaction(book5,2020,2022);
//        borrower.addTransaction(book9,2020,2022);
//        writeLibrary();
        readLibrary();


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
//
//        System.out.println(b.get(2));
//
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
