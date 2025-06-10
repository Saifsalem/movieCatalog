package com.project.model;

import javafx.beans.property.*;

public class Schedule {
    private IntegerProperty scheduleId;
    private StringProperty projectId;
    private StringProperty phaseId;
    private StringProperty startDate;
    private StringProperty endDate;
    private StringProperty taskDetails;
    
    public Schedule() {
        this.scheduleId = new SimpleIntegerProperty();
        this.projectId = new SimpleStringProperty();
        this.phaseId = new SimpleStringProperty();
        this.startDate = new SimpleStringProperty();
        this.endDate = new SimpleStringProperty();
        this.taskDetails = new SimpleStringProperty();
    }
    
    public Schedule(int scheduleId, String projectId, String phaseId, String startDate, 
                   String endDate, String taskDetails) {
        this();
        setScheduleId(scheduleId);
        setProjectId(projectId);
        setPhaseId(phaseId);
        setStartDate(startDate);
        setEndDate(endDate);
        setTaskDetails(taskDetails);
    }
    
    // Schedule ID
    public int getScheduleId() { return scheduleId.get(); }
    public void setScheduleId(int scheduleId) { this.scheduleId.set(scheduleId); }
    public IntegerProperty scheduleIdProperty() { return scheduleId; }
    
    // Project ID
    public String getProjectId() { return projectId.get(); }
    public void setProjectId(String projectId) { this.projectId.set(projectId); }
    public StringProperty projectIdProperty() { return projectId; }
    
    // Phase ID
    public String getPhaseId() { return phaseId.get(); }
    public void setPhaseId(String phaseId) { this.phaseId.set(phaseId); }
    public StringProperty phaseIdProperty() { return phaseId; }
    
    // Start Date
    public String getStartDate() { return startDate.get(); }
    public void setStartDate(String startDate) { this.startDate.set(startDate); }
    public StringProperty startDateProperty() { return startDate; }
    
    // End Date
    public String getEndDate() { return endDate.get(); }
    public void setEndDate(String endDate) { this.endDate.set(endDate); }
    public StringProperty endDateProperty() { return endDate; }
    
    // Task Details
    public String getTaskDetails() { return taskDetails.get(); }
    public void setTaskDetails(String taskDetails) { this.taskDetails.set(taskDetails); }
    public StringProperty taskDetailsProperty() { return taskDetails; }
    
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + getScheduleId() +
                ", projectId='" + getProjectId() + '\'' +
                ", phaseId='" + getPhaseId() + '\'' +
                ", startDate='" + getStartDate() + '\'' +
                ", endDate='" + getEndDate() + '\'' +
                ", taskDetails='" + getTaskDetails() + '\'' +
                '}';
    }
}