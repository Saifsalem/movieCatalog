package com.project.gui;

import com.project.dao.ContractDAO;
import com.project.model.Contract;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContractGUI {
	private ContractDAO contractDAO;
	private TableView<Contract> tableView;
	private ObservableList<Contract> contractList;

	private TextField projectIdField;
	private TextField clientIdField;
	private TextField startDateField;
	private TextField endDateField;
	private TextField totalValueField;
	private TextField statusField;

	private Label statsLabel;
	private Stage stage;

	public ContractGUI() {
		this.contractDAO = new ContractDAO();
		this.tableView = new TableView<>();
		this.contractList = FXCollections.observableArrayList();
		this.initializeFields();
	}

	private void initializeFields() {
		this.projectIdField = new TextField();
		this.clientIdField = new TextField();
		this.startDateField = new TextField();
		this.endDateField = new TextField();
		this.totalValueField = new TextField();
		this.statusField = new TextField();
		this.statsLabel = new Label();
	}

	public void show() {
		stage = new Stage();
		stage.setTitle("Contract Management System");

		VBox root = new VBox(15);
		root.setPadding(new Insets(20));
		root.setStyle("-fx-background-color: #f0f0f0;");

		// Create title
		Label title = new Label("Contract Management");
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
		projectIdField.setStyle(fieldStyle);
		clientIdField.setStyle(fieldStyle);
		startDateField.setStyle(fieldStyle);
		endDateField.setStyle(fieldStyle);
		totalValueField.setStyle(fieldStyle);
		statusField.setStyle(fieldStyle);

		// Add placeholders
		projectIdField.setPromptText("Enter Project ID");
		clientIdField.setPromptText("Enter Client ID");
		startDateField.setPromptText("YYYY-MM-DD");
		endDateField.setPromptText("YYYY-MM-DD");
		totalValueField.setPromptText("Enter Amount");
		statusField.setPromptText("Active/Completed/Pending");

		// Create labels with styling
		String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";

		Label projectLabel = new Label("Project ID:");
		projectLabel.setStyle(labelStyle);
		grid.add(projectLabel, 0, 0);
		grid.add(projectIdField, 1, 0);

		Label clientLabel = new Label("Client ID:");
		clientLabel.setStyle(labelStyle);
		grid.add(clientLabel, 0, 1);
		grid.add(clientIdField, 1, 1);

		Label startLabel = new Label("Start Date:");
		startLabel.setStyle(labelStyle);
		grid.add(startLabel, 0, 2);
		grid.add(startDateField, 1, 2);

		Label endLabel = new Label("End Date:");
		endLabel.setStyle(labelStyle);
		grid.add(endLabel, 2, 0);
		grid.add(endDateField, 3, 0);

		Label valueLabel = new Label("Total Value:");
		valueLabel.setStyle(labelStyle);
		grid.add(valueLabel, 2, 1);
		grid.add(totalValueField, 3, 1);

		Label statusLabel = new Label("Status:");
		statusLabel.setStyle(labelStyle);
		grid.add(statusLabel, 2, 2);
		grid.add(statusField, 3, 2);

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

		Button addButton = new Button("Add Contract");
		addButton.setStyle(successButtonStyle);
		addButton.setOnAction(e -> addContract());

		Button updateButton = new Button("Update Selected");
		updateButton.setStyle(warningButtonStyle);
		updateButton.setOnAction(e -> updateContract());

		Button deleteButton = new Button("Delete Selected");
		deleteButton.setStyle(dangerButtonStyle);
		deleteButton.setOnAction(e -> deleteContract());

		Button refreshButton = new Button("Refresh Data");
		refreshButton.setStyle(buttonStyle);
		refreshButton.setOnAction(e -> loadData());

		Button sortAscButton = new Button("Sort Ascending");
		sortAscButton.setStyle(buttonStyle);
		sortAscButton.setOnAction(e -> sortAscending());

		Button sortDescButton = new Button("Sort Descending");
		sortDescButton.setStyle(buttonStyle);
		sortDescButton.setOnAction(e -> sortDescending());

		Button statsButton = new Button("Show Statistics");
		statsButton.setStyle(buttonStyle);
		statsButton.setOnAction(e -> showStatistics());

		Button clearButton = new Button("Clear Fields");
		clearButton.setStyle(buttonStyle);
		clearButton.setOnAction(e -> clearFields());

		buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, refreshButton, sortAscButton,
				sortDescButton, statsButton, clearButton);
		return buttonBox;
	}

	private void createTable() {
		TableColumn<Contract, Integer> idCol = new TableColumn<>("Contract ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("contractId"));
		idCol.setPrefWidth(100);

		TableColumn<Contract, String> projectIdCol = new TableColumn<>("Project ID");
		projectIdCol.setCellValueFactory(new PropertyValueFactory<>("projectId"));
		projectIdCol.setPrefWidth(120);

		TableColumn<Contract, String> clientIdCol = new TableColumn<>("Client ID");
		clientIdCol.setCellValueFactory(new PropertyValueFactory<>("clientId"));
		clientIdCol.setPrefWidth(120);

		TableColumn<Contract, String> startDateCol = new TableColumn<>("Start Date");
		startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		startDateCol.setPrefWidth(120);

		TableColumn<Contract, String> endDateCol = new TableColumn<>("End Date");
		endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		endDateCol.setPrefWidth(120);

		TableColumn<Contract, Double> totalValueCol = new TableColumn<>("Total Value");
		totalValueCol.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
		totalValueCol.setPrefWidth(120);

		TableColumn<Contract, String> statusCol = new TableColumn<>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		statusCol.setPrefWidth(120);

		tableView.getColumns().addAll(idCol, projectIdCol, clientIdCol, startDateCol, endDateCol, totalValueCol,
				statusCol);
		tableView.setItems(contractList);

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

		Label statsTitle = new Label("Contract Statistics");
		statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");

		statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");

		statsBox.getChildren().addAll(statsTitle, statsLabel);
		return statsBox;
	}

	private void loadData() {
		contractList.clear();
		contractList.addAll(contractDAO.getAllContracts());
		showStatistics(); // Update statistics when data is loaded
	}

	private void addContract() {
		try {
			if (validateFields()) {
				Contract contract = new Contract(0, // ID will be auto-generated
						projectIdField.getText().trim(), clientIdField.getText().trim(),
						startDateField.getText().trim(), endDateField.getText().trim(),
						Double.parseDouble(totalValueField.getText().trim()), statusField.getText().trim());

				if (contractDAO.insertContract(contract)) {
					loadData();
					clearFields();
					showAlert("Success", "Contract added successfully!", Alert.AlertType.INFORMATION);
				} else {
					showAlert("Error", "Failed to add contract to database!", Alert.AlertType.ERROR);
				}
			}
		} catch (NumberFormatException e) {
			showAlert("Error", "Please enter a valid number for total value!", Alert.AlertType.ERROR);
		}
	}

	private void updateContract() {
		Contract selectedContract = tableView.getSelectionModel().getSelectedItem();
		if (selectedContract != null) {
			try {
				if (validateFields()) {
					selectedContract.setProjectId(projectIdField.getText().trim());
					selectedContract.setClientId(clientIdField.getText().trim());
					selectedContract.setStartDate(startDateField.getText().trim());
					selectedContract.setEndDate(endDateField.getText().trim());
					selectedContract.setTotalValue(Double.parseDouble(totalValueField.getText().trim()));
					selectedContract.setStatus(statusField.getText().trim());

					if (contractDAO.updateContract(selectedContract)) {
						loadData();
						clearFields();
						showAlert("Success", "Contract updated successfully!", Alert.AlertType.INFORMATION);
					} else {
						showAlert("Error", "Failed to update contract!", Alert.AlertType.ERROR);
					}
				}
			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter a valid number for total value!", Alert.AlertType.ERROR);
			}
		} else {
			showAlert("Warning", "Please select a contract to update!", Alert.AlertType.WARNING);
		}
	}

	private void deleteContract() {
		Contract selectedContract = tableView.getSelectionModel().getSelectedItem();
		if (selectedContract != null) {
			Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmAlert.setTitle("Confirm Delete");
			confirmAlert.setHeaderText("Delete Contract");
			confirmAlert.setContentText("Are you sure you want to delete this contract?");

			if (confirmAlert.showAndWait().get() == ButtonType.OK) {
				if (contractDAO.deleteContract(selectedContract.getContractId())) {
					loadData();
					clearFields();
					showAlert("Success", "Contract deleted successfully!", Alert.AlertType.INFORMATION);
				} else {
					showAlert("Error", "Failed to delete contract!", Alert.AlertType.ERROR);
				}
			}
		} else {
			showAlert("Warning", "Please select a contract to delete!", Alert.AlertType.WARNING);
		}
	}

	private void populateFields(Contract contract) {
        projectIdField.setText(contract.getProjectId());
        clientIdField.setText(contract.getClientId());
        startDateField.setText(contract.getStartDate());
        endDateField.setText(contract.getEndDate());
        totalValueField.setText(String.valueOf(contract.getTotalValue()));
        statusField.setText(contract.getStatus());
    }

	private boolean validateFields() {
        if (projectIdField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Project ID cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (clientIdField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Client ID cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (startDateField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Start Date cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (endDateField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "End Date cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (totalValueField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Total Value cannot be empty!", Alert.AlertType.WARNING);
            return false