import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    
    public boolean addClient(Client client) {
        String sql = "INSERT INTO Client (client_id, client_name) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, client.getClientId());
            pstmt.setString(2, client.getClientName());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding client: " + e.getMessage());
            return false;
        }
    }
    
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<Client>();
        String sql = "SELECT * FROM Client";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Client client = new Client();
                client.setClientId(rs.getInt("client_id"));
                client.setClientName(rs.getString("client_name"));
                clients.add(client);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving clients: " + e.getMessage());
        }
        
        return clients;
    }
    
    public Client getClientById(int clientId) {
        String sql = "SELECT * FROM Client WHERE client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Client client = new Client();
                    client.setClientId(rs.getInt("client_id"));
                    client.setClientName(rs.getString("client_name"));
                    return client;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving client: " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean updateClient(Client client) {
        String sql = "UPDATE Client SET client_name = ? WHERE client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, client.getClientName());
            pstmt.setInt(2, client.getClientId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating client: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteClient(int clientId) {
        String sql = "DELETE FROM Client WHERE client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clientId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting client: " + e.getMessage());
            return false;
        }
    }
}
