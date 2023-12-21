package com.asu.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public void switchToShopping(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("All-Books.fxml"));
            customerViewNodes = loader.load();
            AllBooksController sceneController = loader.getController();
            sceneController.startAllBooksController();
            customerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            customerScene =new Scene(customerViewNodes);
            customerStage.setScene(customerScene);
            customerStage.show();
        }catch (Exception switchingScenesException){
            System.out.println(switchingScenesException.getMessage());
        }

    }
    public void switchToMyBooks(ActionEvent event)throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("My-Books.fxml"));
            customerViewNodes = loader.load();
            MyBooksController sceneController = loader.getController();
            sceneController.startMyBooksController();
            customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            customerScene = new Scene(customerViewNodes);
            customerStage.setScene(customerScene);
            customerStage.show();
        }catch (Exception switchingScenesException){
            System.out.println(switchingScenesException.getMessage());
        }

    }
    public void switchToManagingCustomerProfile(ActionEvent event)throws IOException {
        try {
            customerViewNodes= FXMLLoader.load(MainApplication.class.getResource("managing-profile-view.fxml"));
            customerStage= (Stage)((Node)event.getSource()).getScene().getWindow();
            customerScene =new Scene(customerViewNodes);
            customerStage.setScene(customerScene);
            customerStage.show();
        }catch (Exception switchingScenesException){
            System.out.println(switchingScenesException.getMessage());
        }

    }
    @FXML
    void switchToOrders(ActionEvent event) {
        try {
            customerViewNodes= FXMLLoader.load(MainApplication.class.getResource("orders-for-customer-view.fxml"));
            customerStage= (Stage)((Node)event.getSource()).getScene().getWindow();
            customerScene =new Scene(customerViewNodes);
            customerStage.setScene(customerScene);
            customerStage.show();
        }catch (Exception switchingScenesException){
            System.out.println(switchingScenesException.getMessage());
        }
    }

    @FXML
    void switchToCart(ActionEvent event) {
        try {
            customerViewNodes= FXMLLoader.load(MainApplication.class.getResource("shopping-cart-view.fxml"));
            customerStage= (Stage)((Node)event.getSource()).getScene().getWindow();
            customerScene =new Scene(customerViewNodes);
            customerStage.setScene(customerScene);
            customerStage.show();
        }catch (Exception switchingScenesException){
            System.out.println(switchingScenesException.getMessage());
        }
    }
    @FXML
    void logout(ActionEvent event) {
        try {
            Library.logOut();
            Parent adminViewNodes = FXMLLoader.load(MainApplication.class.getResource("Login.fxml"));
            Stage adminStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene adminScene =new Scene(adminViewNodes);
            adminStage.setScene(adminScene);
            adminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
