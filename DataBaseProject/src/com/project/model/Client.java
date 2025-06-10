package com.project.model;

import javafx.beans.property.*;

public class Client {
	private StringProperty clientId;
	private StringProperty clientName;
	private StringProperty contactInfo;
	private StringProperty email;
	private StringProperty address;

	public Client() {
		this.clientId = new SimpleStringProperty();
		this.clientName = new SimpleStringProperty();
		this.contactInfo = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.address = new SimpleStringProperty();
	}

	public Client(String clientId, String clientName, String contactInfo, String email, String address) {
		this();
		setClientId(clientId);
		setClientName(clientName);
		setContactInfo(contactInfo);
		setEmail(email);
		setAddress(address);
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

	// Client Name
	public String getClientName() {
		return clientName.get();
	}

	public void setClientName(String clientName) {
		this.clientName.set(clientName);
	}

	public StringProperty clientNameProperty() {
		return clientName;
	}
	
	// Contact Info
	public String getContactInfo() {
		return contactInfo.get();
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo.set(contactInfo);
	}

	public StringProperty contactInfoProperty() {
		return contactInfo;
	}

	// Email
	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public StringProperty emailProperty() {
		return email;
	}

	// Address
	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address.set(address);
	}

	public StringProperty addressProperty() {
		return address;
	}

	// For TableView to work properly with PropertyValueFactory
	public String getName() {
		return getClientName();
	}
	
	public String getContact() {
		return getContactInfo();
	}

	@Override
	public String toString() {
		return "Client{" + 
			"clientId='" + getClientId() + '\'' + 
			", clientName='" + getClientName() + '\'' + 
			", contactInfo='" + getContactInfo() + '\'' + 
			", email='" + getEmail() + '\'' + 
			", address='" + getAddress() + '\'' + 
			'}';
	}
}