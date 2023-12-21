package com.asu.librarysystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Library {
    protected static ArrayList<Book> books = new ArrayList();
    protected static ArrayList<Customer> customers = new ArrayList();
    protected static ArrayList<Borrower> borrowers = new ArrayList();
    private static Account activeAccount;
    private static Admin admin  = new Admin("Ahmad" , "1234" ,"01030243591") ;
    private static ReviewHandler reviewHandler = new ReviewHandler();
    private static DiscountHandler discountHandler = new DiscountHandler();
    private static Stack<Book> previousBooks = new Stack<Book>();
    private static ShoppingCart shoppingCart = new ShoppingCart();

    private static String lastViewer;

    public static String getLastViewer() {
        return lastViewer;
    }


    public static void setLastViewer(String lastViewer) {
        Library.lastViewer = new String(lastViewer);
    }


    public static Stack<Book> getPreviousBooks() {
        return previousBooks;
    }

    //########################## Start for book #########################//
    public static void addBook(Book book) {
        books.add(book);
    }

    public static int getIndexBook(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (id == books.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public static boolean updateTitle(int id, String newTitle) {
        int index = getIndexBook(id);
        if (index != -1) {
            books.get(index).setTitle(newTitle);
            return true;
        }
        return false;

    }

    public static boolean updateAuthor(int id, String newAuthor) {
        int index = getIndexBook(id);
        if (index != -1) {
            books.get(index).setAuthor(newAuthor);
            return true;
        }
        return false;
    }

    public static boolean updatePublicationYear(int id, int newPublicationYear) {
        int index = getIndexBook(id);
        if (index != -1) {
            books.get(index).setPublicationYear(newPublicationYear);
            return true;
        }
        return false;
    }

    public static boolean updateStatus(int id, boolean newStatus) {
        int index = getIndexBook(id);
        if (index != -1) {
            books.get(index).setStatus(newStatus);
            return true;
        }
        return false;
    }

    public static boolean updatePrice(int id, int newPrice) {
        int index = getIndexBook(id);
        if (index != -1) {
            books.get(index).setPrice(newPrice);
            return true;
        }
        return false;
    }

    public static boolean removeBook(Book book) {
        int index = getIndexBook(book.getId());
        if (index != -1) {
            books.remove(index);
            return true;
        }
        return false;
    }

    // NOTE : We Want to Add something to Recommend Some Books if we can not ffind the book which user want //
    public static Book searchBookByTitle(String title) {
        for (int i = 0; i < books.size(); i++) {
            String titleInArray = books.get(i).getTitle().toLowerCase();
            if (titleInArray.equals(title.toLowerCase())) {
                return books.get(i);
            }
        }
        return null;
    }

    // NOTE : We Want to Add something to Recommend Some Books if we can not find the book which user want //
    public static Book searchBookByAuthor(String author) {
        for (int i = 0; i < books.size(); i++) {
            String authorInArray = books.get(i).getAuthor().toLowerCase();
            if (authorInArray.equals(author.toLowerCase())) {
                return books.get(i);
            }
        }
        return null;
    }

    public static Book searchBookById(int bookId) {
        for (int i = 0; i < books.size(); i++) {
            int IdInArray = books.get(i).getId();
            if (bookId == IdInArray) {
                return books.get(i);
            }
        }
        return null;
    }

    //################ End of Books ###################//
    //############### Start of Borrower ###############//
    public static void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }

    public static int getBorrowerIndex(int userId) {
        for (int i = 0; i < borrowers.size(); i++) {
            if (borrowers.get(i).getId() == userId) {
                return i;
            }
        }
        return -1;
    }
    public static boolean updateBorrowerName(int id, String newName) {
        int index = getBorrowerIndex(id);
        if (index != -1) {
            borrowers.get(index).setUserName(newName);
            return true;
        }
        return false;
    }

    public static boolean updateBorrwerPhone(int id, String newPhone) {
        int index = getBorrowerIndex(id);
        if (index != -1) {
            borrowers.get(index).setPhoneNumber(newPhone);
            return true;
        }
        return false;
    }

    public static Borrower searchBorrwerByPhoneNumber(String phoneNumber) {
        for (Borrower b : borrowers) {
            if (b.getPhoneNumber().equals(phoneNumber)) {
                return b;
            }
        }
        return null;
    }

    public static Borrower searchBorrwerByID(int id) {
        for (Borrower b : borrowers) {
            if (b.getId()==id) {
                return b;
            }
        }
        return null;
    }
    
    public static Borrower searchBorrwerByUserName(String userName) {
        for (Borrower b : borrowers) {
            if (b.getUserName().equals(userName)) {
                return b;
            }
        }
        return null;
    }

    public static boolean removeBorrower(Borrower borrower) {
        int index = getBorrowerIndex(borrower.getId());
        if (index != -1) {
            borrowers.remove(index);
            return true;
        }
        return false;
    }

    // ############################ end of borrow #######################
    // ############################ Start of Customer #######################
    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static int getCustomerIndex(int userId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == userId) {
                return i;
            }
        }
        return -1;
    }

    public static boolean removeCustomer(Customer customer) {
        int index = getCustomerIndex(customer.getId());
        if (index != -1) {
            customers.remove(index);
            return true;
        }
        return false;
    }

    public static Customer searchCustomerByID(int id) {
        for (Customer b : customers) {
            if (b.getId()==id) {
                return b;
            }
        }
        return null;
    }

    public static Customer searchCustomerByUserName(String userName) {
        for (Customer b : customers) {
            if (b.getUserName().equals(userName)) {
                return b;
            }
        }
        return null;
    }

    public static Customer searchCustomerByPhoneNumber(String phoneNumber) {
        for (Customer b : customers) {
            if (b.getPhoneNumber().equals(phoneNumber)) {
                return b;
            }
        }
        return null;
    }
    //##########################End customer ###############
