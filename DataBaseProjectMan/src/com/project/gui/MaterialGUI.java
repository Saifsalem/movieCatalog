package com.project.gui;

import com.project.dao.MaterialDAO;
import com.project.model.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MaterialGUI {
    private MaterialDAO materialDAO;
    private TableView<Material> tableView;
    private ObservableList<Material> materialList;
    
    private TextField mnameField;
    private TextField priceField;
    
    private Label statsLabel;
    private Stage stage;
    
    public MaterialGUI() {
        this.materialDAO = new MaterialDAO();
        this.tableView = new TableView<>();
        this.materialList = FXCollections.observableArrayList();
        this.initializeFields();
    }
    
    private void initializeFields() {
        this.mnameField = new TextField();
        this.priceField = new TextField();
        this.statsLabel = new Label();
    }
    
    public void show() {
        stage = new Stage();
        stage.setTitle("Material Management System");
        
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #fce4ec;");
        
        Label title = new Label("Material Management");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        title.setAlignment(Pos.CENTER);
        
        GridPane formGrid = createInputForm();
        HBox buttonBox = createButtons();
        createTable();
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
        
        String fieldStyle = "-fx-padding: 8; -fx-border-color: #bdc3c7; -fx-border-radius: 3;";
        mnameField.setStyle(fieldStyle);
        priceField.setStyle(fieldStyle);
        
        mnameField.setPromptText("Enter Material Name");
        priceField.setPromptText("Enter Price");
        
        String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";
        
        Label nameLabel = new Label("Material Name:");
        nameLabel.setStyle(labelStyle);
        grid.add(nameLabel, 0, 0);
        grid.add(mnameField, 1, 0);
        
        Label priceLabel = new Label("Price:");
        priceLabel.setStyle(labelStyle);
        grid.add(priceLabel, 2, 0);
        grid.add(priceField, 3, 0);
        
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
        
        Button addButton = new Button("Add Material");
        addButton.setStyle(successButtonStyle);
        addButton.setOnAction(e -> addMaterial());
        
        Button updateButton = new Button("Update Selected");
        updateButton.setStyle(warningButtonStyle);
        updateButton.setOnAction(e -> updateMaterial());
        
        Button deleteButton = new Button("Delete Selected");
        deleteButton.setStyle(dangerButtonStyle);
        deleteButton.setOnAction(e -> deleteMaterial());
        
        Button refreshButton = new Button("Refresh");
        refreshButton.setStyle(buttonStyle);
        refreshButton.setOnAction(e -> loadData());
        
        Button sortPriceAscButton = new Button("Sort Price Asc");
        sortPriceAscButton.setStyle(buttonStyle);
        sortPriceAscButton.setOnAction(e -> sortByPriceAscending());
        
        Button sortPriceDescButton = new Button("Sort Price Desc");
        sortPriceDescButton.setStyle(buttonStyle);
        sortPriceDescButton.setOnAction(e -> sortByPriceDescending());
        
        Button sortNameAscButton = new Button("Sort Name Asc");
        sortNameAscButton.setStyle(buttonStyle);
        sortNameAscButton.setOnAction(e -> sortByNameAscending());
        
        Button sortNameDescButton = new Button("Sort Name Desc");
        sortNameDescButton.setStyle(buttonStyle);
        sortNameDescButton.setOnAction(e -> sortByNameDescending());
        
        Button statsButton = new Button("Statistics");
        statsButton.setStyle(buttonStyle);
        statsButton.setOnAction(e -> showStatistics());
        
        Button clearButton = new Button("Clear");
        clearButton.setStyle(buttonStyle);
        clearButton.setOnAction(e -> clearFields());
        
        buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, refreshButton, 
                                      sortPriceAscButton, sortPriceDescButton, sortNameAscButton, 
                                      sortNameDescButton, statsButton, clearButton);
        return buttonBox;
    }
    
    private void createTable() {
        TableColumn<Material, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("materialId"));
        idCol.setPrefWidth(80);
        
        TableColumn<Material, String> nameCol = new TableColumn<>("Material Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("mname"));
        nameCol.setPrefWidth(300);
        
        TableColumn<Material, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(150);
        
        tableView.getColumns().addAll(idCol, nameCol, priceCol);
        tableView.setItems(materialList);
        
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
        
        Label statsTitle = new Label("Material Statistics");
        statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");
        
        statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");
        
        statsBox.getChildren().addAll(statsTitle, statsLabel);
        return statsBox;
    }
    
    private void loadData() {
        materialList.clear();
        materialList.addAll(materialDAO.getAllMaterials());
        showStatistics();
    }
    
    private void addMaterial() {
        try {
            if (validateFields()) {
                Material material = new Material(
                    0, // ID will be auto-generated
                    mnameField.getText().trim(),
                    Double.parseDouble(priceField.getText().trim())
                );
                
                if (materialDAO.insertMaterial(material)) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Material added successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to add material!", Alert.AlertType.ERROR);
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for price!", Alert.AlertType.ERROR);
        }
    }
    
    private void updateMaterial() {
        Material selectedMaterial = tableView.getSelectionModel().getSelectedItem();
        if (selectedMaterial != null) {
            try {
                if (validateFields()) {
                    selectedMaterial.setMname(mnameField.getText().trim());
                    selectedMaterial.setPrice(Double.parseDouble(priceField.getText().trim()));
                    
                    if (materialDAO.updateMaterial(selectedMaterial)) {
                        loadData();
                        clearFields();
                        showAlert("Success", "Material updated successfully!", Alert.AlertType.INFORMATION);
                    } else {
                        showAlert("Error", "Failed to update material!", Alert.AlertType.ERROR);
                    }
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter a valid number for price!", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Warning", "Please select a material to update!", Alert.AlertType.WARNING);
        }
    }
    
    private void deleteMaterial() {
        Material selectedMaterial = tableView.getSelectionModel().getSelectedItem();
        if (selectedMaterial != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Delete");
            confirmAlert.setHeaderText("Delete Material");
            confirmAlert.setContentText("Are you sure you want to delete this material?");
            
            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                if (materialDAO.deleteMaterial(selectedMaterial.getMaterialId())) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Material deleted successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to delete material!", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Warning", "Please select a material to delete!", Alert.AlertType.WARNING);
        }
    }
    
    private void populateFields(Material material) {
        mnameField.setText(material.getMname());
        priceField.setText(String.valueOf(material.getPrice()));
    }
    
    private boolean validateFields() {
        if (mnameField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Material name cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (priceField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Price cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
    
    private void sortByPriceAscending() {
        materialList.clear();
        materialList.addAll(materialDAO.getMaterialsSortedByPriceAscending());
    }
    
    private void sortByPriceDescending() {
        materialList.clear();
        materialList.addAll(materialDAO.getMaterialsSortedByPriceDescending());
    }
    
    private void sortByNameAscending() {
        materialList.clear();
        materialList.addAll(materialDAO.getMaterialsSortedByNameAscending());
    }
    
    private void sortByNameDescending() {
        materialList.clear();
        materialList.addAll(materialDAO.getMaterialsSortedByNameDescending());
    }
    
    private void showStatistics() {
        double maxPrice = materialDAO.getMaxPrice();
        double minPrice = materialDAO.getMinPrice();
        double avgPrice = materialDAO.getAveragePrice();
        double totalValue = materialDAO.getTotalValue();
        int count = materialDAO.getMaterialCount();
        
        String stats = String.format(
            "Total Materials: %d\n" +
            "Maximum Price: $%.2f\n" +
            "Minimum Price: $%.2f\n" +
            "Average Price: $%.2f\n" +
            "Total Value: $%.2f",
            count, maxPrice, minPrice, avgPrice, totalValue
        );
        
        statsLabel.setText(stats);
    }
    
    private void clearFields() {
        mnameField.clear();
        priceField.clear();
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