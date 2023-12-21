package com.asu.librarysystem;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;

public class Transaction
{
    private static int transactionCount = 0;
    private final int  transactionId;
    private int bookId;
    private String bookName;
    private int borrowerId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Transaction(Book book, int borrowerId)
    {
        this.bookId=book.getId();
        this.borrowerId=borrowerId;
        this.borrowDate=java.time.LocalDate.now();
        this.returnDate=java.time.LocalDate.now().plusMonths(1);
        this.bookName = Library.searchBookById(bookId).getTitle();
        this.transactionId = ++transactionCount;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public static int getTransactionCount() {
        return transactionCount;
    }

    public int getTransactionId()
    { 
        return transactionId; 
    }

    public int getBookId()
    {
        return bookId;
    }

    public int getBorrowerId() 
    {
        return borrowerId;
    }

    public LocalDate getBorrowDate()
    {
        return borrowDate;
    }

    public LocalDate getReturnDate()
    {
        return returnDate;
    }
    public double getFines() {
        if (LocalDate.now().isAfter(returnDate)) {
            return ((double) ChronoUnit.DAYS.between(returnDate,LocalDate.now())) * 5;
        }
        return -1;
    }
}
