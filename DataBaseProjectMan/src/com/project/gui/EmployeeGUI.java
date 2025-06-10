package com.project.gui;

import com.project.dao.EmployeeDAO;
import com.project.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EmployeeGUI {
	private EmployeeDAO employeeDAO;
	private TableView<Employee> tableView;
	private ObservableList<Employee> employeeList;

	private TextField empNameField;
	private TextField positionField;
	private TextField salaryField;
	private TextField branchIdField;
	private TextField managerIdField;
	private TextField departmentIdField;
	private ComboBox<String> isManagerCombo;

	private Label statsLabel;
	private Stage stage;

	public EmployeeGUI() {
		this.employeeDAO = new EmployeeDAO();
		this.tableView = new TableView<>();
		this.employeeList = FXCollections.observableArrayList();
		this.initializeFields();
	}

	private void initializeFields() {
		this.empNameField = new TextField();
		this.positionField = new TextField();
		this.salaryField = new TextField();
		this.branchIdField = new TextField();
		this.managerIdField = new TextField();
		this.departmentIdField = new TextField();
		this.isManagerCombo = new ComboBox<>();
		this.statsLabel = new Label();

		// Setup ComboBox
		isManagerCombo.getItems().addAll("Yes", "No");
		isManagerCombo.setValue("No");
	}

	public void show() {
		stage = new Stage();
		stage.setTitle("Employee Management System");

		VBox root = new VBox(15);
		root.setPadding(new Insets(20));
		root.setStyle("-fx-background-color: #ecf0f1;");

		// Create title
		Label title = new Label("Employee Management");
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

		Scene scene = new Scene(root, 1300, 800);
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
		empNameField.setStyle(fieldStyle);
		positionField.setStyle(fieldStyle);
		salaryField.setStyle(fieldStyle);
		branchIdField.setStyle(fieldStyle);
		managerIdField.setStyle(fieldStyle);
		departmentIdField.setStyle(fieldStyle);
		isManagerCombo.setStyle(fieldStyle);

		// Add placeholders
		empNameField.setPromptText("Enter Employee Name");
		positionField.setPromptText("Enter Position");
		salaryField.setPromptText("Enter Salary");
		branchIdField.setPromptText("Enter Branch ID");
		managerIdField.setPromptText("Enter Manager ID");
		departmentIdField.setPromptText("Enter Department ID");

		// Create labels with styling
		String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #34495e;";

		Label nameLabel = new Label("Employee Name:");
		nameLabel.setStyle(labelStyle);
		grid.add(nameLabel, 0, 0);
		grid.add(empNameField, 1, 0);

		Label positionLabel = new Label("Position:");
		positionLabel.setStyle(labelStyle);
		grid.add(positionLabel, 0, 1);
		grid.add(positionField, 1, 1);

		Label salaryLabel = new Label("Salary:");
		salaryLabel.setStyle(labelStyle);
		grid.add(salaryLabel, 0, 2);
		grid.add(salaryField, 1, 2);

		Label branchLabel = new Label("Branch ID:");
		branchLabel.setStyle(labelStyle);
		grid.add(branchLabel, 2, 0);
		grid.add(branchIdField, 3, 0);

		Label managerLabel = new Label("Manager ID:");
		managerLabel.setStyle(labelStyle);
		grid.add(managerLabel, 2, 1);
		grid.add(managerIdField, 3, 1);

		Label deptLabel = new Label("Department ID:");
		deptLabel.setStyle(labelStyle);
		grid.add(deptLabel, 2, 2);
		grid.add(departmentIdField, 3, 2);

		Label isManagerLabel = new Label("Is Manager:");
		isManagerLabel.setStyle(labelStyle);
		grid.add(isManagerLabel, 4, 0);
		grid.add(isManagerCombo, 5, 0);

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
        
        Button addButton = new Button("Add Employee");
        addButton.setStyle(successButtonStyle);
        addButton.setOnAction(e -> addEmployee());
        
        Button updateButton = new Button("Update Selected");
        updateButton.setStyle(warningButtonStyle);
        updateButton.setOnAction(e -> updateEmployee());
        
        Button deleteButton = new Button("Delete Selected");
        deleteButton.setStyle(dangerButtonStyle);
        deleteButton.setOnAction(e -> deleteEmployee());
        
        Button refreshButton = new Button("Refresh Data");
        refreshButton.setStyle(buttonStyle);
        refreshButton.setOnAction(e -> loadData());
        
        Button sortSalaryAscButton = new Button("Sort Salary Asc");
        sortSalaryAscButton.setStyle(buttonStyle);
        sortSalaryAscButton.setOnAction(e -> sortBySalaryAscending());
        
        Button sortSalaryDescButton = new Button("Sort Salary Desc");
        sortSalaryDescButton.setStyle(buttonStyle);
        sortSalaryDescButton.setOnAction(e -> sortBySalaryDescending());
        
        Button sortNameAscButton = new Button("Sort Name Asc");
        sortNameAscButton.setStyle(buttonStyle);
        sortNameAscButton.setOnAction(e -> sortByNameAscending());
        
        Button sortNameDescButton = new Button("Sort Name Desc");
        sortNameDescButton.setStyle(buttonStyle);
        sortNameDescButton.setOnAction(e -> sortByNameDescending());
        
        Button statsButton = new Button("Show Statistics");
        statsButton.setStyle(buttonStyle);
        statsButton.setOnAction(e -> showStatistics());
        
        Button clearButton = new Button("Clear Fields");
        clearButton.setStyle(buttonStyle);
        clearButton.setOnAction(e -> clearFields());
        
        buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, refreshButton, 
                                      sortSalaryAscButton, sortSalaryDescButton, sortNameAscButton, 
                                      sortNameDescButton, statsButton, clearButton);
        return buttonBox;
    }

private void createTable() {
        TableColumn<Employee, Integer> idCol = new TableColumn<>("Employee ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("empId"));
        idCol.setPrefWidth(100);
        
        TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
        nameCol.setPrefWidth(150);
        
        TableColumn<Employee, String> positionCol = new TableColumn<>("Position");
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        positionCol.setPrefWidth(150);
        
        TableColumn<Employee, Double> salaryCol = new TableColumn<>("Salary");
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salaryCol.setPrefWidth(120);
        
        TableColumn<Employee, String> branchCol = new TableColumn<>("Branch ID");
        branchCol.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        branchCol.setPrefWidth(100);
        
        TableColumn<Employee, String> managerCol = new TableColumn<>("Manager ID");
        managerCol.setCellValueFactory(new PropertyValueFactory<>("managerId"));
        man