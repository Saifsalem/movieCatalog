package com.project.model;

import javafx.beans.property.*;

public class Role {
    private StringProperty roleId;
    private StringProperty title;
    
    public Role() {
        this.roleId = new SimpleStringProperty();
        this.title = new SimpleStringProperty();
    }
    
    public Role(String roleId, String title) {
        this();
        setRoleId(roleId);
        setTitle(title);
    }
    
    // Role ID
    public String getRoleId() { return roleId.get(); }
    public void setRoleId(String roleId) { this.roleId.set(roleId); }
    public StringProperty roleIdProperty() { return roleId; }
    
    // Title
    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return title; }
    
    public String toString() {
        return "Role{" +
                "roleId='" + getRoleId() + '\'' +
                ", title='" + getTitle() + '\'' +
                '}';
    }
}