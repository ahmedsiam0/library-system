package com.asu.librarysystem;
public abstract class Account {

    protected int user_id;
    protected String user_name;
    protected String Password;
    protected int Phone_Number;

    public Account(int user_id, String user_name, String Password, int Phone_Number) {
        this.user_name = user_name;
        this.user_id = user_id;
        this.Password = Password;
        this.Phone_Number = Phone_Number;
    }

    public void Signup(int id, String name, String Pass, int Phone) {
        this.user_id = id;
        this.user_name = name;
        this.Password = Pass;
        this.Phone_Number = Phone;
    }

    public void Login(String name, String Pass) {
        this.user_name = name;
        this.Password = Pass;
    }

    public void Logout() {
        this.user_name = null;
        this.user_id = 0;
        this.Password = null;
        this.Phone_Number = 0;
    }
}
