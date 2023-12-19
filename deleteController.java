package com.asu.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class deleteController {

    @FXML
    private TextField deleteID;

    @FXML
    private Button deleteButton;

    @FXML
    private Button cancelButton;
    Stage stage;
    Parent root;
    Scene scene;

    public void scene(ActionEvent event, String Link) throws IOException {
        root = FXMLLoader.load(getClass().getResource(Link));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void switch_To_CRUD_Borrower(ActionEvent event) throws IOException {
        scene(event, "CRUD_Borrower-view.fxml");
    }


    public void saveDelete(ActionEvent event) throws IOException {

        Borrower B = Library.searchBorrwerByID(Integer.parseInt(deleteID.getText()));
        if (B != null) {
            Library.removeBorrower(B);
            scene(event, "CRUD_Borrower-view.fxml");
        } else {
            deleteID.setPromptText("Invalid ID");
        }
    }
}
