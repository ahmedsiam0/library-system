package com.asu.librarysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class BorrowerController implements Initializable{
    private Stage primaryStage;
    private Scene scene;
    private static Borrower currentBorrower = new Borrower("","","");

    @FXML
    private Label currentUserName;

    @FXML
    private TableView<Transaction> TransactionTableView;

    @FXML
    private TableColumn<Transaction, String> BookNameColumn;

    @FXML
    private TableColumn<Transaction, LocalDate> BrwDateColumn;

    @FXML
    private TableColumn<Transaction, LocalDate> RtrnDateColumn;

    @FXML
    private TableColumn<Transaction, Integer> TransactionIdColumn;

    public static void setCurrentBorrower(Borrower currentBorrower) {
        BorrowerController.currentBorrower = currentBorrower;
    }

    public Borrower getCurrentBorrower() {
        return currentBorrower;
    }

    public void goToTransactionsScene (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BorrowerTransactions.fxml")));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(1);
    }
    public void goToMyBooksScene (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BorrowerTransactions.fxml")));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(2);
    }
    public void goToShoppingScene (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BorrowerTransactions.fxml")));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(3);
    }
    public void goToManageProfileScene (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BorrowerTransactions.fxml")));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(4);
    }
    public void goToLogoutScene (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BorrowerTransactions.fxml")));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(5);
    }
    ObservableList<Transaction> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUserName.setText(currentBorrower.getUserName() + "'s Transaction List");
        BookNameColumn.setCellValueFactory(new PropertyValueFactory<Transaction,String>("bookName"));
        BrwDateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("borrowDate"));
        RtrnDateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("returnDate"));
        TransactionIdColumn.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("transactionId"));
        list.addAll(currentBorrower.getBorrowerTransactions());
        TransactionTableView.setItems(list);
    }
}