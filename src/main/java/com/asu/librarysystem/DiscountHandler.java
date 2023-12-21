package com.asu.librarysystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DiscountHandler {
    private static ArrayList<Discount> discounts;

    public DiscountHandler() {
        discounts = new ArrayList<Discount>();
    }

    public void addDiscount(String code, Double discount) {
        int index = findDiscount(code);
        if (index != -1)
            discounts.remove(index);
        discounts.add(new Discount(code, discount));
    }

    public void addDiscount(Discount discount){
        addDiscount(discount.getCode(),discount.getDiscount());
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
    public static void writeDiscount(){
        try {
            FileOutputStream write1=new FileOutputStream("data/datafiles/discount_data.txt");
            for (Discount obj : discounts) {
                write1.write((obj.getCode()+",,,"+ obj.getDiscount()+"\n").getBytes());
            }
            write1.close();
        } catch (FileNotFoundException e) {
            System.out.println("can't write");
        } catch (IOException e) {
            System.out.println("can't write");
        }
    }

    public static void readDiscount(){
        Scanner scanner1 = null;
        try {
            scanner1 = new Scanner(new FileInputStream("data/datafiles/discount_data.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("can't read");
        }

        while (scanner1.hasNextLine()) {
            String line1 = scanner1.nextLine();
            String[] parts1 = line1.split(",,,");
            Discount discount =new Discount(parts1[0],Double.valueOf(parts1[1]));
            addDiscount(discount);
        }
        scanner1.close();
    }
}
