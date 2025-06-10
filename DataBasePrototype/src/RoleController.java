import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoleController implements Initializable {
    
    @FXML private TableView<Role> roleTable;
    @FXML private TableColumn<Role, Integer> roleIdColumn;
    @FXML private TableColumn<Role, String> titleColumn;
    
    @FXML private TextField roleIdField;
    @FXML private TextField titleField;
    
    @FXML private Button addBtn;
    @FXML private Button updateBtn;
    @FXML private Button deleteBtn;
    @FXML private Button refreshBtn;
    @FXML private Button clearBtn;
    
    private RoleDAO roleDAO;
    private ObservableList<Role> roleList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleDAO = new RoleDAO();
        roleList = FXCollections.observableArrayList();
        setupTable();
        setupEventHandlers();
        loadRoles();
    }
    
    private void setupTable() {
        roleIdColumn.setCellValueFactory(new PropertyValueFactory<Role, Integer>("roleId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Role, String>("title"));
        roleTable.setItems(roleList);
        
        // Add selection listener
        roleTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateFields(newValue);
                }
            }
        );
    }
    
    private void setupEventHandlers() {
        addBtn.setOnAction(e -> addRole());
        updateBtn.setOnAction(e -> updateRole());
        deleteBtn.setOnAction(e -> deleteRole());
        refreshBtn.setOnAction(e -> refreshData());
        clearBtn.setOnAction(e -> clearFields());
    }
    
    private void loadRoles() {
        roleList.clear();
        List<Role> roles = roleDAO.getAllRoles();
        roleList.addAll(roles);
    }
    
    private void populateFields(Role role) {
        roleIdField.setText(String.valueOf(role.getRoleId()));
        titleField.setText(role.getTitle());
    }
    
    private void addRole() {
        try {
            Role role = createRoleFromFields();
            if (role != null && roleDAO.addRole(role)) {
                showAlert("Success", "Role added successfully!");
                refreshData();
                clearFields();
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
                refreshData();
                clearFields();
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
        
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Delete Role");
        confirmAlert.setContentText("Are you sure you want to delete this role?");
        
        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            if (roleDAO.deleteRole(selectedRole.getRoleId())) {
                showAlert("Success", "Role deleted successfully!");
                refreshData();
                clearFields();
            } else {
                showAlert("Error", "Failed to delete role!");
            }
        }
    }
    
    private Role createRoleFromFields() {
        try {
            int roleId = Integer.parseInt(roleIdField.getText());
            String title = titleField.getText();
            
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
    
    private void refreshData() {
        loadRoles();
    }
    
    private void clearFields() {
        roleIdField.clear();
        titleField.clear();
        roleTable.getSelectionModel().clearSelection();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
