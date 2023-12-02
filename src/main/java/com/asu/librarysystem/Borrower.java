package com.asu.librarysystem;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Borrower extends Account{
    private ArrayList<Transaction> borrowerTransactions;
    public Borrower(int borrowerId, String borrower_name, String Password, int Phone_Number)
    {
        super(borrowerId, borrower_name, Password, Phone_Number);
        borrowerTransactions = new ArrayList<>();
    }

    public void addTransaction (int bookId ,int borrowerId ,int borrowDate ,int returnDate)
    {
        borrowerTransactions.add(new Transaction(bookId ,borrowerId ,borrowDate ,returnDate));
    }
    public int searchTransactions (int borrowerId,int transactionId)
    {
        for (int i = 0; i < borrowerTransactions.size(); i++) {
            if (borrowerTransactions.get(i).getTransactionId() == transactionId)
            {
                if (this.user_id == borrowerId)
                {
                    return borrowerTransactions.get(i).getBorrowerId();
                }
            }
        }
        return -1;
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
