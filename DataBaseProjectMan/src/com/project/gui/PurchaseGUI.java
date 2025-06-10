package com.project.gui;

import com.project.dao.PurchaseDAO;
import com.project.model.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PurchaseGUI {
	private PurchaseDAO purchaseDAO;
	private TableView<Purchase> tableView;
	private ObservableList<Purchase> purchaseList;

	private TextField supplierIdField;
	private TextField materialIdField;
	private TextField quantityField;
	private TextField purchaseDateField;
	private TextField totalCostField;

	private Label statsLabel;
	private Stage stage;

	public PurchaseGUI() {
		this.purchaseDAO = new PurchaseDAO();
		this.tableView = new TableView<>();
		this.purchaseList = FXCollections.observableArrayList();
		this.initializeFields();
	}

	private void initializeFields() {
		this.supplierIdField = new TextField();
		this.materialIdField = new TextField();
		this.quantityField = new TextField();
		this.purchaseDateField = new TextField();
		this.totalCostField = new TextField();
		this.statsLabel = new Label();
	}

	public void show() {
		stage = new Stage();
		stage.setTitle("Purchase Management System");

		VBox root = new VBox(15);
		root.setPadding(new Insets(20));
		root.setStyle("-fx-background-color: #fff8e1;");

		Label title = new Label("Purchase Management");
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
		supplierIdField.setStyle(fieldStyle);
		materialIdField.setStyle(fieldStyle);
		quantityField.setStyle(fieldStyle);
		purchaseDateField.setStyle(fieldStyle);
		totalCostField.setStyle(fieldStyle);

		supplierIdField.setPromptText("Supplier ID");
		materialIdField.setPromptText("Material ID");
		quantityField.setPromptText("Quantity");
		purchaseDateField.setPromptText("YYYY-MM-DD");
		totalCostField.setPromptText("Total Cost");

		String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";

		Label supplierLabel = new Label("Supplier ID:");
		supplierLabel.setStyle(labelStyle);
		grid.add(supplierLabel, 0, 0);
		grid.add(supplierIdField, 1, 0);

		Label materialLabel = new Label("Material ID:");
		materialLabel.setStyle(labelStyle);
		grid.add(materialLabel, 2, 0);
		grid.add(materialIdField, 3, 0);

		Label quantityLabel = new Label("Quantity:");
		quantityLabel.setStyle(labelStyle);
		grid.add(quantityLabel, 0, 1);
		grid.add(quantityField, 1, 1);

		Label dateLabel = new Label("Purchase Date:");
		dateLabel.setStyle(labelStyle);
		grid.add(dateLabel, 2, 1);
		grid.add(purchaseDateField, 3, 1);

		Label costLabel = new Label("Total Cost:");
		costLabel.setStyle(labelStyle);
		grid.add(costLabel, 0, 2);
		grid.add(totalCostField, 1, 2);

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

		Button addButton = new Button("Add Purchase");
		addButton.setStyle(successButtonStyle);
		addButton.setOnAction(e -> addPurchase());

		Button updateButton = new Button("Update");
		updateButton.setStyle(warningButtonStyle);
		updateButton.setOnAction(e -> updatePurchase());

		Button deleteButton = new Button("Delete");
		deleteButton.setStyle(dangerButtonStyle);
		deleteButton.setOnAction(e -> deletePurchase());

		Button refreshButton = new Button("Refresh");
		refreshButton.setStyle(buttonStyle);
		refreshButton.setOnAction(e -> loadData());

		Button sortCostAscButton = new Button("Sort Cost Asc");
		sortCostAscButton.setStyle(buttonStyle);
		sortCostAscButton.setOnAction(e -> sortByCostAscending());

		Button sortCostDescButton = new Button("Sort Cost Desc");
		sortCostDescButton.setStyle(buttonStyle);
		sortCostDescButton.setOnAction(e -> sortByCostDescending());

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

		buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, refreshButton, sortCostAscButton,
				sortCostDescButton, sortDateAscButton, sortDateDescButton, statsButton, clearButton);
		return buttonBox;
	}

	private void createTable() {
		TableColumn<Purchase, Integer> idCol = new TableColumn<>("Purchase ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("purchaseId"));
		idCol.setPrefWidth(100);

		TableColumn<Purchase, String> supplierCol = new TableColumn<>("Supplier ID");
		supplierCol.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
		supplierCol.setPrefWidth(100);

		TableColumn<Purchase, Integer> materialCol = new TableColumn<>("Material ID");
		materialCol.setCellValueFactory(new PropertyValueFactory<>("materialId"));
		materialCol.setPrefWidth(100);

		TableColumn<Purchase, Integer> quantityCol = new TableColumn<>("Quantity");
		quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		quantityCol.setPrefWidth(100);

		TableColumn<Purchase, String> dateCol = new TableColumn<>("Purchase Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
		dateCol.setPrefWidth(120);

		TableColumn<Purchase, Double> costCol = new TableColumn<>("Total Cost");
		costCol.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
		costCol.setPrefWidth(120);

		tableView.getColumns().addAll(idCol, supplierCol, materialCol, quantityCol, dateCol, costCol);
		tableView.setItems(purchaseList);

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

		Label statsTitle = new Label("Purchase Statistics");
		statsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");

		statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");

		statsBox.getChildren().addAll(statsTitle, statsLabel);
		return statsBox;
	}

	private void loadData() {
		purchaseList.clear();
		purchaseList.addAll(purchaseDAO.getAllPurchases());
		showStatistics();
	}

	private void addPurchase() {
		try {
			if (validateFields()) {
				Purchase purchase = new Purchase(0, // ID will be auto-generated
						supplierIdField.getText().trim(), Integer.parseInt(materialIdField.getText().trim()),
						Integer.parseInt(quantityField.getText().trim()), purchaseDateField.getText().trim(),
						Double.parseDouble(totalCostField.getText().trim()));

				if (purchaseDAO.insertPurchase(purchase)) {
					loadData();
					clearFields();
					showAlert("Success", "Purchase added successfully!", Alert.AlertType.INFORMATION);
				} else {
					showAlert("Error", "Failed to add purchase!", Alert.AlertType.ERROR);
				}
			}
		} catch (NumberFormatException e) {
			showAlert("Error", "Please enter valid numbers for material ID, quantity, and cost!",
					Alert.AlertType.ERROR);
		}
	}

	private void updatePurchase() {
		Purchase selectedPurchase = tableView.getSelectionModel().getSelectedItem();
		if (selectedPurchase != null) {
			try {
				if (validateFields()) {
					selectedPurchase.setSupplierId(supplierIdField.getText().trim());
					selectedPurchase.setMaterialId(Integer.parseInt(materialIdField.getText().trim()));
					selectedPurchase.setQuantity(Integer.parseInt(quantityField.getText().trim()));
					selectedPurchase.setPurchaseDate(purchaseDateField.getText().trim());
					selectedPurchase.setTotalCost(Double.parseDouble(totalCostField.getText().trim()));

					if (purchaseDAO.updatePurchase(selectedPurchase)) {
						loadData();
						clearFields();
						showAlert("Success", "Purchase updated successfully!", Alert.AlertType.INFORMATION);
					} else {
						showAlert("Error", "Failed to update purchase!", Alert.AlertType.ERROR);
					}
				}
			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter valid numbers!", Alert.AlertType.ERROR);
			}
		} else {
			showAlert("Warning", "Please select a purchase to update!", Alert.AlertType.WARNING);
		}
	}

	private void deletePurchase() {
		Purchase selectedPurchase = tableView.getSelectionModel().getSelectedItem();
		if (selectedPurchase != null) {
			Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmAlert.setTitle("Confirm Delete");
			confirmAlert.setHeaderText("Delete Purchase");
			confirmAlert.setContentText("Are you sure you want to delete this purchase?");

			if (confirmAlert.showAndWait().get() == ButtonType.OK) {
				if (purchaseDAO.deletePurchase(selectedPurchase.getPurchaseId())) {
					loadData();
					clearFields();
					showAlert("Success", "Purchase deleted successfully!", Alert.AlertType.INFORMATION);
				} else {
					showAlert("Error", "Failed to delete purchase!", Alert.AlertType.ERROR);
				}
			}
		} else {
			showAlert("Warning", "Please select a purchase to delete!", Alert.AlertType.WARNING);
		}
	}

	private void populateFields(Purchase purchase) {
		supplierIdField.setText(purchase.getSupplierId());
		materialIdField.setText(String.valueOf(purchase.getMaterialId()));
		quantityField.setText(String.valueOf(purchase.getQuantity()));
		purchaseDateField.setText(purchase.getPurchaseDate());
		totalCostField.setText(String.valueOf(purchase.getTotalCost()));
	}

	private boolean validateFields() {
		if (supplierIdField.getText().trim().isEmpty()) {
			showAlert("Validation Error", "Supplier ID cannot be empty!", Alert.AlertType.WARNING);
			return false;
		}
		if (materialIdField.getText().trim().isEmpty()) {
			showAlert("Validation Error", "Material ID cannot be empty!", Alert.AlertType.WARNING);
			return false;
		}
		if (quantityField.getText().trim().isEmpty()) {
			showAlert("Validation Error", "Quantity cannot be empty!", Alert.AlertType.WARNING);
			return false;
		}
		if (purchaseDateField.getText().trim().isEmpty()) {
			showAlert("Validation Error", "Purchase date cannot be empty!", Alert.AlertType.WARNING);
			return false;
		}
		if (totalCostField.getText().trim().isEmpty()) {
			showAlert("Validation Error", "Total cost cannot be empty!", Alert.AlertType.WARNING);
			return false;
		}
		return true;
	}

	private void sortByCostAscending() {
		purchaseList.clear();
		purchaseList.addAll(purchaseDAO.getPurchasesSortedByPriceAscending());
	}

	private void sortByCostDescending() {
		purchaseList.clear();
		purchaseList.addAll(purchaseDAO.getPurchasesSortedByPriceDescending());
	}

	private void sortByDateAscending() {
		purchaseList.clear();
		purchaseList.addAll(purchaseDAO.getPurchasesSortedByDateAscending());
	}

	private void sortByDateDescending() {
        purchaseList.clear();
        purchaseList.addAll(purchaseDAO.getPurchasesSortedByDateDescending());
    }

private void showStatistics() {
        double maxCost = purchaseDAO.getMaxUnitPrice();
        double minCost = purchaseDAO.getMinUnitPrice();
        double avgCost = purchaseDAO.getA