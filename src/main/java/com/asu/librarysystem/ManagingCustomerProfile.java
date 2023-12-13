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

public class ManagingCustomerProfile implements Initializable {
    private Stage customerStage;
    private Scene customerScene;
    private Parent customerViewNodes;

    @FXML
    public void switchToManagingCustomerMain(ActionEvent event)throws IOException {
        customerViewNodes= FXMLLoader.load(MainApplication.class.getResource("CustomerMain.fxml"));
        customerStage= (Stage)((Node)event.getSource()).getScene().getWindow();
        customerScene =new Scene(customerViewNodes);
        customerStage.setScene(customerScene);
        customerStage.show();
    }

    Account customer = new Customer("ahmed khaled","12345678","01026428077");

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
    private Text usernameText;
    @FXML
    private void editProfile(){

        if(PasswordTextField.getText().equals(confiremingPasswordTextField.getText()) && !(PasswordTextField.getText().equals(""))){
            customer.setPassword(PasswordTextField.getText());
            customer.setUserName(acountNameTextField.getText());
            customer.setPhoneNumber(phoneNumberTextField.getText());
            //editing the texts that states username and phone number
            usernameText.setText(acountNameTextField.getText());
            phoneNumberText.setText(phoneNumberTextField.getText());
            //clearing the text boxes after editing the values
            PasswordTextField.clear();
            acountNameTextField.clear();
            phoneNumberTextField.clear();


        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameText.setText(customer.getUserName());
        phoneNumberText.setText(customer.getPhoneNumber());

    }
}