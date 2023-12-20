package com.asu.librarysystem;

public abstract class Account {
    private static int idCounter = 0;
    private final int id;
    private String userName;
    private String Password;
    private String phoneNumber;

    public Account(String userName, String password, String phoneNumber) {
        this.userName = userName;
        this.id = ++idCounter;
        this.Password = password;
        this.phoneNumber = phoneNumber;
    }
    
    public void setPassword(String password) {
        Password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
    
    public String getPassword() {
        return Password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}