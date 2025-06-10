package com.project.gui;

import com.project.dao.BranchDAO;
import com.project.model.Branch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BranchGUI {
	private BranchDAO branchDAO;
	private TableView<Branch> tableView;
	private ObservableList<Branch> branchList;

	private TextField branchIdField;
	private TextField locationField;

	private Label statsLabel;
	private Stage stage;

	public BranchGUI() {
		this.branchDAO = new BranchDAO();
		this.tableView = new TableView<>();
		this.branchList = FXCollections.observableArrayList();
		this.initializeFields();
	}

	private void initializeFields() {
		this.branchIdField = new TextField();
		this.locationField = new TextField();
		this.statsLabel = new Label();
	}

	public void show() {
		stage = new Stage();
		stage.setTitle("Branch Management System");

		VBox root = new VBox(15);
		root.setPadding(new Insets(20));
		root.setStyle("-fx-background-color: #fff3e0;");

		// Create title
		Label title = new Label("Branch Management");
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

		Scene scene = new Scene(root, 800, 600);
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
		branchIdField.setStyle(fieldStyle);
		locationField.setStyle(fieldStyle);

		// Add placeholders
		branchIdField.setPromptText("Enter Branch ID");
		locationField.setPromptText("Enter Branch Location");

		// Create labels with styling
		String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";

		Label branchIdLabel = new Label("Branch ID:");
		branchIdLabel.setStyle(labelStyle);
		grid.add(branchIdLabel, 0, 0);
		grid.add(branchIdField, 1, 0);

		Label locationLabel = new Label("Location:");
		locationLabel.setStyle(labelStyle);
		grid.add(locationLabel, 2, 0);
		grid.add(locationField, 3, 0);

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

		Button addButton = new Button("Add Branch");
		addButton.setStyle(successButtonStyle);
		addButton.setOnAction(e -> addBranch());

		Button updateButton = new Button("Update Selected");
		updateButton.setStyle(warningButtonStyle);
		updateButton.setOnAction(e -> updateBranch());

		Button deleteButton = new Button("Delete Selected");
		deleteButton.setStyle(dangerButtonStyle);
		deleteButton.setOnAction(e -> deleteBranch());

		Button refreshButton = new Button("Refresh Data");
		refreshButton.setStyle(buttonStyle);
		refreshButton.setOnAction(e -> loadData());

		Button sortLocationAscButton = new Button("Sort Location Asc");
		sortLocationAscButton.setStyle(buttonStyle);
		sortLocationAscButton.setOnAction(e -> sortByLocationAscending());

		Button sortLocationDescButton = new Button("Sort Location Desc");
		sortLocationDescButton.setStyle(buttonStyle);
		sortLocationDescButton.setOnAction(e -> sortByLocationDescending());

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

		buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, refreshButton, sortLocationAscButton,
				sortLocationDescButton, sortIdAscButton, sortIdDescButton, statsButton, clearButton);
		return buttonBox;
	}

	private void createTable() {
		TableColumn<Branch, String> idCol = new TableColumn<>("Branch ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("branchId"));
		idCol.setPrefWidth(200);

		TableColumn<Branch, String> locationCol = new TableColumn<>("Location");
		locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
		locationCol.setPrefWidth(300);

		tableView.getColumns().addAll(idCol, locationCol);
		tableView.setItems(branchList);

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

		Label statsTitle = new Label("Branch Statistics");
		statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");

		statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");

		statsBox.getChildren().addAll(statsTitle, statsLabel);
		return statsBox;
	}

	private void loadData() {
		branchList.clear();
		branchList.addAll(branchDAO.getAllBranches());
		showStatistics();
	}

	private void addBranch() {
		if (validateFields()) {
			Branch branch = new Branch(branchIdField.getText().trim(), locationField.getText().trim());

			if (branchDAO.insertBranch(branch)) {
				loadData();
				clearFields();
				showAlert("Success", "Branch added successfully!", Alert.AlertType.INFORMATION);
			} else {
				showAlert("Error", "Failed to add branch to database!", Alert.AlertType.ERROR);
			}
		}
	}

	private void updateBranch() {
		Branch selectedBranch = tableView.getSelectionModel().getSelectedItem();
		if (selectedBranch != null) {
			if (validateFields()) {
				selectedBranch.setBranchId(branchIdField.getText().trim());
				selectedBranch.setLocation(locationField.getText().trim());

				if (branchDAO.updateBranch(selectedBranch)) {
					loadData();
					clearFields();
					showAlert("Success", "Branch updated successfully!", Alert.AlertType.INFORMATION);
				} else {
					showAlert("Error", "Failed to update branch!", Alert.AlertType.ERROR);
				}
			}
		} else {
			showAlert("Warning", "Please select a branch to update!", Alert.AlertType.WARNING);
		}
	}

	private void deleteBranch() {
		Branch selectedBranch = tableView.getSelectionModel().getSelectedItem();
		if (selectedBranch != null) {
			Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmAlert.setTitle("Confirm Delete");
			confirmAlert.setHeaderText("Delete Branch");
			confirmAlert.setContentText("Are you sure you want to delete this branch?");

			if (confirmAlert.showAndWait().get() == ButtonType.OK) {
				if (branchDAO.deleteBranch(selectedBranch.getBranchId())) {
					loadData();
					clearFields();
					showAlert("Success", "Branch deleted successfully!", Alert.AlertType.INFORMATION);
				} else {
					showAlert("Error", "Failed to delete branch!", Alert.AlertType.ERROR);
				}
			}
		} else {
			showAlert("Warning", "Please select a branch to delete!", Alert.AlertType.WARNING);
		}
	}

	private void populateFields(Branch branch) {
		branchIdField.setText(branch.getBranchId());
		locationField.setText(branch.getLocation());
	}

	private boolean validateFields() {
		if (branchIdField.getText().trim().isEmpty()) {
			showAlert("Validation Error", "Branch ID cannot be empty!", Alert.AlertType.WARNING);
			return false;
		}
		if (locationField.getText().trim().isEmpty()) {
			showAlert("Validation Error", "Location cannot be empty!", Alert.AlertType.WARNING);
			return false;
		}
		return true;
	}

	private void sortByLocationAscending() {
		branchList.clear();
		branchList.addAll(branchDAO.getBranchesSortedByLocationAscending());
	}

	private void sortByLocationDescending() {
		branchList.clear();
		branchList.addAll(branchDAO.getBranchesSortedByLocationDescending());
	}

	private void sortByIdAscending() {
		branchList.clear();
		branchList.addAll(branchDAO.getBranchesSortedByIdAscending());
	}

	private void sortByIdDescending() {
		branchList.clear();
		branchList.addAll(branchDAO.getBranchesSortedByIdDescending());
	}

	private void showStatistics() {
		int totalCount = branchDAO.getBranchCount();

		String stats = String.format("Total Branches: %d\n" + "Branch Network Management\n"
				+ "All branch locations are tracked across regions.", totalCount);

		statsLabel.setText(stats);
	}

	private void clearFields() {
		branchIdField.clear();
		locationField.clear();
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