//Note that we want to add a function to check if the string (phone number) contains digits only :)

    public static void signUp(Account account) {
        if (account instanceof Borrower) {
            addBorrower((Borrower) account);
        } else if (account instanceof Customer) {
            addCustomer((Customer) account);
        }
        activeAccount = account;
    }

    public static void logOut() {
        // Really I can't think of method to log out , please check it and you can edit it
        activeAccount = null;
    }

    public static Boolean logInByUserName(String userName, String password) {
        if (searchBorrwerByUserName(userName) != null) {
            if (searchBorrwerByUserName(userName).getPassword().equals(password)) {
                activeAccount = searchBorrwerByUserName(userName);
                return true;
            }
        } else if (searchCustomerByUserName(userName) != null) {
            if (searchCustomerByUserName(userName).getPassword().equals(password)) {
                activeAccount = searchCustomerByUserName(userName);
                return true;
            }
        } else if (admin.getUserName().equals(userName)) {
            if (admin.getPassword().equals(password)) {
                activeAccount = admin;
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    public static boolean logInByphoneNumber(String phoneNumber, String password) {
        if (searchBorrwerByPhoneNumber(phoneNumber) != null) {
            if (searchBorrwerByPhoneNumber(phoneNumber).getPassword().equals(password)) {
                activeAccount = searchBorrwerByPhoneNumber(phoneNumber);
                return true;
            }
        } else if (searchCustomerByPhoneNumber(phoneNumber) != null) {
            if (searchCustomerByPhoneNumber(phoneNumber).getPassword().equals(password)) {
                activeAccount = searchCustomerByPhoneNumber(phoneNumber);
                return true;
            }
        } else if (admin.getPhoneNumber().equals(phoneNumber)) {
            if (admin.getPassword().equals(password)) {
                activeAccount = admin;
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    public static ReviewHandler getReviewHandler() {
        return reviewHandler;
    }

    public static DiscountHandler getDiscountHandler() {
        return discountHandler;
    }

    public static Account getActiveAccount() {
        return activeAccount;
    }

    public static ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public static ArrayList<Book> copyElementOfArrayList() {
        return books;
    }
    public static ArrayList<Customer> getCustomers() {
        return customers;
    }


    public static ArrayList<Book> searchInArrayListBookByTitle(String word , ArrayList<Book> arr) {
        ArrayList<Book> foundBooks = new ArrayList();
        for (int i = 0; i < arr.size(); i++) {
            String fullTitle = arr.get(i).getTitle().toLowerCase();
            for (int j = 0; j < fullTitle.length() - word.length(); j++) {
                int counter = 0;
                for (int k = 0; k < word.length(); k++) {
                    if (word.toLowerCase().charAt(k) == fullTitle.charAt(k + j))
                        counter++;
                    else
                        break;
                }

                if (counter == word.length()) {
                    foundBooks.add(arr.get(i));
                    break;
                }
            }
        }
        return foundBooks;
    }

    public static ArrayList<Book> searchInArrayListBookByAuthor(String word , ArrayList<Book> arr) {
        ArrayList<Book> foundBooks = new ArrayList();
        for (int i = 0; i < arr.size(); i++) {
            String authorName = arr.get(i).getAuthor().toLowerCase();
            for (int j = 0; j < authorName.length() - word.length(); j++) {
                int counter = 0;
                for (int k = 0; k < word.length(); k++) {
                    if (word.toLowerCase().charAt(k) == authorName.charAt(k + j))
                        counter++;
                    else
                        break;
                }

                if (counter == word.length()) {
                    foundBooks.add(arr.get(i));
                    break;
                }
            }
        }
        return foundBooks;
    }

    public static ArrayList<Book> searchBookByTitleInArray(String word) {
        return searchInArrayListBookByTitle(word, books);
    }

    public static ArrayList<Book> searchBookByAuthorInArray(String word) {
        return searchInArrayListBookByAuthor(word, books);
    }

//    public static void writeLibrary() {
//        try {
//            FileOutputStream write1=new FileOutputStream("books_data.txt");
//            for (Book obj : books) {
//                write1.write((obj.getId()+","+obj.getTitle()+","+obj.getAuthor()+","+obj.getPublicationYear()+","+obj.isStatus()+","+obj.getPrice()+","+obj.getCoverPath()+"\n").getBytes());
//            }
//            write1.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("can't write");
//        } catch (IOException e) {
//            System.out.println("can't write");
//        }
//
//        try {
//            FileOutputStream write2=new FileOutputStream("customers_data.txt");
//            for (Customer obj : customers) {
//                write2.write((obj.getId()+","+obj.getUserName()+","+obj.getPassword()+","+obj.getPhoneNumber()+"\n").getBytes());
//            }
//            write2.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("can't write");
//        } catch (IOException e) {
//            System.out.println("can't write");
//        }
//        try {
//            FileOutputStream write3=new FileOutputStream("borrowers_data.txt");
//            for (Borrower obj : borrowers) {
//                write3.write((obj.getId()+","+obj.getUserName()+","+obj.getPassword()+","+obj.getPhoneNumber()+"\n").getBytes());
//            }
//            write3.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("can't write");
//        } catch (IOException e) {
//            System.out.println("can't write");
//        }
//
//        for(Borrower borrower : borrowers){
//            ArrayList<Transaction> transactions =borrower.copyElementOfArrayList();
//            try {
//                FileOutputStream write=new FileOutputStream("transaction_data_"+borrower.getUserName()+".txt");
//                for (Transaction obj : transactions ) {
//                    String bookName=searchBookById(obj.getBookId()).getTitle();
//                    write.write((obj.getTransactionId()+","+bookName+","+obj.getBorrowerId()+","+obj.getBorrowDate()+","+obj.getReturnDate()+"\n").getBytes());
//                }
//                write.close();
//            } catch (FileNotFoundException e) {
//                System.out.println("can't write");
//            } catch (IOException e) {
//                System.out.println("can't write");
//            }
//
//        }
//
//        for(Customer customer : customers){
//            ArrayList<Order> orders =customer.copyElementOfArrayList();
//            try {
//                FileOutputStream write=new FileOutputStream("order_data_"+customer.getUserName()+".txt");
//                for (Order obj : orders ) {
//                    String bookName=searchBookById(obj.getBookId()).getTitle();
//                    write.write((obj.getId()+","+bookName+","+obj.getQuantity()+"\n").getBytes());
//                }
//                write.close();
//            } catch (FileNotFoundException e) {
//                System.out.println("can't write");
//            } catch (IOException e) {
//                System.out.println("can't write");
//            }
//
//        }
//    }

//    public static void readLibrary() {
//        Scanner scanner1 = null;
//        try {
//            scanner1 = new Scanner(new FileInputStream("books_data.txt"));
//        } catch (FileNotFoundException e) {
//            System.out.println("can't read");
//        }
//
//        while (scanner1.hasNextLine()) {
//            String line1 = scanner1.nextLine();
//            String[] parts1 = line1.split(",");
////            Book book=new Book(parts1[1],parts1[2],Integer.valueOf(parts1[3]),Boolean.valueOf(parts1[4]),Integer.valueOf(parts1[5]),Integer.valueOf(parts1[6]),parts1[7]);
//            addBook(book);
//        }
//        scanner1.close();
//
//        Scanner scanner2 = null;
//        try {
//            scanner2 = new Scanner(new FileInputStream("customers_data.txt"));
//        } catch (FileNotFoundException e) {
//            System.out.println("can't read");
//        }
//
//        while (scanner2.hasNextLine()) {
//            String line2 = scanner2.nextLine();
//            String[] parts2 = line2.split(",");
//            Customer customer=new Customer(parts2[1],parts2[2],parts2[3]);
//            addCustomer(customer);
//        }
//        scanner2.close();
//
//        Scanner scanner3 = null;
//        try {
//            scanner3 = new Scanner(new FileInputStream("borrowers_data.txt"));
//        } catch (FileNotFoundException e) {
//            System.out.println("can't read");
//        }
//
//        while (scanner3.hasNextLine()) {
//            String line3 = scanner3.nextLine();
//            String[] parts3 = line3.split(",");
//            Borrower borrower = new Borrower(parts3[1],parts3[2],parts3[3]);
//            addBorrower(borrower);
//        }
//        scanner3.close();
//
//        for(Borrower borrower : borrowers) {
//            Scanner scanner = null;
//            try {
//                scanner = new Scanner(new FileInputStream("transaction_data_"+borrower.getUserName()+".txt"));
//            } catch (FileNotFoundException e) {
//                System.out.println("can't read");
//            }
//
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                String[] parts = line.split(",");
//                Transaction transaction = new Transaction(searchBookByTitle(parts[1]).getId(), borrower.getId(), Integer.valueOf(parts[3]), Integer.valueOf(parts[4]));
//                borrower.addTransaction(searchBookById(transaction.getBookId()), transaction.getBorrowDate(),transaction.getReturnDate());
//            }
//            scanner.close();
//        }
//
//        for(Customer customer : customers) {
//            Scanner scanner = null;
//            try {
//                scanner = new Scanner(new FileInputStream("order_data_"+customer.getUserName()+".txt"));
//            } catch (FileNotFoundException e) {
//                System.out.println("can't read");
//            }
//
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                String[] parts = line.split(",");
//                Order order = new Order(searchBookByTitle(parts[1]).getId(),Integer.valueOf(parts[2]));
//                customer.addOrder(order.getBookId(),order.getQuantity());
//            }
//            scanner.close();
//        }
//    }
}
