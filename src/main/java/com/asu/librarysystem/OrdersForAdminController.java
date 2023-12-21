package com.asu.librarysystem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OrdersForAdminController implements Initializable {

    @FXML
    private TableView<OrderRow> mainTable;

    @FXML
    private TableColumn<OrderRow, String> ownerColumn;

    @FXML
    private TableColumn<OrderRow, String> bookColumn;

    @FXML
    private TableColumn<OrderRow, String> discountColumn;

    @FXML
    private TableColumn<OrderRow, Double> priceColumn;

    @FXML
    private TableColumn<OrderRow, Integer> quantityColumn;

    private ObservableList<OrderRow> tableData = FXCollections.observableArrayList();

    @FXML
    void back(ActionEvent event) {
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
        ownerColumn.setCellValueFactory(new PropertyValueFactory<OrderRow, String>("owner"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<OrderRow, String>("book"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<OrderRow, Integer>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<OrderRow, Double>("price"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<OrderRow, String>("discountCode"));
        
        ArrayList<Customer> customers = Library.getCustomers();
        ArrayList<Order> orders;

        for (var customer : customers) {
            orders = customer.getOrders();
            for (var order : orders) {
                tableData.add(new OrderRow(order, customer.getUserName()));
            }
        }

        mainTable.setItems(tableData);
    }

}
