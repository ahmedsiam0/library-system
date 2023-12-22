package com.asu.librarysystem;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Date;
import java.util.Objects;

public class Borrower extends Account {
    private double borrowerFines = 0;
    private ArrayList<Transaction> borrowerTransactions;
    private ArrayList<Book> reservedBooks = new ArrayList<>();
    public boolean assignBefore;
    private int noOfBooks;
    private boolean isAdmin = false;


    public Borrower(String borrowerName, String password, String PhoneNumber) {
        super(borrowerName, password, PhoneNumber);
        borrowerTransactions = new ArrayList<>();
        assignBefore = false;
    }

    public Borrower(int id,String borrowerName, String password, String PhoneNumber) {
        super(id,borrowerName, password, PhoneNumber);
        borrowerTransactions = new ArrayList<>();
        assignBefore = false;
    }

    public void addReservation(Book book)
    {
        reservedBooks.add(book);
    }

    public ArrayList<Book> getReservedBooks() {
        return reservedBooks;
    }

    public void addTransaction(Book book) {
        borrowerTransactions.add(new Transaction(book, getId()));
        book.setQuantity(book.getQuantity() - 1);
        boolean bool = false;
        for (int i = 0; i < getReservedBooks().size(); i++) {
            if (getReservedBooks().get(i).getId() == book.getId()) {
                bool = true;
                break;
            }
        }
        if (bool) {
            getReservedBooks().remove(book);
        }
    }
    public void addTransaction(int bookId, int borrowerId, LocalDate borrowDate, LocalDate returnDate) {
        borrowerTransactions.add(new Transaction(bookId,borrowerId, getId(), borrowDate, returnDate));
    }
    public void addTransaction(Transaction transaction){
        addTransaction(transaction.getBookId(),transaction.getBorrowerId(),transaction.getBorrowDate(),transaction.getReturnDate());
    }

    public boolean deleteTransaction(int transactionId) {
        try {
            borrowerTransactions.remove(transactionId);
            return true;
        } catch (IndexOutOfBoundsException e) {
            //System.out.println("cant delete because the index is out of bound");
            return false;
        }
    }

    public int searchTransactions(int transactionId) {
        for (int i = 0; i < borrowerTransactions.size(); i++) {
            if (borrowerTransactions.get(i).getTransactionId() == transactionId) {
                return borrowerTransactions.get(i).getBorrowerId();
            }
        }
        return -1;
    }

    public double finesIfLate() {
        for (Transaction borrowerTransaction : borrowerTransactions) {
            if (borrowerTransaction.getBorrowerId() == this.getId() && borrowerTransaction.getFines() >= 0) {
                borrowerFines += borrowerTransaction.getFines();
            }
        }
        return borrowerFines;
    }

    public void setAssignBefore(boolean assignBefore) {
        if (!assignBefore)
            this.assignBefore = true;
        else
            this.assignBefore = false;
    }


    public ArrayList<Transaction> getBorrowerTransactions() {
        return borrowerTransactions;
    }

    public int getNoOfBooks() {
        this.noOfBooks = this.getBorrowerTransactions().size();
        return noOfBooks;
    }

    public boolean getAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
         this.isAdmin = admin;
    }

    public  ArrayList<Book> arrayOFTransactionBooks(){
        ArrayList<Book> transactionBooksArrayList=new ArrayList<Book>();
        for(int i=0;i<borrowerTransactions.size();i++) {
            transactionBooksArrayList.add(Library.searchBookById(borrowerTransactions.get(i).getBookId()));
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
