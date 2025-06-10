import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentController implements Initializable {
    
    @FXML private TableView<Department> departmentTable;
    @FXML private TableColumn<Department, Integer> deptIdColumn;
    @FXML private TableColumn<Department, String> deptNameColumn;
    
    @FXML private TextField deptIdField;
    @FXML private TextField deptNameField;
    
    @FXML private Button addBtn;
    @FXML private Button updateBtn;
    @FXML private Button deleteBtn;
    @FXML private Button refreshBtn;
    @FXML private Button clearBtn;
    
    private DepartmentDAO departmentDAO;
    private ObservableList<Department> departmentList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        departmentDAO = new DepartmentDAO();
        departmentList = FXCollections.observableArrayList();
        setupTable();
        setupEventHandlers();
        loadDepartments();
    }
    
    private void setupTable() {
        deptIdColumn.setCellValueFactory(new PropertyValueFactory<Department, Integer>("deptId"));
        deptNameColumn.setCellValueFactory(new PropertyValueFactory<Department, String>("deptName"));
        departmentTable.setItems(departmentList);
        
        // Add selection listener
        departmentTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateFields(newValue);
                }
            }
        );
    }
    
    private void setupEventHandlers() {
        addBtn.setOnAction(e -> addDepartment());
        updateBtn.setOnAction(e -> updateDepartment());
        deleteBtn.setOnAction(e -> deleteDepartment());
        refreshBtn.setOnAction(e -> refreshData());
        clearBtn.setOnAction(e -> clearFields());
    }
    
    private void loadDepartments() {
        departmentList.clear();
        List<Department> departments = departmentDAO.getAllDepartments();
        departmentList.addAll(departments);
    }
    
    private void populateFields(Department department) {
        deptIdField.setText(String.valueOf(department.getDeptId()));
        deptNameField.setText(department.getDeptName());
    }
    
    private void addDepartment() {
        try {
            Department department = createDepartmentFromFields();
            if (department != null && departmentDAO.addDepartment(department)) {
                showAlert("Success", "Department added successfully!");
                refreshData();
                clearFields();
            } else {
                showAlert("Error", "Failed to add department!");
            }
        } catch (Exception e) {
            showAlert("Error", "Error adding department: " + e.getMessage());
        }
    }
    
    private void updateDepartment() {
        Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();
        if (selectedDepartment == null) {
            showAlert("Error", "Please select a department to update!");
            return;
        }
        
        try {
            Department department = createDepartmentFromFields();
            if (department != null && departmentDAO.updateDepartment(department)) {
                showAlert("Success", "Department updated successfully!");
                refreshData();
                clearFields();
            } else {
                showAlert("Error", "Failed to update department!");
            }
        } catch (Exception e) {
            showAlert("Error", "Error updating department: " + e.getMessage());
        }
    }
    
    private void deleteDepartment() {
        Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();
        if (selectedDepartment == null) {
            showAlert("Error", "Please select a department to delete!");
            return;
        }
        
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Delete Department");
        confirmAlert.setContentText("Are you sure you want to delete this department?");
        
        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            if (departmentDAO.deleteDepartment(selectedDepartment.getDeptId())) {
                showAlert("Success", "Department deleted successfully!");
                refreshData();
                clearFields();
            } else {
                showAlert("Error", "Failed to delete department!");
            }
        }
    }
    
    private Department createDepartmentFromFields() {
        try {
            int deptId = Integer.parseInt(deptIdField.getText());
            String deptName = deptNameField.getText();
            
            if (deptName.trim().isEmpty()) {
                showAlert("Error", "Department name cannot be empty!");
                return null;
            }
            
            return new Department(deptId, deptName);
            
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid department ID!");
            return null;
        }
    }
    
    private void refreshData() {
        loadDepartments();
    }
    
    private void clearFields() {
        deptIdField.clear();
        deptNameField.clear();
        departmentTable.getSelectionModel().clearSelection();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
