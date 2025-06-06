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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ComprehensiveProjectManagementController implements Initializable {

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
    @FXML private Button saveEmployeeBtn;
    @FXML private Button updateEmployeeBtn;
    @FXML private Button deleteEmployeeBtn;
    @FXML private Button refreshEmployeesBtn;
    @FXML private Button clearEmployeeFieldsBtn;
    @FXML private Button sortEmployeeAscBtn;
    @FXML private Button sortEmployeeDescBtn;
    @FXML private Button countEmployeesBtn;
    @FXML private Button maxEmployeeSalaryBtn;
    @FXML private Button minEmployeeSalaryBtn;
    @FXML private Button findEmployeeBtn;
    @FXML private TextField findEmployeeField;

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
    @FXML private Button saveProjectBtn;
    @FXML private Button updateProjectBtn;
    @FXML private Button deleteProjectBtn;
    @FXML private Button refreshProjectsBtn;
    @FXML private Button clearProjectFieldsBtn;
    @FXML private Button sortProjectAscBtn;
    @FXML private Button sortProjectDescBtn;
    @FXML private Button countProjectsBtn;
    @FXML private Button maxProjectRevenueBtn;
    @FXML private Button minProjectCostBtn;
    @FXML private Button findProjectBtn;
    @FXML private TextField findProjectField;

    // Client Management
    @FXML private TextField clientIdField;
    @FXML private TextField clientNameField;
    @FXML private TableView<Client> clientTable;
    @FXML private TableColumn<Client, Integer> clientIdColumn;
    @FXML private TableColumn<Client, String> clientNameColumn;
    @FXML private Button saveClientBtn;
    @FXML private Button updateClientBtn;
    @FXML private Button deleteClientBtn;
    @FXML private Button refreshClientsBtn;
    @FXML private Button clearClientFieldsBtn;
    @FXML private Button sortClientAscBtn;
    @FXML private Button sortClientDescBtn;
    @FXML private Button countClientsBtn;
    @FXML private Button findClientBtn;
    @FXML private TextField findClientField;

    // Material Management
    @FXML private TextField materialIdField;
    @FXML private TextField materialNameField;
    @FXML private TextField materialPriceField;
    @FXML private TableView<Material> materialTable;
    @FXML private TableColumn<Material, Integer> materialIdColumn;
    @FXML private TableColumn<Material, String> materialNameColumn;
    @FXML private TableColumn<Material, Double> materialPriceColumn;
    @FXML private Button saveMaterialBtn;
    @FXML private Button updateMaterialBtn;
    @FXML private Button deleteMaterialBtn;
    @FXML private Button refreshMaterialsBtn;
    @FXML private Button clearMaterialFieldsBtn;
    @FXML private Button sortMaterialAscBtn;
    @FXML private Button sortMaterialDescBtn;
    @FXML private Button countMaterialsBtn;
    @FXML private Button maxMaterialPriceBtn;
    @FXML private Button minMaterialPriceBtn;
    @FXML private Button findMaterialBtn;
    @FXML private TextField findMaterialField;

    // Supplier Management
    @FXML private TextField supplierIdField;
    @FXML private TextField supplierNameField;
    @FXML private TextField supplierLocationField;
    @FXML private TableView<Supplier> supplierTable;
    @FXML private TableColumn<Supplier, Integer> supplierIdColumn;
    @FXML private TableColumn<Supplier, String> supplierNameColumn;
    @FXML private TableColumn<Supplier, String> supplierLocationColumn;
    @FXML private Button saveSupplierBtn;
    @FXML private Button updateSupplierBtn;
    @FXML private Button deleteSupplierBtn;
    @FXML private Button refreshSuppliersBtn;
    @FXML private Button clearSupplierFieldsBtn;
    @FXML private Button sortSupplierAscBtn;
    @FXML private Button sortSupplierDescBtn;
    @FXML private Button countSuppliersBtn;
    @FXML private Button findSupplierBtn;
    @FXML private TextField findSupplierField;

    // Branch Management
    @FXML private TextField branchIdField;
    @FXML private TextField branchLocationField;
    @FXML private TableView<Branch> branchTable;
    @FXML private TableColumn<Branch, Integer> branchIdColumn;
    @FXML private TableColumn<Branch, String> branchLocationColumn;
    @FXML private Button saveBranchBtn;
    @FXML private Button updateBranchBtn;
    @FXML private Button deleteBranchBtn;
    @FXML private Button refreshBranchesBtn;
    @FXML private Button clearBranchFieldsBtn;
    @FXML private Button sortBranchAscBtn;
    @FXML private Button sortBranchDescBtn;
    @FXML private Button countBranchesBtn;
    @FXML private Button findBranchBtn;
    @FXML private TextField findBranchField;

    // Role Management
    @FXML private TextField roleIdField;
    @FXML private TextField roleTitleField;
    @FXML private TableView<Role> roleTable;
    @FXML private TableColumn<Role, Integer> roleIdColumn;
    @FXML private TableColumn<Role, String> roleTitleColumn;
    @FXML private Button saveRoleBtn;
    @FXML private Button updateRoleBtn;
    @FXML private Button deleteRoleBtn;
    @FXML private Button refreshRolesBtn;
    @FXML private Button clearRoleFieldsBtn;
    @FXML private Button sortRoleAscBtn;
    @FXML private Button sortRoleDescBtn;
    @FXML private Button countRolesBtn;
    @FXML private Button findRoleBtn;
    @FXML private TextField findRoleField;

    // Department Management
    @FXML private TextField departmentIdField;
    @FXML private TextField departmentNameField;
    @FXML private TableView<Department> departmentTable;
    @FXML private TableColumn<Department, Integer> departmentIdColumn;
    @FXML private TableColumn<Department, String> departmentNameColumn;
    @FXML private Button saveDepartmentBtn;
    @FXML private Button updateDepartmentBtn;
    @FXML private Button deleteDepartmentBtn;
    @FXML private Button refreshDepartmentsBtn;
    @FXML private Button clearDepartmentFieldsBtn;
    @FXML private Button sortDepartmentAscBtn;
    @FXML private Button sortDepartmentDescBtn;
    @FXML private Button countDepartmentsBtn;
    @FXML private Button findDepartmentBtn;
    @FXML private TextField findDepartmentField;

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
    @FXML private Button savePhaseBtn;
    @FXML private Button updatePhaseBtn;
    @FXML private Button deletePhaseBtn;
    @FXML private Button refreshPhasesBtn;
    @FXML private Button clearPhaseFieldsBtn;
    @FXML private Button sortPhaseAscBtn;
    @FXML private Button sortPhaseDescBtn;
    @FXML private Button countPhasesBtn;
    @FXML private Button findPhaseBtn;
    @FXML private TextField findPhaseField;

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
    @FXML private Button savePaymentBtn;
    @FXML private Button updatePaymentBtn;
    @FXML private Button deletePaymentBtn;
    @FXML private Button refreshPaymentsBtn;
    @FXML private Button clearPaymentFieldsBtn;
    @FXML private Button sortPaymentAscBtn;
    @FXML private Button sortPaymentDescBtn;
    @FXML private Button countPaymentsBtn;
    @FXML private Button maxPaymentAmountBtn;
    @FXML private Button minPaymentAmountBtn;
    @FXML private Button findPaymentBtn;
    @FXML private TextField findPaymentField;

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
        empIdColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("empId"));
        empNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("empName"));
        empPositionColumn.setCellValueFactory(cellData -> {
            Employee emp = cellData.getValue();
            Role role = roleDAO.getRoleById(emp.getPositionId());
            return new javafx.beans.property.SimpleStringProperty(role != null ? role.getTitle() : "Unknown");
        });
        empSalaryColumn.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
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
        employeeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateEmployeeFields(newValue);
            }
        });
    }

    private void setupProjectTable() {
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<Project, Integer>("projectId"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<Project, String>("pname"));
        projectLocationColumn.setCellValueFactory(new PropertyValueFactory<Project, String>("location"));
        projectCostColumn.setCellValueFactory(new PropertyValueFactory<Project, Double>("cost"));
        projectRevenueColumn.setCellValueFactory(new PropertyValueFactory<Project, Double>("revenue"));
        projectBranchColumn.setCellValueFactory(cellData -> {
            Project project = cellData.getValue();
            Branch branch = branchDAO.getBranchById(project.getBranchId());
            return new javafx.beans.property.SimpleStringProperty(branch != null ? branch.getLocation() : "Unknown");
        });

        projectTable.setItems(projectList);
        projectTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateProjectFields(newValue);
            }
        });
    }

    private void setupClientTable() {
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("clientId"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));

        clientTable.setItems(clientList);
        clientTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateClientFields(newValue);
            }
        });
    }

    private void setupMaterialTable() {
        materialIdColumn.setCellValueFactory(new PropertyValueFactory<Material, Integer>("materialId"));
        materialNameColumn.setCellValueFactory(new PropertyValueFactory<Material, String>("mname"));
        materialPriceColumn.setCellValueFactory(new PropertyValueFactory<Material, Double>("price"));

        materialTable.setItems(materialList);
        materialTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateMaterialFields(newValue);
            }
        });
    }

    private void setupSupplierTable() {
        supplierIdColumn.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("suppId"));
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("suppName"));
        supplierLocationColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("location"));

        supplierTable.setItems(supplierList);
        supplierTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateSupplierFields(newValue);
            }
        });
    }

    private void setupBranchTable() {
        branchIdColumn.setCellValueFactory(new PropertyValueFactory<Branch, Integer>("branchId"));
        branchLocationColumn.setCellValueFactory(new PropertyValueFactory<Branch, String>("location"));

        branchTable.setItems(branchList);
        branchTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateBranchFields(newValue);
            }
        });
    }

    private void setupRoleTable() {
        roleIdColumn.setCellValueFactory(new PropertyValueFactory<Role, Integer>("roleId"));
        roleTitleColumn.setCellValueFactory(new PropertyValueFactory<Role, String>("title"));

        roleTable.setItems(roleList);
        roleTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateRoleFields(newValue);
            }
        });
    }

    private void setupDepartmentTable() {
        departmentIdColumn.setCellValueFactory(new PropertyValueFactory<Department, Integer>("deptId"));
        departmentNameColumn.setCellValueFactory(new PropertyValueFactory<Department, String>("deptName"));

        departmentTable.setItems(departmentList);
        departmentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateDepartmentFields(newValue);
            }
        });
    }

    private void setupPhaseTable() {
        phaseIdColumn.setCellValueFactory(new PropertyValueFactory<Phase, Integer>("phaseId"));
        phaseProjectColumn.setCellValueFactory(cellData -> {
            Phase phase = cellData.getValue();
            Project project = projectDAO.getProjectById(phase.getProjectId());
            return new javafx.beans.property.SimpleStringProperty(project != null ? project.getPname() : "Unknown");
        });
        phaseNameColumn.setCellValueFactory(new PropertyValueFactory<Phase, String>("name"));
        phaseStartDateColumn.setCellValueFactory(new PropertyValueFactory<Phase, Date>("startDate"));
        phaseEndDateColumn.setCellValueFactory(new PropertyValueFactory<Phase, Date>("endDate"));
        phaseStatusColumn.setCellValueFactory(new PropertyValueFactory<Phase, String>("status"));

        phaseTable.setItems(phaseList);
        phaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populatePhaseFields(newValue);
            }
        });
    }

    private void setupPaymentTable() {
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("paymentId"));
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
        paymentAmountColumn.setCellValueFactory(new PropertyValueFactory<Payment, Double>("amount"));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<Payment, Date>("pdate"));
        paymentMethodColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("pmethod"));

        paymentTable.setItems(paymentList);
        paymentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populatePaymentFields(newValue);
            }
        });
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
        phaseStatusComboBox.setItems(FXCollections.observableArrayList("Planning", "In Progress", "Completed", "On Hold", "Cancelled"));
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
        paymentMethodComboBox.setItems(FXCollections.observableArrayList("Cash", "Credit Card", "Bank Transfer", "Check", "Online Payment"));
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
        clearEmployeeFieldsBtn.setOnAction(e -> clearEmployeeFields());
        sortEmployeeAscBtn.setOnAction(e -> sortEmployeesAsc());
        sortEmployeeDescBtn.setOnAction(e -> sortEmployeesDesc());
        countEmployeesBtn.setOnAction(e -> countEmployees());
        maxEmployeeSalaryBtn.setOnAction(e -> maxEmployeeSalary());
        minEmployeeSalaryBtn.setOnAction(e -> minEmployeeSalary());
        findEmployeeBtn.setOnAction(e -> findEmployee());
    }

    private void setupProjectHandlers() {
        saveProjectBtn.setOnAction(e -> saveProject());
        updateProjectBtn.setOnAction(e -> updateProject());
        deleteProjectBtn.setOnAction(e -> deleteProject());
        refreshProjectsBtn.setOnAction(e -> loadProjects());
        clearProjectFieldsBtn.setOnAction(e -> clearProjectFields());
        sortProjectAscBtn.setOnAction(e -> sortProjectsAsc());
        sortProjectDescBtn.setOnAction(e -> sortProjectsDesc());
        countProjectsBtn.setOnAction(e -> countProjects());
        maxProjectRevenueBtn.setOnAction(e -> maxProjectRevenue());
        minProjectCostBtn.setOnAction(e -> minProjectCost());
        findProjectBtn.setOnAction(e -> findProject());
    }

    private void setupClientHandlers() {
        saveClientBtn.setOnAction(e -> saveClient());
        updateClientBtn.setOnAction(e -> updateClient());
        deleteClientBtn.setOnAction(e -> deleteClient());
        refreshClientsBtn.setOnAction(e -> loadClients());
        clearClientFieldsBtn.setOnAction(e -> clearClientFields());
        sortClientAscBtn.setOnAction(e -> sortClientsAsc());
        sortClientDescBtn.setOnAction(e -> sortClientsDesc());
        countClientsBtn.setOnAction(e -> countClients());
        findClientBtn.setOnAction(e -> findClient());
    }

    private void setupMaterialHandlers() {
        saveMaterialBtn.setOnAction(e -> saveMaterial());
        updateMaterialBtn.setOnAction(e -> updateMaterial());
        deleteMaterialBtn.setOnAction(e -> deleteMaterial());
        refreshMaterialsBtn.setOnAction(e -> loadMaterials());
        clearMaterialFieldsBtn.setOnAction(e -> clearMaterialFields());
        sortMaterialAscBtn.setOnAction(e -> sortMaterialsAsc());
        sortMaterialDescBtn.setOnAction(e -> sortMaterialsDesc());
        countMaterialsBtn.setOnAction(e -> countMaterials());
        maxMaterialPriceBtn.setOnAction(e -> maxMaterialPrice());
        minMaterialPriceBtn.setOnAction(e -> minMaterialPrice());
        findMaterialBtn.setOnAction(e -> findMaterial());
    }

    private void setupSupplierHandlers() {
        saveSupplierBtn.setOnAction(e -> saveSupplier());
        updateSupplierBtn.setOnAction(e -> updateSupplier());
        deleteSupplierBtn.setOnAction(e -> deleteSupplier());
        refreshSuppliersBtn.setOnAction(e -> loadSuppliers());
        clearSupplierFieldsBtn.setOnAction(e -> clearSupplierFields());
        sortSupplierAscBtn.setOnAction(e -> sortSuppliersAsc());
        sortSupplierDescBtn.setOnAction(e -> sortSuppliersDesc());
        countSuppliersBtn.setOnAction(e -> countSuppliers());
        findSupplierBtn.setOnAction(e -> findSupplier());
    }

    private void setupBranchHandlers() {
        saveBranchBtn.setOnAction(e -> saveBranch());
        updateBranchBtn.setOnAction(e -> updateBranch());
        deleteBranchBtn.setOnAction(e -> deleteBranch());
        refreshBranchesBtn.setOnAction(e -> loadBranches());
        clearBranchFieldsBtn.setOnAction(e -> clearBranchFields());
        sortBranchAscBtn.setOnAction(e -> sortBranchesAsc());
        sortBranchDescBtn.setOnAction(e -> sortBranchesDesc());
        countBranchesBtn.setOnAction(e -> countBranches());
        findBranchBtn.setOnAction(e -> findBranch());
    }

    private void setupRoleHandlers() {
        saveRoleBtn.setOnAction(e -> saveRole());
        updateRoleBtn.setOnAction(e -> updateRole());
        deleteRoleBtn.setOnAction(e -> deleteRole());
        refreshRolesBtn.setOnAction(e -> loadRoles());
        clearRoleFieldsBtn.setOnAction(e -> clearRoleFields());
        sortRoleAscBtn.setOnAction(e -> sortRolesAsc());
        sortRoleDescBtn.setOnAction(e -> sortRolesDesc());
        countRolesBtn.setOnAction(e -> countRoles());
        findRoleBtn.setOnAction(e -> findRole());
    }

    private void setupDepartmentHandlers() {
        saveDepartmentBtn.setOnAction(e -> saveDepartment());
        updateDepartmentBtn.setOnAction(e -> updateDepartment());
        deleteDepartmentBtn.setOnAction(e -> deleteDepartment());
        refreshDepartmentsBtn.setOnAction(e -> loadDepartments());
        clearDepartmentFieldsBtn.setOnAction(e -> clearDepartmentFields());
        sortDepartmentAscBtn.setOnAction(e -> sortDepartmentsAsc());
        sortDepartmentDescBtn.setOnAction(e -> sortDepartmentsDesc());
        countDepartmentsBtn.setOnAction(e -> countDepartments());
        findDepartmentBtn.setOnAction(e -> findDepartment());
    }

    private void setupPhaseHandlers() {
        savePhaseBtn.setOnAction(e -> savePhase());
        updatePhaseBtn.setOnAction(e -> updatePhase());
        deletePhaseBtn.setOnAction(e -> deletePhase());
        refreshPhasesBtn.setOnAction(e -> loadPhases());
        clearPhaseFieldsBtn.setOnAction(e -> clearPhaseFields());
        sortPhaseAscBtn.setOnAction(e -> sortPhasesAsc());
        sortPhaseDescBtn.setOnAction(e -> sortPhasesDesc());
        countPhasesBtn.setOnAction(e -> countPhases());
        findPhaseBtn.setOnAction(e -> findPhase());
    }

    private void setupPaymentHandlers() {
        savePaymentBtn.setOnAction(e -> savePayment());
        updatePaymentBtn.setOnAction(e -> updatePayment());
        deletePaymentBtn.setOnAction(e -> deletePayment());
        refreshPaymentsBtn.setOnAction(e -> loadPayments());
        clearPaymentFieldsBtn.setOnAction(e -> clearPaymentFields());
        sortPaymentAscBtn.setOnAction(e -> sortPaymentsAsc());
        sortPaymentDescBtn.setOnAction(e -> sortPaymentsDesc());
        countPaymentsBtn.setOnAction(e -> countPayments());
        maxPaymentAmountBtn.setOnAction(e -> maxPaymentAmount());
        minPaymentAmountBtn.setOnAction(e -> minPaymentAmount());
        findPaymentBtn.setOnAction(e -> findPayment());
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
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            LocalDateTime now = LocalDateTime.now();
            currentTimeLabel.setText(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }));
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
        List<Role> roles = roleDAO.getAllRoles();
        positionComboBox.setItems(FXCollections.observableArrayList(roles));

        List<Branch> branches = branchDAO.getAllBranches();
        empBranchComboBox.setItems(FXCollections.observableArrayList(branches));

        List<Department> departments = departmentDAO.getAllDepartments();
        empDepartmentComboBox.setItems(FXCollections.observableArrayList(departments));

        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        List<Employee> managers = new ArrayList<Employee>();
        for (Employee emp : allEmployees) {
            if (emp.isManager()) {
                managers.add(emp);
            }
        }
        managerComboBox.setItems(FXCollections.observableArrayList(managers));
    }

    private void loadProjectComboBoxData() {
        List<Branch> branches = branchDAO.getAllBranches();
        projectBranchComboBox.setItems(FXCollections.observableArrayList(branches));

        List<Client> clients = clientDAO.getAllClients();
        projectClientComboBox.setItems(FXCollections.observableArrayList(clients));
    }

    private void loadPhaseComboBoxData() {
        List<Project> projects = projectDAO.getAllProjects();
        phaseProjectComboBox.setItems(FXCollections.observableArrayList(projects));
    }

    private void loadPaymentComboBoxData() {
        List<Client> clients = clientDAO.getAllClients();
        paymentClientComboBox.setItems(FXCollections.observableArrayList(clients));

        List<Supplier> suppliers = supplierDAO.getAllSuppliers();
        paymentSupplierComboBox.setItems(FXCollections.observableArrayList(suppliers));
    }

    // Dashboard Statistics Update
    private void updateDashboardStats() {
        try {
            totalProjectsLabel.setText(String.valueOf(projectDAO.getAllProjects().size()));
            totalEmployeesLabel.setText(String.valueOf(employeeDAO.getAllEmployees().size()));
            totalClientsLabel.setText(String.valueOf(clientDAO.getAllClients().size()));

            // Calculate total revenue
            double totalRevenue = 0.0;
            List<Project> projects = projectDAO.getAllProjects();
            for (Project project : projects) {
                totalRevenue += project.getRevenue();
            }
            totalRevenueLabel.setText(String.format("$%.2f", totalRevenue));
        } catch (Exception e) {
            System.err.println("Error updating dashboard stats: " + e.getMessage());
        }
    }

    // Employee CRUD Operations
    private void saveEmployee() {
        try {
            Employee employee = createEmployeeFromFields();
            if (employee != null && employeeDAO.addEmployee(employee)) {
                showAlert("Success", "Employee added successfully!");
                loadEmployees();
                loadEmployeeComboBoxData();
                clearEmployeeFields();
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
                showAlert("Success", "Employee updated successfully!");// Client CRUD Operations
                private void saveClient() {
                    try {
                        Client client = createClientFromFields();
                        if (client != null && clientDAO.addClient(client)) {
                            showAlert("Success", "Client added successfully!");
                            loadClients();
                            clearClientFields();
                        } else {
                            showAlert("Error", "Failed to add client!");
                        }
                    } catch (Exception e) {
                        showAlert("Error", "Error adding client: " + e.getMessage());
                    }
                }

                private void updateClient() {
                    Client selectedClient = clientTable.getSelectionModel().getSelectedItem();
                    if (selectedClient == null) {
                        showAlert("Error", "Please select a client to update!");
                        return;
                    }

                    try {
                        Client client = createClientFromFields();
                        if (client != null && clientDAO.updateClient(client)) {
                            showAlert("Success", "Client updated successfully!");
                            loadClients();
                            clearClientFields();
                        } else {
                            showAlert("Error", "Failed to update client!");
                        }
                    } catch (Exception e) {
                        showAlert("Error", "Error updating client: " + e.getMessage());
                    }
                }

                private void deleteClient() {
                    Client selectedClient = clientTable.getSelectionModel().getSelectedItem();
                    if (selectedClient == null) {
                        showAlert("Error", "Please select a client to delete!");
                        return;
                    }

                    Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmAlert.setTitle("Confirm Deletion");
                    confirmAlert.setHeaderText("Delete Client");
                    confirmAlert.setContentText("Are you sure you want to delete this client?");

                    if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                        if (clientDAO.deleteClient(selectedClient.getClientId())) {
                            showAlert("Success", "Client deleted successfully!");
                            loadClients();
                            clearClientFields();
                        } else {
                            showAlert("Error", "Failed to delete client!");
                        }
                    }
                }

                private Client createClientFromFields() {
                    try {
                        int clientId = Integer.parseInt(clientIdField.getText());
                        String clientName = clientNameField.getText();

                        if (clientName.trim().isEmpty()) {
                            showAlert("Error", "Client name cannot be empty!");
                            return null;
                        }

                        return new Client(clientId, clientName);

                    } catch (NumberFormatException e) {
                        showAlert("Error", "Please enter a valid client ID!");
                        return null;
                    }
                }

                private void populateClientFields(Client client) {
                    clientIdField.setText(String.valueOf(client.getClientId()));
                    clientNameField.setText(client.getClientName());
                }

                private void clearClientFields() {
                    clientIdField.clear();
                    clientNameField.clear();
                    clientTable.getSelectionModel().clearSelection();
                }

                // Client Utility Methods
                private void sortClientsAsc() {
                    List<Client> sortedList = new ArrayList<Client>(clientList);
                    Collections.sort(sortedList, new Comparator<Client>() {
                        @Override
                        public int compare(Client c1, Client c2) {
                            return c1.getClientName().compareToIgnoreCase(c2.getClientName());
                        }
                    });
                    clientList.setAll(sortedList);
                    statusLabel.setText("Clients sorted A-Z");
                }

                private void sortClientsDesc() {
                    List<Client> sortedList = new ArrayList<Client>(clientList);
                    Collections.sort(sortedList, new Comparator<Client>() {
                        @Override
                        public int compare(Client c1, Client c2) {
                            return c2.getClientName().compareToIgnoreCase(c1.getClientName());
                        }
                    });
                    clientList.setAll(sortedList);
                    statusLabel.setText("Clients sorted Z-A");
                }

                private void countClients() {
                    int count = clientList.size();
                    showAlert("Client Count", "Total number of clients: " + count);
                }

                private void findClient() {
                    String searchText = findClientField.getText().toLowerCase().trim();
                    if (searchText.isEmpty()) {
                        loadClients();
                        return;
                    }

                    List<Client> filteredClients = new ArrayList<Client>();
                    for (Client client : clientDAO.getAllClients()) {
                        if (client.getClientName().toLowerCase().contains(searchText) ||
                            String.valueOf(client.getClientId()).contains(searchText)) {
                            filteredClients.add(client);
                        }
                    }
                    
                    clientList.setAll(filteredClients);
                    statusLabel.setText("Found " + filteredClients.size() + " clients matching '" + searchText + "'");
                }

                // Material CRUD Operations
                private void saveMaterial() {
                    try {
                        Material material = createMaterialFromFields();
                        if (material != null && materialDAO.addMaterial(material)) {
                            showAlert("Success", "Material added successfully!");
                            loadMaterials();
                            clearMaterialFields();
                        } else {
                            showAlert("Error", "Failed to add material!");
                        }
                    } catch (Exception e) {
                        showAlert("Error", "Error adding material: " + e.getMessage());
                    }
                }

                private void updateMaterial() {
                    Material selectedMaterial = materialTable.getSelectionModel().getSelectedItem();
                    if (selectedMaterial == null) {
                        showAlert("Error", "Please select a material to update!");
                        return;
                    }

                    try {
                        Material material = createMaterialFromFields();
                        if (material != null && materialDAO.updateMaterial(material)) {
                            showAlert("Success", "Material updated successfully!");
                            loadMaterials();
                            clearMaterialFields();
                        } else {
                            showAlert("Error", "Failed to update material!");
                        }
                    } catch (Exception e) {
                        showAlert("Error", "Error updating material: " + e.getMessage());
                    }
                }

                private void deleteMaterial() {
                    Material selectedMaterial = materialTable.getSelectionModel().getSelectedItem();
                    if (selectedMaterial == null) {
                        showAlert("Error", "Please select a material to delete!");
                        return;
                    }

                    Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmAlert.setTitle("Confirm Deletion");
                    confirmAlert.setHeaderText("Delete Material");
                    confirmAlert.setContentText("Are you sure you want to delete this material?");

                    if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                        if (materialDAO.deleteMaterial(selectedMaterial.getMaterialId())) {
                            showAlert("Success", "Material deleted successfully!");
                            loadMaterials();
                            clearMaterialFields();
                        } else {
                            showAlert("Error", "Failed to delete material!");
                        }
                    }
                }

                private Material createMaterialFromFields() {
                    try {
                        int materialId = Integer.parseInt(materialIdField.getText());
                        String name = materialNameField.getText();
                        double price = Double.parseDouble(materialPriceField.getText());

                        if (name.trim().isEmpty()) {
                            showAlert("Error", "Material name cannot be empty!");
                            return null;
                        }

                        if (price < 0) {
                            showAlert("Error", "Price cannot be negative!");
                            return null;
                        }

                        return new Material(materialId, name, price);

                    } catch (NumberFormatException e) {
                        showAlert("Error", "Please enter valid numeric values!");
                        return null;
                    }
                }

                private void populateMaterialFields(Material material) {
                    materialIdField.setText(String.valueOf(material.getMaterialId()));
                    materialNameField.setText(material.getMname());
                    materialPriceField.setText(String.valueOf(material.getPrice()));
                }

                private void clearMaterialFields() {
                    materialIdField.clear();
                    materialNameField.clear();
                    materialPriceField.clear();
                    materialTable.getSelectionModel().clearSelection();
                }

                // Material Utility Methods
                private void sortMaterialsAsc() {
                    List<Material> sortedList = new ArrayList<Material>(materialList);
                    Collections.sort(sortedList, new Comparator<Material>() {
                        @Override
                        public int compare(Material m1, Material m2) {
                            return m1.getMname().compareToIgnoreCase(m2.getMname());
                        }
                    });
                    materialList.setAll(sortedList);
                    statusLabel.setText("Materials sorted A-Z");
                }

                private void sortMaterialsDesc() {
                    List<Material> sortedList = new ArrayList<Material>(materialList);
                    Collections.sort(sortedList, new Comparator<Material>() {
                        @Override
                        public int compare(Material m1, Material m2) {
                            return m2.getMname().compareToIgnoreCase(m1.getMname());
                        }
                    });
                    materialList.setAll(sortedList);
                    statusLabel.setText("Materials sorted Z-A");
                }

                private void countMaterials() {
                    int count = materialList.size();
                    showAlert("Material Count", "Total number of materials: " + count);
                }

                private void maxMaterialPrice() {
                    if (materialList.isEmpty()) {
                        showAlert("No Data", "No materials found!");
                        return;
                    }
                    
                    Material maxPriceMaterial = Collections.max(materialList, new Comparator<Material>() {
                        @Override
                        public int compare(Material m1, Material m2) {
                            return Double.compare(m1.getPrice(), m2.getPrice());
                        }
                    });
                    
                    showAlert("Maximum Price", "Highest price: $" + String.format("%.2f", maxPriceMaterial.getPrice()) + 
                              " (Material: " + maxPriceMaterial.getMname() + ")");
                }

                private void minMaterialPrice() {
                    if (materialList.isEmpty()) {
                        showAlert("No Data", "No materials found!");
                        return;
                    }
                    
                    Material minPriceMaterial = Collections.min(materialList, new Comparator<Material>() {
                        @Override
                        public int compare(Material m1, Material m2) {
                            return Double.compare(m1.getPrice(), m2.getPrice());
                        }
                    });
                    
                    showAlert("Minimum Price", "Lowest price: $" + String.format("%.2f", minPriceMaterial.getPrice()) + 
                              " (Material: " + minPriceMaterial.getMname() + ")");
                }

                private void findMaterial() {
                    String searchText = findMaterialField.getText().toLowerCase().trim();
                    if (searchText.isEmpty()) {
                        loadMaterials();
                        return;
                    }

                    List<Material> filteredMaterials = new ArrayList<Material>();
                    for (Material material : materialDAO.getAllMaterials()) {
                        if (material.getMname().toLowerCase().contains(searchText) ||
                            String.valueOf(material.getMaterialId()).contains(searchText)) {
                            filteredMaterials.add(material);
                        }
                    }
                    
                    materialList.setAll(filteredMaterials);
                    statusLabel.setText("Found " + filteredMaterials.size() + " materials matching '" + searchText + "'");
                }

                // Supplier CRUD Operations
                private void saveSupplier() {
                    try {
                        Supplier supplier = createSupplierFromFields();
                        if (supplier != null && supplierDAO.addSupplier(supplier)) {
                            showAlert("Success", "Supplier added successfully!");
                            loadSuppliers();
                            clearSupplierFields();
                        } else {
                            showAlert("Error", "Failed to add supplier!");
                        }
                    } catch (Exception e) {
                        showAlert("Error", "Error adding supplier: " + e.getMessage());
                    }
                }

                private void updateSupplier() {
                    Supplier selectedSupplier = supplierTable.getSelectionModel().getSelectedItem();
                    if (selectedSupplier == null) {
                        showAlert("Error", "Please select a supplier to update!");
                        return;
                    }

                    try {
                        Supplier supplier = createSupplierFromFields();
                        if (supplier != null && supplierDAO.updateSupplier(supplier)) {
                            showAlert("Success", "Supplier updated successfully!");
                            loadSuppliers();
                            clearSupplierFields();
                        } else {
                            showAlert("Error", "Failed to update supplier!");
                        }
                    } catch (Exception e) {
                        showAlert("Error", "Error updating supplier: " + e.getMessage());
                    }
                }

                private void deleteSupplier() {
                    Supplier selectedSupplier = supplierTable.getSelectionModel().getSelectedItem();
                    if (selectedSupplier == null) {
                        showAlert("Error", "Please select a supplier to delete!");
                        return;
                    }

                    Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmAlert.setTitle("Confirm Deletion");
                    confirmAlert.setHeaderText("Delete Supplier");
                    confirmAlert.setContentText("Are you sure you want to delete this supplier?");

                    if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                        if (supplierDAO.deleteSupplier(selectedSupplier.getSuppId())) {
                            showAlert("Success", "Supplier deleted successfully!");
                            loadSuppliers();
                            clearSupplierFields();
                        } else {
                            showAlert("Error", "Failed to delete supplier!");
                        }
                    }
                }

                private Supplier createSupplierFromFields() {
                    try {
                        int suppId = Integer.parseInt(supplierIdField.getText());
                        String suppName = supplierNameField.getText();
                        String location = supplierLocationField.getText();

                        if (suppName.trim().isEmpty() || location.trim().isEmpty()) {
                            showAlert("Error", "Supplier name and location cannot be empty!");
                            return null;
                        }

                        return new Supplier(suppId, suppName, location);

                    } catch (NumberFormatException e) {
                        showAlert("Error", "Please enter a valid supplier ID!");
                        return null;
                    }
                }

                private void populateSupplierFields(Supplier supplier) {
                    supplierIdField.setText(String.valueOf(supplier.getSuppId()));
                    supplierNameField.setText(supplier.getSuppName());
                    supplierLocationField.setText(supplier.getLocation());
                }

                private void clearSupplierFields() {
                    supplierIdField.clear();
                    supplierNameField.clear();
                    supplierLocationField.clear();
                    supplierTable.getSelectionModel().clearSelection();
                }

                // Supplier Utility Methods
                private void sortSuppliersAsc() {
                    List<Supplier> sortedList = new ArrayList<Supplier>(supplierList);
                    Collections.sort(sortedList, new Comparator<Supplier>() {
                        @Override
                        public int compare(Supplier s1, Supplier s2) {
                            return s1.getSuppName().compareToIgnoreCase(s2.getSuppName());
                        }
                    });
                    supplierList.setAll(sortedList);
                    statusLabel.setText("Suppliers sorted A-Z");
                }

                private void sortSuppliersDesc() {
                    List<Supplier> sortedList = new ArrayList<Supplier>(supplierList);
                    Collections.sort(sortedList, new Comparator<Supplier>() {
                        @Override
                        public int compare(Supplier s1, Supplier s2) {
                            return s2.getSuppName().compareToIgnoreCase(s1.getSuppName());
                        }
                    });
                    supplierList.setAll(sortedList);
                    statusLabel.setText("Suppliers sorted Z-A");
                }

                private void countSuppliers() {
                    int count = supplierList.size();
                    showAlert("Supplier Count", "Total number of suppliers: " + count);
                }

                private void findSupplier() {
                    String searchText = findSupplierField.getText().toLowerCase().trim();
                    if (searchText.isEmpty()) {// Phase CRUD Operations
                        private void savePhase() {
                            try {
                                Phase phase = createPhaseFromFields();
                                if (phase != null && phaseDAO.addPhase(phase)) {
                                    showAlert("Success", "Phase added successfully!");
                                    loadPhases();
                                    clearPhaseFields();
                                } else {
                                    showAlert("Error", "Failed to add phase!");
                                }
                            } catch (Exception e) {
                                showAlert("Error", "Error adding phase: " + e.getMessage());
                            }
                        }

                        private void updatePhase() {
                            Phase selectedPhase = phaseTable.getSelectionModel().getSelectedItem();
                            if (selectedPhase == null) {
                                showAlert("Error", "Please select a phase to update!");
                                return;
                            }

                            try {
                                Phase phase = createPhaseFromFields();
                                if (phase != null && phaseDAO.updatePhase(phase)) {
                                    showAlert("Success", "Phase updated successfully!");
                                    loadPhases();
                                    clearPhaseFields();
                                } else {
                                    showAlert("Error", "Failed to update phase!");
                                }
                            } catch (Exception e) {
                                showAlert("Error", "Error updating phase: " + e.getMessage());
                            }
                        }

                        private void deletePhase() {
                            Phase selectedPhase = phaseTable.getSelectionModel().getSelectedItem();
                            if (selectedPhase == null) {
                                showAlert("Error", "Please select a phase to delete!");
                                return;
                            }

                            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                            confirmAlert.setTitle("Confirm Deletion");
                            confirmAlert.setHeaderText("Delete Phase");
                            confirmAlert.setContentText("Are you sure you want to delete this phase?");

                            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                                if (phaseDAO.deletePhase(selectedPhase.getPhaseId())) {
                                    showAlert("Success", "Phase deleted successfully!");
                                    loadPhases();
                                    clearPhaseFields();
                                } else {
                                    showAlert("Error", "Failed to delete phase!");
                                }
                            }
                        }

                        private Phase createPhaseFromFields() {
                            try {
                                int phaseId = Integer.parseInt(phaseIdField.getText());
                                String name = phaseNameField.getText();
                                String description = phaseDescriptionField.getText();

                                if (name.trim().isEmpty()) {
                                    showAlert("Error", "Phase name cannot be empty!");
                                    return null;
                                }

                                if (phaseProjectComboBox.getValue() == null || 
                                    phaseStatusComboBox.getValue() == null ||
                                    phaseStartDatePicker.getValue() == null ||
                                    phaseEndDatePicker.getValue() == null) {
                                    showAlert("Error", "Please fill all required fields!");
                                    return null;
                                }

                                if (phaseEndDatePicker.getValue().isBefore(phaseStartDatePicker.getValue())) {
                                    showAlert("Error", "End date cannot be before start date!");
                                    return null;
                                }

                                int projectId = phaseProjectComboBox.getValue().getProjectId();
                                String status = phaseStatusComboBox.getValue();
                                Date startDate = Date.valueOf(phaseStartDatePicker.getValue());
                                Date endDate = Date.valueOf(phaseEndDatePicker.getValue());

                                return new Phase(phaseId, projectId, name, description, startDate, endDate, status);

                            } catch (NumberFormatException e) {
                                showAlert("Error", "Please enter a valid phase ID!");
                                return null;
                            }
                        }

                        private void populatePhaseFields(Phase phase) {
                            phaseIdField.setText(String.valueOf(phase.getPhaseId()));
                            phaseNameField.setText(phase.getName());
                            phaseDescriptionField.setText(phase.getDescription());
                            phaseStartDatePicker.setValue(phase.getStartDate().toLocalDate());
                            phaseEndDatePicker.setValue(phase.getEndDate().toLocalDate());
                            phaseStatusComboBox.setValue(phase.getStatus());

                            for (Project project : phaseProjectComboBox.getItems()) {
                                if (project.getProjectId() == phase.getProjectId()) {
                                    phaseProjectComboBox.setValue(project);
                                    break;
                                }
                            }
                        }

                        private void clearPhaseFields() {
                            phaseIdField.clear();
                            phaseNameField.clear();
                            phaseDescriptionField.clear();
                            phaseStartDatePicker.setValue(null);
                            phaseEndDatePicker.setValue(null);
                            phaseProjectComboBox.setValue(null);
                            phaseStatusComboBox.setValue(null);
                            phaseTable.getSelectionModel().clearSelection();
                        }

                        // Phase Utility Methods
                        private void sortPhasesAsc() {
                            List<Phase> sortedList = new ArrayList<Phase>(phaseList);
                            Collections.sort(sortedList, new Comparator<Phase>() {
                                @Override
                                public int compare(Phase p1, Phase p2) {
                                    return p1.getName().compareToIgnoreCase(p2.getName());
                                }
                            });
                            phaseList.setAll(sortedList);
                            statusLabel.setText("Phases sorted A-Z");
                        }

                        private void sortPhasesDesc() {
                            List<Phase> sortedList = new ArrayList<Phase>(phaseList);
                            Collections.sort(sortedList, new Comparator<Phase>() {
                                @Override
                                public int compare(Phase p1, Phase p2) {
                                    return p2.getName().compareToIgnoreCase(p1.getName());
                                }
                            });
                            phaseList.setAll(sortedList);
                            statusLabel.setText("Phases sorted Z-A");
                        }

                        private void countPhases() {
                            int count = phaseList.size();
                            showAlert("Phase Count", "Total number of phases: " + count);
                        }

                        private void findPhase() {
                            String searchText = findPhaseField.getText().toLowerCase().trim();
                            if (searchText.isEmpty()) {
                                loadPhases();
                                return;
                            }

                            List<Phase> filteredPhases = new ArrayList<Phase>();
                            for (Phase phase : phaseDAO.getAllPhases()) {
                                if (phase.getName().toLowerCase().contains(searchText) ||
                                    phase.getStatus().toLowerCase().contains(searchText) ||
                                    String.valueOf(phase.getPhaseId()).contains(searchText)) {
                                    filteredPhases.add(phase);
                                }
                            }
                            
                            phaseList.setAll(filteredPhases);
                            statusLabel.setText("Found " + filteredPhases.size() + " phases matching '" + searchText + "'");
                        }

                        // Payment CRUD Operations
                        private void savePayment() {
                            try {
                                Payment payment = createPaymentFromFields();
                                if (payment != null && paymentDAO.addPayment(payment)) {
                                    showAlert("Success", "Payment added successfully!");
                                    loadPayments();
                                    clearPaymentFields();
                                } else {
                                    showAlert("Error", "Failed to add payment!");
                                }
                            } catch (Exception e) {
                                showAlert("Error", "Error adding payment: " + e.getMessage());
                            }
                        }

                        private void updatePayment() {
                            Payment selectedPayment = paymentTable.getSelectionModel().getSelectedItem();
                            if (selectedPayment == null) {
                                showAlert("Error", "Please select a payment to update!");
                                return;
                            }

                            try {
                                Payment payment = createPaymentFromFields();
                                if (payment != null && paymentDAO.updatePayment(payment)) {
                                    showAlert("Success", "Payment updated successfully!");
                                    loadPayments();
                                    clearPaymentFields();
                                } else {
                                    showAlert("Error", "Failed to update payment!");
                                }
                            } catch (Exception e) {
                                showAlert("Error", "Error updating payment: " + e.getMessage());
                            }
                        }

                        private void deletePayment() {
                            Payment selectedPayment = paymentTable.getSelectionModel().getSelectedItem();
                            if (selectedPayment == null) {
                                showAlert("Error", "Please select a payment to delete!");
                                return;
                            }

                            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                            confirmAlert.setTitle("Confirm Deletion");
                            confirmAlert.setHeaderText("Delete Payment");
                            confirmAlert.setContentText("Are you sure you want to delete this payment?");

                            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                                if (paymentDAO.deletePayment(selectedPayment.getPaymentId())) {
                                    showAlert("Success", "Payment deleted successfully!");
                                    loadPayments();
                                    clearPaymentFields();
                                } else {
                                    showAlert("Error", "Failed to delete payment!");
                                }
                            }
                        }

                        private Payment createPaymentFromFields() {
                            try {
                                int paymentId = Integer.parseInt(paymentIdField.getText());
                                double amount = Double.parseDouble(paymentAmountField.getText());

                                if (amount <= 0) {
                                    showAlert("Error", "Payment amount must be greater than zero!");
                                    return null;
                                }

                                if (paymentClientComboBox.getValue() == null || 
                                    paymentSupplierComboBox.getValue() == null ||
                                    paymentMethodComboBox.getValue() == null ||
                                    paymentDatePicker.getValue() == null) {
                                    showAlert("Error", "Please fill all required fields!");
                                    return null;
                                }

                                int fromClient = paymentClientComboBox.getValue().getClientId();
                                int toSupplier = paymentSupplierComboBox.getValue().getSuppId();
                                String pmethod = paymentMethodComboBox.getValue();
                                Date pdate = Date.valueOf(paymentDatePicker.getValue());

                                return new Payment(paymentId, fromClient, toSupplier, amount, pdate, pmethod);

                            } catch (NumberFormatException e) {
                                showAlert("Error", "Please enter valid numeric values!");
                                return null;
                            }
                        }

                        private void populatePaymentFields(Payment payment) {
                            paymentIdField.setText(String.valueOf(payment.getPaymentId()));
                            paymentAmountField.setText(String.valueOf(payment.getAmount()));
                            paymentDatePicker.setValue(payment.getPdate().toLocalDate());
                            paymentMethodComboBox.setValue(payment.getPmethod());

                            for (Client client : paymentClientComboBox.getItems()) {
                                if (client.getClientId() == payment.getFromClient()) {
                                    paymentClientComboBox.setValue(client);
                                    break;
                                }
                            }

                            for (Supplier supplier : paymentSupplierComboBox.getItems()) {
                                if (supplier.getSuppId() == payment.getToSupplier()) {
                                    paymentSupplierComboBox.setValue(supplier);
                                    break;
                                }
                            }
                        }

                        private void clearPaymentFields() {
                            paymentIdField.clear();
                            paymentAmountField.clear();
                            paymentDatePicker.setValue(null);
                            paymentClientComboBox.setValue(null);
                            paymentSupplierComboBox.setValue(null);
                            paymentMethodComboBox.setValue(null);
                            paymentTable.getSelectionModel().clearSelection();
                        }

                        // Payment Utility Methods
                        private void sortPaymentsAsc() {
                            List<Payment> sortedList = new ArrayList<Payment>(paymentList);
                            Collections.sort(sortedList, new Comparator<Payment>() {
                                @Override
                                public int compare(Payment p1, Payment p2) {
                                    return p1.getPdate().compareTo(p2.getPdate());
                                }
                            });
                            paymentList.setAll(sortedList);
                            statusLabel.setText("Payments sorted by date (ascending)");
                        }

                        private void sortPaymentsDesc() {
                            List<Payment> sortedList = new ArrayList<Payment>(paymentList);
                            Collections.sort(sortedList, new Comparator<Payment>() {
                                @Override
                                public int compare(Payment p1, Payment p2) {
                                    return p2.getPdate().compareTo(p1.getPdate());
                                }
                            });
                            paymentList.setAll(sortedList);
                            statusLabel.setText("Payments sorted by date (descending)");
                        }

                        private void countPayments() {
                            int count = paymentList.size();
                            showAlert("Payment Count", "Total number of payments: " + count);
                        }

                        private void maxPaymentAmount() {
                            if (paymentList.isEmpty()) {
                                showAlert("No Data", "No payments found!");
                                return;
                            }
                            
                            Payment maxPayment = Collections.max(paymentList, new Comparator<Payment>() {
                                @Override
                                public int compare(Payment p1, Payment p2) {
                                    return Double.compare(p1.getAmount(), p2.getAmount());
                                }
                            });
                            
                            showAlert("Maximum Payment", "Highest payment: $" + String.format("%.2f", maxPayment.getAmount()) + 
                                      " (Payment ID: " + maxPayment.getPaymentId() + ")");
                        }

                        private void minPaymentAmount() {
                            if (paymentList.isEmpty()) {
                                showAlert("No Data", "No payments found!");
                                return;
                            }
                            
                            Payment minPayment = Collections.min(paymentList, new Comparator<Payment>() {
                                @Override
                                public int compare(Payment p1, Payment p2) {
                                    return Double.compare(p1.getAmount(), p2.getAmount());
                                }
                            });
                            
                            showAlert("Minimum Payment", "Lowest payment: $" + String.format("%.2f", minPayment.getAmount()) + 
                                      " (Payment ID: " + minPayment.getPaymentId() + ")");
                        }

                        private void findPayment() {
                            String searchText = findPaymentField.getText().toLowerCase().trim();
                            if (searchText.isEmpty()) {
                                loadPayments();
                                return;
                            }

                            List<Payment> filteredPayments = new ArrayList<Payment>();
                            for (Payment payment : paymentDAO.getAllPayments()) {
                                if (payment.getPmethod().toLowerCase().contains(searchText) ||
                                    String.valueOf(payment.getPaymentId()).contains(searchText) ||
                                    String.valueOf(payment.getAmount()).contains(searchText)) {
                                    filteredPayments.add(payment);
                                }
                            }
                            
                            paymentList.setAll(filteredPayments);
                            statusLabel.setText("Found " + filteredPayments.size() + " payments matching '" + searchText + "'");
                        }

                        // Report Generation Methods
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
                            report.append("==========================================\n");
                            report.append("Period: ").append(startDate).append(" to ").append(endDate).append("\n");
                            report.append("Total Sales: $").append(String.format("%.2f", totalSales)).append("\n");
                            report.append("Generated on: ").append(LocalDate.now()).append("\n");
                            report.append("==========================================\n");

                            reportTextArea.setText(report.toString());
                            statusLabel.setText("Sales report generated successfully");
                        }

                        private void generateProfitabilityReport() {
                            List<Map<String, Object>> data = reportsDAO.getProjectProfitabilityReport();
                            StringBuilder report = new StringBuilder();
                            report.append("");}}
                    
                    private void findPhase() {
                        String searchText = findPhaseField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadPhases();
                            return;
                        }

                    private void setupBranchHandlers() {
                        saveBranchBtn.setOnAction(e -> saveBranch());
                        updateBranchBtn.setOnAction(e -> updateBranch());
                        deleteBranchBtn.setOnAction(e -> deleteBranch());
                        refreshBranchesBtn.setOnAction(e -> loadBranches());
                        clearBranchFieldsBtn.setOnAction(e -> clearBranchFields());
                        sortBranchAscBtn.setOnAction(e -> sortBranchesAsc());
                        sortBranchDescBtn.setOnAction(e -> sortBranchesDesc());
                        countBranchesBtn.setOnAction(e -> countBranches());
                        findBranchBtn.setOnAction(e -> findBranch());
                    }

                    private void setupRoleHandlers() {
                        saveRoleBtn.setOnAction(e -> saveRole());
                        updateRoleBtn.setOnAction(e -> updateRole());
                        deleteRoleBtn.setOnAction(e -> deleteRole());
                        refreshRolesBtn.setOnAction(e -> loadRoles());
                        clearRoleFieldsBtn.setOnAction(e -> clearRoleFields());
                        sortRoleAscBtn.setOnAction(e -> sortRolesAsc());
                        sortRoleDescBtn.setOnAction(e -> sortRolesDesc());
                        countRolesBtn.setOnAction(e -> countRoles());
                        findRoleBtn.setOnAction(e -> findRole());
                    }

                    private void setupDepartmentHandlers() {
                        saveDepartmentBtn.setOnAction(e -> saveDepartment());
                        updateDepartmentBtn.setOnAction(e -> updateDepartment());
                        deleteDepartmentBtn.setOnAction(e -> deleteDepartment());
                        refreshDepartmentsBtn.setOnAction(e -> loadDepartments());
                        clearDepartmentFieldsBtn.setOnAction(e -> clearDepartmentFields());
                        sortDepartmentAscBtn.setOnAction(e -> sortDepartmentsAsc());
                        sortDepartmentDescBtn.setOnAction(e -> sortDepartmentsDesc());
                        countDepartmentsBtn.setOnAction(e -> countDepartments());
                        findDepartmentBtn.setOnAction(e -> findDepartment());
                    }

                    private void setupPhaseHandlers() {
                        savePhaseBtn.setOnAction(e -> savePhase());
                        updatePhaseBtn.setOnAction(e -> updatePhase());
                        deletePhaseBtn.setOnAction(e -> deletePhase());
                        refreshPhasesBtn.setOnAction(e -> loadPhases());
                        clearPhaseFieldsBtn.setOnAction(e -> clearPhaseFields());
                        sortPhaseAscBtn.setOnAction(e -> sortPhasesAsc());
                        sortPhaseDescBtn.setOnAction(e -> sortPhasesDesc());
                        countPhasesBtn.setOnAction(e -> countPhases());
                        findPhaseBtn.setOnAction(e -> findPhase());
                    }

                    private void setupPaymentHandlers() {
                        savePaymentBtn.setOnAction(e -> savePayment());
                        updatePaymentBtn.setOnAction(e -> updatePayment());
                        deletePaymentBtn.setOnAction(e -> deletePayment());
                        refreshPaymentsBtn.setOnAction(e -> loadPayments());
                        clearPaymentFieldsBtn.setOnAction(e -> clearPaymentFields());
                        sortPaymentAscBtn.setOnAction(e -> sortPaymentsAsc());
                        sortPaymentDescBtn.setOnAction(e -> sortPaymentsDesc());
                        countPaymentsBtn.setOnAction(e -> countPayments());
                        maxPaymentAmountBtn.setOnAction(e -> maxPaymentAmount());
                        minPaymentAmountBtn.setOnAction(e -> minPaymentAmount());
                        findPaymentBtn.setOnAction(e -> findPayment());
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

                        List<Phase> filteredPhases = new ArrayList<>();
                        for (Phase phase : phaseDAO.getAllPhases()) {
                            if (phase.getName().toLowerCase().contains(searchText) ||
                                phase.getStatus().toLowerCase().contains(searchText) ||
                                String.valueOf(phase.getPhaseId()).contains(searchText)) {
                                filteredPhases.add(phase);
                            }
                        }
                        
                        phaseList.setAll(filteredPhases);
                        statusLabel.setText("Found " + filteredPhases.size() + " phases matching '" + searchText + "'");
                    }

                    // Payment CRUD Operations
                    private void savePayment() {
                        try {
                            Payment payment = createPaymentFromFields();
                            if (payment != null && paymentDAO.addPayment(payment)) {
                                showAlert("Success", "Payment added successfully!");
                                loadPayments();
                                clearPaymentFields();
                            } else {
                                showAlert("Error", "Failed to add payment!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error adding payment: " + e.getMessage());
                        }
                    }

                    private void updatePayment() {
                        Payment selectedPayment = paymentTable.getSelectionModel().getSelectedItem();
                        if (selectedPayment == null) {
                            showAlert("Error", "Please select a payment to update!");
                            return;
                        }

                        try {
                            Payment payment = createPaymentFromFields();
                            if (payment != null && paymentDAO.updatePayment(payment)) {
                                showAlert("Success", "Payment updated successfully!");
                                loadPayments();
                                clearPaymentFields();
                            } else {
                                showAlert("Error", "Failed to update payment!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error updating payment: " + e.getMessage());
                        }
                    }

                    private void deletePayment() {
                        Payment selectedPayment = paymentTable.getSelectionModel().getSelectedItem();
                        if (selectedPayment == null) {
                            showAlert("Error", "Please select a payment to delete!");
                            return;
                        }

                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Confirm Deletion");
                        confirmAlert.setHeaderText("Delete Payment");
                        confirmAlert.setContentText("Are you sure you want to delete this payment?");

                        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                            if (paymentDAO.deletePayment(selectedPayment.getPaymentId())) {
                                showAlert("Success", "Payment deleted successfully!");
                                loadPayments();
                                clearPaymentFields();
                            } else {
                                showAlert("Error", "Failed to delete payment!");
                            }
                        }
                    }

                    private Payment createPaymentFromFields() {
                        try {
                            int paymentId = Integer.parseInt(paymentIdField.getText());
                            double amount = Double.parseDouble(paymentAmountField.getText());

                            if (amount <= 0) {
                                showAlert("Error", "Payment amount must be greater than zero!");
                                return null;
                            }

                            if (paymentClientComboBox.getValue() == null || 
                                paymentSupplierComboBox.getValue() == null ||
                                paymentMethodComboBox.getValue() == null ||
                                paymentDatePicker.getValue() == null) {
                                showAlert("Error", "Please fill all required fields!");
                                return null;
                            }

                            int fromClient = paymentClientComboBox.getValue().getClientId();
                            int toSupplier = paymentSupplierComboBox.getValue().getSuppId();
                            String pmethod = paymentMethodComboBox.getValue();
                            Date pdate = Date.valueOf(paymentDatePicker.getValue());

                            return new Payment(paymentId, fromClient, toSupplier, amount, pdate, pmethod);

                        } catch (NumberFormatException e) {
                            showAlert("Error", "Please enter valid numeric values!");
                            return null;
                        }
                    }

                    private void populatePaymentFields(Payment payment) {
                        paymentIdField.setText(String.valueOf(payment.getPaymentId()));
                        paymentAmountField.setText(String.valueOf(payment.getAmount()));
                        paymentDatePicker.setValue(payment.getPdate().toLocalDate());
                        paymentMethodComboBox.setValue(payment.getPmethod());

                        for (Client client : paymentClientComboBox.getItems()) {
                            if (client.getClientId() == payment.getFromClient()) {
                                paymentClientComboBox.setValue(client);
                                break;
                            }
                        }

                        for (Supplier supplier : paymentSupplierComboBox.getItems()) {
                            if (supplier.getSuppId() == payment.getToSupplier()) {
                                paymentSupplierComboBox.setValue(supplier);
                                break;
                            }
                        }
                    }

                    private void clearPaymentFields() {
                        paymentIdField.clear();
                        paymentAmountField.clear();
                        paymentDatePicker.setValue(null);
                        paymentClientComboBox.setValue(null);
                        paymentSupplierComboBox.setValue(null);
                        paymentMethodComboBox.setValue(null);
                        paymentTable.getSelectionModel().clearSelection();
                    }

                    private void sortPaymentsAsc() {
                        List<Payment> sortedList = new ArrayList<>(paymentList);
                        Collections.sort(sortedList, Comparator.comparing(Payment::getPdate));
                        paymentList.setAll(sortedList);
                        statusLabel.setText("Payments sorted by date (ascending)");
                    }

                    private void sortPaymentsDesc() {
                        List<Payment> sortedList = new ArrayList<>(paymentList);
                        Collections.sort(sortedList, (p1, p2) -> p2.getPdate().compareTo(p1.getPdate()));
                        paymentList.setAll(sortedList);
                        statusLabel.setText("Payments sorted by date (descending)");
                    }

                    private void countPayments() {
                        int count = paymentList.size();
                        showAlert("Payment Count", "Total number of payments: " + count);
                    }

                    private void maxPaymentAmount() {
                        if (paymentList.isEmpty()) {
                            showAlert("No Data", "No payments found!");
                            return;
                        }
                        
                        Payment maxPayment = Collections.max(paymentList, Comparator.comparingDouble(Payment::getAmount));
                        showAlert("Maximum Payment", "Highest payment: $" + String.format("%.2f", maxPayment.getAmount()) + 
                                  " (Payment ID: " + maxPayment.getPaymentId() + ")");
                    }

                    private void minPaymentAmount() {
                        if (paymentList.isEmpty()) {
                            showAlert("No Data", "No payments found!");
                            return;
                        }
                        
                        Payment minPayment = Collections.min(paymentList, Comparator.comparingDouble(Payment::getAmount));
                        showAlert("Minimum Payment", "Lowest payment: $" + String.format("%.2f", minPayment.getAmount()) + 
                                  " (Payment ID: " + minPayment.getPaymentId() + ")");
                    }

                    private void findPayment() {
                        String searchText = findPaymentField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadPayments();
                            return;
                        }

                        List<Payment> filteredPayments = new ArrayList<>();
                        for (Payment payment : paymentDAO.getAllPayments()) {
                            if (payment.getPmethod().toLowerCase().contains(searchText) ||
                                String.valueOf(payment.getPaymentId()).contains(searchText) ||
                                String.valueOf(payment.getAmount()).contains(searchText)) {
                                filteredPayments.add(payment);
                            }
                        }
                        
                        paymentList.setAll(filteredPayments);
                        statusLabel.setText("Found " + filteredPayments.size() + " payments matching '" + searchText + "'");
                    }

                    // Report Generation Methods
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
                        report.append("==========================================\n");
                        report.append("Period: ").append(startDate).append(" to ").append(endDate).append("\n");
                        report.append("Total Sales: $").append(String.format("%.2f", totalSales)).append("\n");
                        report.append("Generated on: ").append(LocalDate.now()).append("\n");
                        report.append("==========================================\n");

                        reportTextArea.setText(report.toString());
                        statusLabel.setText("Sales report generated successfully");
                    }

                    private void generateProfitabilityReport() {
                        StringBuilder report = new StringBuilder();
                        report.append("PROFITABILITY REPORT\n");
                        report.append("==========================================\n");
                        
                        try {
                            List<Project> projects = projectDAO.getAllProjects();
                            double totalRevenue = 0;
                            double totalCost = 0;
                            
                            for (Project project : projects) {
                                totalRevenue += project.getRevenue();
                                totalCost += project.getCost();
                            }
                            
                            double profit = totalRevenue - totalCost;
                            double profitMargin = totalRevenue > 0 ? (profit / totalRevenue) * 100 : 0;
                            
                            report.append("Total Revenue: $").append(String.format("%.2f", totalRevenue)).append("\n");
                            report.append("Total Cost: $").append(String.format("%.2f", totalCost)).append("\n");
                            report.append("Net Profit: $").append(String.format("%.2f", profit)).append("\n");
                            report.append("Profit Margin: ").append(String.format("%.2f", profitMargin)).append("%\n");
                            report.append("Generated on: ").append(LocalDate.now()).append("\n");
                            report.append("==========================================\n");
                            
                            reportTextArea.setText(report.toString());
                            statusLabel.setText("Profitability report generated successfully");
                        } catch (Exception e) {
                            showAlert("Error", "Failed to generate profitability report: " + e.getMessage());
                        }
                    }

                    private void generateWorkloadReport() {
                        StringBuilder report = new StringBuilder();
                        report.append("WORKLOAD REPORT\n");
                        report.append("==========================================\n");
                        
                        try {
                            List<Employee> employees = employeeDAO.getAllEmployees();
                            List<Project> projects = projectDAO.getAllProjects();
                            
                            report.append("Total Employees: ").append(employees.size()).append("\n");
                            report.append("Total Projects: ").append(projects.size()).append("\n");
                            
                            if (!employees.isEmpty()) {
                                double avgProjectsPerEmployee = (double) projects.size() / employees.size();
                                report.append("Average Projects per Employee: ").append(String.format("%.2f", avgProjectsPerEmployee)).append("\n");
                            }
                            
                            report.append("Generated on: ").append(LocalDate.now()).append("\n");
                            report.append("==========================================\n");
                            
                            reportTextArea.setText(report.toString());
                            statusLabel.setText("Workload report generated successfully");
                        } catch (Exception e) {
                            showAlert("Error", "Failed to generate workload report: " + e.getMessage());
                        }
                    }

                    private void generateMaterialReport() {
                        StringBuilder report = new StringBuilder();
                        report.append("MATERIAL REPORT\n");
                        report.append("==========================================\n");
                        
                        try {
                            List<Material> materials = materialDAO.getAllMaterials();
                            
                            if (!materials.isEmpty()) {
                                double totalValue = materials.stream().mapToDouble(Material::getPrice).sum();
                                double avgPrice = totalValue / materials.size();
                                Material mostExpensive = Collections.max(materials, Comparator.comparingDouble(Material::getPrice));
                                Material cheapest = Collections.min(materials, Comparator.comparingDouble(Material::getPrice));
                                
                                report.append("Total Materials: ").append(materials.size()).append("\n");
                                report.append("Total Value: $").append(String.format("%.2f", totalValue)).append("\n");
                                report.append("Average Price: $").append(String.format("%.2f", avgPrice)).append("\n");
                                report.append("Most Expensive: ").append(mostExpensive.getMname())
                                      .append(" ($").append(String.format("%.2f", mostExpensive.getPrice())).append(")\n");
                                report.append("Cheapest: ").append(cheapest.getMname())
                                      .append(" ($").append(String.format("%.2f", cheapest.getPrice())).append(")\n");
                            } else {
                                report.append("No materials found.\n");
                            }
                            
                            report.append("Generated on: ").append(LocalDate.now()).append("\n");
                            report.append("==========================================\n");
                            
                            reportTextArea.setText(report.toString());
                            statusLabel.setText("Material report generated successfully");
                        } catch (Exception e) {
                            showAlert("Error", "Failed to generate material report: " + e.getMessage());
                        }
                    }

                    private void generateBranchReport() {
                        StringBuilder report = new StringBuilder();
                        report.append("BRANCH REPORT\n");
                        report.append("==========================================\n");
                        
                        try {
                            List<Branch> branches = branchDAO.getAllBranches();
                            List<Project> projects = projectDAO.getAllProjects();
                            List<Employee> employees = employeeDAO.getAllEmployees();
                            
                            for (Branch branch : branches) {
                                long projectCount = projects.stream().filter(p -> p.getBranchId() == branch.getBranchId()).count();
                                long employeeCount = employees.stream().filter(e -> e.getBranchId() == branch.getBranchId()).count();
                                
                                report.append("Branch: ").append(branch.getLocation()).append("\n");
                                report.append("  Projects: ").append(projectCount).append("\n");
                                report.append("  Employees: ").append(employeeCount).append("\n\n");
                            }
                            
                            report.append("Generated on: ").append(LocalDate.now()).append("\n");
                            report.append("==========================================\n");
                            
                            reportTextArea.setText(report.toString());
                            statusLabel.setText("Branch report generated successfully");
                        } catch (Exception e) {
                            showAlert("Error", "Failed to generate branch report: " + e.getMessage());
                        }
                    }

                    private void generateSupplierReport() {
                        StringBuilder report = new StringBuilder();
                        report.append("SUPPLIER REPORT\n");
                        report.append("==========================================\n");
                        
                        try {
                            List<Supplier> suppliers = supplierDAO.getAllSuppliers();
                            List<Payment> payments = paymentDAO.getAllPayments();
                            
                            for (Supplier supplier : suppliers) {
                                double totalPayments = payments.stream()
                                    .filter(p -> p.getToSupplier() == supplier.getSuppId())
                                    .mapToDouble(Payment::getAmount)
                                    .sum();
                                
                                long paymentCount = payments.stream()
                                    .filter(p -> p.getToSupplier() == supplier.getSuppId())
                                    .count();
                                
                                report.append("Supplier: ").append(supplier.getSuppName()).append("\n");
                                report.append("  Location: ").append(supplier.getLocation()).append("\n");
                                report.append("  Total Payments: $").append(String.format("%.2f", totalPayments)).append("\n");
                                report.append("  Payment Count: ").append(paymentCount).append("\n\n");
                            }
                            
                            report.append("Generated on: ").append(LocalDate.now()).append("\n");
                            report.append("==========================================\n");
                            
                            reportTextArea.setText(report.toString());
                            statusLabel.setText("Supplier report generated successfully");
                        } catch (Exception e) {
                            showAlert("Error", "Failed to generate supplier report: " + e.getMessage());
                        }
                    }

                    private void generateTimelineReport() {
                        StringBuilder report = new StringBuilder();
                        report.append("PROJECT TIMELINE REPORT\n");
                        report.append("==========================================\n");
                        
                        try {
                            List<Phase> phases = phaseDAO.getAllPhases();
                            
                            // Group phases by status
                            Map<String, Long> statusCount = new java.util.HashMap<>();
                            for (Phase phase : phases) {
                                statusCount.put(phase.getStatus(), 
                                    statusCount.getOrDefault(phase.getStatus(), 0L) + 1);
                            }
                            
                            report.append("Phase Status Summary:\n");
                            for (Map.Entry<String, Long> entry : statusCount.entrySet()) {
                                report.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                            }
                            
                            report.append("\nUpcoming Deadlines:\n");
                            LocalDate today = LocalDate.now();
                            for (Phase phase : phases) {
                                LocalDate endDate = phase.getEndDate().toLocalDate();
                                if (endDate.isAfter(today) && endDate.isBefore(today.plusDays(30))) {
                                    long daysUntilDeadline = java.time.temporal.ChronoUnit.DAYS.between(today, endDate);
                                    Project project = projectDAO.getProjectById(phase.getProjectId());
                                    String projectName = project != null ? project.getPname() : "Unknown";
                                    
                                    report.append("  ").append(phase.getName())
                                          .append(" (").append(projectName).append(")")
                                          .append(" - ").append(daysUntilDeadline).append(" days\n");
                                }
                            }
                            
                            report.append("\nGenerated on: ").append(LocalDate.now()).append("\n");
                            report.append("==========================================\n");
                            
                            reportTextArea.setText(report.toString());
                            statusLabel.setText("Timeline report generated successfully");
                        } catch (Exception e) {
                            showAlert("Error", "Failed to generate timeline report: " + e.getMessage());
                        }
                    }

                    // Utility Methods
                    private void showAlert(String title, String message) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(title);
                        alert.setHeaderText(null);
                        alert.setContentText(message);
                        alert.showAndWait();
                    }
                }

                // Note: This controller assumes the following model classes exist with appropriate constructors and getter/setter methods:
                // - Employee (empId, empName, positionId, salary, branchId, departmentId, managerId, isManager)
                // - Project (projectId, pname, location, cost, revenue, branchId, clientId)
                // - Client (clientId, clientName)
                // - Material (materialId, mname, price)
                // - Supplier (suppId, suppName, location)
                // - Branch (branchId, location)
                // - Role (roleId, title)
                // - Department (deptId, deptName)
                // - Phase (phaseId, projectId, name, description, startDate, endDate, status)
                // - Payment (paymentId, fromClient, toSupplier, amount, pdate, pmethod)
                //
                // And the following DAO classes with CRUD operations:
                // - EmployeeDAO, ProjectDAO, ClientDAO, MaterialDAO, SupplierDAO
                // - BranchDAO, RoleDAO, DepartmentDAO, PhaseDAO, PaymentDAO, ReportsDAO    private void findSupplier() {
                        String searchText = findSupplierField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadSuppliers();
                            return;
                        }

                        List<Supplier> filteredSuppliers = new ArrayList<>();
                        for (Supplier supplier : supplierDAO.getAllSuppliers()) {
                            if (supplier.getSuppName().toLowerCase().contains(searchText) ||
                                supplier.getLocation().toLowerCase().contains(searchText) ||
                                String.valueOf(supplier.getSuppId()).contains(searchText)) {
                                filteredSuppliers.add(supplier);
                            }
                        }
                        
                        supplierList.setAll(filteredSuppliers);
                        statusLabel.setText("Found " + filteredSuppliers.size() + " suppliers matching '" + searchText + "'");
                    }

                    // Branch CRUD Operations
                    private void saveBranch() {
                        try {
                            Branch branch = createBranchFromFields();
                            if (branch != null && branchDAO.addBranch(branch)) {
                                showAlert("Success", "Branch added successfully!");
                                loadBranches();
                                clearBranchFields();
                            } else {
                                showAlert("Error", "Failed to add branch!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error adding branch: " + e.getMessage());
                        }
                    }

                    private void updateBranch() {
                        Branch selectedBranch = branchTable.getSelectionModel().getSelectedItem();
                        if (selectedBranch == null) {
                            showAlert("Error", "Please select a branch to update!");
                            return;
                        }

                        try {
                            Branch branch = createBranchFromFields();
                            if (branch != null && branchDAO.updateBranch(branch)) {
                                showAlert("Success", "Branch updated successfully!");
                                loadBranches();
                                clearBranchFields();
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
                                loadBranches();
                                clearBranchFields();
                            } else {
                                showAlert("Error", "Failed to delete branch!");
                            }
                        }
                    }

                    private Branch createBranchFromFields() {
                        try {
                            int branchId = Integer.parseInt(branchIdField.getText());
                            String location = branchLocationField.getText();

                            if (location.trim().isEmpty()) {
                                showAlert("Error", "Branch location cannot be empty!");
                                return null;
                            }

                            return new Branch(branchId, location);

                        } catch (NumberFormatException e) {
                            showAlert("Error", "Please enter a valid branch ID!");
                            return null;
                        }
                    }

                    private void populateBranchFields(Branch branch) {
                        branchIdField.setText(String.valueOf(branch.getBranchId()));
                        branchLocationField.setText(branch.getLocation());
                    }

                    private void clearBranchFields() {
                        branchIdField.clear();
                        branchLocationField.clear();
                        branchTable.getSelectionModel().clearSelection();
                    }

                    private void sortBranchesAsc() {
                        List<Branch> sortedList = new ArrayList<>(branchList);
                        Collections.sort(sortedList, Comparator.comparing(Branch::getLocation));
                        branchList.setAll(sortedList);
                        statusLabel.setText("Branches sorted A-Z");
                    }

                    private void sortBranchesDesc() {
                        List<Branch> sortedList = new ArrayList<>(branchList);
                        Collections.sort(sortedList, (b1, b2) -> b2.getLocation().compareToIgnoreCase(b1.getLocation()));
                        branchList.setAll(sortedList);
                        statusLabel.setText("Branches sorted Z-A");
                    }

                    private void countBranches() {
                        int count = branchList.size();
                        showAlert("Branch Count", "Total number of branches: " + count);
                    }

                    private void findBranch() {
                        String searchText = findBranchField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadBranches();
                            return;
                        }

                        List<Branch> filteredBranches = new ArrayList<>();
                        for (Branch branch : branchDAO.getAllBranches()) {
                            if (branch.getLocation().toLowerCase().contains(searchText) ||
                                String.valueOf(branch.getBranchId()).contains(searchText)) {
                                filteredBranches.add(branch);
                            }
                        }
                        
                        branchList.setAll(filteredBranches);
                        statusLabel.setText("Found " + filteredBranches.size() + " branches matching '" + searchText + "'");
                    }

                    // Role CRUD Operations
                    private void saveRole() {
                        try {
                            Role role = createRoleFromFields();
                            if (role != null && roleDAO.addRole(role)) {
                                showAlert("Success", "Role added successfully!");
                                loadRoles();
                                clearRoleFields();
                            } else {
                                showAlert("Error", "Failed to add role!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error adding role: " + e.getMessage());
                        }
                    }

                    private void updateRole() {
                        Role selectedRole = roleTable.getSelectionModel().getSelectedItem();
                        if (selectedRole == null) {
                            showAlert("Error", "Please select a role to update!");
                            return;
                        }

                        try {
                            Role role = createRoleFromFields();
                            if (role != null && roleDAO.updateRole(role)) {
                                showAlert("Success", "Role updated successfully!");
                                loadRoles();
                                clearRoleFields();
                            } else {
                                showAlert("Error", "Failed to update role!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error updating role: " + e.getMessage());
                        }
                    }

                    private void deleteRole() {
                        Role selectedRole = roleTable.getSelectionModel().getSelectedItem();
                        if (selectedRole == null) {
                            showAlert("Error", "Please select a role to delete!");
                            return;
                        }

                    private void deleteRole() {
                        Role selectedRole = roleTable.getSelectionModel().getSelectedItem();
                        if (selectedRole == null) {
                            showAlert("Error", "Please select a role to delete!");
                            return;
                        }

                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Confirm Deletion");
                        confirmAlert.setHeaderText("Delete Role");
                        confirmAlert.setContentText("Are you sure you want to delete this role?");

                        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                            if (roleDAO.deleteRole(selectedRole.getRoleId())) {
                                showAlert("Success", "Role deleted successfully!");
                                loadRoles();
                                clearRoleFields();
                            } else {
                                showAlert("Error", "Failed to delete role!");
                            }
                        }
                    }

                    private Role createRoleFromFields() {
                        try {
                            int roleId = Integer.parseInt(roleIdField.getText());
                            String title = roleTitleField.getText();

                            if (title.trim().isEmpty()) {
                                showAlert("Error", "Role title cannot be empty!");
                                return null;
                            }

                            return new Role(roleId, title);

                        } catch (NumberFormatException e) {
                            showAlert("Error", "Please enter a valid role ID!");
                            return null;
                        }
                    }

                    private void populateRoleFields(Role role) {
                        roleIdField.setText(String.valueOf(role.getRoleId()));
                        roleTitleField.setText(role.getTitle());
                    }

                    private void clearRoleFields() {
                        roleIdField.clear();
                        roleTitleField.clear();
                        roleTable.getSelectionModel().clearSelection();
                    }

                    private void sortRolesAsc() {
                        List<Role> sortedList = new ArrayList<>(roleList);
                        Collections.sort(sortedList, Comparator.comparing(Role::getTitle));
                        roleList.setAll(sortedList);
                        statusLabel.setText("Roles sorted A-Z");
                    }

                    private void sortRolesDesc() {
                        List<Role> sortedList = new ArrayList<>(roleList);
                        Collections.sort(sortedList, (r1, r2) -> r2.getTitle().compareToIgnoreCase(r1.getTitle()));
                        roleList.setAll(sortedList);
                        statusLabel.setText("Roles sorted Z-A");
                    }

                    private void countRoles() {
                        int count = roleList.size();
                        showAlert("Role Count", "Total number of roles: " + count);
                    }

                    private void findRole() {
                        String searchText = findRoleField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadRoles();
                            return;
                        }

                        List<Role> filteredRoles = new ArrayList<>();
                        for (Role role : roleDAO.getAllRoles()) {
                            if (role.getTitle().toLowerCase().contains(searchText) ||
                                String.valueOf(role.getRoleId()).contains(searchText)) {
                                filteredRoles.add(role);
                            }
                        }
                        
                        roleList.setAll(filteredRoles);
                        statusLabel.setText("Found " + filteredRoles.size() + " roles matching '" + searchText + "'");
                    }

                    // Department CRUD Operations
                    private void saveDepartment() {
                        try {
                            Department department = createDepartmentFromFields();
                            if (department != null && departmentDAO.addDepartment(department)) {
                                showAlert("Success", "Department added successfully!");
                                loadDepartments();
                                clearDepartmentFields();
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
                                loadDepartments();
                                clearDepartmentFields();
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
                                loadDepartments();
                                clearDepartmentFields();
                            } else {
                                showAlert("Error", "Failed to delete department!");
                            }
                        }
                    }

                    private Department createDepartmentFromFields() {
                        try {
                            int deptId = Integer.parseInt(departmentIdField.getText());
                            String deptName = departmentNameField.getText();

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

                    private void populateDepartmentFields(Department department) {
                        departmentIdField.setText(String.valueOf(department.getDeptId()));
                        departmentNameField.setText(department.getDeptName());
                    }

                    private void clearDepartmentFields() {
                        departmentIdField.clear();
                        departmentNameField.clear();
                        departmentTable.getSelectionModel().clearSelection();
                    }

                    private void sortDepartmentsAsc() {
                        List<Department> sortedList = new ArrayList<>(departmentList);
                        Collections.sort(sortedList, Comparator.comparing(Department::getDeptName));
                        departmentList.setAll(sortedList);
                        statusLabel.setText("Departments sorted A-Z");
                    }

                    private void sortDepartmentsDesc() {
                        List<Department> sortedList = new ArrayList<>(departmentList);
                        Collections.sort(sortedList, (d1, d2) -> d2.getDeptName().compareToIgnoreCase(d1.getDeptName()));
                        departmentList.setAll(sortedList);
                        statusLabel.setText("Departments sorted Z-A");
                    }

                    private void countDepartments() {
                        int count = departmentList.size();
                        showAlert("Department Count", "Total number of departments: " + count);
                    }

                    private void findDepartment() {
                        String searchText = findDepartmentField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadDepartments();
                            return;
                        }

                        List<Department> filteredDepartments = new ArrayList<>();
                        for (Department department : departmentDAO.getAllDepartments()) {
                            if (department.getDeptName().toLowerCase().contains(searchText) ||
                                String.valueOf(department.getDeptId()).contains(searchText)) {
                                filteredDepartments.add(department);
                            }
                        }
                        
                        departmentList.setAll(filteredDepartments);
                        statusLabel.setText("Found " + filteredDepartments.size() + " departments matching '" + searchText + "'");
                    }

                    // Phase CRUD Operations
                    private void savePhase() {
                        try {
                            Phase phase = createPhaseFromFields();
                            if (phase != null && phaseDAO.addPhase(phase)) {
                                showAlert("Success", "Phase added successfully!");
                                loadPhases();
                                clearPhaseFields();
                            } else {
                                showAlert("Error", "Failed to add phase!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error adding phase: " + e.getMessage());
                        }
                    }

                    private void updatePhase() {
                        Phase selectedPhase = phaseTable.getSelectionModel().getSelectedItem();
                        if (selectedPhase == null) {
                            showAlert("Error", "Please select a phase to update!");
                            return;
                        }

                        try {
                            Phase phase = createPhaseFromFields();
                            if (phase != null && phaseDAO.updatePhase(phase)) {
                                showAlert("Success", "Phase updated successfully!");
                                loadPhases();
                                clearPhaseFields();
                            } else {
                                showAlert("Error", "Failed to update phase!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error updating phase: " + e.getMessage());
                        }
                    }

                    private void deletePhase() {
                        Phase selectedPhase = phaseTable.getSelectionModel().getSelectedItem();
                        if (selectedPhase == null) {
                            showAlert("Error", "Please select a phase to delete!");
                            return;
                        }

                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Confirm Deletion");
                        confirmAlert.setHeaderText("Delete Phase");
                        confirmAlert.setContentText("Are you sure you want to delete this phase?");

                        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                            if (phaseDAO.deletePhase(selectedPhase.getPhaseId())) {
                                showAlert("Success", "Phase deleted successfully!");
                                loadPhases();
                                clearPhaseFields();
                            } else {
                                showAlert("Error", "Failed to delete phase!");
                            }
                        }
                    }

                    private Phase createPhaseFromFields() {
                        try {
                            int phaseId = Integer.parseInt(phaseIdField.getText());
                            String name = phaseNameField.getText();
                            String description = phaseDescriptionField.getText();

                            if (name.trim().isEmpty()) {
                                showAlert("Error", "Phase name cannot be empty!");
                                return null;
                            }

                            if (phaseProjectComboBox.getValue() == null || 
                                phaseStatusComboBox.getValue() == null ||
                                phaseStartDatePicker.getValue() == null ||
                                phaseEndDatePicker.getValue() == null) {
                                showAlert("Error", "Please fill all required fields!");
                                return null;
                            }

                            if (phaseEndDatePicker.getValue().isBefore(phaseStartDatePicker.getValue())) {
                                showAlert("Error", "End date cannot be before start date!");
                                return null;
                            }

                            int projectId = phaseProjectComboBox.getValue().getProjectId();
                            String status = phaseStatusComboBox.getValue();
                            Date startDate = Date.valueOf(phaseStartDatePicker.getValue());
                            Date endDate = Date.valueOf(phaseEndDatePicker.getValue());

                            return new Phase(phaseId, projectId, name, description, startDate, endDate, status);

                        } catch (NumberFormatException e) {
                            showAlert("Error", "Please enter a valid phase ID!");
                            return null;
                        }
                    }

                    private void populatePhaseFields(Phase phase) {
                        phaseIdField.setText(String.valueOf(phase.getPhaseId()));
                        phaseNameField.setText(phase.getName());
                        phaseDescriptionField.setText(phase.getDescription());
                        phaseStartDatePicker.setValue(phase.getStartDate().toLocalDate());
                        phaseEndDatePicker.setValue(phase.getEndDate().toLocalDate());
                        phaseStatusComboBox.setValue(phase.getStatus());

                        for (Project project : phaseProjectComboBox.getItems()) {
                            if (project.getProjectId() == phase.getProjectId()) {
                                phaseProjectComboBox.setValue(project);
                                break;
                            }
                        }
                    }

                    private void clearPhaseFields() {
                        phaseIdField.clear();
                        phaseNameField.clear();
                        phaseDescriptionField.clear();
                        phaseStartDatePicker.setValue(null);
                        phaseEndDatePicker.setValue(null);
                        phaseProjectComboBox.setValue(null);
                        phaseStatusComboBox.setValue(null);
                        phaseTable.getSelectionModel().clearSelection();
                    }

                    private void sortPhasesAsc() {
                        List<Phase> sortedList = new ArrayList<>(phaseList);
                        Collections.sort(sortedList, Comparator.comparing(Phase::getName));
                        phaseList.setAll(sortedList);
                        statusLabel.setText("Phases sorted A-Z");
                    }

                    private void sortPhasesDesc() {
                        List<Phase> sortedList = new ArrayList<>(phaseList);
                        Collections.sort(sortedList, (p1, p2) -> p2.getName().compareToIgnoreCase(p1.getName()));
                        phaseList.setAll(sortedList);
                        statusLabel.setText("Phases sorted Z-A");
                    }

                    private void countPhases() {
                        int count = phaseList.size();
                        showAlert("Phase Count", "Total number of phases: " + count);
                    }

                    private void findPhase() {
                        String searchText = findPhaseField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadPhases();
                            return;
                        }

                        List<Phase> filteredPhases = new ArrayList<>();
                        for (Phase phase : phaseDAO.getAllPhases()) {
                            if (phase.getName().toLowerCase().contains(searchText) ||
                                phase.getStatus().toLowerCase().contains(searchText) ||
                                String.valueOf(phase.getPhaseId()).contains(searchText)) {
                                filteredPhases.add(phase);
                            }
                        }
                        
                        phaseList.setAll(filteredPhases);
                        statusLabel.setText("Found " + filteredPhases.size() + " phases matching '" + searchText + "'");
                    }import javafx.animation.Animation;
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
                import java.util.ArrayList;
                import java.util.Collections;
                import java.util.Comparator;
                import java.util.List;
                import java.util.Map;
                import java.util.ResourceBundle;

                public class ComprehensiveProjectManagementController implements Initializable {

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
                    @FXML private Button saveEmployeeBtn;
                    @FXML private Button updateEmployeeBtn;
                    @FXML private Button deleteEmployeeBtn;
                    @FXML private Button refreshEmployeesBtn;
                    @FXML private Button clearEmployeeFieldsBtn;
                    @FXML private Button sortEmployeeAscBtn;
                    @FXML private Button sortEmployeeDescBtn;
                    @FXML private Button countEmployeesBtn;
                    @FXML private Button maxEmployeeSalaryBtn;
                    @FXML private Button minEmployeeSalaryBtn;
                    @FXML private Button findEmployeeBtn;
                    @FXML private TextField findEmployeeField;

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
                    @FXML private Button saveProjectBtn;
                    @FXML private Button updateProjectBtn;
                    @FXML private Button deleteProjectBtn;
                    @FXML private Button refreshProjectsBtn;
                    @FXML private Button clearProjectFieldsBtn;
                    @FXML private Button sortProjectAscBtn;
                    @FXML private Button sortProjectDescBtn;
                    @FXML private Button countProjectsBtn;
                    @FXML private Button maxProjectRevenueBtn;
                    @FXML private Button minProjectCostBtn;
                    @FXML private Button findProjectBtn;
                    @FXML private TextField findProjectField;

                    // Client Management
                    @FXML private TextField clientIdField;
                    @FXML private TextField clientNameField;
                    @FXML private TableView<Client> clientTable;
                    @FXML private TableColumn<Client, Integer> clientIdColumn;
                    @FXML private TableColumn<Client, String> clientNameColumn;
                    @FXML private Button saveClientBtn;
                    @FXML private Button updateClientBtn;
                    @FXML private Button deleteClientBtn;
                    @FXML private Button refreshClientsBtn;
                    @FXML private Button clearClientFieldsBtn;
                    @FXML private Button sortClientAscBtn;
                    @FXML private Button sortClientDescBtn;
                    @FXML private Button countClientsBtn;
                    @FXML private Button findClientBtn;
                    @FXML private TextField findClientField;

                    // Material Management
                    @FXML private TextField materialIdField;
                    @FXML private TextField materialNameField;
                    @FXML private TextField materialPriceField;
                    @FXML private TableView<Material> materialTable;
                    @FXML private TableColumn<Material, Integer> materialIdColumn;
                    @FXML private TableColumn<Material, String> materialNameColumn;
                    @FXML private TableColumn<Material, Double> materialPriceColumn;
                    @FXML private Button saveMaterialBtn;
                    @FXML private Button updateMaterialBtn;
                    @FXML private Button deleteMaterialBtn;
                    @FXML private Button refreshMaterialsBtn;
                    @FXML private Button clearMaterialFieldsBtn;
                    @FXML private Button sortMaterialAscBtn;
                    @FXML private Button sortMaterialDescBtn;
                    @FXML private Button countMaterialsBtn;
                    @FXML private Button maxMaterialPriceBtn;
                    @FXML private Button minMaterialPriceBtn;
                    @FXML private Button findMaterialBtn;
                    @FXML private TextField findMaterialField;

                    // Supplier Management
                    @FXML private TextField supplierIdField;
                    @FXML private TextField supplierNameField;
                    @FXML private TextField supplierLocationField;
                    @FXML private TableView<Supplier> supplierTable;
                    @FXML private TableColumn<Supplier, Integer> supplierIdColumn;
                    @FXML private TableColumn<Supplier, String> supplierNameColumn;
                    @FXML private TableColumn<Supplier, String> supplierLocationColumn;
                    @FXML private Button saveSupplierBtn;
                    @FXML private Button updateSupplierBtn;
                    @FXML private Button deleteSupplierBtn;
                    @FXML private Button refreshSuppliersBtn;
                    @FXML private Button clearSupplierFieldsBtn;
                    @FXML private Button sortSupplierAscBtn;
                    @FXML private Button sortSupplierDescBtn;
                    @FXML private Button countSuppliersBtn;
                    @FXML private Button findSupplierBtn;
                    @FXML private TextField findSupplierField;

                    // Branch Management
                    @FXML private TextField branchIdField;
                    @FXML private TextField branchLocationField;
                    @FXML private TableView<Branch> branchTable;
                    @FXML private TableColumn<Branch, Integer> branchIdColumn;
                    @FXML private TableColumn<Branch, String> branchLocationColumn;
                    @FXML private Button saveBranchBtn;
                    @FXML private Button updateBranchBtn;
                    @FXML private Button deleteBranchBtn;
                    @FXML private Button refreshBranchesBtn;
                    @FXML private Button clearBranchFieldsBtn;
                    @FXML private Button sortBranchAscBtn;
                    @FXML private Button sortBranchDescBtn;
                    @FXML private Button countBranchesBtn;
                    @FXML private Button findBranchBtn;
                    @FXML private TextField findBranchField;

                    // Role Management
                    @FXML private TextField roleIdField;
                    @FXML private TextField roleTitleField;
                    @FXML private TableView<Role> roleTable;
                    @FXML private TableColumn<Role, Integer> roleIdColumn;
                    @FXML private TableColumn<Role, String> roleTitleColumn;
                    @FXML private Button saveRoleBtn;
                    @FXML private Button updateRoleBtn;
                    @FXML private Button deleteRoleBtn;
                    @FXML private Button refreshRolesBtn;
                    @FXML private Button clearRoleFieldsBtn;
                    @FXML private Button sortRoleAscBtn;
                    @FXML private Button sortRoleDescBtn;
                    @FXML private Button countRolesBtn;
                    @FXML private Button findRoleBtn;
                    @FXML private TextField findRoleField;

                    // Department Management
                    @FXML private TextField departmentIdField;
                    @FXML private TextField departmentNameField;
                    @FXML private TableView<Department> departmentTable;
                    @FXML private TableColumn<Department, Integer> departmentIdColumn;
                    @FXML private TableColumn<Department, String> departmentNameColumn;
                    @FXML private Button saveDepartmentBtn;
                    @FXML private Button updateDepartmentBtn;
                    @FXML private Button deleteDepartmentBtn;
                    @FXML private Button refreshDepartmentsBtn;
                    @FXML private Button clearDepartmentFieldsBtn;
                    @FXML private Button sortDepartmentAscBtn;
                    @FXML private Button sortDepartmentDescBtn;
                    @FXML private Button countDepartmentsBtn;
                    @FXML private Button findDepartmentBtn;
                    @FXML private TextField findDepartmentField;

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
                    @FXML private Button savePhaseBtn;
                    @FXML private Button updatePhaseBtn;
                    @FXML private Button deletePhaseBtn;
                    @FXML private Button refreshPhasesBtn;
                    @FXML private Button clearPhaseFieldsBtn;
                    @FXML private Button sortPhaseAscBtn;
                    @FXML private Button sortPhaseDescBtn;
                    @FXML private Button countPhasesBtn;
                    @FXML private Button findPhaseBtn;
                    @FXML private TextField findPhaseField;

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
                    @FXML private Button savePaymentBtn;
                    @FXML private Button updatePaymentBtn;
                    @FXML private Button deletePaymentBtn;
                    @FXML private Button refreshPaymentsBtn;
                    @FXML private Button clearPaymentFieldsBtn;
                    @FXML private Button sortPaymentAscBtn;
                    @FXML private Button sortPaymentDescBtn;
                    @FXML private Button countPaymentsBtn;
                    @FXML private Button maxPaymentAmountBtn;
                    @FXML private Button minPaymentAmountBtn;
                    @FXML private Button findPaymentBtn;
                    @FXML private TextField findPaymentField;

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
                        clearEmployeeFieldsBtn.setOnAction(e -> clearEmployeeFields());
                        sortEmployeeAscBtn.setOnAction(e -> sortEmployeesAsc());
                        sortEmployeeDescBtn.setOnAction(e -> sortEmployeesDesc());
                        countEmployeesBtn.setOnAction(e -> countEmployees());
                        maxEmployeeSalaryBtn.setOnAction(e -> maxEmployeeSalary());
                        minEmployeeSalaryBtn.setOnAction(e -> minEmployeeSalary());
                        findEmployeeBtn.setOnAction(e -> findEmployee());
                    }

                    private void setupProjectHandlers() {
                        saveProjectBtn.setOnAction(e -> saveProject());
                        updateProjectBtn.setOnAction(e -> updateProject());
                        deleteProjectBtn.setOnAction(e -> deleteProject());
                        refreshProjectsBtn.setOnAction(e -> loadProjects());
                        clearProjectFieldsBtn.setOnAction(e -> clearProjectFields());
                        sortProjectAscBtn.setOnAction(e -> sortProjectsAsc());
                        sortProjectDescBtn.setOnAction(e -> sortProjectsDesc());
                        countProjectsBtn.setOnAction(e -> countProjects());
                        maxProjectRevenueBtn.setOnAction(e -> maxProjectRevenue());
                        minProjectCostBtn.setOnAction(e -> minProjectCost());
                        findProjectBtn.setOnAction(e -> findProject());
                    }

                    private void setupClientHandlers() {
                        saveClientBtn.setOnAction(e -> saveClient());
                        updateClientBtn.setOnAction(e -> updateClient());
                        deleteClientBtn.setOnAction(e -> deleteClient());
                        refreshClientsBtn.setOnAction(e -> loadClients());
                        clearClientFieldsBtn.setOnAction(e -> clearClientFields());
                        sortClientAscBtn.setOnAction(e -> sortClientsAsc());
                        sortClientDescBtn.setOnAction(e -> sortClientsDesc());
                        countClientsBtn.setOnAction(e -> countClients());
                        findClientBtn.setOnAction(e -> findClient());
                    }

                    private void setupMaterialHandlers() {
                        saveMaterialBtn.setOnAction(e -> saveMaterial());
                        updateMaterialBtn.setOnAction(e -> updateMaterial());
                        deleteMaterialBtn.setOnAction(e -> deleteMaterial());
                        refreshMaterialsBtn.setOnAction(e -> loadMaterials());
                        clearMaterialFieldsBtn.setOnAction(e -> clearMaterialFields());
                        sortMaterialAscBtn.setOnAction(e -> sortMaterialsAsc());
                        sortMaterialDescBtn.setOnAction(e -> sortMaterialsDesc());
                        countMaterialsBtn.setOnAction(e -> countMaterials());
                        maxMaterialPriceBtn.setOnAction(e -> maxMaterialPrice());
                        minMaterialPriceBtn.setOnAction(e -> minMaterialPrice());
                        findMaterialBtn.setOnAction(e -> findMaterial());
                    }

                    private void setupSupplierHandlers() {
                        saveSupplierBtn.setOnAction(e -> saveSupplier());
                        updateSupplierBtn.setOnAction(e -> updateSupplier());
                        deleteSupplierBtn.setOnAction(e -> deleteSupplier());
                        refreshSuppliersBtn.setOnAction(e -> loadSuppliers());
                        clearSupplierFieldsBtn.setOnAction(e -> clearSupplierFields());
                        sortSupplierAscBtn.setOnAction(e -> sortSuppliersAsc());
                        sortSupplierDescBtn.setOnAction(e -> sortSuppliersDesc());
                        countSuppliersBtn.setOnAction(e -> countSuppliers());
                        findSupplierBtn.setOnAction(e -> findSupplier());
                    }

                    private void setupBranchHandlers() {
                        saveBranchBtn.setOnAction(e -> saveBranch());
                        updateBranchBtn.setOnAction(e -> updateBranch());
                        deleteBranchBtn.setOnAction(e -> deleteBranch());
                        refreshBranchesBtn.setOnAction(e -> loadBranches());
                        clearBranchFieldsBtn.setOnAction(e -> clearBranchFields());
                        sortBranchAscBtn.setOnAction(e -> sortBranchesAsc());
                        sortBranchDescBtn.setOnAction(e -> sortBranchesDesc());
                        countBranchesBtn.setOnAction(e -> countBranches());
                        findBranchBtn.setOnAction(e -> findBranch());
                    }

                    private void setupRoleHandlers() {
                        saveRoleBtn.setOnAction(e -> saveRole());
                        updateRoleBtn.setOnAction(e -> updateRole());
                        deleteRoleBtn.setOnAction(e -> deleteRole());
                        refreshRolesBtn.setOnAction(e -> loadRoles());
                        clearRoleFieldsBtn.setOnAction(e -> clearRoleFields());
                        sortRoleAscBtn.setOnAction(e -> sortRolesAsc());
                        sortRoleDescBtn.setOnAction(e -> sortRolesDesc());
                        countRolesBtn.setOnAction(e -> countRoles());
                        findRoleBtn.setOnAction(e -> findRole());
                    }

                    private void setupDepartmentHandlers() {
                        saveDepartmentBtn.setOnAction(e -> saveDepartment());
                        updateDepartmentBtn.setOnAction(e -> updateDepartment());
                        deleteDepartmentBtn.setOnAction(e -> deleteDepartment());
                        refreshDepartmentsBtn.setOnAction(e -> loadDepartments());
                        clearDepartmentFieldsBtn.setOnAction(e -> clearDepartmentFields());
                        sortDepartmentAscBtn.setOnAction(e -> sortDepartmentsAsc());
                        sortDepartmentDescBtn.setOnAction(e -> sortDepartmentsDesc());
                        countDepartmentsBtn.setOnAction(e -> countDepartments());
                        findDepartmentBtn.setOnAction(e -> findDepartment());
                    }

                    private void setupPhaseHandlers() {
                        savePhaseBtn.setOnAction(e -> savePhase());
                        updatePhaseBtn.setOnAction(e -> updatePhase());
                        deletePhaseBtn.setOnAction(e -> deletePhase());
                        refreshPhasesBtn.setOnAction(e -> loadPhases());
                        clearPhaseFieldsBtn.setOnAction(e -> clearPhaseFields());
                        sortPhaseAscBtn.setOnAction(e -> sortPhasesAsc());
                        sortPhaseDescBtn.setOnAction(e -> sortPhasesDesc());
                        countPhasesBtn.setOnAction(e -> countPhases());
                        findPhaseBtn.setOnAction(e -> findPhase());
                    }

                    private void setupPaymentHandlers() {
                        savePaymentBtn.setOnAction(e -> savePayment());
                        updatePaymentBtn.setOnAction(e -> updatePayment());
                        deletePaymentBtn.setOnAction(e -> deletePayment());
                        refreshPaymentsBtn.setOnAction(e -> loadPayments());
                        clearPaymentFieldsBtn.setOnAction(e -> clearPaymentFields());
                        sortPaymentAscBtn.setOnAction(e -> sortPaymentsAsc());
                        sortPaymentDescBtn.setOnAction(e -> sortPaymentsDesc());
                        countPaymentsBtn.setOnAction(e -> countPayments());
                        maxPaymentAmountBtn.setOnAction(e -> maxPaymentAmount());
                        minPaymentAmountBtn.setOnAction(e -> minPaymentAmount());
                        findPaymentBtn.setOnAction(e -> findPayment());
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
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                            LocalDateTime now = LocalDateTime.now();
                            currentTimeLabel.setText(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        }));
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
                        List<Role> roles = roleDAO.getAllRoles();
                        positionComboBox.setItems(FXCollections.observableArrayList(roles));

                        List<Branch> branches = branchDAO.getAllBranches();
                        empBranchComboBox.setItems(FXCollections.observableArrayList(branches));

                        List<Department> departments = departmentDAO.getAllDepartments();
                        empDepartmentComboBox.setItems(FXCollections.observableArrayList(departments));

                        List<Employee> allEmployees = employeeDAO.getAllEmployees();
                        List<Employee> managers = new ArrayList<>();
                        for (Employee emp : allEmployees) {
                            if (emp.isManager()) {
                                managers.add(emp);
                            }
                        }
                        managerComboBox.setItems(FXCollections.observableArrayList(managers));
                    }

                    private void loadProjectComboBoxData() {
                        List<Branch> branches = branchDAO.getAllBranches();
                        projectBranchComboBox.setItems(FXCollections.observableArrayList(branches));

                        List<Client> clients = clientDAO.getAllClients();
                        projectClientComboBox.setItems(FXCollections.observableArrayList(clients));
                    }

                    private void loadPhaseComboBoxData() {
                        List<Project> projects = projectDAO.getAllProjects();
                        phaseProjectComboBox.setItems(FXCollections.observableArrayList(projects));
                    }

                    private void loadPaymentComboBoxData() {
                        List<Client> clients = clientDAO.getAllClients();
                        paymentClientComboBox.setItems(FXCollections.observableArrayList(clients));

                        List<Supplier> suppliers = supplierDAO.getAllSuppliers();
                        paymentSupplierComboBox.setItems(FXCollections.observableArrayList(suppliers));
                    }

                    // Dashboard Statistics Update
                    private void updateDashboardStats() {
                        try {
                            totalProjectsLabel.setText(String.valueOf(projectDAO.getAllProjects().size()));
                            totalEmployeesLabel.setText(String.valueOf(employeeDAO.getAllEmployees().size()));
                            totalClientsLabel.setText(String.valueOf(clientDAO.getAllClients().size()));

                            // Calculate total revenue
                            double totalRevenue = 0.0;
                            List<Project> projects = projectDAO.getAllProjects();
                            for (Project project : projects) {
                                totalRevenue += project.getRevenue();
                            }
                            totalRevenueLabel.setText(String.format("$%.2f", totalRevenue));
                        } catch (Exception e) {
                            System.err.println("Error updating dashboard stats: " + e.getMessage());
                        }
                    }

                    // Employee CRUD Operations
                    private void saveEmployee() {
                        try {
                            Employee employee = createEmployeeFromFields();
                            if (employee != null && employeeDAO.addEmployee(employee)) {
                                showAlert("Success", "Employee added successfully!");
                                loadEmployees();
                                loadEmployeeComboBoxData();
                                clearEmployeeFields();
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
                                loadEmployees();
                                loadEmployeeComboBoxData();
                                clearEmployeeFields();
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
                                loadEmployees();
                                loadEmployeeComboBoxData();
                                clearEmployeeFields();
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

                            if (empName.trim().isEmpty()) {
                                showAlert("Error", "Employee name cannot be empty!");
                                return null;
                            }

                            if (salary < 0) {
                                showAlert("Error", "Salary cannot be negative!");
                                return null;
                            }

                            if (positionComboBox.getValue() == null || 
                                empBranchComboBox.getValue() == null ||
                                empDepartmentComboBox.getValue() == null) {
                                showAlert("Error", "Please fill all required fields!");
                                return null;
                            }

                            int positionId = positionComboBox.getValue().getRoleId();
                            int branchId = empBranchComboBox.getValue().getBranchId();
                            int departmentId = empDepartmentComboBox.getValue().getDeptId();
                            boolean isManager = isManagerCheckBox.isSelected();
                            Integer managerId = managerComboBox.getValue() != null ? managerComboBox.getValue().getEmpId() : null;

                            return new Employee(empId, empName, positionId, salary, branchId, departmentId, managerId, isManager);

                        } catch (NumberFormatException e) {
                            showAlert("Error", "Please enter valid numeric values!");
                            return null;
                        }
                    }

                    private void populateEmployeeFields(Employee employee) {
                        empIdField.setText(String.valueOf(employee.getEmpId()));
                        empNameField.setText(employee.getEmpName());
                        salaryField.setText(String.valueOf(employee.getSalary()));
                        isManagerCheckBox.setSelected(employee.isManager());

                        for (Role role : positionComboBox.getItems()) {
                            if (role.getRoleId() == employee.getPositionId()) {
                                positionComboBox.setValue(role);
                                break;
                            }
                        }

                        for (Branch branch : empBranchComboBox.getItems()) {
                            if (branch.getBranchId() == employee.getBranchId()) {
                                empBranchComboBox.setValue(branch);
                                break;
                            }
                        }

                        for (Department dept : empDepartmentComboBox.getItems()) {
                            if (dept.getDeptId() == employee.getDepartmentId()) {
                                empDepartmentComboBox.setValue(dept);
                                break;
                            }
                        }

                        if (employee.getManagerId() != null) {
                            for (Employee manager : managerComboBox.getItems()) {
                                if (manager.getEmpId() == employee.getManagerId()) {
                                    managerComboBox.setValue(manager);
                                    break;
                                }
                            }
                        }
                    }

                    private void clearEmployeeFields() {
                        empIdField.clear();
                        empNameField.clear();
                        salaryField.clear();
                        positionComboBox.setValue(null);
                        empBranchComboBox.setValue(null);
                        empDepartmentComboBox.setValue(null);
                        managerComboBox.setValue(null);
                        isManagerCheckBox.setSelected(false);
                        employeeTable.getSelectionModel().clearSelection();
                    }

                    // Employee Utility Methods
                    private void sortEmployeesAsc() {
                        List<Employee> sortedList = new ArrayList<>(employeeList);
                        Collections.sort(sortedList, Comparator.comparing(Employee::getEmpName));
                        employeeList.setAll(sortedList);
                        statusLabel.setText("Employees sorted A-Z");
                    }

                    private void sortEmployeesDesc() {
                        List<Employee> sortedList = new ArrayList<>(employeeList);
                        Collections.sort(sortedList, (e1, e2) -> e2.getEmpName().compareToIgnoreCase(e1.getEmpName()));
                        employeeList.setAll(sortedList);
                        statusLabel.setText("Employees sorted Z-A");
                    }

                    private void countEmployees() {
                        int count = employeeList.size();
                        showAlert("Employee Count", "Total number of employees: " + count);
                    }

                    private void maxEmployeeSalary() {
                        if (employeeList.isEmpty()) {
                            showAlert("No Data", "No employees found!");
                            return;
                        }
                        
                        Employee maxSalaryEmployee = Collections.max(employeeList, Comparator.comparingDouble(Employee::getSalary));
                        showAlert("Maximum Salary", "Highest salary: $" + String.format("%.2f", maxSalaryEmployee.getSalary()) + 
                                  " (Employee: " + maxSalaryEmployee.getEmpName() + ")");
                    }

                    private void minEmployeeSalary() {
                        if (employeeList.isEmpty()) {
                            showAlert("No Data", "No employees found!");
                            return;
                        }
                        
                        Employee minSalaryEmployee = Collections.min(employeeList, Comparator.comparingDouble(Employee::getSalary));
                        showAlert("Minimum Salary", "Lowest salary: $" + String.format("%.2f", minSalaryEmployee.getSalary()) + 
                                  " (Employee: " + minSalaryEmployee.getEmpName() + ")");
                    }

                    private void findEmployee() {
                        String searchText = findEmployeeField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadEmployees();
                            return;
                        }

                        List<Employee> filteredEmployees = new ArrayList<>();
                        for (Employee employee : employeeDAO.getAllEmployees()) {
                            if (employee.getEmpName().toLowerCase().contains(searchText) ||
                                String.valueOf(employee.getEmpId()).contains(searchText)) {
                                filteredEmployees.add(employee);
                            }
                        }
                        
                        employeeList.setAll(filteredEmployees);
                        statusLabel.setText("Found " + filteredEmployees.size() + " employees matching '" + searchText + "'");
                    }

                    // Project CRUD Operations
                    private void saveProject() {
                        try {
                            Project project = createProjectFromFields();
                            if (project != null && projectDAO.addProject(project)) {
                                showAlert("Success", "Project added successfully!");
                                loadProjects();
                                clearProjectFields();
                            } else {
                                showAlert("Error", "Failed to add project!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error adding project: " + e.getMessage());
                        }
                    }

                    private void updateProject() {
                        Project selectedProject = projectTable.getSelectionModel().getSelectedItem();
                        if (selectedProject == null) {
                            showAlert("Error", "Please select a project to update!");
                            return;
                        }

                        try {
                            Project project = createProjectFromFields();
                            if (project != null && projectDAO.updateProject(project)) {
                                showAlert("Success", "Project updated successfully!");
                                loadProjects();
                                clearProjectFields();
                            } else {
                                showAlert("Error", "Failed to update project!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error updating project: " + e.getMessage());
                        }
                    }

                    private void deleteProject() {
                        Project selectedProject = projectTable.getSelectionModel().getSelectedItem();
                        if (selectedProject == null) {
                            showAlert("Error", "Please select a project to delete!");
                            return;
                        }

                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Confirm Deletion");
                        confirmAlert.setHeaderText("Delete Project");
                        confirmAlert.setContentText("Are you sure you want to delete this project?");

                        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                            if (projectDAO.deleteProject(selectedProject.getProjectId())) {
                                showAlert("Success", "Project deleted successfully!");
                                loadProjects();
                                clearProjectFields();
                            } else {
                                showAlert("Error", "Failed to delete project!");
                            }
                        }
                    }

                    private Project createProjectFromFields() {
                        try {
                            int projectId = Integer.parseInt(projectIdField.getText());
                            String pname = projectNameField.getText();
                            String location = projectLocationField.getText();
                            double cost = Double.parseDouble(costField.getText());
                            double revenue = Double.parseDouble(revenueField.getText());

                            if (pname.trim().isEmpty() || location.trim().isEmpty()) {
                                showAlert("Error", "Project name and location cannot be empty!");
                                return null;
                            }

                            if (cost < 0 || revenue < 0) {
                                showAlert("Error", "Cost and revenue cannot be negative!");
                                return null;
                            }

                            if (projectBranchComboBox.getValue() == null) {
                                showAlert("Error", "Please select a branch!");
                                return null;
                            }

                            int branchId = projectBranchComboBox.getValue().getBranchId();
                            int clientId = projectClientComboBox.getValue() != null ? projectClientComboBox.getValue().getClientId() : 0;

                            return new Project(projectId, pname, location, cost, revenue, branchId, clientId);

                        } catch (NumberFormatException e) {
                            showAlert("Error", "Please enter valid numeric values!");
                            return null;
                        }
                    }

                    private void populateProjectFields(Project project) {
                        projectIdField.setText(String.valueOf(project.getProjectId()));
                        projectNameField.setText(project.getPname());
                        projectLocationField.setText(project.getLocation());
                        costField.setText(String.valueOf(project.getCost()));
                        revenueField.setText(String.valueOf(project.getRevenue()));

                        for (Branch branch : projectBranchComboBox.getItems()) {
                            if (branch.getBranchId() == project.getBranchId()) {
                                projectBranchComboBox.setValue(branch);
                                break;
                            }
                        }

                        for (Client client : projectClientComboBox.getItems()) {
                            if (client.getClientId() == project.getClientId()) {
                                projectClientComboBox.setValue(client);
                                break;
                            }
                        }
                    }

                    private void clearProjectFields() {
                        projectIdField.clear();
                        projectNameField.clear();
                        projectLocationField.clear();
                        costField.clear();
                        revenueField.clear();
                        projectBranchComboBox.setValue(null);
                        projectClientComboBox.setValue(null);
                        projectTable.getSelectionModel().clearSelection();
                    }

                    // Project Utility Methods
                    private void sortProjectsAsc() {
                        List<Project> sortedList = new ArrayList<>(projectList);
                        Collections.sort(sortedList, Comparator.comparing(Project::getPname));
                        projectList.setAll(sortedList);
                        statusLabel.setText("Projects sorted A-Z");
                    }

                    private void sortProjectsDesc() {
                        List<Project> sortedList = new ArrayList<>(projectList);
                        Collections.sort(sortedList, (p1, p2) -> p2.getPname().compareToIgnoreCase(p1.getPname()));
                        projectList.setAll(sortedList);
                        statusLabel.setText("Projects sorted Z-A");
                    }

                    private void countProjects() {
                        int count = projectList.size();
                        showAlert("Project Count", "Total number of projects: " + count);
                    }

                    private void maxProjectRevenue() {
                        if (projectList.isEmpty()) {
                            showAlert("No Data", "No projects found!");
                            return;
                        }
                        
                        Project maxRevenueProject = Collections.max(projectList, Comparator.comparingDouble(Project::getRevenue));
                        showAlert("Maximum Revenue", "Highest revenue: $" + String.format("%.2f", maxRevenueProject.getRevenue()) + 
                                  " (Project: " + maxRevenueProject.getPname() + ")");
                    }

                    private void minProjectCost() {
                        if (projectList.isEmpty()) {
                            showAlert("No Data", "No projects found!");
                            return;
                        }
                        
                        Project minCostProject = Collections.min(projectList, Comparator.comparingDouble(Project::getCost));
                        showAlert("Minimum Cost", "Lowest cost: $" + String.format("%.2f", minCostProject.getCost()) + 
                                  " (Project: " + minCostProject.getPname() + ")");
                    }

                    private void findProject() {
                        String searchText = findProjectField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadProjects();
                            return;
                        }

                        List<Project> filteredProjects = new ArrayList<>();
                        for (Project project : projectDAO.getAllProjects()) {
                            if (project.getPname().toLowerCase().contains(searchText) ||
                                project.getLocation().toLowerCase().contains(searchText) ||
                                String.valueOf(project.getProjectId()).contains(searchText)) {
                                filteredProjects.add(project);
                            }
                        }
                        
                        projectList.setAll(filteredProjects);
                        statusLabel.setText("Found " + filteredProjects.size() + " projects matching '" + searchText + "'");
                    }

                    // Client CRUD Operations
                    private void saveClient() {
                        try {
                            Client client = createClientFromFields();
                            if (client != null && clientDAO.addClient(client)) {
                                showAlert("Success", "Client added successfully!");
                                loadClients();
                                clearClientFields();
                            } else {
                                showAlert("Error", "Failed to add client!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error adding client: " + e.getMessage());
                        }
                    }

                    private void updateClient() {
                        Client selectedClient = clientTable.getSelectionModel().getSelectedItem();
                        if (selectedClient == null) {
                            showAlert("Error", "Please select a client to update!");
                            return;
                        }

                        try {
                            Client client = createClientFromFields();
                            if (client != null && clientDAO.updateClient(client)) {
                                showAlert("Success", "Client updated successfully!");
                                loadClients();
                                clearClientFields();
                            } else {
                                showAlert("Error", "Failed to update client!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error updating client: " + e.getMessage());
                        }
                    }

                    private void deleteClient() {
                        Client selectedClient = clientTable.getSelectionModel().getSelectedItem();
                        if (selectedClient == null) {
                            showAlert("Error", "Please select a client to delete!");
                            return;
                        }

                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Confirm Deletion");
                        confirmAlert.setHeaderText("Delete Client");
                        confirmAlert.setContentText("Are you sure you want to delete this client?");

                        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                            if (clientDAO.deleteClient(selectedClient.getClientId())) {
                                showAlert("Success", "Client deleted successfully!");
                                loadClients();
                                clearClientFields();
                            } else {
                                showAlert("Error", "Failed to delete client!");
                            }
                        }
                    }

                    private Client createClientFromFields() {
                        try {
                            int clientId = Integer.parseInt(clientIdField.getText());
                            String clientName = clientNameField.getText();

                            if (clientName.trim().isEmpty()) {
                                showAlert("Error", "Client name cannot be empty!");
                                return null;
                            }

                            return new Client(clientId, clientName);

                        } catch (NumberFormatException e) {
                            showAlert("Error", "Please enter a valid client ID!");
                            return null;
                        }
                    }

                    private void populateClientFields(Client client) {
                        clientIdField.setText(String.valueOf(client.getClientId()));
                        clientNameField.setText(client.getClientName());
                    }

                    private void clearClientFields() {
                        clientIdField.clear();
                        clientNameField.clear();
                        clientTable.getSelectionModel().clearSelection();
                    }

                    // Client Utility Methods
                    private void sortClientsAsc() {
                        List<Client> sortedList = new ArrayList<>(clientList);
                        Collections.sort(sortedList, Comparator.comparing(Client::getClientName));
                        clientList.setAll(sortedList);
                        statusLabel.setText("Clients sorted A-Z");
                    }

                    private void sortClientsDesc() {
                        List<Client> sortedList = new ArrayList<>(clientList);
                        Collections.sort(sortedList, (c1, c2) -> c2.getClientName().compareToIgnoreCase(c1.getClientName()));
                        clientList.setAll(sortedList);
                        statusLabel.setText("Clients sorted Z-A");
                    }

                    private void countClients() {
                        int count = clientList.size();
                        showAlert("Client Count", "Total number of clients: " + count);
                    }

                    private void findClient() {
                        String searchText = findClientField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadClients();
                            return;
                        }

                        List<Client> filteredClients = new ArrayList<>();
                        for (Client client : clientDAO.getAllClients()) {
                            if (client.getClientName().toLowerCase().contains(searchText) ||
                                String.valueOf(client.getClientId()).contains(searchText)) {
                                filteredClients.add(client);
                            }
                        }
                        
                        clientList.setAll(filteredClients);
                        statusLabel.setText("Found " + filteredClients.size() + " clients matching '" + searchText + "'");
                    }

                    // Material CRUD Operations
                    private void saveMaterial() {
                        try {
                            Material material = createMaterialFromFields();
                            if (material != null && materialDAO.addMaterial(material)) {
                                showAlert("Success", "Material added successfully!");
                                loadMaterials();
                                clearMaterialFields();
                            } else {
                                showAlert("Error", "Failed to add material!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error adding material: " + e.getMessage());
                        }
                    }

                    private void updateMaterial() {
                        Material selectedMaterial = materialTable.getSelectionModel().getSelectedItem();
                        if (selectedMaterial == null) {
                            showAlert("Error", "Please select a material to update!");
                            return;
                        }

                        try {
                            Material material = createMaterialFromFields();
                            if (material != null && materialDAO.updateMaterial(material)) {
                                showAlert("Success", "Material updated successfully!");
                                loadMaterials();
                                clearMaterialFields();
                            } else {
                                showAlert("Error", "Failed to update material!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error updating material: " + e.getMessage());
                        }
                    }

                    private void deleteMaterial() {
                        Material selectedMaterial = materialTable.getSelectionModel().getSelectedItem();
                        if (selectedMaterial == null) {
                            showAlert("Error", "Please select a material to delete!");
                            return;
                        }

                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Confirm Deletion");
                        confirmAlert.setHeaderText("Delete Material");
                        confirmAlert.setContentText("Are you sure you want to delete this material?");

                        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                            if (materialDAO.deleteMaterial(selectedMaterial.getMaterialId())) {
                                showAlert("Success", "Material deleted successfully!");
                                loadMaterials();
                                clearMaterialFields();
                            } else {
                                showAlert("Error", "Failed to delete material!");
                            }
                        }
                    }

                    private Material createMaterialFromFields() {
                        try {
                            int materialId = Integer.parseInt(materialIdField.getText());
                            String name = materialNameField.getText();
                            double price = Double.parseDouble(materialPriceField.getText());

                            if (name.trim().isEmpty()) {
                                showAlert("Error", "Material name cannot be empty!");
                                return null;
                            }

                            if (price < 0) {
                                showAlert("Error", "Price cannot be negative!");
                                return null;
                            }

                            return new Material(materialId, name, price);

                        } catch (NumberFormatException e) {
                            showAlert("Error", "Please enter valid numeric values!");
                            return null;
                        }
                    }

                    private void populateMaterialFields(Material material) {
                        materialIdField.setText(String.valueOf(material.getMaterialId()));
                        materialNameField.setText(material.getMname());
                        materialPriceField.setText(String.valueOf(material.getPrice()));
                    }

                    private void clearMaterialFields() {
                        materialIdField.clear();
                        materialNameField.clear();
                        materialPriceField.clear();
                        materialTable.getSelectionModel().clearSelection();
                    }

                    // Material Utility Methods
                    private void sortMaterialsAsc() {
                        List<Material> sortedList = new ArrayList<>(materialList);
                        Collections.sort(sortedList, Comparator.comparing(Material::getMname));
                        materialList.setAll(sortedList);
                        statusLabel.setText("Materials sorted A-Z");
                    }

                    private void sortMaterialsDesc() {
                        List<Material> sortedList = new ArrayList<>(materialList);
                        Collections.sort(sortedList, (m1, m2) -> m2.getMname().compareToIgnoreCase(m1.getMname()));
                        materialList.setAll(sortedList);
                        statusLabel.setText("Materials sorted Z-A");
                    }

                    private void countMaterials() {
                        int count = materialList.size();
                        showAlert("Material Count", "Total number of materials: " + count);
                    }

                    private void maxMaterialPrice() {
                        if (materialList.isEmpty()) {
                            showAlert("No Data", "No materials found!");
                            return;
                        }
                        
                        Material maxPriceMaterial = Collections.max(materialList, Comparator.comparingDouble(Material::getPrice));
                        showAlert("Maximum Price", "Highest price: $" + String.format("%.2f", maxPriceMaterial.getPrice()) + 
                                  " (Material: " + maxPriceMaterial.getMname() + ")");
                    }

                    private void minMaterialPrice() {
                        if (materialList.isEmpty()) {
                            showAlert("No Data", "No materials found!");
                            return;
                        }
                        
                        Material minPriceMaterial = Collections.min(materialList, Comparator.comparingDouble(Material::getPrice));
                        showAlert("Minimum Price", "Lowest price: $" + String.format("%.2f", minPriceMaterial.getPrice()) + 
                                  " (Material: " + minPriceMaterial.getMname() + ")");
                    }

                    private void findMaterial() {
                        String searchText = findMaterialField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadMaterials();
                            return;
                        }

                        List<Material> filteredMaterials = new ArrayList<>();
                        for (Material material : materialDAO.getAllMaterials()) {
                            if (material.getMname().toLowerCase().contains(searchText) ||
                                String.valueOf(material.getMaterialId()).contains(searchText)) {
                                filteredMaterials.add(material);
                            }
                        }
                        
                        materialList.setAll(filteredMaterials);
                        statusLabel.setText("Found " + filteredMaterials.size() + " materials matching '" + searchText + "'");
                    }

                    // Supplier CRUD Operations (saving space, implementing key methods only)
                    private void saveSupplier() {
                        try {
                            Supplier supplier = createSupplierFromFields();
                            if (supplier != null && supplierDAO.addSupplier(supplier)) {
                                showAlert("Success", "Supplier added successfully!");
                                loadSuppliers();
                                clearSupplierFields();
                            } else {
                                showAlert("Error", "Failed to add supplier!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error adding supplier: " + e.getMessage());
                        }
                    }

                    private void updateSupplier() {
                        Supplier selectedSupplier = supplierTable.getSelectionModel().getSelectedItem();
                        if (selectedSupplier == null) {
                            showAlert("Error", "Please select a supplier to update!");
                            return;
                        }

                        try {
                            Supplier supplier = createSupplierFromFields();
                            if (supplier != null && supplierDAO.updateSupplier(supplier)) {
                                showAlert("Success", "Supplier updated successfully!");
                                loadSuppliers();
                                clearSupplierFields();
                            } else {
                                showAlert("Error", "Failed to update supplier!");
                            }
                        } catch (Exception e) {
                            showAlert("Error", "Error updating supplier: " + e.getMessage());
                        }
                    }

                    private void deleteSupplier() {
                        Supplier selectedSupplier = supplierTable.getSelectionModel().getSelectedItem();
                        if (selectedSupplier == null) {
                            showAlert("Error", "Please select a supplier to delete!");
                            return;
                        }

                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Confirm Deletion");
                        confirmAlert.setHeaderText("Delete Supplier");
                        confirmAlert.setContentText("Are you sure you want to delete this supplier?");

                        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                            if (supplierDAO.deleteSupplier(selectedSupplier.getSuppId())) {
                                showAlert("Success", "Supplier deleted successfully!");
                                loadSuppliers();
                                clearSupplierFields();
                            } else {
                                showAlert("Error", "Failed to delete supplier!");
                            }
                        }
                    }

                    private Supplier createSupplierFromFields() {
                        try {
                            int suppId = Integer.parseInt(supplierIdField.getText());
                            String suppName = supplierNameField.getText();
                            String location = supplierLocationField.getText();

                            if (suppName.trim().isEmpty() || location.trim().isEmpty()) {
                                showAlert("Error", "Supplier name and location cannot be empty!");
                                return null;
                            }

                            return new Supplier(suppId, suppName, location);

                        } catch (NumberFormatException e) {
                            showAlert("Error", "Please enter a valid supplier ID!");
                            return null;
                        }
                    }

                    private void populateSupplierFields(Supplier supplier) {
                        supplierIdField.setText(String.valueOf(supplier.getSuppId()));
                        supplierNameField.setText(supplier.getSuppName());
                        supplierLocationField.setText(supplier.getLocation());
                    }

                    private void clearSupplierFields() {
                        supplierIdField.clear();
                        supplierNameField.clear();
                        supplierLocationField.clear();
                        supplierTable.getSelectionModel().clearSelection();
                    }

                    // Supplier Utility Methods
                    private void sortSuppliersAsc() {
                        List<Supplier> sortedList = new ArrayList<>(supplierList);
                        Collections.sort(sortedList, Comparator.comparing(Supplier::getSuppName));
                        supplierList.setAll(sortedList);
                        statusLabel.setText("Suppliers sorted A-Z");
                    }

                    private void sortSuppliersDesc() {
                        List<Supplier> sortedList = new ArrayList<>(supplierList);
                        Collections.sort(sortedList, (s1, s2) -> s2.getSuppName().compareToIgnoreCase(s1.getSuppName()));
                        supplierList.setAll(sortedList);
                        statusLabel.setText("Suppliers sorted Z-A");
                    }

                    private void countSuppliers() {
                        int count = supplierList.size();
                        showAlert("Supplier Count", "Total number of suppliers: " + count);
                    }

                    private void findSupplier() {
                        String searchText = findSupplierField.getText().toLowerCase().trim();
                        if (searchText.isEmpty()) {
                            loadSuppliers();
                            return;
                        }

                        List<Supplier> filteredSuppliers = new ArrayList<>();
                        for (Supplier supplier : supplierDAO.getAllSuppliers()) {
                            if (supplier.getSuppName().toLowerCase().contains(searchText) ||
                                supplier.getLocation().toLowerCase().contains(searchText) ||
                                String.valueOf(supplier.getSuppId()).contains(searchText)) {
                                filteredSuppliers.add(supplier);
                            }
                        }
                        
                        supplierList.setAll(filteredSuppliers);
                        statusLabel.setText("Found " + filteredSuppliers.size() + " suppliers matching '" + searchText + "'");
                    }Table();
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
                        employeeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                populateEmployeeFields(newValue);
                            }
                        });
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
                        projectTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                populateProjectFields(newValue);
                            }
                        });
                    }

                    private void setupClientTable() {
                        clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
                        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));

                        clientTable.setItems(clientList);
                        clientTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                populateClientFields(newValue);
                            }
                        });
                    }

                    private void setupMaterialTable() {
                        materialIdColumn.setCellValueFactory(new PropertyValueFactory<>("materialId"));
                        materialNameColumn.setCellValueFactory(new PropertyValueFactory<>("mname"));
                        materialPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

                        materialTable.setItems(materialList);
                        materialTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                populateMaterialFields(newValue);
                            }
                        });
                    }

                    private void setupSupplierTable() {
                        supplierIdColumn.setCellValueFactory(new PropertyValueFactory<>("suppId"));
                        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("suppName"));
                        supplierLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

                        supplierTable.setItems(supplierList);
                        supplierTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                populateSupplierFields(newValue);
                            }
                        });
                    }

                    private void setupBranchTable() {
                        branchIdColumn.setCellValueFactory(new PropertyValueFactory<>("branchId"));
                        branchLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

                        branchTable.setItems(branchList);
                        branchTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                populateBranchFields(newValue);
                            }
                        });
                    }

                    private void setupRoleTable() {
                        roleIdColumn.setCellValueFactory(new PropertyValueFactory<>("roleId"));
                        roleTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

                        roleTable.setItems(roleList);
                        roleTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                populateRoleFields(newValue);
                            }
                        });
                    }

                    private void setupDepartmentTable() {
                        departmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("deptId"));
                        departmentNameColumn.setCellValueFactory(new PropertyValueFactory<>("deptName"));

                        departmentTable.setItems(departmentList);
                        departmentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                populateDepartmentFields(newValue);
                            }
                        });
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
                        phaseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                populatePhaseFields(newValue);
                            }
                        });
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
                        paymentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                populatePaymentFields(newValue);
                            }
                        });
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
                        phaseStatusComboBox.setItems(FXCollections.observableArrayList("Planning", "In Progress", "Completed", "On Hold", "Cancelled"));
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
                        paymentMethodComboBox.setItems(FXCollections.observableArrayList("Cash", "Credit Card", "Bank Transfer", "Check", "Online Payment"));
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
                    }}