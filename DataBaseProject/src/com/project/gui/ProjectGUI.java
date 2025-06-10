package com.project.gui;

import com.project.dao.ProjectDAO;
import com.project.model.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ProjectGUI {
	private ProjectDAO projectDAO;
	private TableView<Project> tableView;
	private ObservableList<Project> projectList;

	private TextField projectIdField;
	private TextField branchIdField;
	private TextField clientIdField;
	private TextField pnameField;
	private TextField locationField;
	private TextField costField;
	private TextField revenueField;

	private Label statsLabel;
	private Stage stage;

	public ProjectGUI() {
		this.projectDAO = new ProjectDAO();
		this.tableView = new TableView<>();
		this.projectList = FXCollections.observableArrayList();
		this.initializeFields();
	}

	private void initializeFields() {
		this.projectIdField = new TextField();
		this.branchIdField = new TextField();
		this.clientIdField = new TextField();
		this.pnameField = new TextField();
		this.locationField = new TextField();
		this.costField = new TextField();
		this.revenueField = new TextField();
		this.statsLabel = new Label();
	}

	public void show() {
		stage = new Stage();
		stage.setTitle("Project Management System");

		VBox root = new VBox(15);
		root.setPadding(new Insets(20));
		root.setStyle("-fx-background-color: #f8f9fa;");

		// Create title
		Label title = new Label("Project Management");
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

		Scene scene = new Scene(root, 1400, 850);
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
		branchIdField.setStyle(fieldStyle);
		clientIdField.setStyle(fieldStyle);
		pnameField.setStyle(fieldStyle);
		locationField.setStyle(fieldStyle);
		costField.setStyle(fieldStyle);
		revenueField.setStyle(fieldStyle);

		// Add placeholders
		projectIdField.setPromptText("Enter Project ID");
		branchIdField.setPromptText("Enter Branch ID");
		clientIdField.setPromptText("Enter Client ID");
		pnameField.setPromptText("Enter Project Name");
		locationField.setPromptText("Enter Location");
		costField.setPromptText("Enter Cost");
		revenueField.setPromptText("Enter Revenue");

		// Create labels with styling
		String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";

		// First row
		Label projectIdLabel = new Label("Project ID:");
		projectIdLabel.setStyle(labelStyle);
		grid.add(projectIdLabel, 0, 0);
		grid.add(projectIdField, 1, 0);

		Label branchIdLabel = new Label("Branch ID:");
		branchIdLabel.setStyle(labelStyle);
		grid.add(branchIdLabel, 2, 0);
		grid.add(branchIdField, 3, 0);

		Label clientIdLabel = new Label("Client ID:");
		clientIdLabel.setStyle(labelStyle);
		grid.add(clientIdLabel, 4, 0);
		grid.add(clientIdField, 5, 0);

		// Second row
		Label pnameLabel = new Label("Project Name:");
		pnameLabel.setStyle(labelStyle);
		grid.add(pnameLabel, 0, 1);
		grid.add(pnameField, 1, 1);

		Label locationLabel = new Label("Location:");
		locationLabel.setStyle(labelStyle);
		grid.add(locationLabel, 2, 1);
		grid.add(locationField, 3, 1);

		// Third row
		Label costLabel = new Label("Cost:");
		costLabel.setStyle(labelStyle);
		grid.add(costLabel, 0, 2);
		grid.add(costField, 1, 2);

		Label revenueLabel = new Label("Revenue:");
		revenueLabel.setStyle(labelStyle);
		grid.add(revenueLabel, 2, 2);
		grid.add(revenueField, 3, 2);

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

		Button addButton = new Button("Add Project");
		addButton.setStyle(successButtonStyle);
		addButton.setOnAction(e -> addProject());

		Button updateButton = new Button("Update Selected");
		updateButton.setStyle(warningButtonStyle);
		updateButton.setOnAction(e -> updateProject());

		Button deleteButton = new Button("Delete Selected");
		deleteButton.setStyle(dangerButtonStyle);
		deleteButton.setOnAction(e -> deleteProject());

		Button refreshButton = new Button("Refresh Data");
		refreshButton.setStyle(buttonStyle);
		refreshButton.setOnAction(e -> loadData());

		Button sortNameAscButton = new Button("Sort Name Asc");
		sortNameAscButton.setStyle(buttonStyle);
		sortNameAscButton.setOnAction(e -> sortByNameAscending());

		Button sortNameDescButton = new Button("Sort Name Desc");
		sortNameDescButton.setStyle(buttonStyle);
		sortNameDescButton.setOnAction(e -> sortByNameDescending());

		Button sortCostAscButton = new Button("Sort Cost Asc");
		sortCostAscButton.setStyle(buttonStyle);
		sortCostAscButton.setOnAction(e -> sortByCostAscending());

		Button sortCostDescButton = new Button("Sort Cost Desc");
		sortCostDescButton.setStyle(buttonStyle);
		sortCostDescButton.setOnAction(e -> sortByCostDescending());

		Button sortRevenueAscButton = new Button("Sort Revenue Asc");
		sortRevenueAscButton.setStyle(buttonStyle);
		sortRevenueAscButton.setOnAction(e -> sortByRevenueAscending());

		Button sortRevenueDescButton = new Button("Sort Revenue Desc");
		sortRevenueDescButton.setStyle(buttonStyle);
		sortRevenueDescButton.setOnAction(e -> sortByRevenueDescending());

		Button statsButton = new Button("Show Statistics");
		statsButton.setStyle(buttonStyle);
		statsButton.setOnAction(e -> showStatistics());

		Button clearButton = new Button("Clear Fields");
		clearButton.setStyle(buttonStyle);
		clearButton.setOnAction(e -> clearFields());

		buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, refreshButton, sortNameAscButton,
				sortNameDescButton, sortCostAscButton, sortCostDescButton, sortRevenueAscButton, sortRevenueDescButton,
				statsButton, clearButton);
		return buttonBox;
	}

	private void createTable() {
		TableColumn<Project, String> idCol = new TableColumn<>("Project ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("projectId"));
		idCol.setPrefWidth(100);

		TableColumn<Project, String> branchCol = new TableColumn<>("Branch ID");
		branchCol.setCellValueFactory(new PropertyValueFactory<>("branchId"));
		branchCol.setPrefWidth(100);

		TableColumn<Project, String> clientCol = new TableColumn<>("Client ID");
		clientCol.setCellValueFactory(new PropertyValueFactory<>("clientId"));
		clientCol.setPrefWidth(100);

		TableColumn<Project, String> nameCol = new TableColumn<>("Project Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("pname"));
		nameCol.setPrefWidth(200);

		TableColumn<Project, String> locationCol = new TableColumn<>("Location");
		locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
		locationCol.setPrefWidth(150);

		TableColumn<Project, Double> costCol = new TableColumn<>("Cost");
		costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		costCol.setPrefWidth(120);
		costCol.setCellFactory(tc -> new TableCell<Project, Double>() {
			@Override
			protected void updateItem(Double cost, boolean empty) {
				super.updateItem(cost, empty);
				if (empty || cost == null) {
					setText(null);
				} else {
					setText(String.format("$%.2f", cost));
				}
			}
		});

		TableColumn<Project, Double> revenueCol = new TableColumn<>("Revenue");
		revenueCol.setCellValueFactory(new PropertyValueFactory<>("revenue"));
		revenueCol.setPrefWidth(120);
		revenueCol.setCellFactory(tc -> new TableCell<Project, Double>() {
			@Override
			protected void updateItem(Double revenue, boolean empty) {
				super.updateItem(revenue, empty);
				if (empty || revenue == null) {
					setText(null);
				} else {
					setText(String.format("$%.2f", revenue));
				}
			}
		});

		TableColumn<Project, Double> profitCol = new TableColumn<>("Profit");
		profitCol.setPrefWidth(120);
		profitCol.setCellValueFactory(cellData -> {
			Project project = cellData.getValue();
			double profit = project.getRevenue() - project.getCost();
			return new javafx.beans.property.SimpleDoubleProperty(profit).asObject();
		});
		profitCol.setCellFactory(tc -> new TableCell<Project, Double>() {
			@Override
			protected void updateItem(Double profit, boolean empty) {
				super.updateItem(profit, empty);
				if (empty || profit == null) {
					setText(null);
				} else {
					setText(String.format("$%.2f", profit));
					if (profit < 0) {
						setStyle("-fx-text-fill: red;");
					} else {
						setStyle("-fx-text-fill: green;");
					}
				}
			}
		});

		tableView.getColumns().addAll(idCol, branchCol, clientCol, nameCol, locationCol, costCol, revenueCol,
				profitCol);
		tableView.setItems(projectList);

		// Add selection listener to populate fields when row is selected
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				populateFields(newSelection);
			}
		});

		tableView.setStyle("-fx-background-color: white;");
		tableView.setPrefHeight(350);
	}

	private VBox createStatsSection() {
		VBox statsBox = new VBox(10);
		statsBox.setPadding(new Insets(15));
		statsBox.setStyle("-fx-background-color: white; -fx-border-color: #bdc3c7; -fx-border-radius: 5;");

		Label statsTitle = new Label("Project Statistics");
		statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #2c3e50;");

		statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");
		statsLabel.setText("Click 'Show Statistics' to view project statistics");

		statsBox.getChildren().addAll(statsTitle, statsLabel);
		return statsBox;
	}

	private void loadData() {
		projectList.clear();
		projectList.addAll(projectDAO.getAllProjects());
		clearFields();
	}

	private void addProject() {
		if (!validateInput()) {
			showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill all required fields correctly!");
			return;
		}

		try {
			Project project = new Project(projectIdField.getText().trim(), branchIdField.getText().trim(),
					clientIdField.getText().trim(), pnameField.getText().trim(), locationField.getText().trim(),
					Double.parseDouble(costField.getText().trim()), Double.parseDouble(revenueField.getText().trim()));

			if (projectDAO.insertProject(project)) {
				showAlert(Alert.AlertType.INFORMATION, "Success", "Project added successfully!");
				loadData();
			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to add project!");
			}
		} catch (NumberFormatException e) {
			showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for cost and revenue!");
		}
	}

	private void updateProject() {
		Project selected = tableView.getSelectionModel().getSelectedItem();
		if (selected == null) {
			showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a project to update!");
			return;
		}

		if (!validateInput()) {
			showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill all required fields correctly!");
			return;
		}

		try {
			selected.setProjectId(projectIdField.getText().trim());
			selected.setBranchId(branchIdField.getText().trim());
			selected.setClientId(clientIdField.getText().trim());
			selected.setPname(pnameField.getText().trim());
			selected.setLocation(locationField.getText().trim());
			selected.setCost(Double.parseDouble(costField.getText().trim()));
			selected.setRevenue(Double.parseDouble(revenueField.getText().trim()));

			if (projectDAO.updateProject(selected)) {
				showAlert(Alert.AlertType.INFORMATION, "Success", "Project updated successfully!");
				loadData();
			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to update project!");
			}
		} catch (NumberFormatException e) {
			showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for cost and revenue!");
		}
	}

	private void deleteProject() {
		Project selected = tableView.getSelectionModel().getSelectedItem();
		if (selected == null) {
			showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a project to delete!");
			return;
		}

		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmAlert.setTitle("Confirm Delete");
		confirmAlert.setHeaderText("Delete Project");
		confirmAlert.setContentText("Are you sure you want to delete project: " + selected.getPname() + "?");

		confirmAlert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				if (projectDAO.deleteProject(selected.getProjectId())) {
					showAlert(Alert.AlertType.INFORMATION, "Success", "Project deleted successfully!");
					loadData();
				} else {
					showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete project!");
				}
			}
		});
	}

	private void sortByNameAscending() {
		projectList.clear();
		projectList.addAll(projectDAO.getProjectsSortedByNameAscending());
	}

	private void sortByNameDescending() {
		projectList.clear();
		projectList.addAll(projectDAO.getProjectsSortedByNameDescending());
	}

	private void sortByCostAscending() {
		projectList.clear();
		projectList.addAll(projectDAO.getProjectsSortedByCostAscending());
	}

	private void sortByCostDescending() {
		projectList.clear();
		projectList.addAll(projectDAO.getProjectsSortedByCostDescending());
	}

	private void sortByRevenueAscending() {
		projectList.clear();
		projectList.addAll(projectDAO.getProjectsSortedByRevenueAscending());
	}

	private void sortByRevenueDescending() {
		projectList.clear();
		projectList.addAll(projectDAO.getProjectsSortedByRevenueDescending());
	}

	private void showStatistics() {
		int totalProjects = projectDAO.getProjectCount();
		double totalCost = projectDAO.getTotalCost();
		double totalRevenue = projectDAO.getTotalRevenue();
		double totalProfit = projectDAO.getTotalProfit();
		double avgCost = projectDAO.getAverageCost();
		double avgRevenue = projectDAO.getAverageRevenue();
		double avgProfit = (totalProjects > 0) ? totalProfit / totalProjects : 0;
		double profitMargin = (totalRevenue > 0) ? (totalProfit / totalRevenue) * 100 : 0;

		String stats = String.format(
				"Total Projects: %d\n" + "Total Cost: $%.2f\n" + "Total Revenue: $%.2f\n" + "Total Profit: $%.2f\n"
						+ "Average Cost per Project: $%.2f\n" + "Average Revenue per Project: $%.2f\n"
						+ "Average Profit per Project: $%.2f\n" + "Overall Profit Margin: %.2f%%",
				totalProjects, totalCost, totalRevenue, totalProfit, avgCost, avgRevenue, avgProfit, profitMargin);

		statsLabel.setText(stats);
	}

	private void clearFields() {
		projectIdField.clear();
		branchIdField.clear();
		clientIdField.clear();
		pnameField.clear();
		locationField.clear();
		costField.clear();
		revenueField.clear();
		tableView.getSelectionModel().clearSelection();
	}

	private void populateFields(Project project) {
		projectIdField.setText(project.getProjectId());
		branchIdField.setText(project.getBranchId());
		clientIdField.setText(project.getClientId());
		pnameField.setText(project.getPname());
		locationField.setText(project.getLocation());
		costField.setText(String.valueOf(project.getCost()));
		revenueField.setText(String.valueOf(project.getRevenue()));
	}

	private boolean validateInput() {
		if (projectIdField.getText().trim().isEmpty() || branchIdField.getText().trim().isEmpty()
				|| clientIdField.getText().trim().isEmpty() || pnameField.getText().trim().isEmpty()
				|| locationField.getText().trim().isEmpty() || costField.getText().trim().isEmpty()
				|| revenueField.getText().trim().isEmpty()) {
			return false;
		}

		try {
			double cost = Double.parseDouble(costField.getText().trim());
			double revenue = Double.parseDouble(revenueField.getText().trim());
			if (cost < 0 || revenue < 0) {
				showAlert(Alert.AlertType.WARNING, "Validation Error", "Cost and Revenue cannot be negative!");
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	private void showAlert(Alert.AlertType type, String title, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
}