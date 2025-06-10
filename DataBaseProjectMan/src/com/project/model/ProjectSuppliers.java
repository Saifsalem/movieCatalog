package com.project.model;

import javafx.beans.property.*;

public class ProjectSuppliers {
    private StringProperty projectId;
    private StringProperty supplierId;
    
    public ProjectSuppliers() {
        this.projectId = new SimpleStringProperty();
        this.supplierId = new SimpleStringProperty();
    }
    
    public ProjectSuppliers(String projectId, String supplierId) {
        this();
        setProjectId(projectId);
        setSupplierId(supplierId);
    }
    
    // Project ID
    public String getProjectId() { return projectId.get(); }
    public void setProjectId(String projectId) { this.projectId.set(projectId); }
    public StringProperty projectIdProperty() { return projectId; }
    
    // Supplier ID
    public String getSupplierId() { return supplierId.get(); }
    public void setSupplierId(String supplierId) { this.supplierId.set(supplierId); }
    public StringProperty supplierIdProperty() { return supplierId; }
    
    public String toString() {
        return "ProjectSuppliers{" +
                "projectId='" + getProjectId() + '\'' +
                ", supplierId='" + getSupplierId() + '\'' +
                '}';
    }
}