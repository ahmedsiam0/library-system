package com.asu.librarysystem;

public class CashPayment implements PaymentMethod {
    private String paymentAddress;

    CashPayment(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }
    
    @Override
    public Boolean pay() {
        if (paymentAddress.length() < 1) {
            return false;
        }
        return true;
    }
    
}
