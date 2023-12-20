package com.asu.librarysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


import java.util.ArrayList;

public class MainApplication extends Application {
    public static ArrayList<Book> books  = new ArrayList<Book>();

    public static Stage st;

    @Override
    public void start(Stage stage) throws IOException {

//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Image icon =new Image("E:\\ibrahem\\oop\\project2\\library-system\\src\\main\\resources\\icon\\icons8-library-64.png");
//        stage.getIcons().add(icon);
//
//        stage.setTitle("library");
//        stage.setScene(scene);
//        stage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");


        stage.setScene(scene);
        st = stage;
        stage.show();


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        /*Book book100 = new Book("Jane Eyre", "Charlotte Bronte", 1847, true, 8
                , 1, "Orphaned Jane is sent to work as a governess for brooding Mr. Rochester's daughter, Adele. Love begins to grow between Jane and her moody employer, but his mysterious first wife threatens to ruin their chance at happiness."
                , """", new Category[]{Category.HORROR, Category.DRAMA});*/
        Book book100 = new Book("Harry Potter and the Prisoner of Azkaban","J. K. Rowling",2023,true,200,4,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.HORROR});


        Book book1 = new Book("Harry Potter and the Prisoner of Azkaban","J. K. Rowling",2023,true,200,4,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.HORROR});
        Book book2 = new Book("Harry Potter and the Chamber of Secrets","J. K. Rowling",2020,true,200,3,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.ADVENTURE});
        Book book3 = new Book("The Alchemist ","Paulo Coelho ",2021,true,200,2,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.ADVENTURE});
        Book book4 = new Book("A Tale of Two Cities","Charles Dickens",2023,true,200,4,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.ADVENTURE});
        Book book5 = new Book("The Little Prince ","Antoine de Saint-ExupÃ©ry",2020,true,200,3,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.ADVENTURE});
        Book book6 = new Book("Harry Potter and the Philosopher's Stone","J. K. Rowling",2021,true,200,2,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.ADVENTURE});
        Book book7 = new Book("And Then There Were None","Agatha Christie",2023,true,200,4,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.ADVENTURE});
        Book book8 = new Book("The Hobbit","J. R. R. Tolkien",2020,true,200,3,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.ADVENTURE, Category.HORROR});
        Book book9 = new Book("She: A History of Adventure","H. Rider Haggard",2021,true,200,2,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.ADVENTURE});
        Book book10 = new Book("The Da Vinci Code","Dan Brown",2021,true,200,2,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.ADVENTURE});
        Library.addBook(book1);
        Library.addBook(book2);
        Library. addBook(book3);
        Library.addBook(book4);
        Library.addBook(book5);
        Library.addBook(book6);
        Library.addBook(book7);
        Library.addBook(book8);
        Library.addBook(book9);
        Library.addBook(book10);
//        Customer customer=new Customer("Ibrahem","123","111");
//        Library.addCustomer(customer);
//        Borrower borrower = new Borrower("Ibrahem","111","111");
//        Library.addBorrower(borrower);
//        Library.logInByUserName("Ibrahem","111");
//
//        customer.addOrder(book1.getId(),2);
//        customer.addOrder(book2.getId(),2);
//        customer.addOrder(book3.getId(),1);
//        customer.addOrder(book5.getId(),2);
//        customer.addOrder(book7.getId(),2);
//
//        borrower.addTransaction(book3,2020,2022);
//        borrower.addTransaction(book5,2020,2022);
//        borrower.addTransaction(book9,2020,2022);



        books.add(book100);
        Library.addBorrower(new Borrower("arsany", "123", "01277535814"));
        Library.logInByUserName("arsany", "123");


        launch();


    }
}
