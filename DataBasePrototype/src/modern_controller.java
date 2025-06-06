import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ModernProjectManagementController implements Initializable {
    
    // Navigation Buttons
    @FXML private Button dashboardBtn;
    @FXML private Button reportsBtn;
    @FXML private Button employeeManagementBtn;
    @FXML private Button projectManagementBtn;
    @FXML private Button clientManagementBtn;
    @FXML private Button supplierManagementBtn;
    @FXML private Button materialManagementBtn;
    @FXML private Button branchManagementBtn;
    @FXML private Button roleManagementBtn;
    @FXML private Button departmentManagementBtn;
    @FXML private Button phaseManagementBtn;
    @FXML private Button paymentManagementBtn;
    
    // Views
    @FXML private VBox dashboardView;
    @FXML private VBox employeeManagementView;
    @FXML private VBox projectManagementView;
    @FXML private VBox clientManagementView;
    @FXML private VBox supplierManagementView;
    @FXML private VBox materialManagementView;
    @FXML private VBox branchManagementView;
    @FXML private VBox roleManagementView;
    @FXML private VBox departmentManagementView;
    @FXML private VBox phaseManagementView;
    @FXML private VBox paymentManagementView;
    @FXML private VBox reportsView;
    
    // Dashboard Elements
    @FXML private Label totalProjectsLabel;
    @FXML private Label totalEmployeesLabel;
    @FXML private Label totalClientsLabel;
    @FXML private Label totalRevenueLabel;
    @FXML private Label statusLabel;
    @FXML private Label currentTimeLabel;
    
    // Employee Management
    @FXML private TextField empIdField;
    @FXML private TextField empNameField;
    @FXML private ComboBox<Role> positionComboBox;
    @FXML private TextField salaryField;
    @FXML private ComboBox<Branch> empBranchComboBox;
    @FXML private ComboBox<Department> empDepartmentComboBox;
    @FXML private ComboBox<Employee> managerComboBox;
    @FXML private CheckBox isManagerCheckBox;
    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, Integer> empIdColumn;
    @FXML private TableColumn<Employee, String> empNameColumn;
    @FXML private TableColumn<Employee, String> empPositionColumn;
    @FXML private TableColumn<Employee, Double> empSalaryColumn;
    @FXML private TableColumn<Employee, String> empBranchColumn;
    @FXML private TableColumn<Employee, String> empDepartmentColumn;
    @FXML private TableColumn<Employee, String> empManagerColumn;
    @FXML private Button addEmployeeBtn;
    @FXML private Button saveEmployeeBtn;
    @FXML private Button updateEmployeeBtn;
    @FXML private Button deleteEmployeeBtn;
    @FXML private Button refreshEmployeesBtn;
    
    // Project Management
    @FXML private TextField projectIdField;
    @FXML private TextField projectNameField;
    @FXML private TextField projectLocationField;
    @FXML private TextField costField;
    @FXML private TextField revenueField;
    @FXML private ComboBox<Branch> projectBranchComboBox;
    @FXML private ComboBox<Client> projectClientComboBox;
    @FXML private TableView<Project> projectTable;
    @FXML private TableColumn<Project, Integer> projectIdColumn;
    @FXML private TableColumn<Project, String> projectNameColumn;
    @FXML private TableColumn<Project, String> projectLocationColumn;
    @FXML private TableColumn<Project, Double> projectCostColumn;
    @FXML private TableColumn<Project, Double> projectRevenueColumn;
    @FXML private TableColumn<Project, String> projectBranchColumn;
    @FXML private Button addProjectBtn;
    @FXML private Button saveProjectBtn;
    @FXML private Button updateProjectBtn;
    @FXML private Button deleteProjectBtn;
    @FXML private Button refreshProjectsBtn;
    
    // Client Management
    @FXML private TextField clientIdField;
    @FXML private TextField clientNameField;
    @FXML private TableView<Client> clientTable;
    @FXML private TableColumn<Client, Integer> clientIdColumn;
    @FXML private TableColumn<Client, String> clientNameColumn;
    @FXML private Button addClientBtn;
    @FXML private Button saveClientBtn;
    @FXML private Button updateClientBtn;
    @FXML private Button deleteClientBtn;
    @FXML private Button refreshClientsBtn;
    
    // Material Management
    @FXML private TextField materialIdField;
    @FXML private TextField materialNameField;
    @FXML private TextField materialPriceField;
    @FXML private TableView<Material> materialTable;
    @FXML private TableColumn<Material, Integer> materialIdColumn;
    @FXML private TableColumn<Material, String> materialNameColumn;
    @FXML private TableColumn<Material, Double> materialPriceColumn;
    @FXML private Button addMaterialBtn;
    @FXML private Button saveMaterialBtn;
    @FXML private Button updateMaterialBtn;
    @FXML private Button deleteMaterialBtn;
    @FXML private Button refreshMaterialsBtn;
    
    // Supplier Management
    @FXML private TextField supplierIdField;
    @FXML private TextField supplierNameField;
    @FXML private TextField supplierLocationField;
    @FXML private TableView<Supplier> supplierTable;
    @FXML private TableColumn<Supplier, Integer> supplierIdColumn;
    @FXML private TableColumn<Supplier, String> supplierNameColumn;
    @FXML private TableColumn<Supplier, String> supplierLocationColumn;
    @FXML private Button addSupplierBtn;
    @FXML private Button saveSupplierBtn;
    @FXML private Button updateSupplierBtn;
    @FXML private Button deleteSupplierBtn;
    @FXML private Button refreshSuppliersBtn;
    
    // Branch Management
    @FXML private TextField branchIdField;
    @FXML private TextField branchLocationField;
    @FXML private TableView<Branch> branchTable;
    @FXML private TableColumn<Branch, Integer> branchIdColumn;
    @FXML private TableColumn<Branch, String> branchLocationColumn;
    @FXML private Button addBranchBtn;
    @FXML private Button saveBranchBtn;
    @FXML private Button updateBranchBtn;
    @FXML private Button deleteBranchBtn;
    @FXML private Button refreshBranchesBtn;
    
    // Role Management
    @FXML private TextField roleIdField;
    @FXML private TextField roleTitleField;
    @FXML private TableView<Role> roleTable;
    @FXML private TableColumn<Role, Integer> roleIdColumn;
    @FXML private TableColumn<Role, String> roleTitleColumn;
    @FXML private Button addRoleBtn;
    @FXML private Button saveRoleBtn;
    @FXML private Button updateRoleBtn;
    @FXML private Button deleteRoleBtn;
    @FXML private Button refreshRolesBtn;
    
    // Department Management
    @FXML private TextField departmentIdField;
    @FXML private TextField departmentNameField;
    @FXML private TableView<Department> departmentTable;
    @FXML private TableColumn<Department, Integer> departmentIdColumn;
    @FXML private TableColumn<Department, String> departmentNameColumn;
    @FXML private Button addDepartmentBtn;
    @FXML private Button saveDepartmentBtn;
    @FXML private Button updateDepartmentBtn;
    @FXML private Button deleteDepartmentBtn;
    @FXML private Button refreshDepartmentsBtn;
    
    // Phase Management
    @FXML private TextField phaseIdField;
    @FXML private ComboBox<Project> phaseProjectComboBox;
    @FXML private TextField phaseNameField;
    @FXML private TextArea phaseDescriptionField;
    @FXML private DatePicker phaseStartDatePicker;
    @FXML private DatePicker phaseEndDatePicker;
    @FXML private ComboBox<String> phaseStatusComboBox;
    @FXML private TableView<Phase> phaseTable;
    @FXML private TableColumn<Phase, Integer> phaseIdColumn;
    @FXML private TableColumn<Phase, String> phaseProjectColumn;
    @FXML private TableColumn<Phase, String> phaseNameColumn;
    @FXML private TableColumn<Phase, Date> phaseStartDateColumn;
    @FXML private TableColumn<Phase, Date> phaseEndDateColumn;
    @FXML private TableColumn<Phase, String> phaseStatusColumn;
    @FXML private Button addPhaseBtn;
    @FXML private Button savePhaseBtn;
    @FXML private Button updatePhaseBtn;
    @FXML private Button deletePhaseBtn;
    @FXML private Button refreshPhasesBtn;
    
    // Payment Management
    @FXML private TextField paymentIdField;
    @FXML private ComboBox<Client> paymentClientComboBox;
    @FXML private ComboBox<Supplier> paymentSupplierComboBox;
    @FXML private TextField paymentAmountField;
    @FXML private DatePicker paymentDatePicker;
    @FXML private ComboBox<String> paymentMethodComboBox;
    @FXML private TableView<Payment> paymentTable;
    @FXML private TableColumn<Payment, Integer> paymentIdColumn;
    @FXML private TableColumn<Payment, String> paymentClientColumn;
    @FXML private TableColumn<Payment, String> paymentSupplierColumn;
    @FXML private TableColumn<Payment, Double> paymentAmountColumn;
    @FXML private TableColumn<Payment, Date> paymentDateColumn;
    @FXML private TableColumn<Payment, String> paymentMethodColumn;
    @FXML private Button addPaymentBtn;
    @FXML private Button savePaymentBtn;
    @FXML private Button updatePaymentBtn;
    @FXML private Button deletePaymentBtn;
    @FXML private Button refreshPaymentsBtn;
    
    // Reports
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private TextArea reportTextArea;
    @FXML private Label totalSalesLabel;
    @FXML private Button generateSalesReportBtn;
    @FXML private Button generateProfitabilityReportBtn;
    @FXML private Button generateWorkloadReportBtn;
    @FXML private Button generateMaterialReportBtn;
    @FXML private Button generateBranchReportBtn;
    @FXML private Button generateSupplierReportBtn;
    @FXML private Button generateTimelineReportBtn;
    
    // DAOs
    private EmployeeDAO employeeDAO;
    private ProjectDAO projectDAO;
    private ClientDAO clientDAO;
    private MaterialDAO materialDAO;
    private SupplierDAO supplierDAO;
    private BranchDAO branchDAO;
    private RoleDAO roleDAO;
    private DepartmentDAO departmentDAO;
    private PhaseDAO phaseDAO;
    private PaymentDAO paymentDAO;
    private ReportsDAO reportsDAO;
    
    // Observable Lists
    private ObservableList<Employee> employeeList;
    private ObservableList<Project> projectList;
    private ObservableList<Client> clientList;
    private ObservableList<Material> materialList;
    private ObservableList<Supplier> supplierList;
    private ObservableList<Branch> branchList;
    private ObservableList<Role> roleList;
    private ObservableList<Department> departmentList;
    private ObservableList<Phase> phaseList;
    private ObservableList<Payment> paymentList;
    
    // Current view tracking
    private VBox currentView;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeDAOs();
        initializeObservableLists();
        setupTables();
        setupComboBoxes();
        setupEventHandlers();
        
        // Start with dashboard view
        showDashboard();
        
        // Update dashboard statistics
        updateDashboardStats();
        
        // Setup time display
        setupTimeDisplay();
        
        // Load initial data
        loadAllData();
        
        statusLabel.setText("Application initialized successfully");
    }
    
    private void initializeDAOs() {
        employeeDAO = new EmployeeDAO();
        projectDAO = new ProjectDAO();
        clientDAO = new ClientDAO();
        materialDAO = new MaterialDAO();
        supplierDAO = new SupplierDAO();
        branchDAO = new BranchDAO();
        roleDAO = new RoleDAO();
        departmentDAO = new DepartmentDAO();
        phaseDAO = new PhaseDAO();
        paymentDAO = new PaymentDAO();
        reportsDAO = new ReportsDAO();
    }
    
    private void initializeObservableLists() {
        employeeList = FXCollections.observableArrayList();
        projectList = FXCollections.observableArrayList();
        clientList = FXCollections.observableArrayList();
        materialList = FXCollections.observableArrayList();
        supplierList = FXCollections.observableArrayList();
        branchList = FXCollections.observableArrayList();
        roleList = FXCollections.observableArrayList();
        departmentList = FXCollections.observableArrayList();
        phaseList = FXCollections.observableArrayList();
        paymentList = FXCollections.observableArrayList();
    }
    
    private void setupTables() {
        setupEmployeeTable();
        setupProjectTable();
        setupClientTable();
        setupMaterialTable();
        setupSupplierTable();
        setupBranchTable();
        setupRoleTable();
        setupDepartmentTable();
        setupPhaseTable();
        setupPaymentTable();
    }
    
    private void setupEmployeeTable() {
        empIdColumn.setCellValueFactory(new PropertyValueFactory<>("empId"));
        empNameColumn.setCellValueFactory(new PropertyValueFactory<>("empName"));
        empPositionColumn.setCellValueFactory(cellData -> {
            Employee emp = cellData.getValue();
            Role role = roleDAO.getRoleById(emp.getPositionId());
            return new javafx.beans.property.SimpleStringProperty(role != null ? role.getTitle() : "Unknown");
        });
        empSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        empBranchColumn.setCellValueFactory(cellData -> {
            Employee emp = cellData.getValue();
            Branch branch = branchDAO.getBranchById(emp.getBranchId());
            return new javafx.beans.property.SimpleStringProperty(branch != null ? branch.getLocation() : "Unknown");
        });
        empDepartmentColumn.setCellValueFactory(cellData -> {
            Employee emp = cellData.getValue();
            Department dept = departmentDAO.getDepartmentById(emp.getDepartmentId());
            return new javafx.beans.property.SimpleStringProperty(dept != null ? dept.getDeptName() : "Unknown");
        });
        empManagerColumn.setCellValueFactory(cellData -> {
            Employee emp = cellData.getValue();
            if (emp.getManagerId() != null) {
                Employee manager = employeeDAO.getEmployeeById(emp.getManagerId());
                return new javafx.beans.property.SimpleStringProperty(manager != null ? manager.getEmpName() : "None");
            }
            return new javafx.beans.property.SimpleStringProperty("None");
        });
        
        employeeTable.setItems(employeeList);
        employeeTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateEmployeeFields(newValue);
                }
            }
        );
    }
    
    private void setupProjectTable() {
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("pname"));
        projectLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        projectCostColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        projectRevenueColumn.setCellValueFactory(new PropertyValueFactory<>("revenue"));
        projectBranchColumn.setCellValueFactory(cellData -> {
            Project project = cellData.getValue();
            Branch branch = branchDAO.getBranchById(project.getBranchId());
            return new javafx.beans.property.SimpleStringProperty(branch != null ? branch.getLocation() : "Unknown");
        });
        
        projectTable.setItems(projectList);
        projectTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateProjectFields(newValue);
                }
            }
        );
    }
    
    private void setupClientTable() {
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        
        clientTable.setItems(clientList);
        clientTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateClientFields(newValue);
                }
            }
        );
    }
    
    private void setupMaterialTable() {
        materialIdColumn.setCellValueFactory(new PropertyValueFactory<>("materialId"));
        materialNameColumn.setCellValueFactory(new PropertyValueFactory<>("mname"));
        materialPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        materialTable.setItems(materialList);
        materialTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateMaterialFields(newValue);
                }
            }
        );
    }
    
    private void setupSupplierTable() {
        supplierIdColumn.setCellValueFactory(new PropertyValueFactory<>("suppId"));
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("suppName"));
        supplierLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        
        supplierTable.setItems(supplierList);
        supplierTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateSupplierFields(newValue);
                }
            }
        );
    }
    
    private void setupBranchTable() {
        branchIdColumn.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        branchLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        
        branchTable.setItems(branchList);
        branchTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateBranchFields(newValue);
                }
            }
        );
    }
    
    private void setupRoleTable() {
        roleIdColumn.setCellValueFactory(new PropertyValueFactory<>("roleId"));
        roleTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        roleTable.setItems(roleList);
        roleTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateRoleFields(newValue);
                }
            }
        );
    }
    
    private void setupDepartmentTable() {
        departmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("deptId"));
        departmentNameColumn.setCellValueFactory(new PropertyValueFactory<>("deptName"));
        
        departmentTable.setItems(departmentList);
        departmentTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateDepartmentFields(newValue);
                }
            }
        );
    }
    
    private void setupPhaseTable() {
        phaseIdColumn.setCellValueFactory(new PropertyValueFactory<>("phaseId"));
        phaseProjectColumn.setCellValueFactory(cellData -> {
            Phase phase = cellData.getValue();
            Project project = projectDAO.getProjectById(phase.getProjectId());
            return new javafx.beans.property.SimpleStringProperty(project != null ? project.getPname() : "Unknown");
        });
        phaseNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phaseStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        phaseEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        phaseStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        phaseTable.setItems(phaseList);
        phaseTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populatePhaseFields(newValue);
                }
            }
        );
    }
    
    private void setupPaymentTable() {
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        paymentClientColumn.setCellValueFactory(cellData -> {
            Payment payment = cellData.getValue();
            Client client = clientDAO.getClientById(payment.getFromClient());
            return new javafx.beans.property.SimpleStringProperty(client != null ? client.getClientName() : "Unknown");
        });
        paymentSupplierColumn.setCellValueFactory(cellData -> {
            Payment payment = cellData.getValue();
            Supplier supplier = supplierDAO.getSupplierById(payment.getToSupplier());
            return new javafx.beans.property.SimpleStringProperty(supplier != null ? supplier.getSuppName() : "Unknown");
        });
        paymentAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("pdate"));
        paymentMethodColumn.setCellValueFactory(new PropertyValueFactory<>("pmethod"));
        
        paymentTable.setItems(paymentList);
        paymentTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populatePaymentFields(newValue);
                }
            }
        );
    }
    
    private void setupComboBoxes() {
        setupEmployeeComboBoxes();
        setupProjectComboBoxes();
        setupPhaseComboBoxes();
        setupPaymentComboBoxes();
    }
    
    private void setupEmployeeComboBoxes() {
        // Position ComboBox
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
        
        // Branch ComboBox
        empBranchComboBox.setConverter(new StringConverter<Branch>() {
            @Override
            public String toString(Branch branch) {
                return branch != null ? branch.getBranchId() + " - " + branch.getLocation() : "";
            }
            
            @Override
            public Branch fromString(String string) {
                return null;
            }
        });
        
        // Department ComboBox
        empDepartmentComboBox.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department department) {
                return department != null ? department.getDeptId() + " - " + department.getDeptName() : "";
            }
            
            @Override
            public Department fromString(String string) {
                return null;
            }
        });
        
        // Manager ComboBox
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
    
    private void setupProjectComboBoxes() {
        // Project Branch ComboBox
        projectBranchComboBox.setConverter(new StringConverter<Branch>() {
            @Override
            public String toString(Branch branch) {
                return branch != null ? branch.getBranchId() + " - " + branch.getLocation() : "";
            }
            
            @Override
            public Branch fromString(String string) {
                return null;
            }
        });
        
        // Project Client ComboBox
        projectClientComboBox.setConverter(new StringConverter<Client>() {
            @Override
            public String toString(Client client) {
                return client != null ? client.getClientId() + " - " + client.getClientName() : "";
            }
            
            @Override
            public Client fromString(String string) {
                return null;
            }
        });
    }
    
    private void setupPhaseComboBoxes() {
        // Phase Project ComboBox
        phaseProjectComboBox.setConverter(new StringConverter<Project>() {
            @Override
            public String toString(Project project) {
                return project != null ? project.getProjectId() + " - " + project.getPname() : "";
            }
            
            @Override
            public Project fromString(String string) {
                return null;
            }
        });
        
        // Phase Status ComboBox
        phaseStatusComboBox.setItems(FXCollections.observableArrayList(
            "Planning", "In Progress", "Completed", "On Hold", "Cancelled"
        ));
    }
    
    private void setupPaymentComboBoxes() {
        // Payment Client ComboBox
        paymentClientComboBox.setConverter(new StringConverter<Client>() {
            @Override
            public String toString(Client client) {
                return client != null ? client.getClientId() + " - " + client.getClientName() : "";
            }
            
            @Override
            public Client fromString(String string) {
                return null;
            }
        });
        
        // Payment Supplier ComboBox
        paymentSupplierComboBox.setConverter(new StringConverter<Supplier>() {
            @Override
            public String toString(Supplier supplier) {
                return supplier != null ? supplier.getSuppId() + " - " + supplier.getSuppName() : "";
            }
            
            @Override
            public Supplier fromString(String string) {
                return null;
            }
        });
        
        // Payment Method ComboBox
        paymentMethodComboBox.setItems(FXCollections.observableArrayList(
            "Cash", "Credit Card", "Bank Transfer", "Check", "Online Payment"
        ));
    }
    
    private void setupEventHandlers() {
        setupNavigationHandlers();
        setupEmployeeHandlers();
        setupProjectHandlers();
        setupClientHandlers();
        setupMaterialHandlers();
        setupSupplierHandlers();
        setupBranchHandlers();
        setupRoleHandlers();
        setupDepartmentHandlers();
        setupPhaseHandlers();
        setupPaymentHandlers();
        setupReportHandlers();
    }
    
    private void setupNavigationHandlers() {
        dashboardBtn.setOnAction(e -> showDashboard());
        reportsBtn.setOnAction(e -> showReports());
        employeeManagementBtn.setOnAction(e -> showEmployeeManagement());
        projectManagementBtn.setOnAction(e -> showProjectManagement());
        clientManagementBtn.setOnAction(e -> showClientManagement());
        supplierManagementBtn.setOnAction(e -> showSupplierManagement());
        materialManagementBtn.setOnAction(e -> showMaterialManagement());
        branchManagementBtn.setOnAction(e -> showBranchManagement());
        roleManagementBtn.setOnAction(e -> showRoleManagement());
        departmentManagementBtn.setOnAction(e -> showDepartmentManagement());
        phaseManagementBtn.setOnAction(e -> showPhaseManagement());
        paymentManagementBtn.setOnAction(e -> showPaymentManagement());
    }
    
    private void setupEmployeeHandlers() {
        saveEmployeeBtn.setOnAction(e -> saveEmployee());
        updateEmployeeBtn.setOnAction(e -> updateEmployee());
        deleteEmployeeBtn.setOnAction(e -> deleteEmployee());
        refreshEmployeesBtn.setOnAction(e -> loadEmployees());
        addEmployeeBtn.setOnAction(e -> clearEmployeeFields());
    }
    
    private void setupProjectHandlers() {
        saveProjectBtn.setOnAction(e -> saveProject());
        updateProjectBtn.setOnAction(e -> updateProject());
        deleteProjectBtn.setOnAction(e -> deleteProject());
        refreshProjectsBtn.setOnAction(e -> loadProjects());
        addProjectBtn.setOnAction(e -> clearProjectFields());
    }
    
    private void setupClientHandlers() {
        saveClientBtn.setOnAction(e -> saveClient());
        updateClientBtn.setOnAction(e -> updateClient());
        deleteClientBtn.setOnAction(e -> deleteClient());
        refreshClientsBtn.setOnAction(e -> loadClients());
        addClientBtn.setOnAction(e -> clearClientFields());
    }
    
    private void setupMaterialHandlers() {
        saveMaterialBtn.setOnAction(e -> saveMaterial());
        updateMaterialBtn.setOnAction(e -> updateMaterial());
        deleteMaterialBtn.setOnAction(e -> deleteMaterial());
        refreshMaterialsBtn.setOnAction(e -> loadMaterials());
        addMaterialBtn.setOnAction(e -> clearMaterialFields());
    }
    
    private void setupSupplierHandlers() {
        saveSupplierBtn.setOnAction(e -> saveSupplier());
        updateSupplierBtn.setOnAction(e -> updateSupplier());
        deleteSupplierBtn.setOnAction(e -> deleteSupplier());
        refreshSuppliersBtn.setOnAction(e -> loadSuppliers());
        addSupplierBtn.setOnAction(e -> clearSupplierFields());
    }
    
    private void setupBranchHandlers() {
        saveBranchBtn.setOnAction(e -> saveBranch());
        updateBranchBtn.setOnAction(e -> updateBranch());
        deleteBranchBtn.setOnAction(e -> deleteBranch());
        refreshBranchesBtn.setOnAction(e -> loadBranches());
        addBranchBtn.setOnAction(e -> clearBranchFields());
    }
    
    private void setupRoleHandlers() {
        saveRoleBtn.setOnAction(e -> saveRole());
        updateRoleBtn.setOnAction(e -> updateRole());
        deleteRoleBtn.setOnAction(e -> deleteRole());
        refreshRolesBtn.setOnAction(e -> loadRoles());
        addRoleBtn.setOnAction(e -> clearRoleFields());
    }
    
    private void setupDepartmentHandlers() {
        saveDepartmentBtn.setOnAction(e -> saveDepartment());
        updateDepartmentBtn.setOnAction(e -> updateDepartment());
        deleteDepartmentBtn.setOnAction(e -> deleteDepartment());
        refreshDepartmentsBtn.setOnAction(e -> loadDepartments());
        addDepartmentBtn.setOnAction(e -> clearDepartmentFields());
    }
    
    private void setupPhaseHandlers() {
        savePhaseBtn.setOnAction(e -> savePhase());
        updatePhaseBtn.setOnAction(e -> updatePhase());
        deletePhaseBtn.setOnAction(e -> deletePhase());
        refreshPhasesBtn.setOnAction(e -> loadPhases());
        addPhaseBtn.setOnAction(e -> clearPhaseFields());
    }
    
    private void setupPaymentHandlers() {
        savePaymentBtn.setOnAction(e -> savePayment());
        updatePaymentBtn.setOnAction(e -> updatePayment());
        deletePaymentBtn.setOnAction(e -> deletePayment());
        refreshPaymentsBtn.setOnAction(e -> loadPayments());
        addPaymentBtn.setOnAction(e -> clearPaymentFields());
    }
    
    private void setupReportHandlers() {
        generateSalesReportBtn.setOnAction(e -> generateSalesReport());
        generateProfitabilityReportBtn.setOnAction(e -> generateProfitabilityReport());
        generateWorkloadReportBtn.setOnAction(e -> generateWorkloadReport());
        generateMaterialReportBtn.setOnAction(e -> generateMaterialReport());
        generateBranchReportBtn.setOnAction(e -> generateBranchReport());
        generateSupplierReportBtn.setOnAction(e -> generateSupplierReport());
        generateTimelineReportBtn.setOnAction(e -> generateTimelineReport());
        
        // Set default dates
        startDatePicker.setValue(LocalDate.now().minusMonths(1));
        endDatePicker.setValue(LocalDate.now());
    }
    
    private void setupTimeDisplay() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                LocalDateTime now = LocalDateTime.now();
                currentTimeLabel.setText(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    // Navigation Methods
    private void showView(VBox view) {
        if (currentView != null) {
            currentView.setVisible(false);
        }
        view.setVisible(true);
        currentView = view;
        resetNavigationButtons();
    }
    
    private void resetNavigationButtons() {
        String defaultStyle = "-fx-background-color: #34495e; -fx-text-fill: white; -fx-font-size: 13px; -fx-padding: 12; -fx-background-radius: 6;";
        String activeStyle = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 13px; -fx-padding: 12; -fx-background-radius: 6;";
        
        dashboardBtn.setStyle(currentView == dashboardView ? activeStyle : defaultStyle);
        reportsBtn.setStyle(currentView == reportsView ? activeStyle : defaultStyle);
        employeeManagementBtn.setStyle(currentView == employeeManagementView ? activeStyle : defaultStyle);
        projectManagementBtn.setStyle(currentView == projectManagementView ? activeStyle : defaultStyle);
        clientManagementBtn.setStyle(currentView == clientManagementView ? activeStyle : defaultStyle);
        supplierManagementBtn.setStyle(currentView == supplierManagementView ? activeStyle : defaultStyle);
        materialManagementBtn.setStyle(currentView == materialManagementView ? activeStyle : defaultStyle);
        branchManagementBtn.setStyle(currentView == branchManagementView ? activeStyle : defaultStyle);
        roleManagementBtn.setStyle(currentView == roleManagementView ? activeStyle : defaultStyle);
        departmentManagementBtn.setStyle(currentView == departmentManagementView ? activeStyle : defaultStyle);
        phaseManagementBtn.setStyle(currentView == phaseManagementView ? activeStyle : defaultStyle);
        paymentManagementBtn.setStyle(currentView == paymentManagementView ? activeStyle : defaultStyle);
    }
    
    private void showDashboard() {
        showView(dashboardView);
        updateDashboardStats();
        statusLabel.setText("Dashboard - Overview");
    }
    
    private void showReports() {
        showView(reportsView);
        statusLabel.setText("Reports & Analytics");
    }
    
    private void showEmployeeManagement() {
        showView(employeeManagementView);
        loadEmployees();
        loadEmployeeComboBoxData();
        statusLabel.setText("Employee Management");
    }
    
    private void showProjectManagement() {
        showView(projectManagementView);
        loadProjects();
        loadProjectComboBoxData();
        statusLabel.setText("Project Management");
    }
    
    private void showClientManagement() {
        showView(clientManagementView);
        loadClients();
        statusLabel.setText("Client Management");
    }
    
    private void showMaterialManagement() {
        showView(materialManagementView);
        loadMaterials();
        statusLabel.setText("Material Management");
    }
    
    private void showSupplierManagement() {
        showView(supplierManagementView);
        loadSuppliers();
        statusLabel.setText("Supplier Management");
    }
    
    private void showBranchManagement() {
        showView(branchManagementView);
        loadBranches();
        statusLabel.setText("Branch Management");
    }
    
    private void showRoleManagement() {
        showView(roleManagementView);
        loadRoles();
        statusLabel.setText("Role Management");
    }
    
    private void showDepartmentManagement() {
        showView(departmentManagementView);
        loadDepartments();
        statusLabel.setText("Department Management");
    }
    
    private void showPhaseManagement() {
        showView(phaseManagementView);
        loadPhases();
        loadPhaseComboBoxData();
        statusLabel.setText("Phase Management");
    }
    
    private void showPaymentManagement() {
        showView(paymentManagementView);
        loadPayments();
        loadPaymentComboBoxData();
        statusLabel.setText("Payment Management");
    }
    
    // Data Loading Methods
    private void loadAllData() {
        loadEmployees();
        loadProjects();
        loadClients();
        loadMaterials();
        loadSuppliers();
        loadBranches();
        loadRoles();
        loadDepartments();
        loadPhases();
        loadPayments();
        loadComboBoxData();
    }
    
    private void loadEmployees() {
        try {
            employeeList.clear();
            List<Employee> employees = employeeDAO.getAllEmployees();
            employeeList.addAll(employees);
            statusLabel.setText("Employees loaded: " + employees.size());
        } catch (Exception e) {
            showAlert("Error", "Failed to load employees: " + e.getMessage());
        }
    }
    
    private void loadProjects() {
        try {
            projectList.clear();
            List<Project> projects = projectDAO.getAllProjects();
            projectList.addAll(projects);
            statusLabel.setText("Projects loaded: " + projects.size());
        } catch (Exception e) {
            showAlert("Error", "Failed to load projects: " + e.getMessage());
        }
    }
    
    private void loadClients() {
        try {
            clientList.clear();
            List<Client> clients = clientDAO.getAllClients();
            clientList.addAll(clients);
            statusLabel.setText("Clients loaded: " + clients.size());
        } catch (Exception e) {
            showAlert("Error", "Failed to load clients: " + e.getMessage());
        }
    }
    
    private void loadMaterials() {
        try {
            materialList.clear();
            List<Material> materials = materialDAO.getAllMaterials();
            materialList.addAll(materials);
            statusLabel.setText("Materials loaded: " + materials.size());
        } catch (Exception e) {
            showAlert("Error", "Failed to load materials: " + e.getMessage());
        }
    }
    
    private void loadSuppliers() {
        try {
            supplierList.clear();
            List<Supplier> suppliers = supplierDAO.getAllSuppliers();
            supplierList.addAll(suppliers);
            statusLabel.setText("Suppliers loaded: " + suppliers.size());
        } catch (Exception e) {
            showAlert("Error", "Failed to load suppliers: " + e.getMessage());
        }
    }
    
    private void loadBranches() {
        try {
            branchList.clear();
            List<Branch> branches = branchDAO.getAllBranches();
            branchList.addAll(branches);
            statusLabel.setText("Branches loaded: " + branches.size());
        } catch (Exception e) {
            showAlert("Error", "Failed to load branches: " + e.getMessage());
        }
    }
    
    private void loadRoles() {
        try {
            roleList.clear();
            List<Role> roles = roleDAO.getAllRoles();
            roleList.addAll(roles);
            statusLabel.setText("Roles loaded: " + roles.size());
        } catch (Exception e) {
            showAlert("Error", "Failed to load roles: " + e.getMessage());
        }
    }
    
    private void loadDepartments() {
        try {
            departmentList.clear();
            List<Department> departments = departmentDAO.getAllDepartments();
            departmentList.addAll(departments);
            statusLabel.setText("Departments loaded: " + departments.size());
        } catch (Exception e) {
            showAlert("Error", "Failed to load departments: " + e.getMessage());
        }
    }
    
    private void loadPhases() {
        try {
            phaseList.clear();
            List<Phase> phases = phaseDAO.getAllPhases();
            phaseList.addAll(phases);
            statusLabel.setText("Phases loaded: " + phases.size());
        } catch (Exception e) {
            showAlert("Error", "Failed to load phases: " + e.getMessage());
        }
    }
    
    private void loadPayments() {
        try {
            paymentList.clear();
            List<Payment> payments = paymentDAO.getAllPayments();
            paymentList.addAll(payments);
            statusLabel.setText("Payments loaded: " + payments.size());
        } catch (Exception e) {
            showAlert("Error", "Failed to load payments: " + e.getMessage());
        }
    }
    
    // ComboBox Data Loading
    private void loadComboBoxData() {
        loadEmployeeComboBoxData();
        loadProjectComboBoxData();
        loadPhaseComboBoxData();
        loadPaymentComboBoxData();
    }
    
    private void loadEmployeeComboBoxData() {
        // Load positions
        List<Role> roles = roleDAO.getAllRoles();
        positionComboBox.setItems(FXCollections.observableArrayList(roles));
        
        // Load branches
        List<Branch> branches = branchDAO.getAllBranches();
        empBranchComboBox.setItems(FXCollections.observableArrayList(branches));
        
        // Load departments
        List<Department> departments = departmentDAO.getAllDepartments();
        empDepartmentComboBox.setItems(FXCollections.observableArrayList(departments));
        
        // Load managers (employees who are managers)
        List<Employee> managers = employeeDAO.getAllEmployees();
        managers.removeIf(emp -> !emp.isManager());
        managerComboBox.setItems(FXCollections.observableArrayList(managers));
    }
    
    private void loadProjectComboBoxData() {
        // Load branches
        List<Branch> branches = branchDAO.getAllBranches();
        projectBranchComboBox.setItems(FXCollections.observableArrayList(branches));
        
        // Load clients
        List<Client> clients = clientDAO.getAllClients();
        projectClientComboBox.setItems(FXCollections.observableArrayList(clients));
    }
    
    private void loadPhaseComboBoxData() {
        // Load projects
        List<Project> projects = projectDAO.getAllProjects();
        phaseProjectComboBox.setItems(FXCollections.observableArrayList(projects));
    }
    
    private void loadPaymentComboBoxData() {
        // Load clients
        List<Client> clients = clientDAO.getAllClients();
        paymentClientComboBox.setItems(FXCollections.observableArrayList(clients));
        
        // Load suppliers
        List<Supplier> suppliers = supplierDAO.getAllSuppliers();
        paymentSupplierComboBox.setItems(FXCollections.observableArrayList(suppliers));
    }