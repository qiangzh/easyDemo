package com.ruleengine.example1;

public class Customer {

    private double creditLimit;

    public Customer() {
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void decrementCreditLimit(double amount) {
        creditLimit = creditLimit - amount;
    }

}