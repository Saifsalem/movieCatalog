import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class EmployeeController implements Initializable {
    
    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, Integer> empIdColumn;
    @FXML private TableColumn<Employee, String> empNameColumn;
    @FXML private TableColumn<Employee, Integer> positionIdColumn;
    @FXML private TableColumn<Employee, Double> salaryColumn;
    @FXML private TableColumn<Employee, Integer> branchIdColumn;
    @FXML private TableColumn<Employee, Integer> managerIdColumn;
    @FXML private TableColumn<Employee, Integer> departmentIdColumn;
    @FXML private TableColumn<Employee, Boolean> isManagerColumn;
    
    @FXML private TextField empIdField;
    @FXML private TextField empNameField;
    @FXML private ComboBox<Role> positionComboBox;
    @FXML private TextField salaryField;
    @FXML private ComboBox<Branch> branchComboBox;
    @FXML private ComboBox<Employee> managerComboBox;
    @FXML private ComboBox<Department> departmentComboBox;
    @FXML private CheckBox isManagerCheckBox;
    
    @FXML private Button addEmployeeBtn;
    @FXML private Button updateEmployeeBtn;
    @FXML private Button deleteEmployeeBtn;
    @FXML private Button refreshEmployeeBtn;
    @FXML private Button clearFieldsBtn;
    
    private EmployeeDAO employeeDAO;
    private BranchDAO branchDAO;
    private DepartmentDAO departmentDAO;
    private RoleDAO roleDAO;
    private ObservableList<Employee> employeeList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeDAO = new EmployeeDAO();
        branchDAO = new BranchDAO();
        departmentDAO = new DepartmentDAO();
        roleDAO = new RoleDAO();
        employeeList = FXCollections.observableArrayList();
        
        setupEmployeeTable();
        setupComboBoxes();
        setupEventHandlers();
        loadEmployees();
        loadComboBoxData();
    }
    
    private void setupEmployeeTable() {
        empIdColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("empId"));
        empNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("empName"));
        positionIdColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("positionId"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        branchIdColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("branchId"));
        managerIdColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("managerId"));
        departmentIdColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("departmentId"));
        isManagerColumn.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("manager"));
        
        employeeTable.setItems(employeeList);
        
        // Add selection listener
        employeeTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateFields(newValue);
                }
            }
        );
    }
    
    private void setupComboBoxes() {
        // Setup display converters for ComboBoxes
        branchComboBox.setConverter(new StringConverter<Branch>() {
            @Override
            public String toString(Branch branch) {
                return branch != null ? branch.getBranchId() + " - " + branch.getLocation() : "";
            }
            
            @Override
            public Branch fromString(String string) {
                return null;
            }
        });
        
        departmentComboBox.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department department) {
                return department != null ? department.getDeptId() + " - " + department.getDeptName() : "";
            }
            
            @Override
            public Department fromString(String string) {
                return null;
            }
        });
        
        positionComboBox.setConverter(new StringConverter<Role>() {
            @Override
            public String toString(Role role) {
                return role != null ? role.getRoleId() + " - " + role.getTitle() : "";
            }
            
            @Override
            public Role fromString(String string) {
                return null;
            }
        });
        
        managerComboBox.setConverter(new StringConverter<Employee>() {
            @Override
            public String toString(Employee employee) {
                return employee != null ? employee.getEmpId() + " - " + employee.getEmpName() : "";
            }
            
            @Override
            public Employee fromString(String string) {
                return null;
            }
        });
    }
    
    private void setupEventHandlers() {
        addEmployeeBtn.setOnAction(e -> addEmployee());
        updateEmployeeBtn.setOnAction(e -> updateEmployee());
        deleteEmployeeBtn.setOnAction(e -> deleteEmployee());
        refreshEmployeeBtn.setOnAction(e -> refreshData());
        clearFieldsBtn.setOnAction(e -> clearFields());
    }
    
    private void loadEmployees() {
        employeeList.clear();
        List<Employee> employees = employeeDAO.getAllEmployees();
        employeeList.addAll(employees);
    }
    
    private void loadComboBoxData() {
        // Load branches
        List<Branch> branches = branchDAO.getAllBranches();
        branchComboBox.setItems(FXCollections.observableArrayList(branches));
        
        // Load departments
        List<Department> departments = departmentDAO.getAllDepartments();
        departmentComboBox.setItems(FXCollections.observableArrayList(departments));
        
        // Load roles
        List<Role> roles = roleDAO.getAllRoles();
        positionComboBox.setItems(FXCollections.observableArrayList(roles));
        
        // Load managers (employees who are managers) - using traditional loop instead of streams
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        List<Employee> managers = new ArrayList<Employee>();
        for (Employee emp : allEmployees) {
            if (emp.isManager()) {
                managers.add(emp);
            }
        }
        managerComboBox.setItems(FXCollections.observableArrayList(managers));
    }
    
    private void populateFields(Employee employee) {
        empIdField.setText(String.valueOf(employee.getEmpId()));
        empNameField.setText(employee.getEmpName());
        salaryField.setText(String.valueOf(employee.getSalary()));
        isManagerCheckBox.setSelected(employee.isManager());
        
        // Set ComboBox selections - using traditional loops instead of streams
        List<Branch> branches = branchComboBox.getItems();
        for (Branch branch : branches) {
            if (branch.getBranchId() == employee.getBranchId()) {
                branchComboBox.setValue(branch);
                break;
            }
        }
        
        List<Department> departments = departmentComboBox.getItems();
        for (Department dept : departments) {
            if (dept.getDeptId() == employee.getDepartmentId()) {
                departmentComboBox.setValue(dept);
                break;
            }
        }
        
        List<Role> roles = positionComboBox.getItems();
        for (Role role : roles) {
            if (role.getRoleId() == employee.getPositionId()) {
                positionComboBox.setValue(role);
                break;
            }
        }
        
        if (employee.getManagerId() != null) {
            List<Employee> managers = managerComboBox.getItems();
            for (Employee manager : managers) {
                if (manager.getEmpId() == employee.getManagerId().intValue()) {
                    managerComboBox.setValue(manager);
                    break;
                }
            }
        } else {
            managerComboBox.setValue(null);
        }
    }
    
    private void addEmployee() {
        try {
            Employee employee = createEmployeeFromFields();
            if (employee != null && employeeDAO.addEmployee(employee)) {
                showAlert("Success", "Employee added successfully!");
                refreshData();
                clearFields();
            } else {
                showAlert("Error", "Failed to add employee!");
            }
        } catch (Exception e) {
            showAlert("Error", "Error adding employee: " + e.getMessage());
        }
    }
    
    private void updateEmployee() {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showAlert("Error", "Please select an employee to update!");
            return;
        }
        
        try {
            Employee employee = createEmployeeFromFields();
            if (employee != null && employeeDAO.updateEmployee(employee)) {
                showAlert("Success", "Employee updated successfully!");
                refreshData();
                clearFields();
            } else {
                showAlert("Error", "Failed to update employee!");
            }
        } catch (Exception e) {
            showAlert("Error", "Error updating employee: " + e.getMessage());
        }
    }
    
    private void deleteEmployee() {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showAlert("Error", "Please select an employee to delete!");
            return;
        }
        
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Delete Employee");
        confirmAlert.setContentText("Are you sure you want to delete this employee?");
        
        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            if (employeeDAO.deleteEmployee(selectedEmployee.getEmpId())) {
                showAlert("Success", "Employee deleted successfully!");
                refreshData();
                clearFields();
            } else {
                showAlert("Error", "Failed to delete employee!");
            }
        }
    }
    
    private Employee createEmployeeFromFields() {
        try {
            int empId = Integer.parseInt(empIdField.getText());
            String empName = empNameField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            boolean isManager = isManagerCheckBox.isSelected();
            
            if (empName.trim().isEmpty()) {
                showAlert("Error", "Employee name cannot be empty!");
                return null;
            }
            
            if (branchComboBox.getValue() == null || 
                departmentComboBox.getValue() == null || 
                positionComboBox.getValue() == null) {
                showAlert("Error", "Please select branch, department, and position!");
                return null;
            }
            
            int branchId = branchComboBox.getValue().getBranchId();
            int departmentId = departmentComboBox.getValue().getDeptId();
            int positionId = positionComboBox.getValue().getRoleId();
            Integer managerId = null;
            if (managerComboBox.getValue() != null) {
                managerId = Integer.valueOf(managerComboBox.getValue().getEmpId());
            }
            
            return new Employee(empId, empName, positionId, salary, branchId, managerId, departmentId, isManager);
            
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numeric values!");
            return null;
        }
    }
    
    private void refreshData() {
        loadEmployees();
        loadComboBoxData();
    }
    
    private void clearFields() {
        empIdField.clear();
        empNameField.clear();
        salaryField.clear();
        branchComboBox.setValue(null);
        departmentComboBox.setValue(null);
        positionComboBox.setValue(null);
        managerComboBox.setValue(null);
        isManagerCheckBox.setSelected(false);
        employeeTable.getSelectionModel().clearSelection();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}