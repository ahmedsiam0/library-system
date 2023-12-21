package com.asu.librarysystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReviewHandler {
    private static ArrayList<Review> reviews;
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
    public void addReview(Review review) {
        addReview(review.getId(),review.getBookId(),review.getRating(),review.getText());
    }

    public static void writeReviews(){
        try {
            FileOutputStream write1=new FileOutputStream("data/datafiles/reviews_data.txt");
            for (Review obj : reviews) {
                write1.write((obj.getIdCounter()+",,,"+ obj.getId()+",,,"+ obj.getReviewerId()+",,,"+obj.getBookId()+",,,"+obj.getRating()+",,,"+obj.getText()+"\n").getBytes());
            }
            write1.close();
        } catch (FileNotFoundException e) {
            System.out.println("can't write");
        } catch (IOException e) {
            System.out.println("can't write");
        }
    }

    public static void readReviews(){
        Scanner scanner1 = null;
        try {
            scanner1 = new Scanner(new FileInputStream("data/datafiles/reviews_data.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("can't read");
        }

        while (scanner1.hasNextLine()) {
            String line1 = scanner1.nextLine();
            String[] parts1 = line1.split(",,,");
            Review review =new Review(Integer.valueOf(parts1[1]),Integer.valueOf(parts1[2]),Integer.valueOf(parts1[3]),Integer.valueOf(parts1[4]),parts1[5]);
            review.setIdCounter(Integer.valueOf(parts1[0]));
            addReview(review);
        }
        scanner1.close();
    }
}
