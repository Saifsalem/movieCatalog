package com.project.gui;

import com.project.dao.RoleDAO;
import com.project.model.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RoleGUI {
    private RoleDAO roleDAO;
    private TableView<Role> tableView;
    private ObservableList<Role> roleList;
    
    private TextField roleIdField;
    private TextField titleField;
    
    private Label statsLabel;
    private Stage stage;
    
    public RoleGUI() {
        this.roleDAO = new RoleDAO();
        this.tableView = new TableView<>();
        this.roleList = FXCollections.observableArrayList();
        this.initializeFields();
    }
    
    private void initializeFields() {
        this.roleIdField = new TextField();
        this.titleField = new TextField();
        this.statsLabel = new Label();
    }
    
    public void show() {
        stage = new Stage();
        stage.setTitle("Role Management System");
        
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #e3f2fd;");
        
        // Create title
        Label title = new Label("Role Management");
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
        roleIdField.setStyle(fieldStyle);
        titleField.setStyle(fieldStyle);
        
        // Add placeholders
        roleIdField.setPromptText("Enter Role ID");
        titleField.setPromptText("Enter Role Title");
        
        // Create labels with styling
        String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";
        
        Label roleIdLabel = new Label("Role ID:");
        roleIdLabel.setStyle(labelStyle);
        grid.add(roleIdLabel, 0, 0);
        grid.add(roleIdField, 1, 0);
        
        Label titleLabel = new Label("Title:");
        titleLabel.setStyle(labelStyle);
        grid.add(titleLabel, 2, 0);
        grid.add(titleField, 3, 0);
        
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
        
        Button addButton = new Button("Add Role");
        addButton.setStyle(successButtonStyle);
        addButton.setOnAction(e -> addRole());
        
        Button updateButton = new Button("Update Selected");
        updateButton.setStyle(warningButtonStyle);
        updateButton.setOnAction(e -> updateRole());
        
        Button deleteButton = new Button("Delete Selected");
        deleteButton.setStyle(dangerButtonStyle);
        deleteButton.setOnAction(e -> deleteRole());
        
        Button refreshButton = new Button("Refresh Data");
        refreshButton.setStyle(buttonStyle);
        refreshButton.setOnAction(e -> loadData());
        
        Button sortTitleAscButton = new Button("Sort Title Asc");
        sortTitleAscButton.setStyle(buttonStyle);
        sortTitleAscButton.setOnAction(e -> sortByTitleAscending());
        
        Button sortTitleDescButton = new Button("Sort Title Desc");
        sortTitleDescButton.setStyle(buttonStyle);
        sortTitleDescButton.setOnAction(e -> sortByTitleDescending());
        
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
                                      sortTitleAscButton, sortTitleDescButton, sortIdAscButton, 
                                      sortIdDescButton, statsButton, clearButton);
        return buttonBox;
    }
    
    private void createTable() {
        TableColumn<Role, String> idCol = new TableColumn<>("Role ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("roleId"));
        idCol.setPrefWidth(200);
        
        TableColumn<Role, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(300);
        
        tableView.getColumns().addAll(idCol, titleCol);
        tableView.setItems(roleList);
        
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
        
        Label statsTitle = new Label("Role Statistics");
        statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");
        
        statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");
        
        statsBox.getChildren().addAll(statsTitle, statsLabel);
        return statsBox;
    }
    
    private void loadData() {
        roleList.clear();
        roleList.addAll(roleDAO.getAllRoles());
        showStatistics();
    }
    
    private void addRole() {
        if (validateFields()) {
            Role role = new Role(
                roleIdField.getText().trim(),
                titleField.getText().trim()
            );
            
            if (roleDAO.insertRole(role)) {
                loadData();
                clearFields();
                showAlert("Success", "Role added successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to add role to database!", Alert.AlertType.ERROR);
            }
        }
    }
    
    private void updateRole() {
        Role selectedRole = tableView.getSelectionModel().getSelectedItem();
        if (selectedRole != null) {
            if (validateFields()) {
                selectedRole.setRoleId(roleIdField.getText().trim());
                selectedRole.setTitle(titleField.getText().trim());
                
                if (roleDAO.updateRole(selectedRole)) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Role updated successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to update role!", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Warning", "Please select a role to update!", Alert.AlertType.WARNING);
        }
    }
    
    private void deleteRole() {
        Role selectedRole = tableView.getSelectionModel().getSelectedItem();
        if (selectedRole != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Delete");
            confirmAlert.setHeaderText("Delete Role");
            confirmAlert.setContentText("Are you sure you want to delete this role?");
            
            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                if (roleDAO.deleteRole(selectedRole.getRoleId())) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Role deleted successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to delete role!", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Warning", "Please select a role to delete!", Alert.AlertType.WARNING);
        }
    }
    
    private void populateFields(Role role) {
        roleIdField.setText(role.getRoleId());
        titleField.setText(role.getTitle());
    }
    
    private boolean validateFields() {
        if (roleIdField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Role ID cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (titleField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Title cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
    
    private void sortByTitleAscending() {
        roleList.clear();
        roleList.addAll(roleDAO.getRolesSortedByTitleAscending());
    }
    
    private void sortByTitleDescending() {
        roleList.clear();
        roleList.addAll(roleDAO.getRolesSortedByTitleDescending());
    }
    
    private void sortByIdAscending() {
        roleList.clear();
        roleList.addAll(roleDAO.getRolesSortedByIdAscending());
    }
    
    private void sortByIdDescending() {
        roleList.clear();
        roleList.addAll(roleDAO.getRolesSortedByIdDescending());
    }
    
    private void showStatistics() {
        int totalCount = roleDAO.getRoleCount();
        
        String stats = String.format(
            "Total Roles: %d\n" +
            "Role Management System\n" +
            "All employee roles and positions are tracked.",
            totalCount
        );
        
        statsLabel.setText(stats);
    }
    
    private void clearFields() {
        roleIdField.clear();
        titleField.clear();
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