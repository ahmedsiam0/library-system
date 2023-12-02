package com.asu.librarysystem;

import java.util.ArrayList;

public class Borrower extends Account{
    private ArrayList<Transaction> borrowerTransactions;
    public Borrower(int borrowerId, String borrower_name, String Password, int Phone_Number)
    {
        super(borrowerId, borrower_name, Password, Phone_Number);
        borrowerTransactions = new ArrayList<>();
    }

    public void addTransaction (int bookId ,int borrowDate ,int returnDate)
    {
        borrowerTransactions.add(new Transaction(bookId ,this.user_id ,borrowDate ,returnDate));
    }

    public void deleteTransaction (int transactionId)
    {
        borrowerTransactions.remove(transactionId);
    }

    public Transaction searchTransaction (int transactionId)
    {
        return borrowerTransactions.get(transactionId);
    }

    public int finesIfLate (int borrowerId)
    {
        if (borrowerTransactions.get(borrowerId).getReturnDate() > (java.time.LocalDate.now().getYear()*10000+java.time.LocalDate.now().getMonthValue()*100+java.time.LocalDate.now().getDayOfMonth()))
        {
            return borrowerTransactions.get(borrowerId).getFines();
        }
        return -1;
    }
}
