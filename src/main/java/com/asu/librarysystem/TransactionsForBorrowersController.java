package com.asu.librarysystem;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class TransactionsForBorrowersController implements Initializable {
    private Stage primaryStage = new Stage();
    private Scene scene;
    private static Borrower currentBorrower = (Borrower)Library.getActiveAccount();
    @FXML
    private Label Back;
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
/*
    public static void setCurrentBorrower(Borrower currentBorrower) {
        TransactionsForBorrowersController.currentBorrower = currentBorrower;
    }*/

    public static Borrower getCurrentBorrower() {
        return currentBorrower;
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
        Back.setOnMouseClicked(mouseEvent -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("borrower-main-view.fxml")));
                primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}