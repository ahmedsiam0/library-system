package com.asu.librarysystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class PaymentViewController implements Initializable {

    @FXML
    private ToggleGroup paymentMethod;

    @FXML
    private ToggleButton cardToggle;

    @FXML
    private ToggleButton cashToggle;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cvvField;

    @FXML
    private TextField discountTextField;

    @FXML
    private TextField paymentAddressField;

    @FXML
    private Label totalPriceLabel;

    @FXML
    void applyDiscount(ActionEvent event) {
        String discountCode = discountTextField.getText();
        double discount = Library.getDiscountHandler().getDiscount(discountCode);
        if (discount == -1.0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Invalid Discount Code!");
            alert.show();
        } else {
            Library.getShoppingCart().setDiscount(discountCode);
            totalPriceLabel.setText("Total price: " + Library.getShoppingCart().getTotalPrice() + "$");
        }
    }

    @FXML
    void cardPayment(ActionEvent event) {
        cardNumberField.setDisable(false);
        cvvField.setDisable(false);
        paymentAddressField.setDisable(true);
    }

    @FXML
    void cashPayment(ActionEvent event) {
        cardNumberField.setDisable(true);
        cvvField.setDisable(true);
        paymentAddressField.setDisable(false);
    }

    @FXML
    void finish(ActionEvent event) throws IOException {
        Toggle toggle = paymentMethod.getSelectedToggle();
        PaymentMethod payment;

        if (toggle.equals(cardToggle)) {
            payment = new CardPayment(cardNumberField.getText(), cvvField.getText());
        } else {
            payment = new CashPayment(paymentAddressField.getText());
        }
        ShoppingCart cart = Library.getShoppingCart();
        cart.setpaymentMethod(payment);
        Boolean success = payment.pay();

        if (success) {
            cart.completePurchase();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Purchase Done.\n Enjoy reading.");
            alert.show();
            back(event);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Invalid info!\nCard Number should be 16 digit length (if required)\nCVV 3 digit (if required)\naddress shouldn't be empty (if required)");
            alert.show();
        }
    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shopping-cart-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        totalPriceLabel.setText("Total price: " + Library.getShoppingCart().getTotalPrice() + "$");
        cardPayment(null);
    }

}
