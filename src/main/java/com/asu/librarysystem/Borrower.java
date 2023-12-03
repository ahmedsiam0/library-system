package com.asu.librarysystem;

import java.util.ArrayList;

public class Borrower extends Account{
    private double borrowerFines = 0;
    private ArrayList<Transaction> borrowerTransactions;
    public Borrower(int borrowerId, String borrower_name, String Password, int Phone_Number)
    {
        super(borrowerId, borrower_name, Password, Phone_Number);
        borrowerTransactions = new ArrayList<>();
    }
    public void addTransaction (Book book ,int borrowDate ,int returnDate)
    {
        borrowerTransactions.add(new Transaction(book.getId() ,this.user_id ,borrowDate ,returnDate));
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
            if (borrowerTransaction.getBorrowerId() == this.user_id && borrowerTransaction.getFines() >= 0) {
                borrowerFines += borrowerTransaction.getFines();
            }
        }
        return borrowerFines;
    }
}
