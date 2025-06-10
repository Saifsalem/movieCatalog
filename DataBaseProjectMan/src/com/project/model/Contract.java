package com.project.model;

import javafx.beans.property.*;

public class Contract {
    private IntegerProperty contractId;
    private StringProperty projectId;
    private StringProperty clientId;
    private StringProperty startDate;
    private StringProperty endDate;
    private DoubleProperty totalValue;
    private StringProperty status;
    
    public Contract() {
        this.contractId = new SimpleIntegerProperty();
        this.projectId = new SimpleStringProperty();
        this.clientId = new SimpleStringProperty();
        this.startDate = new SimpleStringProperty();
        this.endDate = new SimpleStringProperty();
        this.totalValue = new SimpleDoubleProperty();
        this.status = new SimpleStringProperty();
    }
    
    public Contract(int contractId, String projectId, String clientId, String startDate, 
                   String endDate, double totalValue, String status) {
        this();
        setContractId(contractId);
        setProjectId(projectId);
        setClientId(clientId);
        setStartDate(startDate);
        setEndDate(endDate);
        setTotalValue(totalValue);
        setStatus(status);
    }
    
    // Contract ID
    public int getContractId() { return contractId.get(); }
    public void setContractId(int contractId) { this.contractId.set(contractId); }
    public IntegerProperty contractIdProperty() { return contractId; }
    
    // Project ID
    public String getProjectId() { return projectId.get(); }
    public void setProjectId(String projectId) { this.projectId.set(projectId); }
    public StringProperty projectIdProperty() { return projectId; }
    
    // Client ID
    public String getClientId() { return clientId.get(); }
    public void setClientId(String clientId) { this.clientId.set(clientId); }
    public StringProperty clientIdProperty() { return clientId; }
    
    // Start Date
    public String getStartDate() { return startDate.get(); }
    public void setStartDate(String startDate) { this.startDate.set(startDate); }
    public StringProperty startDateProperty() { return startDate; }
    
    // End Date
    public String getEndDate() { return endDate.get(); }
    public void setEndDate(String endDate) { this.endDate.set(endDate); }
    public StringProperty endDateProperty() { return endDate; }
    
    // Total Value
    public double getTotalValue() { return totalValue.get(); }
    public void setTotalValue(double totalValue) { this.totalValue.set(totalValue); }
    public DoubleProperty totalValueProperty() { return totalValue; }
    
    // Status
    public String getStatus() { return status.get(); }
    public void setStatus(String status) { this.status.set(status); }
    public StringProperty statusProperty() { return status; }
    
    public String toString() {
        return "Contract{" +
                "contractId=" + getContractId() +
                ", projectId='" + getProjectId() + '\'' +
                ", clientId='" + getClientId() + '\'' +
                ", startDate='" + getStartDate() + '\'' +
                ", endDate='" + getEndDate() + '\'' +
                ", totalValue=" + getTotalValue() +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}