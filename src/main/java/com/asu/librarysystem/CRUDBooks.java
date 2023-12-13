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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CRUDBooks implements Initializable {
    @FXML
    private Stage adminStage;
    @FXML
    private Scene adminScene;
    @FXML
    private Parent adminViewNodes;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String>  authorNameColumn;
    @FXML
    private TableColumn<Book, Integer> publicationYearColumn;
    @FXML
    private TableColumn<Book, Integer> priceColumn;
    @FXML
    private TableColumn<Book, Integer> ratingColumn;
    @FXML
    private TableColumn<Book, Integer> quantityColumn;
    @FXML
    private TableColumn<Book, Boolean> statusColumn;
    @FXML
    private TextField titleField;
    @FXML
    private TextField  authorNameField;
    @FXML
    private TextField  publicationyearField;
    @FXML
    private TextField  priceField;
    @FXML
    private TextField  ratingField;
    @FXML
    private TextField  coverPathField;
    @FXML
    private TextField  quantityField;
    @FXML
    private CheckBox statusCheckBox;
    @FXML
    private ImageView coverImageView;
    @FXML
    private Image coverImage ;
    @FXML
    private ObservableList<Book> bookList = FXCollections.observableArrayList(
        new Book("Atomic Habits","James Clear",2018,true,25,5,7,"data/covers/2.png")
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initializing columns
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorNameColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publicationYearColumn.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        bookTable.setItems(bookList);

    }
    private void clearFields() {
        titleField.clear();
        authorNameField.clear();
        publicationyearField.clear();
        priceField.clear();
        ratingField.clear();
        quantityField.clear();
        statusCheckBox.setSelected(false);
        coverPathField.clear();

    }
    @FXML
    private void AddBook() {
        // Get input from fields
        String title = titleField.getText();
        String author = authorNameField.getText();
        int year = Integer.parseInt(publicationyearField.getText());
        int price = Integer.parseInt(priceField.getText());
        int rating = Integer.parseInt(ratingField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        boolean status = statusCheckBox.isSelected();
        String coverPath = coverPathField.getText();

        coverImage = new Image(getClass().getResourceAsStream(coverPath));
        coverImageView.setImage(coverImage);

        Book newBook = new Book(title, author, year, status, price, rating, quantity,coverPath);

        bookList.add(newBook);

        // Clear input fields
        clearFields();
    }

    private Book selectedBook;
    @FXML
    private void showBookDetails() {

        selectedBook = bookTable.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {

            titleField.setText(selectedBook.getTitle());
            authorNameField.setText(selectedBook.getAuthor());
            publicationyearField.setText(String.valueOf(selectedBook.getPublicationYear()));
            priceField.setText(String.valueOf(selectedBook.getPrice()));
            ratingField.setText(String.valueOf(selectedBook.getRating()));
            quantityField.setText(String.valueOf(selectedBook.getQuantity()));
            statusCheckBox.setSelected(selectedBook.isStatus());
            coverPathField.setText(selectedBook.getCoverPath());

            coverImage = new Image(getClass().getResourceAsStream(coverPathField.getText()));
            coverImageView.setImage(coverImage);
        }
    }
    @FXML
    private void UpdateBook() {

        selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            // Update selected book with input values
            selectedBook.setTitle(titleField.getText());
            selectedBook.setAuthor(authorNameField.getText());
            selectedBook.setPublicationYear(Integer.parseInt(publicationyearField.getText()));
            selectedBook.setPrice(Integer.parseInt(priceField.getText()));
            selectedBook.setQuantity(Integer.parseInt(quantityField.getText()));
            selectedBook.setStatus(statusCheckBox.isSelected());
            selectedBook.setCover(coverPathField.getText());
            coverImage = new Image(getClass().getResourceAsStream(coverPathField.getText()));
            coverImageView.setImage(coverImage);

            // Refresh TableView
            // I made a similar CRUD as a practice and Update wasn't working and chat GPT and it recommended this statement
            bookTable.refresh();
        }
    }
    @FXML
    private void DeleteBook() {

        selectedBook = bookTable.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {

            bookList.remove(selectedBook);

            clearFields();
        }
    }
    @FXML
    public void switchToManagingAdminMain(ActionEvent event)throws IOException {

        try {
            adminViewNodes= FXMLLoader.load(MainApplication.class.getResource("AdminMain.fxml"));
            adminStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            adminScene =new Scene(adminViewNodes);
            adminStage.setScene(adminScene);
            adminStage.show();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
