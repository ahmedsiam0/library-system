package com.asu.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.asu.librarysystem.Library.*;

public class MyBooksBorrowerController {
    @FXML
    private Label userName;
    @FXML
    private GridPane bookContenerCustomer;
    @FXML
    private TextField searshText;


    public void startMyBooksBorrowerController(Borrower borrower){
        displayUserName(borrower);
        putsBooks(borrower);

    }
    public void displayUserName(Borrower borrower){
        userName.setText(borrower.getUserName());
    }

    private Borrower whoUserNow(){
        Borrower borrower=searchBorrwerByUserName(userName.getText());
        return borrower;
    }

    public void backButton(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("name.fxml"));
            Parent root = loader.load();

//            clsssName objName = loader.getController();
//            objName.method;
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private FXMLLoader fxmlLoader;
    private VBox CardOfBook;
    public void putsBooks(Borrower borrower){
        int colm=0;
        int row=1;
        ArrayList<Book> transactionBooksArrayList=borrower.arrayOFTransactionBooks();
        try {
            for(int i=0;i<transactionBooksArrayList.size();i++){
                fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Book-View-Card.fxml"));
                CardOfBook=fxmlLoader.load();
                BooksViewCard booksCard= fxmlLoader.getController();
                booksCard.setData(transactionBooksArrayList.get(i));
                if(colm==6){
                    colm=0;
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



    private ArrayList<Book> arrayReadyToSearch(String word){
        if(word.equals("")){
            word=" ";
        }
        Borrower borrower=whoUserNow();
        ArrayList<Book> transactionBooksArrayList=borrower.arrayOFTransactionBooks();
        ArrayList<Book>foundBooks=searchInArrayListBookByTitle(word,transactionBooksArrayList);
        ArrayList<Book>foundBooks2=searchInArrayListBookByAuthor(word,transactionBooksArrayList);
        foundBooks.addAll(foundBooks2);
        for (int i = 0; i < foundBooks.size(); i++) {
            for (int j = i + 1; j < foundBooks.size(); j++) {
                if (foundBooks.get(i).equals(foundBooks.get(j))) {
                    foundBooks.remove(j);
                }
            }
        }
        return foundBooks;
    }
    public void searshBook(){
        String word = searshText.getText();
        ArrayList<Book>foundBooks=arrayReadyToSearch(word);
        int colm=0;
        int row=1;
        try {
            GridPane parentPane = (GridPane) CardOfBook.getParent();
            if (parentPane != null && !parentPane.getChildren().isEmpty()) {
                for (int i = parentPane.getChildren().size() - 1; i >= 0; i--) {
                    Node child = parentPane.getChildren().get(i);
                    if (child instanceof VBox) {
                        parentPane.getChildren().remove(i);
                    }
                }
            }

            for(int i=0;i<foundBooks.size();i++){
                fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Book-View-Card.fxml"));
                CardOfBook=fxmlLoader.load();
                BooksViewCard booksCard= fxmlLoader.getController();
                booksCard.setData(foundBooks.get(i));
                if(colm==6){
                    colm=0;
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
//    public void putsBooks(Borrower borrower){
//        int colm=0;
//        int row=1;
//        ArrayList<Transaction>transactionArrayList=borrower.copyElementOfArrayList();
//        try {
//            for(int i=0;i<transactionArrayList.size();i++){
//                FXMLLoader fxmlLoader=new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("Book-View-Card.fxml"));
//                VBox CardOfBook=fxmlLoader.load();
//                BooksViewCard booksCard= fxmlLoader.getController();
//                booksCard.setData(searchBookById(transactionArrayList.get(i).getBookId()));
//                if(colm==6){
//                    colm=0;
//                    row++;
//                }
//                bookContenerCustomer.add(CardOfBook,colm++,row);
//                GridPane.setMargin(CardOfBook,new Insets(10));
//
//            }
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }

}
