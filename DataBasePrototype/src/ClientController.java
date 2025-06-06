import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    
    @FXML private TableView<Client> clientTable;
    @FXML private TableColumn<Client, Integer> clientIdColumn;
    @FXML private TableColumn<Client, String> clientNameColumn;
    
    @FXML private TextField clientIdField;
    @FXML private TextField clientNameField;
    
    @FXML private Button addBtn;
    @FXML private Button updateBtn;
    @FXML private Button deleteBtn;
    @FXML private Button refreshBtn;
    @FXML private Button clearBtn;
    
    private ClientDAO clientDAO;
    private ObservableList<Client> clientList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientDAO = new ClientDAO();
        clientList = FXCollections.observableArrayList();
        setupTable();
        setupEventHandlers();
        loadClients();
    }
    
    private void setupTable() {
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("clientId"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        clientTable.setItems(clientList);
        
        // Add selection listener
        clientTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    populateFields(newValue);
                }
            }
        );
    }
    
    private void setupEventHandlers() {
        addBtn.setOnAction(e -> addClient());
        updateBtn.setOnAction(e -> updateClient());
        deleteBtn.setOnAction(e -> deleteClient());
        refreshBtn.setOnAction(e -> refreshData());
        clearBtn.setOnAction(e -> clearFields());
    }
    
    private void loadClients() {
        clientList.clear();
        List<Client> clients = clientDAO.getAllClients();
        clientList.addAll(clients);
    }
    
    private void populateFields(Client client) {
        clientIdField.setText(String.valueOf(client.getClientId()));
        clientNameField.setText(client.getClientName());
    }
    
    private void addClient() {
        try {
            Client client = createClientFromFields();
            if (client != null && clientDAO.addClient(client)) {
                showAlert("Success", "Client added successfully!");
                refreshData();
                clearFields();
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
                refreshData();
                clearFields();
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
                refreshData();
                clearFields();
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
    
    private void refreshData() {
        loadClients();
    }
    
    private void clearFields() {
        clientIdField.clear();
        clientNameField.clear();
        clientTable.getSelectionModel().clearSelection();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
