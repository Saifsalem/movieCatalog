package com.project.model;

import javafx.beans.property.*;

public class Project {
	private StringProperty projectId;
	private StringProperty branchId;
	private StringProperty clientId;
	private StringProperty pname;
	private StringProperty location;
	private DoubleProperty cost;
	private DoubleProperty revenue;

	public Project() {
		this.projectId = new SimpleStringProperty();
		this.branchId = new SimpleStringProperty();
		this.clientId = new SimpleStringProperty();
		this.pname = new SimpleStringProperty();
		this.location = new SimpleStringProperty();
		this.cost = new SimpleDoubleProperty();
		this.revenue = new SimpleDoubleProperty();
	}

	public Project(String projectId, String branchId, String clientId, String pname, String location, double cost,
			double revenue) {
		this();
		setProjectId(projectId);
		setBranchId(branchId);
		setClientId(clientId);
		setPname(pname);
		setLocation(location);
		setCost(cost);
		setRevenue(revenue);
	}

	// Project ID
	public String getProjectId() {
		return projectId.get();
	}

	public void setProjectId(String projectId) {
		this.projectId.set(projectId);
	}

	public StringProperty projectIdProperty() {
		return projectId;
	}

	// Branch ID
	public String getBranchId() {
		return branchId.get();
	}

	public void setBranchId(String branchId) {
		this.branchId.set(branchId);
	}

	public StringProperty branchIdProperty() {
		return branchId;
	}

	// Client ID
	public String getClientId() {
		return clientId.get();
	}

	public void setClientId(String clientId) {
		this.clientId.set(clientId);
	}

	public StringProperty clientIdProperty() {
		return clientId;
	}

	// Project Name
	public String getPname() {
		return pname.get();
	}

	public void setPname(String pname) {
		this.pname.set(pname);
	}

	public StringProperty pnameProperty() {
		return pname;
	}

	// Location
	public String getLocation() {
		return location.get();
	}

	public void setLocation(String location) {
		this.location.set(location);
	}

	public StringProperty locationProperty() {
		return location;
	}

	// Cost
	public double getCost() {
		return cost.get();
	}

	public void setCost(double cost) {
		this.cost.set(cost);
	}

	public DoubleProperty costProperty() {
		return cost;
	}

	// Revenue
	public double getRevenue() {
		return revenue.get();
	}

	public void setRevenue(double revenue) {
		this.revenue.set(revenue);
	}

	public DoubleProperty revenueProperty() {
		return revenue;
	}

	public String toString() {
		return "Project{" + "projectId='" + getProjectId() + '\'' + ", branchId='" + getBranchId() + '\''
				+ ", clientId='" + getClientId() + '\'' + ", pname='" + getPname() + '\'' + ", location='"
				+ getLocation() + '\'' + ", cost=" + getCost() + ", revenue=" + getRevenue() + '}';
	}
}