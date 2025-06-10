package com.project.model;

import javafx.beans.property.*;

public class Payment {
    private IntegerProperty paymentId;
    private StringProperty fromClient;
    private StringProperty toSupplier;
    private DoubleProperty amount;
    private StringProperty pdate;
    private StringProperty pmethod;
    
    public Payment() {
        this.paymentId = new SimpleIntegerProperty();
        this.fromClient = new SimpleStringProperty();
        this.toSupplier = new SimpleStringProperty();
        this.amount = new SimpleDoubleProperty();
        this.pdate = new SimpleStringProperty();
        this.pmethod = new SimpleStringProperty();
    }
    
    public Payment(int paymentId, String fromClient, String toSupplier, double amount, 
                  String pdate, String pmethod) {
        this();
        setPaymentId(paymentId);
        setFromClient(fromClient);
        setToSupplier(toSupplier);
        setAmount(amount);
        setPdate(pdate);
        setPmethod(pmethod);
    }
    
    // Payment ID
    public int getPaymentId() { return paymentId.get(); }
    public void setPaymentId(int paymentId) { this.paymentId.set(paymentId); }
    public IntegerProperty paymentIdProperty() { return paymentId; }
    
    // From Client
    public String getFromClient() { return fromClient.get(); }
    public void setFromClient(String fromClient) { this.fromClient.set(fromClient); }
    public StringProperty fromClientProperty() { return fromClient; }
    
    // To Supplier
    public String getToSupplier() { return toSupplier.get(); }
    public void setToSupplier(String toSupplier) { this.toSupplier.set(toSupplier); }
    public StringProperty toSupplierProperty() { return toSupplier; }
    
    // Amount
    public double getAmount() { return amount.get(); }
    public void setAmount(double amount) { this.amount.set(amount); }
    public DoubleProperty amountProperty() { return amount; }
    
    // Payment Date
    public String getPdate() { return pdate.get(); }
    public void setPdate(String pdate) { this.pdate.set(pdate); }
    public StringProperty pdateProperty() { return pdate; }
    
    // Payment Method
    public String getPmethod() { return pmethod.get(); }
    public void setPmethod(String pmethod) { this.pmethod.set(pmethod); }
    public StringProperty pmethodProperty() { return pmethod; }
    
    public String toString() {
        return "Payment{" +
                "paymentId=" + getPaymentId() +
                ", fromClient='" + getFromClient() + '\'' +
                ", toSupplier='" + getToSupplier() + '\'' +
                ", amount=" + getAmount() +
                ", pdate='" + getPdate() + '\'' +
                ", pmethod='" + getPmethod() + '\'' +
                '}';
    }
}