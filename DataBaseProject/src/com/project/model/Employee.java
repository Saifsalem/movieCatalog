package com.project.model;

import javafx.beans.property.*;

public class Employee {
    private IntegerProperty empId;
    private StringProperty empName;
    private StringProperty positionId;
    private DoubleProperty salary;
    private StringProperty branchId;
    private StringProperty managerId;
    private StringProperty departmentId;
    private StringProperty isManager;
    
    public Employee() {
        this.empId = new SimpleIntegerProperty();
        this.empName = new SimpleStringProperty();
        this.positionId = new SimpleStringProperty();
        this.salary = new SimpleDoubleProperty();
        this.branchId = new SimpleStringProperty();
        this.managerId = new SimpleStringProperty();
        this.departmentId = new SimpleStringProperty();
        this.isManager = new SimpleStringProperty();
    }
    
    public Employee(int empId, String empName, String positionId, double salary, 
                   String branchId, String managerId, String departmentId, String isManager) {
        this();
        setEmpId(empId);
        setEmpName(empName);
        setPositionId(positionId);
        setSalary(salary);
        setBranchId(branchId);
        setManagerId(managerId);
        setDepartmentId(departmentId);
        setIsManager(isManager);
    }
    
    // Employee ID
    public int getEmpId() { return empId.get(); }
    public void setEmpId(int empId) { this.empId.set(empId); }
    public IntegerProperty empIdProperty() { return empId; }
    
    // Employee Name
    public String getEmpName() { return empName.get(); }
    public void setEmpName(String empName) { this.empName.set(empName); }
    public StringProperty empNameProperty() { return empName; }
    
    // Position ID
    public String getPositionId() { return positionId.get(); }
    public void setPositionId(String positionId) { this.positionId.set(positionId); }
    public StringProperty positionIdProperty() { return positionId; }
    
    // Salary
    public double getSalary() { return salary.get(); }
    public void setSalary(double salary) { this.salary.set(salary); }
    public DoubleProperty salaryProperty() { return salary; }
    
    // Branch ID
    public String getBranchId() { return branchId.get(); }
    public void setBranchId(String branchId) { this.branchId.set(branchId); }
    public StringProperty branchIdProperty() { return branchId; }
    
    // Manager ID
    public String getManagerId() { return managerId.get(); }
    public void setManagerId(String managerId) { this.managerId.set(managerId); }
    public StringProperty managerIdProperty() { return managerId; }
    
    // Department ID
    public String getDepartmentId() { return departmentId.get(); }
    public void setDepartmentId(String departmentId) { this.departmentId.set(departmentId); }
    public StringProperty departmentIdProperty() { return departmentId; }
    
    // Is Manager
    public String getIsManager() { return isManager.get(); }
    public void setIsManager(String isManager) { this.isManager.set(isManager); }
    public StringProperty isManagerProperty() { return isManager; }
    
    public String toString() {
        return "Employee{" +
                "empId=" + getEmpId() +
                ", empName='" + getEmpName() + '\'' +
                ", positionId='" + getPositionId() + '\'' +
                ", salary=" + getSalary() +
                ", branchId='" + getBranchId() + '\'' +
                ", managerId='" + getManagerId() + '\'' +
                ", departmentId='" + getDepartmentId() + '\'' +
                ", isManager='" + getIsManager() + '\'' +
                '}';
    }
}