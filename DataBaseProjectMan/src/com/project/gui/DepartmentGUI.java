package com.project.gui;

import com.project.dao.DepartmentDAO;
import com.project.model.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DepartmentGUI {
    private DepartmentDAO departmentDAO;
    private TableView<Department> tableView;
    private ObservableList<Department> departmentList;
    
    private TextField deptIdField;
    private TextField deptNameField;
    
    private Label statsLabel;
    private Stage stage;
    
    public DepartmentGUI() {
        this.departmentDAO = new DepartmentDAO();
        this.tableView = new TableView<>();
        this.departmentList = FXCollections.observableArrayList();
        this.initializeFields();
    }
    
    private void initializeFields() {
        this.deptIdField = new TextField();
        this.deptNameField = new TextField();
        this.statsLabel = new Label();
    }
    
    public void show() {
        stage = new Stage();
        stage.setTitle("Department Management System");
        
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #e8f5e8;");
        
        // Create title
        Label title = new Label("Department Management");
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
        deptIdField.setStyle(fieldStyle);
        deptNameField.setStyle(fieldStyle);
        
        // Add placeholders
        deptIdField.setPromptText("Enter Department ID");
        deptNameField.setPromptText("Enter Department Name");
        
        // Create labels with styling
        String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";
        
        Label deptIdLabel = new Label("Department ID:");
        deptIdLabel.setStyle(labelStyle);
        grid.add(deptIdLabel, 0, 0);
        grid.add(deptIdField, 1, 0);
        
        Label deptNameLabel = new Label("Department Name:");
        deptNameLabel.setStyle(labelStyle);
        grid.add(deptNameLabel, 2, 0);
        grid.add(deptNameField, 3, 0);
        
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
        
        Button addButton = new Button("Add Department");
        addButton.setStyle(successButtonStyle);
        addButton.setOnAction(e -> addDepartment());
        
        Button updateButton = new Button("Update Selected");
        updateButton.setStyle(warningButtonStyle);
        updateButton.setOnAction(e -> updateDepartment());
        
        Button deleteButton = new Button("Delete Selected");
        deleteButton.setStyle(dangerButtonStyle);
        deleteButton.setOnAction(e -> deleteDepartment());
        
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
        
        buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, refreshButton, 
                                      sortNameAscButton, sortNameDescButton, sortIdAscButton, 
                                      sortIdDescButton, statsButton, clearButton);
        return buttonBox;
    }
    
    private void createTable() {
        TableColumn<Department, String> idCol = new TableColumn<>("Department ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("deptId"));
        idCol.setPrefWidth(200);
        
        TableColumn<Department, String> nameCol = new TableColumn<>("Department Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("deptName"));
        nameCol.setPrefWidth(300);
        
        tableView.getColumns().addAll(idCol, nameCol);
        tableView.setItems(departmentList);
        
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
        
        Label statsTitle = new Label("Department Statistics");
        statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");
        
        statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");
        
        statsBox.getChildren().addAll(statsTitle, statsLabel);
        return statsBox;
    }
    
    private void loadData() {
        departmentList.clear();
        departmentList.addAll(departmentDAO.getAllDepartments());
        showStatistics();
    }
    
    private void addDepartment() {
        if (validateFields()) {
            Department department = new Department(
                deptIdField.getText().trim(),
                deptNameField.getText().trim()
            );
            
            if (departmentDAO.insertDepartment(department)) {
                loadData();
                clearFields();
                showAlert("Success", "Department added successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to add department to database!", Alert.AlertType.ERROR);
            }
        }
    }
    
    private void updateDepartment() {
        Department selectedDepartment = tableView.getSelectionModel().getSelectedItem();
        if (selectedDepartment != null) {
            if (validateFields()) {
                selectedDepartment.setDeptId(deptIdField.getText().trim());
                selectedDepartment.setDeptName(deptNameField.getText().trim());
                
                if (departmentDAO.updateDepartment(selectedDepartment)) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Department updated successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to update department!", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Warning", "Please select a department to update!", Alert.AlertType.WARNING);
        }
    }
    
    private void deleteDepartment() {
        Department selectedDepartment = tableView.getSelectionModel().getSelectedItem();
        if (selectedDepartment != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Delete");
            confirmAlert.setHeaderText("Delete Department");
            confirmAlert.setContentText("Are you sure you want to delete this department?");
            
            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                if (departmentDAO.deleteDepartment(selectedDepartment.getDeptId())) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Department deleted successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to delete department!", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Warning", "Please select a department to delete!", Alert.AlertType.WARNING);
        }
    }
    
    private void populateFields(Department department) {
        deptIdField.setText(department.getDeptId());
        deptNameField.setText(department.getDeptName());
    }
    
    private boolean validateFields() {
        if (deptIdField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Department ID cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (deptNameField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Department Name cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
    
    private void sortByNameAscending() {
        departmentList.clear();
        departmentList.addAll(departmentDAO.getDepartmentsSortedAscending());
    }
    
    private void sortByNameDescending() {
        departmentList.clear();
        departmentList.addAll(departmentDAO.getDepartmentsSortedDescending());
    }
    
    private void sortByIdAscending() {
        departmentList.clear();
        departmentList.addAll(departmentDAO.getDepartmentsSortedByIdAscending());
    }
    
    private void sortByIdDescending() {
        departmentList.clear();
        departmentList.addAll(departmentDAO.getDepartmentsSortedByIdDescending());
    }
    
    private void showStatistics() {
        int totalCount = departmentDAO.getDepartmentCount();
        
        String stats = String.format(
            "Total Departments: %d\n" +
            "Department Management Statistics\n" +
            "All departments are currently tracked in the system.",
            totalCount
        );
        
        statsLabel.setText(stats);
    }
    
    private void clearFields() {
        deptIdField.clear();
        deptNameField.clear();
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