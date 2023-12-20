package com.asu.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateController {


    @FXML
    private TextField id;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    @FXML
    private TextField phonenumber;

    @FXML
    private Button update;

    @FXML
    private Button cancel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void scene(ActionEvent event, String Link) throws IOException {
        root = FXMLLoader.load(getClass().getResource(Link));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void switch_To_CRUD_Borrower(ActionEvent event) throws IOException {
        scene(event, "CRUD_Borrower-view.fxml");
    }

    public void saveUpdate(ActionEvent event) throws IOException {

        Borrower B = Library.searchBorrwerByID(Integer.parseInt(id.getText()));

        if (B != null) {
            B.setUserName(username.getText());
            B.setPassword(password.getText());
            B.setPhoneNumber(phonenumber.getText());
            scene(event, "CRUD_Borrower-view.fxml");
        } else {
            id.setPromptText("Invalid ID");
        }
    }

}
