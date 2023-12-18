package com.asu.librarysystem;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.ArrayList;

public class Borrower extends Account{
    private double borrowerFines = 0;
    private ArrayList<Transaction> borrowerTransactions;

    public Borrower( String borrower_name, String Password, String Phone_Number)
    {
        super( borrower_name, Password, Phone_Number);
        borrowerTransactions = new ArrayList<>();
    }
    public void addTransaction (Book book) {
        try{
            borrowerTransactions.add(new Transaction(book.getId(), getId(), java.time.LocalDate.now(), java.time.LocalDate.now().plusMonths(1)));
        }
        catch (NullPointerException e){
            System.out.println("Can't find the book called \"" + book.getTitle() + "\" in our Library");
            System.out.println("Please add the Book to the Library and try again");
        }
    }

    public void deleteTransaction (int transactionId) {
        try {
            borrowerTransactions.remove(transactionId);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("cant delete because the index is out of bound");
        }
    }

    public Transaction searchTransaction (int transactionId) {
        try {
            return borrowerTransactions.get(transactionId);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("cant search because the index is out of bound");
            return null;
        }
    }


    public double finesIfLate ()
    {
        for (Transaction borrowerTransaction : borrowerTransactions) {
            if (borrowerTransaction.getBorrowerId() == getId() && borrowerTransaction.getFines() >= 0) {
                borrowerFines += borrowerTransaction.getFines();
            }
        }
        return borrowerFines;
    }

    public ArrayList<Transaction> getBorrowerTransactions() {
        return borrowerTransactions;
    }
}
