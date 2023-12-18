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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("book-view.fxml"));
            Parent root = loader.load();

            BookController bookc = loader.getController();
            bookc.setScene(MainApplication.books.get(0));

            Scene newScene = new Scene(root);
            MainApplication.st.setScene(newScene);
            MainApplication.st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}