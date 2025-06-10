package com.project.gui;

import com.project.dao.PaymentDAO;
import com.project.model.Payment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PaymentGUI {
    private PaymentDAO paymentDAO;
    private TableView<Payment> tableView;
    private ObservableList<Payment> paymentList;
    
    private TextField fromClientField;
    private TextField toSupplierField;
    private TextField amountField;
    private TextField pdateField;
    private TextField pmethodField;
    
    private Label statsLabel;
    private Stage stage;
    
    public PaymentGUI() {
        this.paymentDAO = new PaymentDAO();
        this.tableView = new TableView<>();
        this.paymentList = FXCollections.observableArrayList();
        this.initializeFields();
    }
    
    private void initializeFields() {
        this.fromClientField = new TextField();
        this.toSupplierField = new TextField();
        this.amountField = new TextField();
        this.pdateField = new TextField();
        this.pmethodField = new TextField();
        this.statsLabel = new Label();
    }
    
    public void show() {
        stage = new Stage();
        stage.setTitle("Payment Management System");
        
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f1f8e9;");
        
        Label title = new Label("Payment Management");
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
        fromClientField.setStyle(fieldStyle);
        toSupplierField.setStyle(fieldStyle);
        amountField.setStyle(fieldStyle);
        pdateField.setStyle(fieldStyle);
        pmethodField.setStyle(fieldStyle);
        
        fromClientField.setPromptText("Client ID");
        toSupplierField.setPromptText("Supplier ID");
        amountField.setPromptText("Payment Amount");
        pdateField.setPromptText("YYYY-MM-DD");
        pmethodField.setPromptText("Cash/Card/Transfer");
        
        String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";
        
        Label fromLabel = new Label("From Client:");
        fromLabel.setStyle(labelStyle);
        grid.add(fromLabel, 0, 0);
        grid.add(fromClientField, 1, 0);
        
        Label toLabel = new Label("To Supplier:");
        toLabel.setStyle(labelStyle);
        grid.add(toLabel, 2, 0);
        grid.add(toSupplierField, 3, 0);
        
        Label amountLabel = new Label("Amount:");
        amountLabel.setStyle(labelStyle);
        grid.add(amountLabel, 0, 1);
        grid.add(amountField, 1, 1);
        
        Label dateLabel = new Label("Payment Date:");
        dateLabel.setStyle(labelStyle);
        grid.add(dateLabel, 2, 1);
        grid.add(pdateField, 3, 1);
        
        Label methodLabel = new Label("Payment Method:");
        methodLabel.setStyle(labelStyle);
        grid.add(methodLabel, 0, 2);
        grid.add(pmethodField, 1, 2);
        
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
        
        Button addButton = new Button("Add Payment");
        addButton.setStyle(successButtonStyle);
        addButton.setOnAction(e -> addPayment());
        
        Button updateButton = new Button("Update");
        updateButton.setStyle(warningButtonStyle);
        updateButton.setOnAction(e -> updatePayment());
        
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle(dangerButtonStyle);
        deleteButton.setOnAction(e -> deletePayment());
        
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
        TableColumn<Payment, Integer> idCol = new TableColumn<>("Payment ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        idCol.setPrefWidth(100);
        
        TableColumn<Payment, String> fromCol = new TableColumn<>("From Client");
        fromCol.setCellValueFactory(new PropertyValueFactory<>("fromClient"));
        fromCol.setPrefWidth(120);
        
        TableColumn<Payment, String> toCol = new TableColumn<>("To Supplier");
        toCol.setCellValueFactory(new PropertyValueFactory<>("toSupplier"));
        toCol.setPrefWidth(120);
        
        TableColumn<Payment, Double> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amountCol.setPrefWidth(120);
        
        TableColumn<Payment, String> dateCol = new TableColumn<>("Payment Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("pdate"));
        dateCol.setPrefWidth(120);
        
        TableColumn<Payment, String> methodCol = new TableColumn<>("Payment Method");
        methodCol.setCellValueFactory(new PropertyValueFactory<>("pmethod"));
        methodCol.setPrefWidth(150);
        
        tableView.getColumns().addAll(idCol, fromCol, toCol, amountCol, dateCol, methodCol);
        tableView.setItems(paymentList);
        
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
        
        Label statsTitle = new Label("Payment Statistics");
        statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");
        
        statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");
        
        statsBox.getChildren().addAll(statsTitle, statsLabel);
        return statsBox;
    }
    
    private void loadData() {
        paymentList.clear();
        paymentList.addAll(paymentDAO.getAllPayments());
        showStatistics();
    }
    
    private void addPayment() {
        try {
            if (validateFields()) {
                Payment payment = new Payment(
                    0, // ID will be auto-generated
                    fromClientField.getText().trim(),
                    toSupplierField.getText().trim(),
                    Double.parseDouble(amountField.getText().trim()),
                    pdateField.getText().trim(),
                    pmethodField.getText().trim()
                );
                
                if (paymentDAO.insertPayment(payment)) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Payment added successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to add payment!", Alert.AlertType.ERROR);
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for amount!", Alert.AlertType.ERROR);
        }
    }
    
    private void updatePayment() {
        Payment selectedPayment = tableView.getSelectionModel().getSelectedItem();
        if (selectedPayment != null) {
            try {
                if (validateFields()) {
                    selectedPayment.setFromClient(fromClientField.getText().trim());
                    selectedPayment.setToSupplier(toSupplierField.getText().trim());
                    selectedPayment.setAmount(Double.parseDouble(amountField.getText().trim()));
                    selectedPayment.setPdate(pdateField.getText().trim());
                    selectedPayment.setPmethod(pmethodField.getText().trim());
                    
                    if (paymentDAO.updatePayment(selectedPayment)) {
                        loadData();
                        clearFields();
                        showAlert("Success", "Payment updated successfully!", Alert.AlertType.INFORMATION);
                    } else {
                        showAlert("Error", "Failed to update payment!", Alert.AlertType.ERROR);
                    }
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter a valid number for amount!", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Warning", "Please select a payment to update!", Alert.AlertType.WARNING);
        }
    }
    
    private void deletePayment() {
        Payment selectedPayment = tableView.getSelectionModel().getSelectedItem();
        if (selectedPayment != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Delete");
            confirmAlert.setHeaderText("Delete Payment");
            confirmAlert.setContentText("Are you sure you want to delete this payment?");
            
            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                if (paymentDAO.deletePayment(selectedPayment.getPaymentId())) {
                    loadData();
                    clearFields();
                    showAlert("Success", "Payment deleted successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to delete payment!", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Warning", "Please select a payment to delete!", Alert.AlertType.WARNING);
        }
    }
    
    private void populateFields(Payment payment) {
        fromClientField.setText(payment.getFromClient());
        toSupplierField.setText(payment.getToSupplier());
        amountField.setText(String.valueOf(payment.getAmount()));
        pdateField.setText(payment.getPdate());
        pmethodField.setText(payment.getPmethod());
    }
    
    private boolean validateFields() {
        if (fromClientField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "From Client cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (toSupplierField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "To Supplier cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (amountField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Amount cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (pdateField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Payment date cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        if (pmethodField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Payment method cannot be empty!", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
    
    private void sortByAmountAscending() {
        paymentList.clear();
        paymentList.addAll(paymentDAO.getPaymentsSortedByAmountAscending());
    }
    
    private void sortByAmountDescending() {
        paymentList.clear();
        paymentList.addAll(paymentDAO.getPaymentsSortedByAmountDescending());
    }
    
    private void sortByDateAscending() {
        paymentList.clear();
        paymentList.addAll(paymentDAO.getPaymentsSortedByDateAscending());
    }
    
    private void sortByDateDescending() {
        paymentList.clear();
        paymentList.addAll(paymentDAO.getPaymentsSortedByDateDescending());
    }
    
    private void showStatistics() {
        double maxAmount = paymentDAO.getMaxPaymentAmount();
        double minAmount = paymentDAO.getMinPaymentAmount();
        double avgAmount = paymentDAO.getAveragePaymentAmount();
        double totalAmount = paymentDAO.getTotalPaymentAmount();
        int count = paymentDAO.getPaymentCount();
        
        String stats = String.format(
            "Total Payments: %d\n" +
            "Maximum Amount: $%.2f\n" +
            "Minimum Amount: $%.2f\n" +
            "Average Amount: $%.2f\n" +
            "Total Amount: $%.2f",
            count, maxAmount, minAmount, avgAmount, totalAmount
        );
        
        statsLabel.setText(stats);
    }
    
    private void clearFields() {
        fromClientField.clear();
        toSupplierField.clear();
        amountField.clear();
        pdateField.clear();
        pmethodField.clear();
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