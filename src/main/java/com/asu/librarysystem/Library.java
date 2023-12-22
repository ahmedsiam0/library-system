package com.asu.librarysystem;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Library {
    private static ArrayList<Book> books = new ArrayList();
    private static ArrayList<Customer> customers = new ArrayList();
    private static ArrayList<Borrower> borrowers = new ArrayList();
    private static Account activeAccount;
    private static Admin admin  = new Admin("admin" , "admin" ,"1111") ;
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
    public static ArrayList<Book> getBooks() {
        return books;
    }
    public static ArrayList<Borrower> getBorrowers() {
        return borrowers;
    }

    public static Account searchAccountById(int id) {
        for (Customer b : customers) {
            if (b.getId() == id) {
                return b;
            }
        }
        for (Borrower m : borrowers) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;

    }

        public static void writeLibrary () {
            try {
                FileOutputStream write1 = new FileOutputStream("data/datafiles/books_data.txt");
                for (Book obj : books) {
                    write1.write((obj.getIdCounter() + ",,," + obj.getId() + ",,," + obj.getTitle() + ",,," + obj.getAuthor() + ",,," + obj.getPublicationYear() + ",,," + obj.isStatus() + ",,," + obj.getPrice() + ",,," + obj.getQuantity() + ",,," + obj.getDescription() + ",,," + obj.getCoverPath() + "\n").getBytes());
                    ArrayList<Category> categories = obj.getCategories();
                    for (Category c : categories) {
                        write1.write((c.name() + ",,,").getBytes());
                    }
                    write1.write("\n".getBytes());
                }
                write1.close();
            } catch (FileNotFoundException e) {
                System.out.println("can't write");
            } catch (IOException e) {
                System.out.println("can't write");
            }

            try {
                FileOutputStream write2 = new FileOutputStream("data/datafiles/customers_data.txt");
                for (Customer obj : customers) {
                    write2.write((obj.getIdCounter() + ",,," + obj.getId() + ",,," + obj.getUserName() + ",,," + obj.getPassword() + ",,," + obj.getPhoneNumber() + "\n").getBytes());
                }
                write2.close();
            } catch (FileNotFoundException e) {
                System.out.println("can't write");
            } catch (IOException e) {
                System.out.println("can't write");
            }
            try {
                FileOutputStream write3 = new FileOutputStream("data/datafiles/borrowers_data.txt");
                for (Borrower obj : borrowers) {
                    write3.write((obj.getIdCounter() + ",,," + obj.getId() + ",,," + obj.getUserName() + ",,," + obj.getPassword() + ",,," + obj.getPhoneNumber() + "\n").getBytes());
                }
                write3.close();
            } catch (FileNotFoundException e) {
                System.out.println("can't write");
            } catch (IOException e) {
                System.out.println("can't write");
            }

            for (Borrower borrower : borrowers) {
                ArrayList<Transaction> transactions = borrower.getBorrowerTransactions();
                try {
                    FileOutputStream write = new FileOutputStream("data/datafiles/transaction_data_" + borrower.getUserName() + ".txt");
                    for (Transaction obj : transactions) {
                        write.write((obj.getTransactionCount() + ",,," + obj.getTransactionId() + ",,," + obj.getBookId() + ",,," + obj.getBorrowerId() + ",,," + obj.getBorrowDate() + ",,," + obj.getReturnDate() + "\n").getBytes());
                    }
                    write.close();
                } catch (FileNotFoundException e) {
                    System.out.println("can't write");
                } catch (IOException e) {
                    System.out.println("can't write");
                }

            }

            for (Customer customer : customers) {
                ArrayList<Order> orders = customer.getOrders();
                try {
                    FileOutputStream write = new FileOutputStream("data/datafiles/order_data_" + customer.getUserName() + ".txt");
                    for (Order obj : orders) {
                        write.write((obj.getIdCounter() + ",,," + obj.getId() + ",,," + obj.getBookId() + ",,," + obj.getQuantity() + "\n").getBytes());
                    }
                    write.close();
                } catch (FileNotFoundException e) {
                    System.out.println("can't write");
                } catch (IOException e) {
                    System.out.println("can't write");
                }

            }
            try {
                FileOutputStream write1 = new FileOutputStream("data/datafiles/reviews_data.txt");
                ArrayList<Review> reviews = reviewHandler.getReviews();
                for (Review obj : reviews) {
                    write1.write((obj.getIdCounter() + ",,," + obj.getId() + ",,," + obj.getReviewerId() + ",,," + obj.getBookId() + ",,," + obj.getRating() + ",,," + obj.getText() + "\n").getBytes());
                }
                write1.close();
            } catch (FileNotFoundException e) {
                System.out.println("can't write");
            } catch (IOException e) {
                System.out.println("can't write");
            }
            try {
                FileOutputStream write1 = new FileOutputStream("data/datafiles/discount_data.txt");
                ArrayList<Discount> discounts = discountHandler.getDiscounts();
                for (Discount obj : discounts) {
                    write1.write((obj.getCode() + ",,," + obj.getDiscount() + "\n").getBytes());
                }
                write1.close();
            } catch (FileNotFoundException e) {
                System.out.println("can't write");
            } catch (IOException e) {
                System.out.println("can't write");
            }
        }

        public static void readLibrary () {
            Scanner scanner1 = null;
            try {

                scanner1 = new Scanner(new FileInputStream("data/datafiles/books_data.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("can't read");
            }

            while (scanner1.hasNextLine()) {
                String line1 = scanner1.nextLine();
                String[] parts1 = line1.split(",,,");
                String line2 = scanner1.nextLine();
                String[] parts2 = line2.split(",,,");
                ArrayList<Category> categories = new ArrayList<Category>();
                for (String s : parts2) {
                    switch (s) {
                        case "HORROR":
                            categories.add(Category.HORROR);
                            break;
                        case "DRAMA":
                            categories.add(Category.DRAMA);
                            break;
                        case "NOVEL":
                            categories.add(Category.NOVEL);
                            break;
                        case "SELFDEVELOPMENT":
                            categories.add(Category.SELFDEVELOPMENT);
                            break;
                        case "ADVENTURE":
                            categories.add(Category.ADVENTURE);
                            break;
                        default:
                            break;
                    }
                }
                Book book = new Book(Integer.valueOf(parts1[1]), parts1[2], parts1[3], Integer.valueOf(parts1[4]), Boolean.valueOf(parts1[5]), Integer.valueOf(parts1[6]), Integer.valueOf(parts1[7]), parts1[8], parts1[9], categories);
                book.setIdCounter(Integer.valueOf(parts1[0]));
                addBook(book);
            }
            scanner1.close();

            Scanner scanner2 = null;
            try {
                scanner2 = new Scanner(new FileInputStream("data/datafiles/customers_data.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("can't read");
            }

            while (scanner2.hasNextLine()) {
                String line2 = scanner2.nextLine();
                String[] parts2 = line2.split(",,,");
                Customer customer = new Customer(Integer.valueOf(parts2[1]), parts2[2], parts2[3], parts2[4]);
                customer.setIdCounter(Integer.valueOf(parts2[0]));
                addCustomer(customer);
            }
            scanner2.close();

            Scanner scanner3 = null;
            try {
                scanner3 = new Scanner(new FileInputStream("data/datafiles/borrowers_data.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("can't read");
            }

            while (scanner3.hasNextLine()) {
                String line3 = scanner3.nextLine();
                String[] parts3 = line3.split(",,,");
                Borrower borrower = new Borrower(Integer.valueOf(parts3[1]), parts3[2], parts3[3], parts3[4]);
                borrower.setIdCounter(Integer.valueOf(parts3[0]));
                addBorrower(borrower);
            }
            scanner3.close();

            for (Borrower borrower : borrowers) {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(new FileInputStream("data/datafiles/transaction_data_" + borrower.getUserName() + ".txt"));
                } catch (FileNotFoundException e) {
                    System.out.println("can't read");
                }

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",,,");
                    Transaction transaction = new Transaction(Integer.valueOf(parts[1]), Integer.valueOf(parts[2]), Integer.valueOf(parts[3]), LocalDate.parse(parts[4]), LocalDate.parse(parts[5]));
                    transaction.setTransactionCount(Integer.valueOf(parts[0]));
                    borrower.addTransaction(transaction);
                }
                scanner.close();
            }

            for (Customer customer : customers) {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(new FileInputStream("data/datafiles/order_data_" + customer.getUserName() + ".txt"));
                } catch (FileNotFoundException e) {
                    System.out.println("can't read");
                }

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",,,");
                    Order order = new Order(Integer.valueOf(parts[1]), Integer.valueOf(parts[2]), Integer.valueOf(parts[3]));
                    customer.setIdCounter(Integer.valueOf(parts[0]));
                    customer.addOrder(order);
                }
                scanner.close();
            }

            Scanner scanner4 = null;
            try {
                scanner4 = new Scanner(new FileInputStream("data/datafiles/reviews_data.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("can't read");
            }

            while (scanner4.hasNextLine()) {
                String line1 = scanner4.nextLine();
                String[] parts1 = line1.split(",,,");
                Review review = new Review(Integer.valueOf(parts1[1]), Integer.valueOf(parts1[2]), Integer.valueOf(parts1[3]), Integer.valueOf(parts1[4]), parts1[5]);
                review.setIdCounter(Integer.valueOf(parts1[0]));
                reviewHandler.addReview(review);
            }

            scanner4.close();
            Scanner scanner5 = null;
            try {
                scanner5 = new Scanner(new FileInputStream("data/datafiles/discount_data.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("can't read");
            }

            while (scanner5.hasNextLine()) {
                String line1 = scanner5.nextLine();
                String[] parts1 = line1.split(",,,");
                Discount discount = new Discount(parts1[0], Double.valueOf(parts1[1]));
                discountHandler.addDiscount(discount);
            }
            scanner5.close();
        }

}
