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

public class ProjectController implements Initializable {
    
    @FXML private TableView<Project> projectTable;
    @FXML private TableColumn<Project, Integer> projectIdColumn;
    @FXML private TableColumn<Project, String> projectNameColumn;
    @FXML private TableColumn<Project, String> projectLocationColumn;
    @FXML private TableColumn<Project, Double> costColumn;
    @FXML private TableColumn<Project, Double> revenueColumn;
    @FXML private TableColumn<Project, Integer> branchIdColumn;
    @FXML private TableColumn<Project, Integer> clientIdColumn;
    
    @FXML private TextField projectIdField;
    @FXML private TextField projectNameField;
    @FXML private TextField projectLocationField;
    @FXML private TextField costField;
    @FXML private TextField revenueField;
    @FXML private ComboBox<Branch> branchComboBox;
    @FXML private ComboBox<Client> clientComboBox;
    
    @FXML private Button addProjectBtn;
    @FXML private Button updateProjectBtn;
    @FXML private Button deleteProjectBtn;
    @FXML private Button refreshProjectBtn;
    @FXML private Button clearFieldsBtn;
    
    private ProjectDAO projectDAO;
    private BranchDAO branchDAO;
    private ClientDAO clientDAO;
    private ObservableList<Project> projectList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectDAO = new ProjectDAO();
        branchDAO = new BranchDAO();
        clientDAO = new ClientDAO();
        projectList = FXCollections.observableArrayList();
        
        setupProjectTable();
        setupComboBoxes();
        setupEventHandlers();
        loadProjects();
        loadComboBoxData();
    }
    
    private void setupProjectTable() {
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<Project, Integer>("projectId"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<Project, String>("pname"));
        projectLocationColumn.setCellValueFactory(new PropertyValueFactory<Project, String>("location"));
        costColumn.setCellValueFactory(new PropertyValueFactory<Project, Double>("cost"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory<Project, Double>("revenue"));
        branchIdColumn.setCellValueFactory(new PropertyValueFactory<Project, Integer>("branchId"));
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<Project, Integer>("clientId"));
        
        projectTable.setItems(projectList);
        
        // Add selection listener
        projectTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateFields(newValue);
                }
            }
        );
    }
    
    private void setupComboBoxes() {
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
        
        clientComboBox.setConverter(new StringConverter<Client>() {
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
    
    private void setupEventHandlers() {
        addProjectBtn.setOnAction(e -> addProject());
        updateProjectBtn.setOnAction(e -> updateProject());
        deleteProjectBtn.setOnAction(e -> deleteProject());
        refreshProjectBtn.setOnAction(e -> refreshData());
        clearFieldsBtn.setOnAction(e -> clearFields());
    }
    
    private void loadProjects() {
        projectList.clear();
        List<Project> projects = projectDAO.getAllProjects();
        projectList.addAll(projects);
    }
    
    private void loadComboBoxData() {
        // Load branches
        List<Branch> branches = branchDAO.getAllBranches();
        branchComboBox.setItems(FXCollections.observableArrayList(branches));
        
        // Load clients
        List<Client> clients = clientDAO.getAllClients();
        clientComboBox.setItems(FXCollections.observableArrayList(clients));
    }
    
    private void populateFields(Project project) {
        projectIdField.setText(String.valueOf(project.getProjectId()));
        projectNameField.setText(project.getPname());
        projectLocationField.setText(project.getLocation());
        costField.setText(String.valueOf(project.getCost()));
        revenueField.setText(String.valueOf(project.getRevenue()));
        
        // Set ComboBox selections
        branchComboBox.getItems().stream()
            .filter(branch -> branch.getBranchId() == project.getBranchId())
            .findFirst()
            .ifPresent(branchComboBox::setValue);
            
        clientComboBox.getItems().stream()
            .filter(client -> client.getClientId() == project.getClientId())
            .findFirst()
            .ifPresent(clientComboBox::setValue);
    }
    
    private void addProject() {
        try {
            Project project = createProjectFromFields();
            if (project != null && projectDAO.addProject(project)) {
                showAlert("Success", "Project added successfully!");
                refreshData();
                clearFields();
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
                refreshData();
                clearFields();
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
                refreshData();
                clearFields();
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
            
            if (branchComboBox.getValue() == null || clientComboBox.getValue() == null) {
                showAlert("Error", "Please select branch and client!");
                return null;
            }
            
            int branchId = branchComboBox.getValue().getBranchId();
            int clientId = clientComboBox.getValue().getClientId();
            
            return new Project(projectId, branchId, clientId, pname, location, cost, revenue);
            
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numeric values!");
            return null;
        }
    }
    
    private void refreshData() {
        loadProjects();
        loadComboBoxData();
    }
    
    private void clearFields() {
        projectIdField.clear();
        projectNameField.clear();
        projectLocationField.clear();
        costField.clear();
        revenueField.clear();
        branchComboBox.setValue(null);
        clientComboBox.setValue(null);
        projectTable.getSelectionModel().clearSelection();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
