package com.project.model;

import javafx.beans.property.*;

public class Sales {
    private IntegerProperty saleId;
    private StringProperty projectId;
    private StringProperty clientId;
    private DoubleProperty amount;
    private StringProperty issueDate;
    private StringProperty dueDate;
    
    public Sales() {
        this.saleId = new SimpleIntegerProperty();
        this.projectId = new SimpleStringProperty();
        this.clientId = new SimpleStringProperty();
        this.amount = new SimpleDoubleProperty();
        this.issueDate = new SimpleStringProperty();
        this.dueDate = new SimpleStringProperty();
    }
    
    public Sales(int saleId, String projectId, String clientId, double amount, 
                String issueDate, String dueDate) {
        this();
        setSaleId(saleId);
        setProjectId(projectId);
        setClientId(clientId);
        setAmount(amount);
        setIssueDate(issueDate);
        setDueDate(dueDate);
    }
    
    // Sale ID
    public int getSaleId() { return saleId.get(); }
    public void setSaleId(int saleId) { this.saleId.set(saleId); }
    public IntegerProperty saleIdProperty() { return saleId; }
    
    // Project ID
    public String getProjectId() { return projectId.get(); }
    public void setProjectId(String projectId) { this.projectId.set(projectId); }
    public StringProperty projectIdProperty() { return projectId; }
    
    // Client ID
    public String getClientId() { return clientId.get(); }
    public void setClientId(String clientId) { this.clientId.set(clientId); }
    public StringProperty clientIdProperty() { return clientId; }
    
    // Amount
    public double getAmount() { return amount.get(); }
    public void setAmount(double amount) { this.amount.set(amount); }
    public DoubleProperty amountProperty() { return amount; }
    
    // Issue Date
    public String getIssueDate() { return issueDate.get(); }
    public void setIssueDate(String issueDate) { this.issueDate.set(issueDate); }
    public StringProperty issueDateProperty() { return issueDate; }
    
    // Due Date
    public String getDueDate() { return dueDate.get(); }
    public void setDueDate(String dueDate) { this.dueDate.set(dueDate); }
    public StringProperty dueDateProperty() { return dueDate; }
    
    public String toString() {
        return "Sales{" +
                "saleId=" + getSaleId() +
                ", projectId='" + getProjectId() + '\'' +
                ", clientId='" + getClientId() + '\'' +
                ", amount=" + getAmount() +
                ", issueDate='" + getIssueDate() + '\'' +
                ", dueDate='" + getDueDate() + '\'' +
                '}';
    }
}