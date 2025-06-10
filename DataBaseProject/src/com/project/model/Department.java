package com.project.model;

import javafx.beans.property.*;

public class Department {
    private StringProperty deptId;
    private StringProperty deptName;
    
    public Department() {
        this.deptId = new SimpleStringProperty();
        this.deptName = new SimpleStringProperty();
    }
    
    public Department(String deptId, String deptName) {
        this();
        setDeptId(deptId);
        setDeptName(deptName);
    }
    
    // Department ID
    public String getDeptId() { return deptId.get(); }
    public void setDeptId(String deptId) { this.deptId.set(deptId); }
    public StringProperty deptIdProperty() { return deptId; }
    
    // Department Name
    public String getDeptName() { return deptName.get(); }
    public void setDeptName(String deptName) { this.deptName.set(deptName); }
    public StringProperty deptNameProperty() { return deptName; }
    
    public String toString() {
        return "Department{" +
                "deptId='" + getDeptId() + '\'' +
                ", deptName='" + getDeptName() + '\'' +
                '}';
    }
}