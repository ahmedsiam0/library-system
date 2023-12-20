package com.asu.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    @FXML
    private Stage adminStage;
    @FXML
    private Scene adminScene;
    @FXML
    private Parent adminViewNodes;

    @FXML
    public void switchToManagingBooks(ActionEvent event)throws IOException {

        try {
            adminViewNodes= FXMLLoader.load(MainApplication.class.getResource("CRUDBooks.fxml"));
            adminStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            adminScene =new Scene(adminViewNodes);
            adminStage.setScene(adminScene);
            adminStage.show();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    @FXML
    public void switchToManagingBorrowers(ActionEvent event)throws IOException {

        try {
            adminViewNodes= FXMLLoader.load(MainApplication.class.getResource("ManagingBorrowers.fxml"));
            adminStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            adminScene =new Scene(adminViewNodes);
            adminStage.setScene(adminScene);
            adminStage.show();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    @FXML
    public void switchToManagingProfile(ActionEvent event)throws IOException {

        try {
            adminViewNodes= FXMLLoader.load(MainApplication.class.getResource("ManagingAdminProfile.fxml"));
            adminStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            adminScene =new Scene(adminViewNodes);
            adminStage.setScene(adminScene);
            adminStage.show();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void switchToManagingDiscounts(ActionEvent event)throws IOException {
        try {
            adminViewNodes= FXMLLoader.load(MainApplication.class.getResource("ManagingDiscounts.fxml"));
            adminStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            adminScene =new Scene(adminViewNodes);
            adminStage.setScene(adminScene);
            adminStage.show();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    @FXML
    public void switchToManagingOrderHistory(ActionEvent event)throws IOException {
        try {
            adminViewNodes= FXMLLoader.load(MainApplication.class.getResource("OrderHistory.fxml"));
            adminStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            adminScene =new Scene(adminViewNodes);
            adminStage.setScene(adminScene);
            adminStage.show();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
