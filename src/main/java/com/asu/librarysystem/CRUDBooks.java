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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private ImageView coverImageView;
    @FXML
    private Image coverImage ;
    @FXML
    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    @FXML
    private TextField categoriesField;
    @FXML
    private TextArea descriptionBox;;

    @FXML
    private CheckBox dramaBox;

    @FXML
    private CheckBox horrorBox;

    @FXML
    private CheckBox novelBox;
    @FXML
    private CheckBox selfDevBox;
    @FXML
    private CheckBox adventureBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorNameColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publicationYearColumn.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        ArrayList<Book> books = Library.getBooks();

        for (var book : books) {
            bookList.add(book);
        }

        booksTable.setItems(bookList);

    }
    private void clearFields() {
        titleField.clear();
        authorNameField.clear();
        publicationYearField.clear();
        priceField.clear();
        quantityField.clear();
        coverPathField.clear();
        descriptionBox.clear();
        adventureBox.setSelected(false);
        dramaBox.setSelected(false);
        horrorBox.setSelected(false);
        novelBox.setSelected(false);
        selfDevBox.setSelected(false);
    }

    private Category[] checkedCategories() {
        ArrayList<Category> checkedCategories = new ArrayList<>();

        if (dramaBox.isSelected()) {
            checkedCategories.add(Category.DRAMA);
        }
        if (adventureBox.isSelected()) {
            checkedCategories.add(Category.ADVENTURE);
        }
        if (selfDevBox.isSelected()) {
            checkedCategories.add(Category.SELFDEVELOPMENT);
        }
        if (horrorBox.isSelected()) {
            checkedCategories.add(Category.HORROR);
        }
        if (novelBox.isSelected()) {
            checkedCategories.add(Category.NOVEL);
        }
        Category[] cat = new Category[checkedCategories.size()];
        cat = checkedCategories.toArray(cat);
        return cat;
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
            String description = descriptionBox.getText();
            
            Book newBook = new Book(title, author, year, true, price, quantity,
                                description,coverPath, checkedCategories());
        
            InputStream fileLocation;
            try {
                fileLocation = new FileInputStream(coverPathField.getText());
                coverImage = new Image(fileLocation);
                coverImageView.setImage(coverImage);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            bookList.add(newBook);
            Library.addBook(newBook);

            clearFields();
            booksTable.refresh();
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
            coverPathField.setText(selectedBook.getCoverPath());
            descriptionBox.setText(selectedBook.getDescription());


            adventureBox.setSelected(false);
            dramaBox.setSelected(false);
            horrorBox.setSelected(false);
            novelBox.setSelected(false);
            selfDevBox.setSelected(false);

            for (Category cat : selectedBook.getCategories()) {
                if (cat == Category.ADVENTURE) {
                    adventureBox.setSelected(true);
                } else if (cat == Category.DRAMA) {
                    dramaBox.setSelected(true);
                } else if (cat == Category.HORROR) {
                    horrorBox.setSelected(true);
                } else if (cat == Category.NOVEL) {
                    novelBox.setSelected(true);
                } else if (cat == Category.SELFDEVELOPMENT) {
                    selfDevBox.setSelected(true);
                }
            }

            InputStream fileLocation;
            try {
                fileLocation = new FileInputStream(coverPathField.getText());
                coverImage = new Image(fileLocation);
                coverImageView.setImage(coverImage);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
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
                selectedBook.setDescription(descriptionBox.getText());
                ArrayList<Category> al = new ArrayList<Category>(Arrays.asList(checkedCategories()));
                selectedBook.setCategories(al);

                InputStream fileLocation;
                try {
                    fileLocation = new FileInputStream(coverPathField.getText());
                    coverImage = new Image(fileLocation);
                    coverImageView.setImage(coverImage);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

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
            Library.removeBook(selectedBook);

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
