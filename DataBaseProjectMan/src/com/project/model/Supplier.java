package com.project.model;

import javafx.beans.property.*;

public class Supplier {
    private StringProperty suppId;
    private StringProperty suppName;
    private StringProperty location;
    
    public Supplier() {
        this.suppId = new SimpleStringProperty();
        this.suppName = new SimpleStringProperty();
        this.location = new SimpleStringProperty();
    }
    
    public Supplier(String suppId, String suppName, String location) {
        this();
        setSuppId(suppId);
        setSuppName(suppName);
        setLocation(location);
    }
    
    // Supplier ID
    public String getSuppId() { return suppId.get(); }
    public void setSuppId(String suppId) { this.suppId.set(suppId); }
    public StringProperty suppIdProperty() { return suppId; }
    
    // Supplier Name
    public String getSuppName() { return suppName.get(); }
    public void setSuppName(String suppName) { this.suppName.set(suppName); }
    public StringProperty suppNameProperty() { return suppName; }
    
    // Location
    public String getLocation() { return location.get(); }
    public void setLocation(String location) { this.location.set(location); }
    public StringProperty locationProperty() { return location; }
    
    public String toString() {
        return "Supplier{" +
                "suppId='" + getSuppId() + '\'' +
                ", suppName='" + getSuppName() + '\'' +
                ", location='" + getLocation() + '\'' +
                '}';
    }
}