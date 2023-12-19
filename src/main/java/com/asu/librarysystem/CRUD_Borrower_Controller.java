package com.asu.librarysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;

import static com.asu.librarysystem.Library.copyElementOfArrayList;

public class CRUD_Borrower_Controller implements Initializable {
    @FXML
    private TableColumn<Borrower, String> borrowerName;
    @FXML
    private TableView<Borrower> borrowersTable;
    @FXML
    private TableColumn<Borrower, Integer> idcolumn;
    @FXML
    private TableColumn<Borrower, String> borrowerPhoneNumber;
    @FXML
    private TableColumn<Borrower, Integer> borrowerNumberofBooks;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button backButton;

    Stage stage;
    Parent root;
    Scene scene;

    public Scene checkScenes = null;
    public Parent roots;

    public void scene(ActionEvent event, String Link) throws IOException {
        root = FXMLLoader.load(getClass().getResource(Link));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void switch_To_Signup(ActionEvent event) throws IOException {

        scene(event, "SIGNup.fxml");
//        cameFromCRUD.setAdmin(true);

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUD_Borrower-view.fxml"));
//        roots = loader.load();
////        SignupController Sign = loader.getController();
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    }


    public void switch_To_Main(ActionEvent event) throws IOException {
        scene(event, "Update.fxml");//Admin Main fxml file
    }

    public void switch_To_Delete_view(ActionEvent event) throws IOException {
        scene(event, "Delete-view.fxml");
    }

    public void switch_To_Update_view(ActionEvent event) throws IOException {
        scene(event, "Update.fxml");
//        return
    }

    ObservableList<Borrower> List = FXCollections.observableArrayList(Library.borrowers);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idcolumn.setCellValueFactory(new PropertyValueFactory<Borrower, Integer>("id"));
        borrowerName.setCellValueFactory(new PropertyValueFactory<Borrower, String>("userName"));
        borrowerNumberofBooks.setCellValueFactory(new PropertyValueFactory<Borrower, Integer>("noOfBooks"));
        borrowerPhoneNumber.setCellValueFactory(new PropertyValueFactory<Borrower, String>("phoneNumber"));
        System.out.println(Library.borrowers);
//        List.addAll(Arrays.asList(Library.borrowers));
        borrowersTable.setItems(List);
    }
}
