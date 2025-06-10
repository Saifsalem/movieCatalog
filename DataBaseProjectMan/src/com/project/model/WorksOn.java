package com.project.model;

import javafx.beans.property.*;

public class WorksOn {
    private IntegerProperty empId;
    private StringProperty projectId;
    private StringProperty role;
    
    public WorksOn() {
        this.empId = new SimpleIntegerProperty();
        this.projectId = new SimpleStringProperty();
        this.role = new SimpleStringProperty();
    }
    
    public WorksOn(int empId, String projectId, String role) {
        this();
        setEmpId(empId);
        setProjectId(projectId);
        setRole(role);
    }
    
    // Employee ID
    public int getEmpId() { return empId.get(); }
    public void setEmpId(int empId) { this.empId.set(empId); }
    public IntegerProperty empIdProperty() { return empId; }
    
    // Project ID
    public String getProjectId() { return projectId.get(); }
    public void setProjectId(String projectId) { this.projectId.set(projectId); }
    public StringProperty projectIdProperty() { return projectId; }
    
    // Role
    public String getRole() { return role.get(); }
    public void setRole(String role) { this.role.set(role); }
    public StringProperty roleProperty() { return role; }
    
    public String toString() {
        return "WorksOn{" +
                "empId=" + getEmpId() +
                ", projectId='" + getProjectId() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}