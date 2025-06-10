package com.project.model;

import javafx.beans.property.*;

public class Branch {
	private StringProperty branchId;
	private StringProperty location;

	public Branch() {
		this.branchId = new SimpleStringProperty();
		this.location = new SimpleStringProperty();
	}

	public Branch(String branchId, String location) {
		this();
		setBranchId(branchId);
		setLocation(location);
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

	public String toString() {
		return "Branch{" + "branchId='" + getBranchId() + '\'' + ", location='" + getLocation() + '\'' + '}';
	}
}