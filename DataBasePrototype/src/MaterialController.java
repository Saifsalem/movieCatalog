import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MaterialController implements Initializable {
    
    @FXML private TableView<Material> materialTable;
    @FXML private TableColumn<Material, Integer> materialIdColumn;
    @FXML private TableColumn<Material, String> nameColumn;
    @FXML private TableColumn<Material, Double> priceColumn;
    
    @FXML private TextField materialIdField;
    @FXML private TextField nameField;
    @FXML private TextField priceField;
    
    @FXML private Button addBtn;
    @FXML private Button updateBtn;
    @FXML private Button deleteBtn;
    @FXML private Button refreshBtn;
    @FXML private Button clearBtn;
    
    private MaterialDAO materialDAO;
    private ObservableList<Material> materialList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        materialDAO = new MaterialDAO();
        materialList = FXCollections.observableArrayList();
        setupTable();
        setupEventHandlers();
        loadMaterials();
    }
    
    private void setupTable() {
        materialIdColumn.setCellValueFactory(new PropertyValueFactory<Material, Integer>("materialId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Material, String>("mname"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Material, Double>("price"));
        materialTable.setItems(materialList);
        
        // Add selection listener
        materialTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateFields(newValue);
                }
            }
        );
    }
    
    private void setupEventHandlers() {
        addBtn.setOnAction(e -> addMaterial());
        updateBtn.setOnAction(e -> updateMaterial());
        deleteBtn.setOnAction(e -> deleteMaterial());
        refreshBtn.setOnAction(e -> refreshData());
        clearBtn.setOnAction(e -> clearFields());
    }
    
    private void loadMaterials() {
        materialList.clear();
        List<Material> materials = materialDAO.getAllMaterials();
        materialList.addAll(materials);
    }
    
    private void populateFields(Material material) {
        materialIdField.setText(String.valueOf(material.getMaterialId()));
        nameField.setText(material.getMname());
        priceField.setText(String.valueOf(material.getPrice()));
    }
    
    private void addMaterial() {
        try {
            Material material = createMaterialFromFields();
            if (material != null && materialDAO.addMaterial(material)) {
                showAlert("Success", "Material added successfully!");
                refreshData();
                clearFields();
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
                refreshData();
                clearFields();
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
                refreshData();
                clearFields();
            } else {
                showAlert("Error", "Failed to delete material!");
            }
        }
    }
    
    private Material createMaterialFromFields() {
        try {
            int materialId = Integer.parseInt(materialIdField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            
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
    
    private void refreshData() {
        loadMaterials();
    }
    
    private void clearFields() {
        materialIdField.clear();
        nameField.clear();
        priceField.clear();
        materialTable.getSelectionModel().clearSelection();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
