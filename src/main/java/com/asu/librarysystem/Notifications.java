package com.asu.librarysystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Notifications {
    private static final Borrower currentBorrower = TransactionsForBorrowersController.getCurrentBorrower();
    public static ArrayList<Integer> booksDueDateIn3Days() {
        ArrayList<Integer> transactionsDueIn3DaysIds = new ArrayList<>();
        for(int i = 0; i < currentBorrower.getBorrowerTransactions().size(); i++) {
            if(ChronoUnit.DAYS.between(LocalDate.now(),currentBorrower.getBorrowerTransactions().get(i).getReturnDate()) <= 3 && ChronoUnit.DAYS.between(LocalDate.now(),currentBorrower.getBorrowerTransactions().get(i).getReturnDate()) > 0)
            {
                transactionsDueIn3DaysIds.add(i);
            }
        }
        return transactionsDueIn3DaysIds;
    }

    public static ArrayList<String> booksOverDueDate() {
        ArrayList<String> booksOverDueDateIds= new ArrayList<>();
        for(int i = 0; i < currentBorrower.getBorrowerTransactions().size(); i++) {
            if(ChronoUnit.DAYS.between(LocalDate.now(), TransactionsForBorrowersController.getCurrentBorrower().getBorrowerTransactions().get(0).getReturnDate()) <= 0) {
                booksOverDueDateIds.add(TransactionsForBorrowersController.getCurrentBorrower().getBorrowerTransactions().get(i).getBookName());
            }
        }
        return booksOverDueDateIds;
    }
    public Boolean availableForPickup() {
        boolean x = false;
        for (int i = 0; i < currentBorrower.getReservedBooks().size(); i++) {
            if(currentBorrower.getReservedBooks().get(i).isAvailable())
            {
                x = true;
            }
        }
        return x;
    }

    public void reservationConfirmation(Book book)
    {
        boolean bool = true;
        for (int i = 0; i < currentBorrower.getReservedBooks().size(); i++) {
            if (currentBorrower.getReservedBooks().get(i).getId() == book.getId()) {
                bool = false;
                break;
            }
        }
        if (bool) {
            currentBorrower.getReservedBooks().add(book);
        }
    }
}
