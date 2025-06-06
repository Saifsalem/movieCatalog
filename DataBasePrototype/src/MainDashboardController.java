
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class MainDashboardController implements Initializable {
    
    @FXML private TabPane mainTabPane;
    
    // Branch Management Tab
    @FXML private TableView<Branch> branchTable;
    @FXML private TableColumn<Branch, Integer> branchIdColumn;
    @FXML private TableColumn<Branch, String> branchLocationColumn;
    @FXML private TextField branchIdField;
    @FXML private TextField branchLocationField;
    @FXML private Button addBranchBtn;
    @FXML private Button updateBranchBtn;
    @FXML private Button deleteBranchBtn;
    @FXML private Button refreshBranchBtn;
    
    // Reports Tab
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private Button generateSalesReportBtn;
    @FXML private Button generateProfitabilityReportBtn;
    @FXML private Button generateWorkloadReportBtn;
    @FXML private Button generateMaterialReportBtn;
    @FXML private Button generateBranchReportBtn;
    @FXML private Button generateSupplierReportBtn;
    @FXML private Button generateTimelineReportBtn;
    @FXML private TextArea reportTextArea;
    @FXML private Label totalSalesLabel;
    
    // Navigation Buttons
    @FXML private Button manageEmployeesBtn;
    @FXML private Button manageProjectsBtn;
    @FXML private Button manageSuppliersBtn;
    @FXML private Button manageMaterialsBtn;
    @FXML private Button manageClientsBtn;
    
    private BranchDAO branchDAO;
    private ReportsDAO reportsDAO;
    private ObservableList<Branch> branchList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        branchDAO = new BranchDAO();
        reportsDAO = new ReportsDAO();
        branchList = FXCollections.observableArrayList();
        
        setupBranchTable();
        setupEventHandlers();
        loadBranches();
        
        // Set default dates
        startDatePicker.setValue(LocalDate.now().minusMonths(1));
        endDatePicker.setValue(LocalDate.now());
    }
    
    private void setupBranchTable() {
        branchIdColumn.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        branchLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        branchTable.setItems(branchList);
        
        // Add selection listener
        branchTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    branchIdField.setText(String.valueOf(newValue.getBranchId()));
                    branchLocationField.setText(newValue.getLocation());
                }
            }
        );
    }
    
    private void setupEventHandlers() {
        // Branch management events
        addBranchBtn.setOnAction(e -> addBranch());
        updateBranchBtn.setOnAction(e -> updateBranch());
        deleteBranchBtn.setOnAction(e -> deleteBranch());
        refreshBranchBtn.setOnAction(e -> loadBranches());
        
        // Report generation events
        generateSalesReportBtn.setOnAction(e -> generateSalesReport());
        generateProfitabilityReportBtn.setOnAction(e -> generateProfitabilityReport());
        generateWorkloadReportBtn.setOnAction(e -> generateWorkloadReport());
        generateMaterialReportBtn.setOnAction(e -> generateMaterialReport());
        generateBranchReportBtn.setOnAction(e -> generateBranchReport());
        generateSupplierReportBtn.setOnAction(e -> generateSupplierReport());
        generateTimelineReportBtn.setOnAction(e -> generateTimelineReport());
        
        // Navigation events
        manageEmployeesBtn.setOnAction(e -> openEmployeeManagement());
        manageProjectsBtn.setOnAction(e -> openProjectManagement());
        manageSuppliersBtn.setOnAction(e -> openSupplierManagement());
        manageMaterialsBtn.setOnAction(e -> openMaterialManagement());
        manageClientsBtn.setOnAction(e -> openClientManagement());
    }
    
    private void loadBranches() {
        branchList.clear();
        List<Branch> branches = branchDAO.getAllBranches();
        branchList.addAll(branches);
    }
    
    private void addBranch() {
        try {
            int branchId = Integer.parseInt(branchIdField.getText());
            String location = branchLocationField.getText();
            
            if (location.trim().isEmpty()) {
                showAlert("Error", "Location cannot be empty!");
                return;
            }
            
            Branch branch = new Branch(branchId, location);
            if (branchDAO.addBranch(branch)) {
                showAlert("Success", "Branch added successfully!");
                clearBranchFields();
                loadBranches();
            } else {
                showAlert("Error", "Failed to add branch!");
            }
            
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid branch ID!");
        }
    }
    
    private void updateBranch() {
        Branch selectedBranch = branchTable.getSelectionModel().getSelectedItem();
        if (selectedBranch == null) {
            showAlert("Error", "Please select a branch to update!");
            return;
        }
        
        try {
            String location = branchLocationField.getText();
            if (location.trim().isEmpty()) {
                showAlert("Error", "Location cannot be empty!");
                return;
            }
            
            selectedBranch.setLocation(location);
            if (branchDAO.updateBranch(selectedBranch)) {
                showAlert("Success", "Branch updated successfully!");
                clearBranchFields();
                loadBranches();
            } else {
                showAlert("Error", "Failed to update branch!");
            }
            
        } catch (Exception e) {
            showAlert("Error", "Error updating branch: " + e.getMessage());
        }
    }
    
    private void deleteBranch() {
        Branch selectedBranch = branchTable.getSelectionModel().getSelectedItem();
        if (selectedBranch == null) {
            showAlert("Error", "Please select a branch to delete!");
            return;
        }
        
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Delete Branch");
        confirmAlert.setContentText("Are you sure you want to delete this branch?");
        
        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            if (branchDAO.deleteBranch(selectedBranch.getBranchId())) {
                showAlert("Success", "Branch deleted successfully!");
                clearBranchFields();
                loadBranches();
            } else {
                showAlert("Error", "Failed to delete branch!");
            }
        }
    }
    
    private void clearBranchFields() {
        branchIdField.clear();
        branchLocationField.clear();
        branchTable.getSelectionModel().clearSelection();
    }
    
    private void generateSalesReport() {
        if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
            showAlert("Error", "Please select both start and end dates!");
            return;
        }
        
        Date startDate = Date.valueOf(startDatePicker.getValue());
        Date endDate = Date.valueOf(endDatePicker.getValue());
        
        double totalSales = reportsDAO.getTotalSalesForPeriod(startDate, endDate);
        totalSalesLabel.setText("Total Sales: $" + String.format("%.2f", totalSales));
        
        StringBuilder report = new StringBuilder();
        report.append("SALES REPORT\n");
        report.append("Period: ").append(startDate).append(" to ").append(endDate).append("\n");
        report.append("Total Sales: $").append(String.format("%.2f", totalSales)).append("\n");
        report.append("Generated on: ").append(LocalDate.now()).append("\n");
        
        reportTextArea.setText(report.toString());
    }
    
    private void generateProfitabilityReport() {
        List<Map<String, Object>> data = reportsDAO.getProjectProfitabilityReport();
        StringBuilder report = new StringBuilder();
        report.append("PROJECT PROFITABILITY REPORT\n");
        report.append("==========================================\n\n");
        
        for (Map<String, Object> row : data) {
            report.append("Project ID: ").append(row.get("project_id")).append("\n");
            report.append("Project Name: ").append(row.get("project_name")).append("\n");
            report.append("Cost: $").append(String.format("%.2f", (Double)row.get("cost"))).append("\n");
            report.append("Revenue: $").append(String.format("%.2f", (Double)row.get("revenue"))).append("\n");
            report.append("Profit: $").append(String.format("%.2f", (Double)row.get("profit"))).append("\n");
            report.append("Profit Margin: ").append(String.format("%.2f", (Double)row.get("profit_margin"))).append("%\n");
            report.append("------------------------------------------\n");
        }
        
        reportTextArea.setText(report.toString());
    }
    
    private void generateWorkloadReport() {
        List<Map<String, Object>> data = reportsDAO.getEmployeeWorkloadReport();
        StringBuilder report = new StringBuilder();
        report.append("EMPLOYEE WORKLOAD REPORT\n");
        report.append("==========================================\n\n");
        
        for (Map<String, Object> row : data) {
            report.append("Employee ID: ").append(row.get("emp_id")).append("\n");
            report.append("Employee Name: ").append(row.get("emp_name")).append("\n");
            report.append("Department: ").append(row.get("department")).append("\n");
            report.append("Project Count: ").append(row.get("project_count")).append("\n");
            report.append("Projects: ").append(row.get("projects")).append("\n");
            report.append("------------------------------------------\n");
        }
        
        reportTextArea.setText(report.toString());
    }
    
    private void generateMaterialReport() {
        List<Map<String, Object>> data = reportsDAO.getMaterialUsageReport();
        StringBuilder report = new StringBuilder();
        report.append("MATERIAL USAGE REPORT\n");
        report.append("==========================================\n\n");
        
        for (Map<String, Object> row : data) {
            report.append("Material ID: ").append(row.get("material_id")).append("\n");
            report.append("Material Name: ").append(row.get("material_name")).append("\n");
            report.append("Total Used: ").append(row.get("total_used")).append("\n");
            report.append("Projects Used In: ").append(row.get("projects_used_in")).append("\n");
            report.append("Average Usage per Project: ").append(String.format("%.2f", (Double)row.get("avg_usage_per_project"))).append("\n");
            report.append("------------------------------------------\n");
        }
        
        reportTextArea.setText(report.toString());
    }
    
    private void generateBranchReport() {
        List<Map<String, Object>> data = reportsDAO.getBranchPerformanceReport();
        StringBuilder report = new StringBuilder();
        report.append("BRANCH PERFORMANCE REPORT\n");
        report.append("==========================================\n\n");
        
        for (Map<String, Object> row : data) {
            report.append("Branch ID: ").append(row.get("branch_id")).append("\n");
            report.append("Location: ").append(row.get("location")).append("\n");
            report.append("Total Projects: ").append(row.get("total_projects")).append("\n");
            report.append("Total Revenue: $").append(String.format("%.2f", (Double)row.get("total_revenue"))).append("\n");
            report.append("Total Cost: $").append(String.format("%.2f", (Double)row.get("total_cost"))).append("\n");
            report.append("Employee Count: ").append(row.get("employee_count")).append("\n");
            report.append("------------------------------------------\n");
        }
        
        reportTextArea.setText(report.toString());
    }
    
    private void generateSupplierReport() {
        List<Map<String, Object>> data = reportsDAO.getSupplierPerformanceReport();
        StringBuilder report = new StringBuilder();
        report.append("SUPPLIER PERFORMANCE REPORT\n");
        report.append("==========================================\n\n");
        
        for (Map<String, Object> row : data) {
            report.append("Supplier ID: ").append(row.get("supplier_id")).append("\n");
            report.append("Supplier Name: ").append(row.get("supplier_name")).append("\n");
            report.append("Location: ").append(row.get("location")).append("\n");
            report.append("Total Purchases: ").append(row.get("total_purchases")).append("\n");
            report.append("Total Spent: $").append(String.format("%.2f", (Double)row.get("total_spent"))).append("\n");
            report.append("Average Purchase Cost: $").append(String.format("%.2f", (Double)row.get("avg_purchase_cost"))).append("\n");
            report.append("Projects Supplied: ").append(row.get("projects_supplied")).append("\n");
            report.append("------------------------------------------\n");
        }
        
        reportTextArea.setText(report.toString());
    }
    
    private void generateTimelineReport() {
        List<Map<String, Object>> data = reportsDAO.getProjectTimelineReport();
        StringBuilder report = new StringBuilder();
        report.append("PROJECT TIMELINE REPORT\n");
        report.append("==========================================\n\n");
        
        for (Map<String, Object> row : data) {
            report.append("Project ID: ").append(row.get("project_id")).append("\n");
            report.append("Project Name: ").append(row.get("project_name")).append("\n");
            report.append("Total Phases: ").append(row.get("total_phases")).append("\n");
            report.append("Completed Phases: ").append(row.get("completed_phases")).append("\n");
            report.append("Project Start: ").append(row.get("project_start")).append("\n");
            report.append("Project End: ").append(row.get("project_end")).append("\n");
            report.append("Duration (Days): ").append(row.get("duration_days")).append("\n");
            report.append("------------------------------------------\n");
        }
        
        reportTextArea.setText(report.toString());
    }
    
    // Navigation methods - open different management windows
    private void openEmployeeManagement() {
        openNewWindow("/fxml/EmployeeManagement.fxml", "Employee Management");
    }
    
    private void openProjectManagement() {
        openNewWindow("/fxml/ProjectManagement.fxml", "Project Management");
    }
    
    private void openSupplierManagement() {
        openNewWindow("/fxml/SupplierManagement.fxml", "Supplier Management");
    }
    
    private void openMaterialManagement() {
        openNewWindow("/fxml/MaterialManagement.fxml", "Material Management");
    }
    
    private void openClientManagement() {
        openNewWindow("/fxml/ClientManagement.fxml", "Client Management");
    }
    
    private void openNewWindow(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            
        } catch (IOException e) {
            showAlert("Error", "Could not open " + title + ": " + e.getMessage());
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
