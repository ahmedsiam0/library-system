package com.asu.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {
    private Stage customerStage;
    private Scene customerScene;
    private Parent customerViewNodes;

    public void switchToShopping(ActionEvent event)throws IOException {
        customerViewNodes= FXMLLoader.load(MainApplication.class.getResource("Shopping.fxml"));
        customerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        customerScene =new Scene(customerViewNodes);
        customerStage.setScene(customerScene);
        customerStage.show();
    }
    public void switchToMyBooks(ActionEvent event)throws IOException {
        customerViewNodes = FXMLLoader.load(MainApplication.class.getResource("MyBooks.fxml"));
        customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerScene = new Scene(customerViewNodes);
        customerStage.setScene(customerScene);
        customerStage.show();
    }
    public void switchToManagingCustomerProfile(ActionEvent event)throws IOException {
        customerViewNodes= FXMLLoader.load(MainApplication.class.getResource("ManagingCustomerProfile.fxml"));
        customerStage= (Stage)((Node)event.getSource()).getScene().getWindow();
        customerScene =new Scene(customerViewNodes);
        customerStage.setScene(customerScene);
        customerStage.show();
    }

}
