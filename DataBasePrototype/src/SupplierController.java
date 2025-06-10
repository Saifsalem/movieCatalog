import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {
    
    @FXML private TableView<Supplier> supplierTable;
    @FXML private TableColumn<Supplier, Integer> suppIdColumn;
    @FXML private TableColumn<Supplier, String> suppNameColumn;
    @FXML private TableColumn<Supplier, String> locationColumn;
    
    @FXML private TextField suppIdField;
    @FXML private TextField suppNameField;
    @FXML private TextField locationField;
    
    @FXML private Button addBtn;
    @FXML private Button updateBtn;
    @FXML private Button deleteBtn;
    @FXML private Button refreshBtn;
    @FXML private Button clearBtn;
    
    private SupplierDAO supplierDAO;
    private ObservableList<Supplier> supplierList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supplierDAO = new SupplierDAO();
        supplierList = FXCollections.observableArrayList();
        setupTable();
        setupEventHandlers();
        loadSuppliers();
    }
    
    private void setupTable() {
        suppIdColumn.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("suppId"));
        suppNameColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("suppName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("location"));
        supplierTable.setItems(supplierList);
        
        // Add selection listener
        supplierTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateFields(newValue);
                }
            }
        );
    }
    
    private void setupEventHandlers() {
        addBtn.setOnAction(e -> addSupplier());
        updateBtn.setOnAction(e -> updateSupplier());
        deleteBtn.setOnAction(e -> deleteSupplier());
        refreshBtn.setOnAction(e -> refreshData());
        clearBtn.setOnAction(e -> clearFields());
    }
    
    private void loadSuppliers() {
        supplierList.clear();
        List<Supplier> suppliers = supplierDAO.getAllSuppliers();
        supplierList.addAll(suppliers);
    }
    
    private void populateFields(Supplier supplier) {
        suppIdField.setText(String.valueOf(supplier.getSuppId()));
        suppNameField.setText(supplier.getSuppName());
        locationField.setText(supplier.getLocation());
    }
    
    private void addSupplier() {
        try {
            Supplier supplier = createSupplierFromFields();
            if (supplier != null && supplierDAO.addSupplier(supplier)) {
                showAlert("Success", "Supplier added successfully!");
                refreshData();
                clearFields();
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
                refreshData();
                clearFields();
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
                refreshData();
                clearFields();
            } else {
                showAlert("Error", "Failed to delete supplier!");
            }
        }
    }
    
    private Supplier createSupplierFromFields() {
        try {
            int suppId = Integer.parseInt(suppIdField.getText());
            String suppName = suppNameField.getText();
            String location = locationField.getText();
            
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
    
    private void refreshData() {
        loadSuppliers();
    }
    
    private void clearFields() {
        suppIdField.clear();
        suppNameField.clear();
        locationField.clear();
        supplierTable.getSelectionModel().clearSelection();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
