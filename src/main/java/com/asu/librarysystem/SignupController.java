package com.asu.librarysystem;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class SignupController {
    @FXML
    private TextField username;


    @FXML
    private PasswordField password;

    @FXML
    private RadioButton customerRadio;

    @FXML
    private ToggleGroup User;

    @FXML
    private RadioButton borrowerRadio;

    @FXML
    private TextField phonenumber;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    Stage stage;
    Parent root;
    Scene scene;
    Borrower cameFromCRUD =new Borrower("Admin" , "1111" , "01021211");
    Borrower currentBorrower ;

    public void scene(ActionEvent event, String Link) throws IOException {
        root = FXMLLoader.load(getClass().getResource(Link));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void AddUser(String userName, String phoneNumber, String Password) {


        if (customerRadio.isSelected()) {
            Customer C = new Customer(userName, Password, phoneNumber);
            Library.customers.add(C);
            System.out.println("Customer");
        } else if (borrowerRadio.isSelected()) {
            Borrower B = new Borrower(userName, Password, phoneNumber);
            Library.borrowers.add(B);
            System.out.println("Borrower");
        }
    }

    public void goToScene(ActionEvent event) throws IOException {
        scene(event, "Login.fxml");
    }

    public void saveUser(ActionEvent event) throws IOException {


        AddUser(username.getText(), phonenumber.getText(), password.getText());

        if (Library.getActiveAccount() instanceof Admin) {
            scene(event, "CRUD_Borrower-view.fxml");

        } else {
            scene(event, "Login.fxml");
        }
    }


}

