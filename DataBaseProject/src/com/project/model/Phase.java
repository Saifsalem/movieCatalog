package com.project.model;

import javafx.beans.property.*;

public class Phase {
    private StringProperty phaseId;
    private StringProperty projectId;
    private StringProperty name;
    private StringProperty description;
    private StringProperty startDate;
    private StringProperty endDate;
    private StringProperty status;
    
    public Phase() {
        this.phaseId = new SimpleStringProperty();
        this.projectId = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.startDate = new SimpleStringProperty();
        this.endDate = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
    }
    
    public Phase(String phaseId, String projectId, String name, String description, 
                String startDate, String endDate, String status) {
        this();
        setPhaseId(phaseId);
        setProjectId(projectId);
        setName(name);
        setDescription(description);
        setStartDate(startDate);
        setEndDate(endDate);
        setStatus(status);
    }
    
    // Phase ID
    public String getPhaseId() { return phaseId.get(); }
    public void setPhaseId(String phaseId) { this.phaseId.set(phaseId); }
    public StringProperty phaseIdProperty() { return phaseId; }
    
    // Project ID
    public String getProjectId() { return projectId.get(); }
    public void setProjectId(String projectId) { this.projectId.set(projectId); }
    public StringProperty projectIdProperty() { return projectId; }
    
    // Name
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }
    
    // Description
    public String getDescription() { return description.get(); }
    public void setDescription(String description) { this.description.set(description); }
    public StringProperty descriptionProperty() { return description; }
    
    // Start Date
    public String getStartDate() { return startDate.get(); }
    public void setStartDate(String startDate) { this.startDate.set(startDate); }
    public StringProperty startDateProperty() { return startDate; }
    
    // End Date
    public String getEndDate() { return endDate.get(); }
    public void setEndDate(String endDate) { this.endDate.set(endDate); }
    public StringProperty endDateProperty() { return endDate; }
    
    // Status
    public String getStatus() { return status.get(); }
    public void setStatus(String status) { this.status.set(status); }
    public StringProperty statusProperty() { return status; }
    
    public String toString() {
        return "Phase{" +
                "phaseId='" + getPhaseId() + '\'' +
                ", projectId='" + getProjectId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", startDate='" + getStartDate() + '\'' +
                ", endDate='" + getEndDate() + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}