package com.asu.librarysystem;

public class CardPayment implements PaymentMethod {
    private String cardNumber;
    private String cvv;


    CardPayment(String cardNumber, String cvv) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    @Override
    public Boolean pay() {
        if (cardNumber.length() != 16)
            return false;
        if (cvv.length() != 3)
            return false;
        return true;
    }
    
}
