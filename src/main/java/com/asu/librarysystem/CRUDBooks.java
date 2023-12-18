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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;

public class CRUDBooks implements Initializable {
    @FXML
    private Stage adminStage;
    @FXML
    private Scene adminScene;
    @FXML
    private Parent adminViewNodes;
    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String>  authorNameColumn;
    @FXML
    private TableColumn<Book, Integer> publicationYearColumn;
    @FXML
    private TableColumn<Book, Integer> priceColumn;
    @FXML
    private TableColumn<Book, Integer> quantityColumn;
    @FXML
    private TextField titleField;
    @FXML
    private TextField  authorNameField;
    @FXML
    private TextField  publicationYearField;
    @FXML
    private TextField  priceField;
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
    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorNameColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publicationYearColumn.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        booksTable.setItems(bookList);

    }
    private void clearFields() {
        titleField.clear();
        authorNameField.clear();
        publicationYearField.clear();
        priceField.clear();
        quantityField.clear();
        statusCheckBox.setSelected(false);
        coverPathField.clear();
    }
    @FXML
    private void AddBook() {
        try {
            String title = titleField.getText();
            String author = authorNameField.getText();
            int year = Integer.parseInt(publicationYearField.getText());
            int price = Integer.parseInt(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String coverPath = coverPathField.getText();

            coverImage = new Image(coverPath);
            coverImageView.setImage(coverImage);

        Book newBook = new Book(title, author, year, true, price, quantity,
                                "",coverPath, new Category[]{Category.HORROR});
        
            bookList.add(newBook);

            clearFields();

        }catch (Exception addingBookException){
            System.out.println(addingBookException.getCause());
        }

    }

    private Book selectedBook;
    @FXML
    private void showBookDetails() {

        selectedBook = booksTable.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {

            titleField.setText(selectedBook.getTitle());
            authorNameField.setText(selectedBook.getAuthor());
            publicationYearField.setText(String.valueOf(selectedBook.getPublicationYear()));
            priceField.setText(String.valueOf(selectedBook.getPrice()));
            quantityField.setText(String.valueOf(selectedBook.getQuantity()));
            statusCheckBox.setSelected(selectedBook.isAvailable());
            coverPathField.setText(selectedBook.getCoverPath());

            coverImage = new Image(coverPathField.getText());
            coverImageView.setImage(coverImage);
        }
    }
    @FXML
    private void UpdateBook() {
        try {
            selectedBook = booksTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                // Update selected book with input values
                selectedBook.setTitle(titleField.getText());
                selectedBook.setAuthor(authorNameField.getText());
                selectedBook.setPublicationYear(Integer.parseInt(publicationYearField.getText()));
                selectedBook.setPrice(Integer.parseInt(priceField.getText()));
                selectedBook.setQuantity(Integer.parseInt(quantityField.getText()));
                selectedBook.setCover(coverPathField.getText());
                coverImage = new Image(coverPathField.getText());
                coverImageView.setImage(coverImage);

                // Refresh TableView
                // I made a similar CRUD as a practice and Update wasn't working and I asked chatGPT it recommended this statement
                booksTable.refresh();
            }
        }catch (Exception updatingException){
            System.out.println(updatingException.getCause());
        }

    }
    @FXML
    private void DeleteBook() {

        selectedBook = booksTable.getSelectionModel().getSelectedItem();

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

        }catch (Exception switchingScenesException){
            System.out.println(switchingScenesException.getCause());
        }
    }
}
