package com.asu.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupbutton;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


    public void scene(ActionEvent event, String Link) throws IOException {
        root = FXMLLoader.load(getClass().getResource(Link));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }


    public void switchToMain(ActionEvent event) throws IOException , NullPointerException {
        if (username.getText().isEmpty() || password.getText().isEmpty())
        {
            username.setPromptText("Invalid Username");
            password.setPromptText("Invalid password");
            alert.setContentText("You should Fill Username & Password.");
            alert.show();
        }

        else if (!Library.logInByUserName(username.getText(), password.getText())) {
            username.clear();
            password.clear();

            alert.setContentText("Username or password is incorrect.");
            alert.show();

        }

        else if (Library.logInByUserName(username.getText(), password.getText())){
            if(Library.getActiveAccount() instanceof Admin)
            scene(event, "AdminMain.fxml");//Admin Main fxml file
            else if (Library.getActiveAccount() instanceof Borrower) {
                scene(event, "All-Books.fxml");//Borrower Main fxml file

            } else if (Library.getActiveAccount() instanceof Customer) {
                scene(event, "CustomerMain.fxml");//Customer Main fxml file

            }
        }
    }
    public void switchToSignup(ActionEvent event) throws IOException {
        scene(event, "SIGNup.fxml");
    }
}
