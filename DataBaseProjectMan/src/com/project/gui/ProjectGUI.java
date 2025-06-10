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
    private TextField nameField;
    private TextArea descriptionField;
    private TextField startDateField;
    private TextField endDateField;
    private TextField statusField;
    
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
        this.nameField = new TextField();
        this.descriptionField = new TextArea();
        this.startDateField = new TextField();
        this.endDateField = new TextField();
        this.statusField = new TextField();
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
        nameField.setStyle(fieldStyle);
        descriptionField.setStyle(fieldStyle);
        startDateField.setStyle(fieldStyle);
        endDateField.setStyle(fieldStyle);
        statusField.setStyle(fieldStyle);
        
        // Set description field properties
        descriptionField.setPrefRowCount(3);
        descriptionField.setWrapText(true);
        
        // Add placeholders
        projectIdField.setPromptText("Enter Project ID");
        nameField.setPromptText("Enter Project Name");
        descriptionField.setPromptText("Enter Project Description");
        startDateField.setPromptText("YYYY-MM-DD");
        endDateField.setPromptText("YYYY-MM-DD");
        statusField.setPromptText("Active/Completed/Planning");
        
        // Create labels with styling
        String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";
        
        Label projectIdLabel = new Label("Project ID:");
        projectIdLabel.setStyle(labelStyle);
        grid.add(projectIdLabel, 0, 0);
        grid.add(projectIdField, 1, 0);
        
        Label nameLabel = new Label("Project Name:");
        nameLabel.setStyle(labelStyle);
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        
        Label descLabel = new Label("Description:");
        descLabel.setStyle(labelStyle);
        grid.add(descLabel, 0, 2);
        grid.add(descriptionField, 1, 2);
        
        Label startLabel = new Label("Start Date:");
        startLabel.setStyle(labelStyle);
        grid.add(startLabel, 2, 0);
        grid.add(startDateField, 3, 0);
        
        Label endLabel = new Label("End Date:");
        endLabel.setStyle(labelStyle);
        grid.add(endLabel, 2, 1);
        grid.add(endDateField, 3, 1);
        
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
        
        Button sortDateAscButton = new Button("Sort Date Asc");
        sortDateAscButton.setStyle(buttonStyle);
        sortDateAscButton.setOnAction(e -> sortByDateAscending());
        
        Button sortDateDescButton = new Button("Sort Date Desc");
        sortDateDescButton.setStyle(buttonStyle);
        sortDateDescButton.setOnAction(e -> sortByDateDescending());
        
        Button statsButton = new Button("Show Statistics");
        statsButton.setStyle(buttonStyle);
        statsButton.setOnAction(e -> showStatistics());
        
        Button clearButton = new Button("Clear Fields");
        clearButton.setStyle(buttonStyle);
        clearButton.setOnAction(e -> clearFields());
        
        buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, refreshButton, 
                                      sortNameAscButton, sortNameDescButton, sortDateAscButton, 
                                      sortDateDescButton, statsButton, clearButton);
        return buttonBox;
    }
    
    private void createTable() {
        TableColumn<Project, String> idCol = new TableColumn<>("Project ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        idCol.setPrefWidth(120);
        
        TableColumn<Project, String> nameCol = new TableColumn<>("Project Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);
        
        TableColumn<Project, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descCol.setPrefWidth(300);
        
        TableColumn<Project, String> startDateCol = new TableColumn<>("Start Date");
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startDateCol.setPrefWidth(120);
        
        TableColumn<Project, String> endDateCol = new TableColumn<>("End Date");
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        endDateCol.setPrefWidth(120);
        
        TableColumn<Project, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(120);
        
        tableView.getColumns().addAll(idCol, nameCol, descCol, startDateCol, endDateCol, statusCol);
        tableView.setItems(projectList);
        
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
        
        Label statsTitle = new Label("Project Statistics");
        statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");
        
        statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");
        
        statsBox.getChildren().addAll(statsTitle, statsLabel);
        return statsBox;
    }
    
    private void loadData() {
        projectList.clear();
        projectList.addAll(projectDAO.getAllProjects());
        showStatistics();
    }
    
    private void addProject() {
        if (validateFields()) {
            Project project = new Project(
                projectIdField.getText().trim(),
                nameField.getText().trim(),
                descriptionField.getText().trim(),
                startDateField.getText().trim(),
                endDateField.getText().trim(),
                statusField.getText().trim()
            );
            
            if (projectDAO.insertProject(project)) {
                loadData();
                clearFields();
                showAlert("Success", "Project added successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to add project to database!", Alert.AlertType.ERROR);
            }
        }
    }
    
    private void updateProject() {
        Project selectedProject = tableView.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            if (validateFields()) {
                selectedProject.setProjectId(projectIdField.getText().trim());
                selectedProject.setName(nameField.getText().trim());
                selectedProject.setDescription(descriptionField.getText().trim());
                selectedProject.setStartDate(startDateField.getText().trim());
                selectedProject.setEndDate(endDateField.getText().trim());
                selectedProject.setStatus(statusField.getText().trim());
                
                if (projectDAO.updateProject(selectedProject)) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Project updated successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to update project!", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Warning", "Please select a project to update!", Alert.AlertType.WARNING);
        }
    }
    
    private void deleteProject() {
        Project selectedProject = tableView.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Delete");
            confirmAlert.setHeaderText("Delete Project");
            confirmAlert.setContentText("Are you sure you want to delete this project?");
            
            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                if (projectDAO.deleteProject(selectedProject.getProjectId())) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Project deleted successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to delete project!", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Warning", "Please select a project to delete!", Alert.AlertType.WARNING);
        }
    }
    
    private void populateFields(Project project) {
        projectIdField.setText(project.getProjectId());
        nameField.setText(project.getName());
        descriptionField.setText(project.getDescription());
        startDateField.setText(project.getStartDate());
        endDateField.setText(project.getEndDate());
        statusField.setText(project.getStatus());
    }
    
    private boolean validateFields() {
        if (projectIdField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Project ID cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (nameField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Project Name cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (startDateField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Start Date cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (statusField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Status cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
    
    private void sortByNameAscending() {
        projectList.clear();
        projectList.addAll(projectDAO.getProjectsSortedByNameAscending());
    }
    
    private void sortByNameDescending() {
        projectList.clear();
        projectList.addAll(projectDAO.getProjectsSortedByNameDescending());
    }
    
    private void sortByDateAscending() {
        projectList.clear();
        projectList.addAll(projectDAO.getProjectsSortedByDateAscending());
    }
    
    private void sortByDateDescending() {
        projectList.clear();
        projectList.addAll(projectDAO.getProjectsSortedByDateDescending());
    }
    
    private void showStatistics() {
        int totalCount = projectDAO.getProjectCount();
        int activeCount = projectDAO.getActiveProjectCount();
        int completedCount = projectDAO.getCompletedProjectCount();
        
        String stats = String.format(
            "Total Projects: %d\n" +
            "Active Projects: %d\n" +
            "Completed Projects: %d\n" +
            "Pending Projects: %d",
            totalCount, activeCount, completedCount, (totalCount - activeCount - completedCount)
        );
        
        statsLabel.setText(stats);
    }
    
    private void clearFields() {
        projectIdField.clear();
        nameField.clear();
        descriptionField.clear();
        startDateField.clear();
        endDateField.clear();
        statusField.clear();
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