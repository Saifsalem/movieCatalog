package com.project.gui;

import com.project.dao.SalesDAO;
import com.project.model.Sales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SalesGUI {
    private SalesDAO salesDAO;
    private TableView<Sales> tableView;
    private ObservableList<Sales> salesList;
    
    private TextField projectIdField;
    private TextField clientIdField;
    private TextField amountField;
    private TextField issueDateField;
    private TextField dueDateField;
    
    private Label statsLabel;
    private Stage stage;
    
    public SalesGUI() {
        this.salesDAO = new SalesDAO();
        this.tableView = new TableView<>();
        this.salesList = FXCollections.observableArrayList();
        this.initializeFields();
    }
    
    private void initializeFields() {
        this.projectIdField = new TextField();
        this.clientIdField = new TextField();
        this.amountField = new TextField();
        this.issueDateField = new TextField();
        this.dueDateField = new TextField();
        this.statsLabel = new Label();
    }
    
    public void show() {
        stage = new Stage();
        stage.setTitle("Sales Management System");
        
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #e8f5e8;");
        
        Label title = new Label("Sales Management");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        title.setAlignment(Pos.CENTER);
        
        GridPane formGrid = createInputForm();
        HBox buttonBox = createButtons();
        createTable();
        VBox statsBox = createStatsSection();
        
        root.getChildren().addAll(title, formGrid, buttonBox, tableView, statsBox);
        
        Scene scene = new Scene(root, 1100, 700);
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
        projectIdField.setStyle(fieldStyle);
        clientIdField.setStyle(fieldStyle);
        amountField.setStyle(fieldStyle);
        issueDateField.setStyle(fieldStyle);
        dueDateField.setStyle(fieldStyle);
        
        projectIdField.setPromptText("Project ID");
        clientIdField.setPromptText("Client ID");
        amountField.setPromptText("Sales Amount");
        issueDateField.setPromptText("YYYY-MM-DD");
        dueDateField.setPromptText("YYYY-MM-DD");
        
        String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";
        
        Label projectLabel = new Label("Project ID:");
        projectLabel.setStyle(labelStyle);
        grid.add(projectLabel, 0, 0);
        grid.add(projectIdField, 1, 0);
        
        Label clientLabel = new Label("Client ID:");
        clientLabel.setStyle(labelStyle);
        grid.add(clientLabel, 2, 0);
        grid.add(clientIdField, 3, 0);
        
        Label amountLabel = new Label("Amount:");
        amountLabel.setStyle(labelStyle);
        grid.add(amountLabel, 0, 1);
        grid.add(amountField, 1, 1);
        
        Label issueLabel = new Label("Issue Date:");
        issueLabel.setStyle(labelStyle);
        grid.add(issueLabel, 2, 1);
        grid.add(issueDateField, 3, 1);
        
        Label dueLabel = new Label("Due Date:");
        dueLabel.setStyle(labelStyle);
        grid.add(dueLabel, 0, 2);
        grid.add(dueDateField, 1, 2);
        
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
        
        Button addButton = new Button("Add Sale");
        addButton.setStyle(successButtonStyle);
        addButton.setOnAction(e -> addSale());
        
        Button updateButton = new Button("Update");
        updateButton.setStyle(warningButtonStyle);
        updateButton.setOnAction(e -> updateSale());
        
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle(dangerButtonStyle);
        deleteButton.setOnAction(e -> deleteSale());
        
        Button refreshButton = new Button("Refresh");
        refreshButton.setStyle(buttonStyle);
        refreshButton.setOnAction(e -> loadData());
        
        Button sortAmountAscButton = new Button("Sort Amount Asc");
        sortAmountAscButton.setStyle(buttonStyle);
        sortAmountAscButton.setOnAction(e -> sortByAmountAscending());
        
        Button sortAmountDescButton = new Button("Sort Amount Desc");
        sortAmountDescButton.setStyle(buttonStyle);
        sortAmountDescButton.setOnAction(e -> sortByAmountDescending());
        
        Button sortDateAscButton = new Button("Sort Date Asc");
        sortDateAscButton.setStyle(buttonStyle);
        sortDateAscButton.setOnAction(e -> sortByDateAscending());
        
        Button sortDateDescButton = new Button("Sort Date Desc");
        sortDateDescButton.setStyle(buttonStyle);
        sortDateDescButton.setOnAction(e -> sortByDateDescending());
        
        Button statsButton = new Button("Statistics");
        statsButton.setStyle(buttonStyle);
        statsButton.setOnAction(e -> showStatistics());
        
        Button clearButton = new Button("Clear");
        clearButton.setStyle(buttonStyle);
        clearButton.setOnAction(e -> clearFields());
        
        buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, refreshButton, 
                                      sortAmountAscButton, sortAmountDescButton, sortDateAscButton, 
                                      sortDateDescButton, statsButton, clearButton);
        return buttonBox;
    }
    
    private void createTable() {
        TableColumn<Sales, Integer> idCol = new TableColumn<>("Sale ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("saleId"));
        idCol.setPrefWidth(80);
        
        TableColumn<Sales, String> projectCol = new TableColumn<>("Project ID");
        projectCol.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        projectCol.setPrefWidth(120);
        
        TableColumn<Sales, String> clientCol = new TableColumn<>("Client ID");
        clientCol.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        clientCol.setPrefWidth(120);
        
        TableColumn<Sales, Double> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amountCol.setPrefWidth(120);
        
        TableColumn<Sales, String> issueCol = new TableColumn<>("Issue Date");
        issueCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        issueCol.setPrefWidth(120);
        
        TableColumn<Sales, String> dueCol = new TableColumn<>("Due Date");
        dueCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        dueCol.setPrefWidth(120);
        
        tableView.getColumns().addAll(idCol, projectCol, clientCol, amountCol, issueCol, dueCol);
        tableView.setItems(salesList);
        
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
        
        Label statsTitle = new Label("Sales Statistics");
        statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");
        
        statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");
        
        statsBox.getChildren().addAll(statsTitle, statsLabel);
        return statsBox;
    }
    
    private void loadData() {
        salesList.clear();
        salesList.addAll(salesDAO.getAllSales());
        showStatistics();
    }
    
    private void addSale() {
        try {
            if (validateFields()) {
                Sales sale = new Sales(
                    0, // ID will be auto-generated
                    projectIdField.getText().trim(),
                    clientIdField.getText().trim(),
                    Double.parseDouble(amountField.getText().trim()),
                    issueDateField.getText().trim(),
                    dueDateField.getText().trim()
                );
                
                if (salesDAO.insertSales(sale)) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Sale added successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to add sale!", Alert.AlertType.ERROR);
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for amount!", Alert.AlertType.ERROR);
        }
    }
    
    private void updateSale() {
        Sales selectedSale = tableView.getSelectionModel().getSelectedItem();
        if (selectedSale != null) {
            try {
                if (validateFields()) {
                    selectedSale.setProjectId(projectIdField.getText().trim());
                    selectedSale.setClientId(clientIdField.getText().trim());
                    selectedSale.setAmount(Double.parseDouble(amountField.getText().trim()));
                    selectedSale.setIssueDate(issueDateField.getText().trim());
                    selectedSale.setDueDate(dueDateField.getText().trim());
                    
                    if (salesDAO.updateSales(selectedSale)) {
                        loadData();
                        clearFields();
                        showAlert("Success", "Sale updated successfully!", Alert.AlertType.INFORMATION);
                    } else {
                        showAlert("Error", "Failed to update sale!", Alert.AlertType.ERROR);
                    }
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter a valid number for amount!", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Warning", "Please select a sale to update!", Alert.AlertType.WARNING);
        }
    }
    
    private void deleteSale() {
        Sales selectedSale = tableView.getSelectionModel().getSelectedItem();
        if (selectedSale != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Delete");
            confirmAlert.setHeaderText("Delete Sale");
            confirmAlert.setContentText("Are you sure you want to delete this sale?");
            
            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                if (salesDAO.deleteSales(selectedSale.getSaleId())) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Sale deleted successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to delete sale!", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Warning", "Please select a sale to delete!", Alert.AlertType.WARNING);
        }
    }
    
    private void populateFields(Sales sale) {
        projectIdField.setText(sale.getProjectId());
        clientIdField.setText(sale.getClientId());
        amountField.setText(String.valueOf(sale.getAmount()));
        issueDateField.setText(sale.getIssueDate());
        dueDateField.setText(sale.getDueDate());
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
        if (amountField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Amount cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (issueDateField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Issue date cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (dueDateField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Due date cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
    
    private void sortByAmountAscending() {
        salesList.clear();
        salesList.addAll(salesDAO.getSalesSortedByAmountAscending());
    }
    
    private void sortByAmountDescending() {
        salesList.clear();
        salesList.addAll(salesDAO.getSalesSortedByAmountDescending());
    }
    
    private void sortByDateAscending() {
        salesList.clear();
        salesList.addAll(salesDAO.getSalesSortedByDateAscending());
    }
    
    private void sortByDateDescending() {
        salesList.clear();
        salesList.addAll(salesDAO.getSalesSortedByDateDescending());
    }
    
    private void showStatistics() {
        double maxAmount = salesDAO.getMaxSalesAmount();
        double minAmount = salesDAO.getMinSalesAmount();
        double avgAmount = salesDAO.getAverageSalesAmount();
        double totalAmount = salesDAO.getTotalSalesAmount();
        int count = salesDAO.getSalesCount();
        
        String stats = String.format(
            "Total Sales: %d\n" +
            "Maximum Amount: $%.2f\n" +
            "Minimum Amount: $%.2f\n" +
            "Average Amount: $%.2f\n" +
            "Total Sales Amount: $%.2f",
            count, maxAmount, minAmount, avgAmount, totalAmount
        );
        
        statsLabel.setText(stats);
    }
    
    private void clearFields() {
        projectIdField.clear();
        clientIdField.clear();
        amountField.clear();
        issueDateField.clear();
        dueDateField.clear();
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