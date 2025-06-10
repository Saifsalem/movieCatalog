package com.project.model;

import javafx.beans.property.*;

public class Purchase {
    private IntegerProperty purchaseId;
    private StringProperty supplierId;
    private IntegerProperty materialId;
    private IntegerProperty quantity;
    private StringProperty purchaseDate;
    private DoubleProperty totalCost;
    
    public Purchase() {
        this.purchaseId = new SimpleIntegerProperty();
        this.supplierId = new SimpleStringProperty();
        this.materialId = new SimpleIntegerProperty();
        this.quantity = new SimpleIntegerProperty();
        this.purchaseDate = new SimpleStringProperty();
        this.totalCost = new SimpleDoubleProperty();
    }
    
    public Purchase(int purchaseId, String supplierId, int materialId, int quantity, 
                   String purchaseDate, double totalCost) {
        this();
        setPurchaseId(purchaseId);
        setSupplierId(supplierId);
        setMaterialId(materialId);
        setQuantity(quantity);
        setPurchaseDate(purchaseDate);
        setTotalCost(totalCost);
    }
    
    // Purchase ID
    public int getPurchaseId() { return purchaseId.get(); }
    public void setPurchaseId(int purchaseId) { this.purchaseId.set(purchaseId); }
    public IntegerProperty purchaseIdProperty() { return purchaseId; }
    
    // Supplier ID
    public String getSupplierId() { return supplierId.get(); }
    public void setSupplierId(String supplierId) { this.supplierId.set(supplierId); }
    public StringProperty supplierIdProperty() { return supplierId; }
    
    // Material ID
    public int getMaterialId() { return materialId.get(); }
    public void setMaterialId(int materialId) { this.materialId.set(materialId); }
    public IntegerProperty materialIdProperty() { return materialId; }
    
    // Quantity
    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public IntegerProperty quantityProperty() { return quantity; }
    
    // Purchase Date
    public String getPurchaseDate() { return purchaseDate.get(); }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate.set(purchaseDate); }
    public StringProperty purchaseDateProperty() { return purchaseDate; }
    
    // Total Cost
    public double getTotalCost() { return totalCost.get(); }
    public void setTotalCost(double totalCost) { this.totalCost.set(totalCost); }
    public DoubleProperty totalCostProperty() { return totalCost; }
    
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + getPurchaseId() +
                ", supplierId='" + getSupplierId() + '\'' +
                ", materialId=" + getMaterialId() +
                ", quantity=" + getQuantity() +
                ", purchaseDate='" + getPurchaseDate() + '\'' +
                ", totalCost=" + getTotalCost() +
                '}';
    }
}