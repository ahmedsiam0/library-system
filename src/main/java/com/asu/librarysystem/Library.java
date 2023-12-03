package com.asu.librarysystem;

import java.util.ArrayList;

public class Library 
{
    protected static ArrayList<Book> books = new ArrayList();
    protected static ArrayList<Customer> customers = new ArrayList();
    protected static ArrayList<Borrower> borrowers = new ArrayList();
    private static Account activeAccount;
    private static Admin admin ;

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
                if (index!=-1){
                    customers.remove(index);
                    return true;
                }
                return false;
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
    }
    else if(account instanceof Customer){
        addCustomer((Customer) account);
    }
    activeAccount=account;
}

    public static void logOut(){
        // Really I can't think of method to log out , please check it and you can edit it
        activeAccount=null;
    }
    public static boolean logInByUserName(String userName, String password){
      if (searchBorrwerByUserName(userName)!=null){
            if(searchBorrwerByUserName(userName).getPassword().equals(password)){
                activeAccount=searchBorrwerByUserName(userName);
                return true;
            }
        }else if(searchCustomerByUserName(userName)!=null){
            if(searchCustomerByUserName(userName).getPassword().equals(password)){
                activeAccount=searchCustomerByUserName(userName);
                return true;
            }
        }else if (admin.getUserName().equals(userName)){
            if(admin.getPassword().equals(password)){
                activeAccount=admin;
                return true;
            }
        }
      else{
          return false;
      }
        return false;
    }
    public static boolean logInByphoneNumber(String phoneNumber, String password){
        if (searchBorrwerByPhoneNumber(phoneNumber)!=null){
            if(searchBorrwerByPhoneNumber(phoneNumber).getPassword().equals(password)){
                activeAccount=searchBorrwerByPhoneNumber(phoneNumber);
                return true;
            }
        }else if(searchCustomerByPhoneNumber(phoneNumber)!=null){
            if(searchCustomerByPhoneNumber(phoneNumber).getPassword().equals(password)){
                activeAccount=searchCustomerByPhoneNumber(phoneNumber);
                return true;
            }
        }else if (admin.getPhoneNumber().equals(phoneNumber)){
            if(admin.getPassword().equals(password)){
                activeAccount=admin;
                return true;
            }
        }
        else{
            return false;
        }
        return false;
    }
}
