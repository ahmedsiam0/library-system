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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MyBooksController {
    @FXML
    private Label userName;
    @FXML
    private GridPane bookContenerCustomer;
    @FXML
    private TextField searshText;


    public void startMyBooksController(){
        displayUserName();
        putsBooks();
    }
    private void displayUserName(){
        Account account = Library.getActiveAccount();
        if (account instanceof Customer) {
            Customer customer =(Customer) account;
            userName.setText(customer.getUserName());
        }
        else if(account instanceof Borrower){
            Borrower borrower =(Borrower) account;
            userName.setText(borrower.getUserName());
        }
    }


    public void backButton(ActionEvent event){
        try {
            Account account = Library.getActiveAccount();
            if (account instanceof Customer) {
                Customer customer =(Customer) account;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMain.fxml"));
                Parent root = loader.load();
//            clsssName objName = loader.getController();
//            objName.method;
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene= new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if(account instanceof Borrower){
                Borrower borrower =(Borrower) account;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("borrower-main-view.fxml"));
                Parent root = loader.load();
//            clsssName objName = loader.getController();
//            objName.method;
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene= new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FXMLLoader fxmlLoader;
    private VBox CardOfBook;
    public void putsBooks(){
        int colm=0;
        int row=1;
        Account account = Library.getActiveAccount();
        ArrayList<Book> booksArrayList = null;
        if (account instanceof Customer) {
            Customer customer =(Customer) account;
            booksArrayList=customer.arrayOFOrderBooks();
        }
        else if(account instanceof Borrower){
            Borrower borrower =(Borrower) account;
            booksArrayList=borrower.arrayOFTransactionBooks();
        }

        try {
            for(int i=0;i<booksArrayList.size();i++){
                fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Book-View-Card.fxml"));
                CardOfBook=fxmlLoader.load();
                BooksViewCard booksCard= fxmlLoader.getController();
                booksCard.setData(booksArrayList.get(i));
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
        Account account = Library.getActiveAccount();
        ArrayList<Book>foundBooks = null;
        ArrayList<Book>foundBooks2 = null;
        if (account instanceof Customer) {
            Customer customer =(Customer) account;
            ArrayList<Book> orderBooksArrayList=customer.arrayOFOrderBooks();
            foundBooks=Library.searchInArrayListBookByTitle(word,orderBooksArrayList);
            foundBooks2=Library.searchInArrayListBookByAuthor(word,orderBooksArrayList);
        }
        else if(account instanceof Borrower){
            Borrower borrower =(Borrower) account;
            ArrayList<Book> transactionBooksArrayList=borrower.arrayOFTransactionBooks();
            foundBooks=Library.searchInArrayListBookByTitle(word,transactionBooksArrayList);
            foundBooks2=Library.searchInArrayListBookByAuthor(word,transactionBooksArrayList);
        }
        //Customer customer=whoUserNow();

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
    public void searchBook(){
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
    public void searshBookByKey(KeyEvent event){
        searchBook();
    }
    public void searshBookByAction(ActionEvent event){
        searchBook();
    }

}
