package com.project.gui;

import com.project.dao.SupplierDAO;
import com.project.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SupplierGUI {
    private SupplierDAO supplierDAO;
    private TableView<Supplier> tableView;
    private ObservableList<Supplier> supplierList;
    
    private TextField suppIdField;
    private TextField suppNameField;
    private TextField locationField;
    
    private Label statsLabel;
    private Stage stage;
    
    public SupplierGUI() {
        this.supplierDAO = new SupplierDAO();
        this.tableView = new TableView<>();
        this.supplierList = FXCollections.observableArrayList();
        this.initializeFields();
    }
    
    private void initializeFields() {
        this.suppIdField = new TextField();
        this.suppNameField = new TextField();
        this.locationField = new TextField();
        this.statsLabel = new Label();
    }
    
    public void show() {
        stage = new Stage();
        stage.setTitle("Supplier Management System");
        
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f3e5f5;");
        
        // Create title
        Label title = new Label("Supplier Management");
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
        
        Scene scene = new Scene(root, 900, 700);
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
        suppIdField.setStyle(fieldStyle);
        suppNameField.setStyle(fieldStyle);
        locationField.setStyle(fieldStyle);
        
        // Add placeholders
        suppIdField.setPromptText("Enter Supplier ID");
        suppNameField.setPromptText("Enter Supplier Name");
        locationField.setPromptText("Enter Supplier Location");
        
        // Create labels with styling
        String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";
        
        Label suppIdLabel = new Label("Supplier ID:");
        suppIdLabel.setStyle(labelStyle);
        grid.add(suppIdLabel, 0, 0);
        grid.add(suppIdField, 1, 0);
        
        Label suppNameLabel = new Label("Supplier Name:");
        suppNameLabel.setStyle(labelStyle);
        grid.add(suppNameLabel, 2, 0);
        grid.add(suppNameField, 3, 0);
        
        Label locationLabel = new Label("Location:");
        locationLabel.setStyle(labelStyle);
        grid.add(locationLabel, 0, 1);
        grid.add(locationField, 1, 1);
        
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
        
        Button addButton = new Button("Add Supplier");
        addButton.setStyle(successButtonStyle);
        addButton.setOnAction(e -> addSupplier());
        
        Button updateButton = new Button("Update Selected");
        updateButton.setStyle(warningButtonStyle);
        updateButton.setOnAction(e -> updateSupplier());
        
        Button deleteButton = new Button("Delete Selected");
        deleteButton.setStyle(dangerButtonStyle);
        deleteButton.setOnAction(e -> deleteSupplier());
        
        Button refreshButton = new Button("Refresh Data");
        refreshButton.setStyle(buttonStyle);
        refreshButton.setOnAction(e -> loadData());
        
        Button sortNameAscButton = new Button("Sort Name Asc");
        sortNameAscButton.setStyle(buttonStyle);
        sortNameAscButton.setOnAction(e -> sortByNameAscending());
        
        Button sortNameDescButton = new Button("Sort Name Desc");
        sortNameDescButton.setStyle(buttonStyle);
        sortNameDescButton.setOnAction(e -> sortByNameDescending());
        
        Button sortLocationAscButton = new Button("Sort Location Asc");
        sortLocationAscButton.setStyle(buttonStyle);
        sortLocationAscButton.setOnAction(e -> sortByLocationAscending());
        
        Button sortLocationDescButton = new Button("Sort Location Desc");
        sortLocationDescButton.setStyle(buttonStyle);
        sortLocationDescButton.setOnAction(e -> sortByLocationDescending());
        
        Button statsButton = new Button("Show Statistics");
        statsButton.setStyle(buttonStyle);
        statsButton.setOnAction(e -> showStatistics());
        
        Button clearButton = new Button("Clear Fields");
        clearButton.setStyle(buttonStyle);
        clearButton.setOnAction(e -> clearFields());
        
        buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, refreshButton, 
                                      sortNameAscButton, sortNameDescButton, sortLocationAscButton, 
                                      sortLocationDescButton, statsButton, clearButton);
        return buttonBox;
    }
    
    private void createTable() {
        TableColumn<Supplier, String> idCol = new TableColumn<>("Supplier ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("suppId"));
        idCol.setPrefWidth(120);
        
        TableColumn<Supplier, String> nameCol = new TableColumn<>("Supplier Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("suppName"));
        nameCol.setPrefWidth(250);
        
        TableColumn<Supplier, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationCol.setPrefWidth(200);
        
        tableView.getColumns().addAll(idCol, nameCol, locationCol);
        tableView.setItems(supplierList);
        
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
        
        Label statsTitle = new Label("Supplier Statistics");
        statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");
        
        statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");
        
        statsBox.getChildren().addAll(statsTitle, statsLabel);
        return statsBox;
    }
    
    private void loadData() {
        supplierList.clear();
        supplierList.addAll(supplierDAO.getAllSuppliers());
        showStatistics();
    }
    
    private void addSupplier() {
        if (validateFields()) {
            Supplier supplier = new Supplier(
                suppIdField.getText().trim(),
                suppNameField.getText().trim(),
                locationField.getText().trim()
            );
            
            if (supplierDAO.insertSupplier(supplier)) {
                loadData();
                clearFields();
                showAlert("Success", "Supplier added successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to add supplier to database!", Alert.AlertType.ERROR);
            }
        }
    }
    
    private void updateSupplier() {
        Supplier selectedSupplier = tableView.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            if (validateFields()) {
                selectedSupplier.setSuppId(suppIdField.getText().trim());
                selectedSupplier.setSuppName(suppNameField.getText().trim());
                selectedSupplier.setLocation(locationField.getText().trim());
                
                if (supplierDAO.updateSupplier(selectedSupplier)) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Supplier updated successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to update supplier!", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Warning", "Please select a supplier to update!", Alert.AlertType.WARNING);
        }
    }
    
    private void deleteSupplier() {
        Supplier selectedSupplier = tableView.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Delete");
            confirmAlert.setHeaderText("Delete Supplier");
            confirmAlert.setContentText("Are you sure you want to delete this supplier?");
            
            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                if (supplierDAO.deleteSupplier(selectedSupplier.getSuppId())) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Supplier deleted successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to delete supplier!", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Warning", "Please select a supplier to delete!", Alert.AlertType.WARNING);
        }
    }
    
    private void populateFields(Supplier supplier) {
        suppIdField.setText(supplier.getSuppId());
        suppNameField.setText(supplier.getSuppName());
        locationField.setText(supplier.getLocation());
    }
    
    private boolean validateFields() {
        if (suppIdField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Supplier ID cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (suppNameField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Supplier Name cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (locationField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Location cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
    
    private void sortByNameAscending() {
        supplierList.clear();
        supplierList.addAll(supplierDAO.getSuppliersSortedByNameAscending());
    }
    
    private void sortByNameDescending() {
        supplierList.clear();
        supplierList.addAll(supplierDAO.getSuppliersSortedByNameDescending());
    }
    
    private void sortByLocationAscending() {
        supplierList.clear();
        supplierList.addAll(supplierDAO.getSuppliersSortedByLocationAscending());
    }
    
    private void sortByLocationDescending() {
        supplierList.clear();
        supplierList.addAll(supplierDAO.getSuppliersSortedByLocationDescending());
    }
    
    private void showStatistics() {
        int totalCount = supplierDAO.getSupplierCount();
        
        String stats = String.format(
            "Total Suppliers: %d\n" +
            "Supplier Network Management\n" +
            "All supplier relationships are tracked for procurement.",
            totalCount
        );
        
        statsLabel.setText(stats);
    }
    
    private void clearFields() {
        suppIdField.clear();
        suppNameField.clear();
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