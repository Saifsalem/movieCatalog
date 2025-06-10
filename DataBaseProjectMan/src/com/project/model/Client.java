package com.project.model;

import javafx.beans.property.*;

public class Client {
	private StringProperty clientId;
	private StringProperty clientName;

	public Client() {
		this.clientId = new SimpleStringProperty();
		this.clientName = new SimpleStringProperty();
	}

	public Client(String clientId, String clientName) {
		this();
		setClientId(clientId);
		setClientName(clientName);
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

	public String toString() {
		return "Client{" + "clientId='" + getClientId() + '\'' + ", clientName='" + getClientName() + '\'' + '}';
	}

}