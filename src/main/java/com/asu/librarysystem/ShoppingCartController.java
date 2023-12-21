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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class ShoppingCartController implements Initializable {

    @FXML
    private TableView<Order> mainTable;

    @FXML
    private TableColumn<Order, String> bookColumn;

    @FXML
    private TableColumn<Order, Integer> quantityColumn;
    
    @FXML
    private TableColumn<Order, Double> priceColumn;

    @FXML
    private GridPane root;

    private ObservableList<Order> tableData = FXCollections.observableArrayList();

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("book"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));

        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        quantityColumn.setOnEditCommit((CellEditEvent<Order, Integer> t) -> {
            Order order = ((Order) t.getTableView().getItems().get(t.getTablePosition().getRow()));
            order.setQuantity(t.getNewValue());
            Library.getShoppingCart().updateOrderQuantity(order.getBookId(), t.getNewValue());
            t.getTableView().refresh();
        });

        ArrayList<Order> orders = Library.getShoppingCart().getOrders();

        for (var order : orders) {
            tableData.add(order);
        }

        mainTable.setItems(tableData);
    }
    
    public void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CustomerMain.fxml"));
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(ActionEvent event) {
        ObservableList<Order> selected = mainTable.getSelectionModel().getSelectedItems();
        for (var order : selected) {
            Library.getShoppingCart().deleteBook(order.getBookId());
        }
        mainTable.getItems().removeAll(selected);
    }

    public void proceed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("payment-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
