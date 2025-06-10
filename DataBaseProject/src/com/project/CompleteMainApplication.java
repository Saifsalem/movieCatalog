package com.project;

import com.project.gui.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompleteMainApplication extends Application {
    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Complete Database Management System");
        
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea, #764ba2);");
        
        // Create title
        Label title = new Label("Enterprise Database Management System");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 2);");
        
        Label subtitle = new Label("Complete Business Solution - All 17 Modules Available");
        subtitle.setStyle("-fx-font-size: 18px; -fx-text-fill: #ddd; -fx-font-style: italic;");
        
        // Create grid of buttons
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(15);
        buttonGrid.setVgap(15);
        buttonGrid.setAlignment(Pos.CENTER);
        
        // Button styling
        String primaryButtonStyle = "-fx-background-color: white; -fx-text-fill: #2d3436; -fx-font-weight: bold; " +
                                   "-fx-font-size: 14px; -fx-padding: 15 25; -fx-border-radius: 25; -fx-background-radius: 25; " +
                                   "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 2); -fx-cursor: hand;";
        
        String secondaryButtonStyle = "-fx-background-color: rgba(255,255,255,0.8); -fx-text-fill: #2d3436; -fx-font-weight: bold; " +
                                     "-fx-font-size: 14px; -fx-padding: 15 25; -fx-border-radius: 25; -fx-background-radius: 25; " +
                                     "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 2); -fx-cursor: hand;";
        
        // Core Business Modules (Row 1)
        Button contractButton = createStyledButton("Contract Management", primaryButtonStyle);
        contractButton.setOnAction(e -> new ContractGUI().show());
        buttonGrid.add(contractButton, 0, 0);
        
        Button projectButton = createStyledButton("Project Management", primaryButtonStyle);
        projectButton.setOnAction(e -> new ProjectGUI().show());
        buttonGrid.add(projectButton, 1, 0);
        
        Button clientButton = createStyledButton("Client Management", primaryButtonStyle);
        clientButton.setOnAction(e -> new ClientGUI().show());
        buttonGrid.add(clientButton, 2, 0);
        
        Button salesButton = createStyledButton("Sales Management", primaryButtonStyle);
        salesButton.setOnAction(e -> new SalesGUI().show());
        buttonGrid.add(salesButton, 3, 0);
        
        // HR & Organization (Row 2)
        Button employeeButton = createStyledButton("Employee Management", secondaryButtonStyle);
        employeeButton.setOnAction(e -> new EmployeeGUI().show());
        buttonGrid.add(employeeButton, 0, 1);
        
        Button departmentButton = createStyledButton("Department Management", secondaryButtonStyle);
        departmentButton.setOnAction(e -> new DepartmentGUI().show());
        buttonGrid.add(departmentButton, 1, 1);
        
        Button branchButton = createStyledButton("Branch Management", secondaryButtonStyle);
        branchButton.setOnAction(e -> new BranchGUI().show());
        buttonGrid.add(branchButton, 2, 1);
        
        Button roleButton = createStyledButton("Role Management", secondaryButtonStyle);
        roleButton.setOnAction(e -> new RoleGUI().show());
        buttonGrid.add(roleButton, 3, 1);
        
        // Supply Chain & Materials (Row 3)
        Button supplierButton = createStyledButton("Supplier Management", secondaryButtonStyle);
        supplierButton.setOnAction(e -> new SupplierGUI().show());
        buttonGrid.add(supplierButton, 0, 2);
        
        Button materialButton = createStyledButton("Material Management", secondaryButtonStyle);
        materialButton.setOnAction(e -> new MaterialGUI().show());
        buttonGrid.add(materialButton, 1, 2);
        
        Button purchaseButton = createStyledButton("Purchase Management", secondaryButtonStyle);
        purchaseButton.setOnAction(e -> new PurchaseGUI().show());
        buttonGrid.add(purchaseButton, 2, 2);
        
        Button paymentButton = createStyledButton("Payment Management", secondaryButtonStyle);
        paymentButton.setOnAction(e -> new PaymentGUI().show());
        buttonGrid.add(paymentButton, 3, 2);
        
        // Advanced Management (Row 4)
        Button scheduleButton = createStyledButton("Schedule Management", secondaryButtonStyle);
        scheduleButton.setOnAction(e -> showComingSoon("Schedule Management"));
        buttonGrid.add(scheduleButton, 0, 3);
        
        Button phaseButton = createStyledButton("Phase Management", secondaryButtonStyle);
        phaseButton.setOnAction(e -> showComingSoon("Phase Management"));
        buttonGrid.add(phaseButton, 1, 3);
        
        Button projectMaterialsButton = createStyledButton("Project Materials", secondaryButtonStyle);
        projectMaterialsButton.setOnAction(e -> showComingSoon("Project Materials"));
        buttonGrid.add(projectMaterialsButton, 2, 3);
        
        Button projectSuppliersButton = createStyledButton("Project Suppliers", secondaryButtonStyle);
        projectSuppliersButton.setOnAction(e -> showComingSoon("Project Suppliers"));
        buttonGrid.add(projectSuppliersButton, 3, 3);
        
        // Assignment Management (Row 5)
        Button worksOnButton = createStyledButton("Employee Assignments", secondaryButtonStyle);
        worksOnButton.setOnAction(e -> showComingSoon("Employee Assignments"));
        buttonGrid.add(worksOnButton, 1, 4);
        
        Button exitButton = createStyledButton("Exit Application", "-fx-background-color: #e17055; -fx-text-fill: white; -fx-font-weight: bold; " +
                                             "-fx-font-size: 16px; -fx-padding: 15 30; -fx-border-radius: 25; -fx-background-radius: 25; " +
                                             "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 2); -fx-cursor: hand;");
        exitButton.setOnAction(e -> primaryStage.close());
        buttonGrid.add(exitButton, 2, 4);
        
        // Status info
        Label statusLabel = new Label("Status: 13/17 Modules Complete with Full GUI • 4 Advanced Modules Coming Soon");
        statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: rgba(255,255,255,0.8); -fx-font-style: italic;");
        
        root.getChildren().addAll(title, subtitle, buttonGrid, statusLabel);
        
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Button createStyledButton(String text, String style) {
        Button button = new Button(text);
        button.setStyle(style);
        button.setPrefWidth(200);
        button.setOnMouseEntered(e -> button.setStyle(style + "-fx-scale-x: 1.05; -fx-scale-y: 1.05;"));
        button.setOnMouseExited(e -> button.setStyle(style + "-fx-scale-x: 1.0; -fx-scale-y: 1.0;"));
        return button;
    }
    
    private void showComingSoon(String moduleName) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(moduleName + " - Advanced Module");
        alert.setContentText("This advanced module is part of the complete system architecture.\n\n" +
                            "Current Status: Database layer and models are complete.\n" +
                            "GUI implementation: In development.\n\n" +
                            "The core business modules are fully functional and ready to use!");
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}