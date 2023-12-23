package com.asu.librarysystem;

public class Review {
    private static int idCounter = 0;
    private final int id;
    private int reviewerId;
    private int bookId;
    private int rating;
    private String text;

    public Review(int reviewerId, int bookId, int rating, String text) {
        id = ++idCounter;
        this.reviewerId = reviewerId;
        this.bookId = bookId;
        setRating(rating);
        this.text = text;
    }
    public Review(int id, int reviewerId, int bookId, int rating, String text) {
        this.id = id;
        this.reviewerId = reviewerId;
        this.bookId = bookId;
        setRating(rating);
        this.text = text;
    }
    public Review(Review review) {
        this(review.getId(), review.getReviewerId(), review.getBookId(),review.getRating(),review.getText());
    }

    public int getIdCounter(){
        return idCounter;
    }
    public static void setIdCounter(int idCounter){
        Review.idCounter=idCounter;
    }

    public void setRating(int rating) {
        if (rating > 5)
            this.rating = 5;
        else if (rating < 1)
            this.rating = 1;
        else
            this.rating = rating;
    }
    public int getRating() {
        return rating;
    }
    public int getId() {
        return id;
    }
    public int getBookId() {
        return bookId;
    }
    public int getReviewerId() {
        return reviewerId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
