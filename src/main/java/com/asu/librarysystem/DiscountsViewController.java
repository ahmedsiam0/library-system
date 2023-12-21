package com.asu.librarysystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DiscountsViewController implements Initializable {
    @FXML
    private TableView<Discount> mainTable;

    @FXML
    private TableColumn<Discount, String> codeColumn;

    @FXML
    private TableColumn<Discount, Double> discountColumn;

    @FXML
    private GridPane root;

    @FXML
    private TextField addDiscount;

    private Stage stage;

    private ObservableList<Discount> tableData = FXCollections.observableArrayList();

    private static String discountInitial = "Lucky";
    private static int discountCounter = 0;

    public void delete(ActionEvent event) {
        ObservableList<Discount> selected = mainTable.getSelectionModel().getSelectedItems();
        for (var discount : selected) {
            Library.getDiscountHandler().deleteDiscount(discount.getCode());
        }
        mainTable.getItems().removeAll(selected);
    }
    public void add(ActionEvent event) {
        try {
            String discountCode = discountInitial + discountCounter;
            double discount = Double.parseDouble(addDiscount.getText());

            if (discount <= 0.0 || discount > 100.0)
                throw new Exception();

            tableData.add(new Discount(discountCode, discount));
            Library.getDiscountHandler().addDiscount(discountCode, discount);
            
            discountCounter++;
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Only a number more than 0 and less than or equal 100 is allowed");
            alert.show();
        }
    }
    public void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminMain.fxml"));
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codeColumn.setCellValueFactory(new PropertyValueFactory<Discount, String>("code"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<Discount, Double>("discount"));

        

        ArrayList<Discount> discounts = Library.getDiscountHandler().getDiscounts();

        for (var discount : discounts) {
            tableData.add(discount);
        }

        

        mainTable.setItems(tableData);
    }
}
