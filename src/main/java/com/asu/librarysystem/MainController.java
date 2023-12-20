package com.asu.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    @FXML
    Button button;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void pressed(ActionEvent event) {

        // try {
        //     FXMLLoader loader = new FXMLLoader(getClass().getResource("Book-View-Details.fxml"));//Book-View-Details.fxml  book-view2.fxml
        //     Parent root = loader.load();

        //     BookController bookc = loader.getController();
        //     bookc.setScene(Library.searchBookByTitle("The Hobbit"));

        //     Scene newScene = new Scene(root);
        //     MainApplication.st.setScene(newScene);
        //     MainApplication.st.show();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("All-Books.fxml"));
           root = loader.load();

           AllBooksController sceneController = loader.getController();
           sceneController.startAllBooksController();
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene= new Scene(root);
           stage.setScene(scene);
           stage.show();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

}