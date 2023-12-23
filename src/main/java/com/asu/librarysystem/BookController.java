package com.asu.librarysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Stack;

public class BookController implements Initializable {

    @FXML
    VBox mainContainer;
    @FXML
    ImageView bookCover, statusImage, oneStarIcon, twoStarsIcon, threeStarsIcon, fourStarsIcon, fiveStarsIcon, notifyMe;
    @FXML
    Label name, author, releaseDate, price, description, countRatings, totalRating, textAreaCharCount, categories, userName;
    @FXML
    Text warningMessage;
    @FXML
    ProgressBar oneStarBar, twoStarsBar, threeStarsBar, fourStarsBar, fiveStarsBar;
    @FXML
    TextArea reviewTextArea;
    @FXML
    Button reviewSubmitButton;
    @FXML
    ComboBox<Integer> ratingOptions;
    @FXML
    VBox commentSection;
    @FXML
    HBox propertiesButtons;
    @FXML
    GridPane recommendations;

    private Book currentBook;

    private final ObservableList<Integer> optionsList = FXCollections.observableArrayList(1, 2, 3, 4, 5);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ratingOptions.setItems(optionsList);
        notifyMe.setOnMouseClicked(mouseEvent -> {
            Notifications notifications = new Notifications();
            notifications.reservationConfirmation(currentBook);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("You will be notified when book is available.");
            alert.show();
        });
    }

    public void setScene(Book book) throws IOException {
        displayUserName();
        currentBook = book;
        setCover(book.getCoverPath());

        setProperties(book.getTitle(), book.getAuthor(), Integer.toString(book.getPublicationYear())
                      , book.getDescription(), book.getPrice(), book.getQuantity());

        // Library.getReviewHandler().addReview(2, book.getId(), 1, "I don't love this book");
        ArrayList<Integer> ratings = Library.getReviewHandler().getBookRatings(book.getId());
        setRatings(ratings);

        InputStream stream1 = new FileInputStream("data/bookSceneAssets/Rating_icons/1_star.png");
        InputStream stream2 = new FileInputStream("data/bookSceneAssets/Rating_icons/2_stars.png");
        InputStream stream3 = new FileInputStream("data/bookSceneAssets/Rating_icons/3_stars.png");
        InputStream stream4 = new FileInputStream("data/bookSceneAssets/Rating_icons/4_stars.png");
        InputStream stream5 = new FileInputStream("data/bookSceneAssets/Rating_icons/5_stars.png");


        oneStarIcon.setImage(new Image(stream1));
        twoStarsIcon.setImage(new Image(stream2));
        threeStarsIcon.setImage(new Image(stream3));
        fourStarsIcon.setImage(new Image(stream4));
        fiveStarsIcon.setImage(new Image(stream5));

        showRecommendations();


        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 300 && !change.getControlNewText().endsWith("\b")) {
                return null; // Reject the change
            } else {
                return change; // Accept the change
            }
        });

        reviewTextArea.setTextFormatter(formatter);


        ArrayList<Review> comments = Library.getReviewHandler().getBookReviews(book.getId());
        showComments(comments);

    }



    private void setCover(String path) {
        try {
            InputStream inputs = new FileInputStream(path);
            Image cover = new Image(inputs);
            bookCover.setImage(cover);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void setProperties(String name, String author, String releaseDate,String description
            ,int price, int quantity){

        Label nameAttribute = new Label("Title");
        nameAttribute.setStyle("-fx-font-weight: bold;");
        this.name.setText(nameAttribute.getText() + ": " + name);

        Label authorAttribute = new Label("Author");
        authorAttribute.setStyle("-fx-font-weight: bold;");
        this.author.setText(authorAttribute.getText() + ": " + author);
        
        String categoriesText = new String();
        String category = new String();
        for (Category cat : currentBook.getCategories()){
            category = cat.toString();
            categoriesText += category.substring(0, 1) + category.substring(1).toLowerCase() + ", ";
        }
        if (categoriesText.length() > 3) {
            categories.setText("Categories: " + categoriesText.substring(0, categoriesText.length() - 2));
        } else {
            categories.setText("Categories: None");
        }

        Label releaseDateAttribute = new Label("Release Date");
        releaseDateAttribute.setStyle("-fx-font-weight: bold;");
        this.releaseDate.setText(releaseDateAttribute.getText() + ": " + releaseDate);

        Label priceAttribute = new Label("Price");
        priceAttribute.setStyle("-fx-font-weight: bold;");
        this.price.setText(priceAttribute.getText() + ": " + formatPrice(price) + "$");


        this.description.setText(description);

        if (quantity > 0){
            try {
                InputStream imageLocation;
                if (Library.getActiveAccount() instanceof Customer) {
                    imageLocation = new FileInputStream("data/bookSceneAssets/Status_icons/addToCartButton.png");
                } else {
                    imageLocation = new FileInputStream("data/bookSceneAssets/Status_icons/borrowButton.png");
                }
                
                Image available = new Image(imageLocation);

                statusImage.setImage(available);
                statusImage.setAccessibleRole(AccessibleRole.BUTTON);
                statusImage.setCursor(Cursor.HAND);
                statusImage.setOnMouseClicked(this::addToCart);

                propertiesButtons.getChildren().remove(notifyMe);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                InputStream outOfStockImage = new FileInputStream("data/bookSceneAssets/Status_icons/outOfStock.png");
                Image outOfStock = new Image(outOfStockImage);
                statusImage.setImage(outOfStock);
                statusImage.setCursor(Cursor.DEFAULT);
                statusImage.setOnMouseClicked(null);
                if (Library.getActiveAccount() instanceof Borrower) {
                    InputStream notifyMeImage = new FileInputStream("data/bookSceneAssets/Status_icons/notifyMeButton.png");
                    Image notifyMe = new Image(notifyMeImage);
                    this.notifyMe.setImage(notifyMe);
                }


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addToCart(MouseEvent e) {
        if (Library.getActiveAccount() instanceof Customer) {
            Library.getShoppingCart().addBook(currentBook.getId());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Added to Shopping Cart.");
            alert.show();
        } else {
            Borrower account = (Borrower)Library.getActiveAccount();
            account.addTransaction(currentBook);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("You have borrowed this book successfully!\nEnjoy Reading.");
            alert.show();
        }
    }

    private String formatPrice(int price){
        return (--price) + ".99";
    }



    private void setRatings(ArrayList<Integer> ratings){
        int total = 0;
        for (int i = 1; i <= 5; i++){
            total += ratings.get(i);
        }
        this.countRatings.setText(total + " RATINGS");

        oneStarBar.setProgress( changeProgress(total, ratings.get(1)) );
        twoStarsBar.setProgress( changeProgress(total, ratings.get(2)) );
        threeStarsBar.setProgress( changeProgress(total, ratings.get(3)) );
        fourStarsBar.setProgress( changeProgress(total, ratings.get(4)) );
        fiveStarsBar.setProgress( changeProgress(total, ratings.get(5)) );

        String score = calculateScore(ratings, total);
        totalRating.setText(score);
    }

    public double changeProgress(int totalRating, int barRating){
        return ((double)barRating / totalRating);
    }

    public String calculateScore(ArrayList<Integer> reviews, int total){
        if (total == 0){
            return "0";
        }

        double allRatings = reviews.get(1) + reviews.get(2) * 2 + reviews.get(3) * 3 + reviews.get(4) * 4
                + reviews.get(5) * 5;

        double score = allRatings / (double) (total * 5);

        score = score * 5;


        return String.format("%.1f", score);
    }


    private void showRecommendations() throws IOException {
        ArrayList<Book> availableBooks = Library.copyElementOfArrayList();
        int counter = 0;
        for (Book book : availableBooks){
            if (counter == 6) break;

            if (findingCommonCategories(book) && book.getId() != currentBook.getId() && book.isAvailable()){
                FXMLLoader fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Book-View-Card.fxml"));

                VBox CardOfBook=fxmlLoader.load();

                BooksViewCard recommendedBook = fxmlLoader.getController();
                recommendedBook.setData(book);

                recommendations.add(CardOfBook, counter,0);
                GridPane.setMargin(CardOfBook,new Insets(10));
                counter++;
            }
        }
    }

    private boolean findingCommonCategories(Book book){
        for (Category category : book.getCategories()) {
            for (Category category2 : currentBook.getCategories()) {
                if (category == category2) {
                    return true;
                }
            }
        }

        return false;
    }



    public void setTypedCharacter(KeyEvent event){
        int inputTextLength = reviewTextArea.getLength();

        int maxCommentLenght = 300;
        if (maxCommentLenght - inputTextLength <= 0){
            displayCharLimitReachedWarning();
        } else {
            warningMessage.setText("");

            textAreaCharCount.setText( Integer.toString(maxCommentLenght - inputTextLength) );
        }
    }

    private void displayCharLimitReachedWarning(){
        textAreaCharCount.setText("0");
        String maxCharLimitReached = "Warning: Character limit reached";
        warningMessage.setText(maxCharLimitReached);
    }








    public void submitReview(ActionEvent event) throws FileNotFoundException {
        if (choseNoRating()){
            displayNoRatingSelectedWarning();
        } else {
            Account activeUser = Library.getActiveAccount();
            Library.getReviewHandler().addReview(activeUser.getId(), currentBook.getId(),
                    ratingOptions.getValue(), reviewTextArea.getText());
            reviewTextArea.setText("");
            warningMessage.setFill(Color.GREEN);
            warningMessage.setText("Review added successfully");
            System.out.println("review added");

            showComments(Library.getReviewHandler().getBookReviews(currentBook.getId()));
            setRatings(Library.getReviewHandler().getBookRatings(currentBook.getId()));
        }

    }

    private boolean choseNoRating(){
        return ratingOptions.getValue() == null;
    }

    private void displayNoRatingSelectedWarning(){
        String noRatingSelected = "Warning: you haven't chosen a rating";
        warningMessage.setText(noRatingSelected);
    }




    private void showComments(ArrayList<Review> comments) throws FileNotFoundException {
        commentSection.getChildren().clear();

        InputStream stream1 = new FileInputStream("data/bookSceneAssets/Rating_icons/oneStarRate.png");
        InputStream stream2 = new FileInputStream("data/bookSceneAssets/Rating_icons/twoStarsRate.png");
        InputStream stream3 = new FileInputStream("data/bookSceneAssets/Rating_icons/threeStarsRate.png");
        InputStream stream4 = new FileInputStream("data/bookSceneAssets/Rating_icons/fourStarsRate.png");
        InputStream stream5 = new FileInputStream("data/bookSceneAssets/Rating_icons/fiveStarsRate.png");
        InputStream stream6 = new FileInputStream("data/bookSceneAssets/user-profile.png");

        Image oneStar = new Image(stream1);
        Image twoStars = new Image(stream2);
        Image threeStars = new Image(stream3);
        Image fourStars = new Image(stream4);
        Image fiveStars = new Image(stream5);
        Image userIcon = new Image(stream6);

        for (var comment : comments){
            VBox container = new VBox();
            container.setSpacing(20d);
            container.setMaxWidth(1214);
            container.setAlignment(Pos.CENTER_LEFT);


            HBox innerContainer = new HBox();
            innerContainer.setSpacing(20d);
            innerContainer.setMaxWidth(1214);
            innerContainer.setAlignment(Pos.CENTER_LEFT);


            Label username = new Label();


//            ArrayList<Borrower> borrowers = Library.getBorrowers();
//            ArrayList<Customer> customers = Library.getCustomers();
//            if(Library.searchCustomerByID(comment.getReviewerId())==null){
//                name= Library.searchBorrwerByID(comment.getReviewerId()).getUserName();
//            }
//            else {
//                name= Library.searchCustomerByID(comment.getReviewerId()).getUserName();
//            }

            String name=Library.searchAccountById(comment.getReviewerId()).getUserName();

            username.setText(name);//String.valueOf(comment.getReviewerId())
            username.setStyle("-fx-font-weight: 200;");
            username.setStyle("-fx-font-size: 20px;");


            ImageView rate = new ImageView();
            rate.setFitWidth(190);//228
            rate.setFitHeight(50);
            if (comment.getRating() == 1){
                rate.setImage(oneStar);
            } else if (comment.getRating() == 2){
                rate.setImage(twoStars);
            } else if (comment.getRating() == 3){
                rate.setImage(threeStars);
            } else if (comment.getRating() == 4){
                rate.setImage(fourStars);
            } else {
                rate.setImage(fiveStars);
            }

            ImageView user = new ImageView(userIcon);

            Label text = new Label();
            text.setStyle("-fx-font-size: 15px;");
            text.setText(comment.getText());

            innerContainer.getChildren().addAll(user, username, rate);
            container.getChildren().addAll(innerContainer, text);

            commentSection.getChildren().add(container);
        }
    }

    
    public void backButton(ActionEvent event){
        try {
            Stack<Book> previousBooks = Library.getPreviousBooks();
            previousBooks.pop();

            FXMLLoader loader;
            Parent root;
            
            if (previousBooks.isEmpty()) {
                if (Library.getLastViewer().equals("AllBooks")) {
                    loader = new FXMLLoader(getClass().getResource("All-Books.fxml"));
                    root = loader.load();
     
                    AllBooksController sceneController = loader.getController();
                    sceneController.startAllBooksController();
                } else {
                    loader = new FXMLLoader(getClass().getResource("My-Books.fxml"));
                    root = loader.load();
     
                    MyBooksController sceneController = loader.getController();
                    sceneController.startMyBooksController();
                }
            } else {
                loader = new FXMLLoader(getClass().getResource("Book-View-Details.fxml"));
                root = loader.load();
                BookController sceneController = loader.getController();
                sceneController.setScene(previousBooks.peek());
            }

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void displayUserName(){
        Account account = Library.getActiveAccount();
        if (account instanceof Customer) {
            Customer customer =(Customer) account;
            userName.setText(customer.getUserName());
        }
        else if(account instanceof Borrower){
            Borrower borrower =(Borrower) account;
            userName.setText(borrower.getUserName());
        }
    }
}
