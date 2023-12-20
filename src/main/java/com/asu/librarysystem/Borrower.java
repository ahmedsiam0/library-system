package com.asu.librarysystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.asu.librarysystem.Library.searchBookById;
import static com.asu.librarysystem.Library.searchBookByTitle;

public class Borrower extends Account
{
    private double borrowerFines = 0;
    private ArrayList<Transaction> borrowerTransactions;
    
    public Borrower( String borrowerName, String password, String PhoneNumber)
    {
        super( borrowerName, password, PhoneNumber);
        borrowerTransactions = new ArrayList<>();
    }

     public void addTransaction (Book book ,int borrowDate ,int returnDate)
    {
        borrowerTransactions.add(new Transaction(book.getId() ,getId() ,borrowDate ,returnDate));
    }
     
     public boolean deleteTransaction (int transactionId) {
        try {
            borrowerTransactions.remove(transactionId);
            return true;
        }
        catch (IndexOutOfBoundsException e)
        {
            //System.out.println("cant delete because the index is out of bound");
            return false;
        }
    }
    
    public int searchTransactions (int transactionId)
    {
        for (int i = 0; i < borrowerTransactions.size(); i++) {
            if (borrowerTransactions.get(i).getTransactionId() == transactionId)
            {
                return borrowerTransactions.get(i).getBorrowerId();
            }
        }
        return -1;
    }

    public double finesIfLate ()
    {
        for (Transaction borrowerTransaction : borrowerTransactions) {
            if (borrowerTransaction.getBorrowerId() == this.getId() && borrowerTransaction.getFines() >= 0) {
                borrowerFines += borrowerTransaction.getFines();
            }
        }
        return borrowerFines;
    }
    public ArrayList<Transaction> copyElementOfArrayList(){
        return borrowerTransactions;
    }

    public  ArrayList<Book> arrayOFTransactionBooks(){
        ArrayList<Book> transactionBooksArrayList=new ArrayList<Book>();
        for(int i=0;i<borrowerTransactions.size();i++) {
            transactionBooksArrayList.add(searchBookById(borrowerTransactions.get(i).getBookId()));
        }
        return transactionBooksArrayList;
    }
//    public void writeTransaction(){
//        try {
//            FileOutputStream write=new FileOutputStream("transaction_data.txt");
//            for (Transaction obj : borrowerTransactions ) {
//                String bookName=searchBookById(obj.getBookId()).getTitle();
//                write.write((obj.getTransactionId()+","+bookName+","+obj.getBorrowerId()+","+obj.getBorrowDate()+","+obj.getReturnDate()+"\n").getBytes());
//            }
//            write.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("can't write");
//        } catch (IOException e) {
//            System.out.println("can't write");
//        }
//    }

//    public void readTransaction(){
//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(new FileInputStream("transaction_data.txt"));
//        } catch (FileNotFoundException e) {
//            System.out.println("can't read");
//        }
//
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            String[] parts = line.split(",");
//            Transaction transaction =new Transaction(searchBookByTitle(parts[1]).getId(),getId(),Integer.valueOf(parts[3]),Integer.valueOf(parts[4]));
//        }
//        scanner1.close();
//
//    }
}
