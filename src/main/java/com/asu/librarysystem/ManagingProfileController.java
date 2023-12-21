package com.asu.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagingProfileController implements Initializable{
    @FXML
    public void switchToMain(ActionEvent event)throws IOException {
        try {
            Parent root;
            if (Library.getActiveAccount() instanceof Admin) {
                root = FXMLLoader.load(MainApplication.class.getResource("AdminMain.fxml"));
            } else if (Library.getActiveAccount() instanceof Customer) {
                root = FXMLLoader.load(MainApplication.class.getResource("CustomerMain.fxml"));
            } else {
                root = FXMLLoader.load(MainApplication.class.getResource("borrower-main-view.fxml"));
            }
            Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene =new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception switchingScenesException){
            System.out.println(switchingScenesException.getMessage());
        }
    }

    private Account account = Library.getActiveAccount();

    @FXML
    private TextField acountNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField PasswordTextField;
    @FXML
    private TextField confiremingPasswordTextField;

    @FXML
    private Text phoneNumberText;
    @FXML
    private Text acountNameText;
    @FXML
    private void editProfile(){

        if(PasswordTextField.getText().equals(confiremingPasswordTextField.getText()) && !(PasswordTextField.getText().isEmpty())){
            account.setPassword(PasswordTextField.getText());
            account.setUserName(acountNameTextField.getText());
            account.setPhoneNumber(phoneNumberTextField.getText());

            acountNameText.setText(acountNameTextField.getText());
            phoneNumberText.setText(phoneNumberTextField.getText());

            PasswordTextField.clear();
            confiremingPasswordTextField.clear();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acountNameText.setText(account.getUserName());
        phoneNumberText.setText(account.getPhoneNumber());
        acountNameTextField.setText(account.getUserName());
        phoneNumberTextField.setText(account.getPhoneNumber());
    }
}
