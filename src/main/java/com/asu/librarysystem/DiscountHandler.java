package com.asu.librarysystem;

import java.util.ArrayList;

public class DiscountHandler {
    private ArrayList<Discount> discounts;

    public DiscountHandler() {
        discounts = new ArrayList<Discount>();
    }

    public void addDiscount(String code, Double discount) {
        int index = findDiscount(code);
        if (index != -1)
            discounts.remove(index);
        discounts.add(new Discount(code, discount));
    }

    public void deleteDiscount(String code) {
        int index = findDiscount(code);
        if (index != -1)
            discounts.remove(index);
    }

    public double getDiscount(String code) {
        int index = findDiscount(code);
        if (index == -1)
            return -1.0;
        return discounts.get(index).getDiscount();
    }

    public ArrayList<Discount> getDiscounts() {
        return (new ArrayList<Discount>(discounts));
    }

    private int findDiscount(String code) {
        for (int i = 0; i < discounts.size(); i++) {
            if (discounts.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }
}
