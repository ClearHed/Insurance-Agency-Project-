package com.InsuranceAgency.paymentservice.repo.model;


import javax.persistence.*;

@Entity
@Table(name = "Payments")
public final class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int PaymentID;
    private int customerID;
    private int amount;

    public Payment(){

    }

    public Payment(int paymentID, int customerID, int amount) {
        PaymentID = paymentID;
        this.customerID = customerID;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(int paymentID) {
        PaymentID = paymentID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
