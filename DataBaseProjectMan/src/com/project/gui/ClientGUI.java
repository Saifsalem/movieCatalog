package com.project.gui;

import com.project.dao.ClientDAO;
import com.project.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ClientGUI {
	private ClientDAO clientDAO;
	private TableView<Client> tableView;
	private ObservableList<Client> clientList;

	private TextField clientIdField;
	private TextField nameField;
	private TextField contactField;
	private TextField emailField;
	private TextArea addressField;

	private Label statsLabel;
	private Stage stage;

	public ClientGUI() {
		this.clientDAO = new ClientDAO();
		this.tableView = new TableView<>();
		this.clientList = FXCollections.observableArrayList();
		this.initializeFields();
	}

	private void initializeFields() {
		this.clientIdField = new TextField();
		this.nameField = new TextField();
		this.contactField = new TextField();
		this.emailField = new TextField();
		this.addressField = new TextArea();
		this.statsLabel = new Label();
	}

	public void show() {
		stage = new Stage();
		stage.setTitle("Client Management System");

		VBox root = new VBox(15);
		root.setPadding(new Insets(20));
		root.setStyle("-fx-background-color: #f5f6fa;");

		// Create title
		Label title = new Label("Client Management");
		title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
		title.setAlignment(Pos.CENTER);

		// Create input form
		GridPane formGrid = createInputForm();

		// Create buttons
		HBox buttonBox = createButtons();

		// Create table
		createTable();

		// Create statistics section
		VBox statsBox = createStatsSection();

		root.getChildren().addAll(title, formGrid, buttonBox, tableView, statsBox);

		Scene scene = new Scene(root, 1200, 800);
		stage.setScene(scene);
		stage.show();

		loadData();
	}

	private GridPane createInputForm() {
		GridPane grid = new GridPane();
		grid.setHgap(15);
		grid.setVgap(10);
		grid.setPadding(new Insets(15));
		grid.setStyle("-fx-background-color: white; -fx-border-color: #bdc3c7; -fx-border-radius: 5;");

		// Configure field styles
		String fieldStyle = "-fx-padding: 8; -fx-border-color: #bdc3c7; -fx-border-radius: 3;";
		clientIdField.setStyle(fieldStyle);
		nameField.setStyle(fieldStyle);
		contactField.setStyle(fieldStyle);
		emailField.setStyle(fieldStyle);
		addressField.setStyle(fieldStyle);

		// Set address field properties
		addressField.setPrefRowCount(3);
		addressField.setWrapText(true);

		// Add placeholders
		clientIdField.setPromptText("Enter Client ID");
		nameField.setPromptText("Enter Client Name");
		contactField.setPromptText("Enter Phone Number");
		emailField.setPromptText("Enter Email Address");
		addressField.setPromptText("Enter Client Address");

		// Create labels with styling
		String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";

		Label clientIdLabel = new Label("Client ID:");
		clientIdLabel.setStyle(labelStyle);
		grid.add(clientIdLabel, 0, 0);
		grid.add(clientIdField, 1, 0);

		Label nameLabel = new Label("Client Name:");
		nameLabel.setStyle(labelStyle);
		grid.add(nameLabel, 0, 1);
		grid.add(nameField, 1, 1);

		Label contactLabel = new Label("Contact:");
		contactLabel.setStyle(labelStyle);
		grid.add(contactLabel, 0, 2);
		grid.add(contactField, 1, 2);

		Label emailLabel = new Label("Email:");
		emailLabel.setStyle(labelStyle);
		grid.add(emailLabel, 2, 0);
		grid.add(emailField, 3, 0);

		Label addressLabel = new Label("Address:");
		addressLabel.setStyle(labelStyle);
		grid.add(addressLabel, 2, 1);
		grid.add(addressField, 3, 1, 1, 2); // Span 2 rows

		return grid;
	}

	private HBox createButtons() {
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(10));

		String buttonStyle = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 5; -fx-background-radius: 5;";
		String successButtonStyle = "-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 5; -fx-background-radius: 5;";
		String warningButtonStyle = "-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 5; -fx-background-radius: 5;";
		String dangerButtonStyle = "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 5; -fx-background-radius: 5;";

		Button addButton = new Button("Add Client");
		addButton.setStyle(successButtonStyle);
		addButton.setOnAction(e -> addClient());

		Button updateButton = new Button("Update Selected");
		updateButton.setStyle(warningButtonStyle);
		updateButton.setOnAction(e -> updateClient());

		Button deleteButton = new Button("Delete Selected");
		deleteButton.setStyle(dangerButtonStyle);
		deleteButton.setOnAction(e -> deleteClient());

		Button refreshButton = new Button("Refresh Data");
		refreshButton.setStyle(buttonStyle);
		refreshButton.setOnAction(e -> loadData());

		Button sortNameAscButton = new Button("Sort Name Asc");
		sortNameAscButton.setStyle(buttonStyle);
		sortNameAscButton.setOnAction(e -> sortByNameAscending());

		Button sortNameDescButton = new Button("Sort Name Desc");
		sortNameDescButton.setStyle(buttonStyle);
		sortNameDescButton.setOnAction(e -> sortByNameDescending());

		Button sortIdAscButton = new Button("Sort ID Asc");
		sortIdAscButton.setStyle(buttonStyle);
		sortIdAscButton.setOnAction(e -> sortByIdAscending());

		Button sortIdDescButton = new Button("Sort ID Desc");
		sortIdDescButton.setStyle(buttonStyle);
		sortIdDescButton.setOnAction(e -> sortByIdDescending());

		Button statsButton = new Button("Show Statistics");
		statsButton.setStyle(buttonStyle);
		statsButton.setOnAction(e -> showStatistics());

		Button clearButton = new Button("Clear Fields");
		clearButton.setStyle(buttonStyle);
		clearButton.setOnAction(e -> clearFields());

		buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, refreshButton, sortNameAscButton,
				sortNameDescButton, sortIdAscButton, sortIdDescButton, statsButton, clearButton);
		return buttonBox;
	}

	private void createTable() {
		TableColumn<Client, String> idCol = new TableColumn<>("Client ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("clientId"));
		idCol.setPrefWidth(120);

		TableColumn<Client, String> nameCol = new TableColumn<>("Client Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameCol.setPrefWidth(200);

		TableColumn<Client, String> contactCol = new TableColumn<>("Contact");
		contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
		contactCol.setPrefWidth(150);

		TableColumn<Client, String> emailCol = new TableColumn<>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailCol.setPrefWidth(200);

		TableColumn<Client, String> addressCol = new TableColumn<>("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
		addressCol.setPrefWidth(300);

		tableView.getColumns().addAll(idCol, nameCol, contactCol, emailCol, addressCol);
		tableView.setItems(clientList);

		// Add selection listener to populate fields when row is selected
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				populateFields(newSelection);
			}
		});

		tableView.setStyle("-fx-background-color: white;");
		tableView.setPrefHeight(300);
	}

	private VBox createStatsSection() {
		VBox statsBox = new VBox(10);
		statsBox.setPadding(new Insets(15));
		statsBox.setStyle("-fx-background-color: white; -fx-border-color: #bdc3c7; -fx-border-radius: 5;");

		Label statsTitle = new Label("Client Statistics");
		statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");

		statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");

		statsBox.getChildren().addAll(statsTitle, statsLabel);
		return statsBox;
	}

	private void loadData() {
		clientList.clear();
		clientList.addAll(clientDAO.getAllClients());
		showStatistics();
	}

	private void addClient() {
		if (validateFields()) {
			Client client = new Client(clientIdField.getText().trim(), nameField.getText().trim(),
					contactField.getText().trim(), emailField.getText().trim(), addressField.getText().trim());

			if (clientDAO.insertClient(client)) {
				loadData();
				clearFields();
				showAlert("Success", "Client added successfully!", Alert.AlertType.INFORMATION);
			} else {
				showAlert("Error", "Failed to add client to database!", Alert.AlertType.ERROR);
			}
		}
	}

	private void updateClient() {
		Client selectedClient = tableView.getSelectionModel().getSelectedItem();
		if (selectedClient != null) {
			if (validateFields()) {
				selectedClient.setClientId(clientIdField.getText().trim());
				selectedClient.setName(nameField.getText().trim());
				selectedClient.setContact(contactField.getText().trim());
				selectedClient.setEmail(emailField.getText().trim());
				selectedClient.setAddress(addressField.getText().trim());

				if (clientDAO.updateClient(selectedClient)) {
					loadData();
					clearFields();
					showAlert("Success", "Client updated successfully!", Alert.AlertType.INFORMATION);
				} else {
					showAlert("Error", "Failed to update client!", Alert.AlertType.ERROR);
				}
			}
		} else {
			showAlert("Warning", "Please select a client to update!", Alert.AlertType.WARNING);
		}
	}

	private void deleteClient() {
		Client selectedClient = tableView.getSelectionModel().getSelectedItem();
		if (selectedClient != null) {
			Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmAlert.setTitle("Confirm Delete");
			confirmAlert.setHeaderText("Delete Client");
			confirmAlert.setContentText("Are you sure you want to delete this client?");

			if (confirmAlert.showAndWait().get() == ButtonType.OK) {
				if (clientDAO.deleteClient(selectedClient.getClientId())) {
					loadData();
					clearFields();
					showAlert("Success", "Client deleted successfully!", Alert.AlertType.INFORMATION);
				} else {
					showAlert("Error", "Failed to delete client!", Alert.AlertType.ERROR);
				}
			}
		} else {
			showAlert("Warning", "Please select a client to delete!", Alert.AlertType.WARNING);
		}
	}

	private void populateFields(Client client) {
		clientIdField.setText(client.getClientId());
		nameField.setText(client.getName());
		contactField.setText(client.getContact());
		emailField.setText(client.getEmail());
		addressField.setText(client.getAddress());
	}

	private boolean validateFields() {
		if (clientIdField.getText().trim().isEmpty()) {
			showAlert("Validation Error", "Client ID cannot be empty!", Alert.AlertType.WARNING);
			return false;
		}
		if (nameField.getText().trim().isEmpty()) {
			showAlert("Validation Error", "Client Name cannot be empty!", Alert.AlertType.WARNING);
			return false;
		}
		return true;
	}

	private void sortByNameAscending() {
		clientList.clear();
		clientList.addAll(clientDAO.getClientsSortedByNameAscending());
	}

	private void sortByNameDescending() {
		clientList.clear();
		clientList.addAll(clientDAO.getClientsSortedByNameDescending());
	}

	private void sortByIdAscending() {
		clientList.clear();
		clientList.addAll(clientDAO.getClientsSortedByIdAscending());
	}

	private void sortByIdDescending() {
		clientList.clear();
		clientList.addAll(clientDAO.getClientsSortedByIdDescending());
	}

	private void showStatistics() {
		int totalCount = clientDAO.getClientCount();
		int withEmailCount = clientDAO.getClientsWithEmailCount();
		int withContactCount = clientDAO.getClientsWithContactCount();

		double emailPercentage = totalCount > 0 ? (withEmailCount * 100.0 / totalCount) : 0;
		double contactPercentage = totalCount > 0 ? (withContactCount * 100.0 / totalCount) : 0;

		String stats = String.format(
				"Total Clients: %d\n" + "Clients with Email: %d (%.1f%%)\n" + "Clients with Contact: %d (%.1f%%)\n"
						+ "Clients without Email: %d\n" + "Clients without Contact: %d",
				totalCount, withEmailCount, emailPercentage, withContactCount, contactPercentage,
				(totalCount - withEmailCount), (totalCount - withContactCount));

		statsLabel.setText(stats);
	}

	private void clearFields() {
		clientIdField.clear();
		nameField.clear();
		contactField.clear();
		emailField.clear();
		addressField.clear();
		tableView.getSelectionModel().clearSelection();
	}

	private void showAlert(String title, String message, Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}