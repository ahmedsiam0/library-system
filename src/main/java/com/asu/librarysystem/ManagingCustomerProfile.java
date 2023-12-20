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

        try {
            customerViewNodes= FXMLLoader.load(MainApplication.class.getResource("CustomerMain.fxml"));
            customerStage= (Stage)((Node)event.getSource()).getScene().getWindow();
            customerScene =new Scene(customerViewNodes);
            customerStage.setScene(customerScene);
            customerStage.show();
        }catch (Exception switchingScenesException){
            System.out.println(switchingScenesException.getMessage());
        }
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
    private Text acountNameText;
    @FXML
    private void editProfile(){

        if(PasswordTextField.getText().equals(confiremingPasswordTextField.getText()) && !(PasswordTextField.getText().isEmpty())){
            customer.setPassword(PasswordTextField.getText());
            customer.setUserName(acountNameTextField.getText());
            customer.setPhoneNumber(phoneNumberTextField.getText());

            acountNameText.setText(acountNameTextField.getText());
            phoneNumberText.setText(phoneNumberTextField.getText());

            PasswordTextField.clear();
            confiremingPasswordTextField.clear();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acountNameText.setText(customer.getUserName());
        phoneNumberText.setText(customer.getPhoneNumber());
        acountNameTextField.setText(customer.getUserName());
        phoneNumberTextField.setText(customer.getPhoneNumber());
    }
}