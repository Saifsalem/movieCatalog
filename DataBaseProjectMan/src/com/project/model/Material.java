package com.project.model;

import javafx.beans.property.*;

public class Material {
    private IntegerProperty materialId;
    private StringProperty mname;
    private DoubleProperty price;
    
    public Material() {
        this.materialId = new SimpleIntegerProperty();
        this.mname = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
    }
    
    public Material(int materialId, String mname, double price) {
        this();
        setMaterialId(materialId);
        setMname(mname);
        setPrice(price);
    }
    
    // Material ID
    public int getMaterialId() { return materialId.get(); }
    public void setMaterialId(int materialId) { this.materialId.set(materialId); }
    public IntegerProperty materialIdProperty() { return materialId; }
    
    // Material Name
    public String getMname() { return mname.get(); }
    public void setMname(String mname) { this.mname.set(mname); }
    public StringProperty mnameProperty() { return mname; }
    
    // Price
    public double getPrice() { return price.get(); }
    public void setPrice(double price) { this.price.set(price); }
    public DoubleProperty priceProperty() { return price; }
    
    public String toString() {
        return "Material{" +
                "materialId=" + getMaterialId() +
                ", mname='" + getMname() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}