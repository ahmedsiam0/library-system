package com.asu.librarysystem;

public class Discount {
    private String code;
    private double discount;

    public Discount(String code, double discount) {
        this.code = code;
        this.discount = discount;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getDiscount() {
        return discount;
    }
}
