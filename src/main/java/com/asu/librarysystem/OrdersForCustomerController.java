package com.asu.librarysystem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OrdersForCustomerController implements Initializable {

    @FXML
    private TableView<Order> mainTable;

    @FXML
    private TableColumn<Order, String> bookColumn;

    @FXML
    private TableColumn<Order, String> discountColumn;

    @FXML
    private TableColumn<Order, Double> priceColumn;

    @FXML
    private TableColumn<Order, Integer> quantityColumn;

    private ObservableList<Order> tableData = FXCollections.observableArrayList();

    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("book"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("discountCode"));
        
        ArrayList<Order> orders = ((Customer)Library.getActiveAccount()).getOrders();

        for (var order : orders) {
            tableData.add(order);
        }

        mainTable.setItems(tableData);
    }

}
