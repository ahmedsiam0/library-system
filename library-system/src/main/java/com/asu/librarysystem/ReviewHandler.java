package com.asu.librarysystem;

import java.util.ArrayList;
import java.util.HashMap;

public class ReviewHandler {
    private ArrayList<Review> reviews;
    private HashMap<Integer, ArrayList<Integer>> bookRatings;

    public ReviewHandler() {
        reviews = new ArrayList<Review>();
        bookRatings = new HashMap<Integer, ArrayList<Integer>>();
    }
    
    public void addReview(int reviewerId, int bookId, int rating, String text) {
        Review review = findReview(reviewerId, bookId);
        if (review.getReviewerId() != -1) {
            updateReview(reviewerId, bookId, rating, text);
            return;
        }
        review = new Review(reviewerId, bookId, rating, text);

        reviews.add(review);
        if (!bookRatings.containsKey(bookId)) {
            bookRatings.put(bookId, new ArrayList<Integer>());
            for (int i = 0; i < 6; i++)
                bookRatings.get(bookId).add(0);
        }
        addBookRating(bookId, review.getRating());
    }

    public void updateReview(int reviewerId, int bookId, int rating, String text) {
        Review review = findReview(reviewerId, bookId);
        if (review.getRating() != rating) {
            removeBookRating(bookId, review.getRating());
            review.setRating(rating);
            addBookRating(bookId, review.getRating());
        }
        if (!review.getText().equals(text)) {
            review.setText(text);
        }
    }
    public void deleteReview(int reviewerId, int bookId) {
        int index = findReviewIndex(reviewerId, bookId);
        if (index == -1)
            return;
        Review review = reviews.get(index);
        removeBookRating(bookId, review.getRating());
        reviews.remove(index);
    }
    public ArrayList<Review> getBookReviews(int bookId) {
        ArrayList<Review> bookReviews = new ArrayList<Review>();

        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getBookId() == bookId) {
                bookReviews.add(new Review(reviews.get(i)));
            }
        }

        return bookReviews;
    }

    /**
     * returns an ArrayList of 6 elements (first one with index 0 is always 0)
     * that contains number of reviewers that gave a specific rating (from 1 to 5)
     * where the index is the rating and the value is the number of reviewers who gave this rating.
     */
    public ArrayList<Integer> getBookRatings(int bookId) {
        return (new ArrayList<Integer>(bookRatings.get(bookId)));
    }

    public String getReviewText(int reviewerId, int bookId) {
        return (new String(findReview(reviewerId, bookId).getText()));
    }

    public int getReviewRating(int reviewerId, int bookId) {
        return findReview(reviewerId, bookId).getRating();
    }
    
    private void addBookRating(int bookId, int rating) {
        Integer number = bookRatings.get(bookId).get(rating);
        bookRatings.get(bookId).set(rating, number + 1);
    }
    private void removeBookRating(int bookId, int rating) {
        Integer number = bookRatings.get(bookId).get(rating);
        bookRatings.get(bookId).set(rating, number - 1);
    }
    private int findReviewIndex(int reviewerId, int bookId) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getReviewerId() == reviewerId && reviews.get(i).getBookId() == bookId) {
                return i;
            }
        }
        return -1;
    }
    private Review findReview(int reviewerId, int bookId) {
        int index = findReviewIndex(reviewerId, bookId);
        if (index == -1)
            return (new Review(-1, -1, -1, ""));
        else
            return reviews.get(index);
    }
}
