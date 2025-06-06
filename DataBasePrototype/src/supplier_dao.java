import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class supplier_dao {
    
    public boolean addSupplier(Supplier supplier) {
        String sql = "INSERT INTO Supplier (supp_id, supp_name, location) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, supplier.getSuppId());
            pstmt.setString(2, supplier.getSuppName());
            pstmt.setString(3, supplier.getLocation());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding supplier: " + e.getMessage());
            return false;
        }
    }
    
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<Supplier>();
        String sql = "SELECT * FROM Supplier";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSuppId(rs.getInt("supp_id"));
                supplier.setSuppName(rs.getString("supp_name"));
                supplier.setLocation(rs.getString("location"));
                suppliers.add(supplier);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving suppliers: " + e.getMessage());
        }
        
        return suppliers;
    }
    
    public Supplier getSupplierById(int suppId) {
        String sql = "SELECT * FROM Supplier WHERE supp_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, suppId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Supplier supplier = new Supplier();
                    supplier.setSuppId(rs.getInt("supp_id"));
                    supplier.setSuppName(rs.getString("supp_name"));
                    supplier.setLocation(rs.getString("location"));
                    return supplier;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving supplier: " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean updateSupplier(Supplier supplier) {
        String sql = "UPDATE Supplier SET supp_name = ?, location = ? WHERE supp_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, supplier.getSuppName());
            pstmt.setString(2, supplier.getLocation());
            pstmt.setInt(3, supplier.getSuppId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating supplier: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteSupplier(int suppId) {
        String sql = "DELETE FROM Supplier WHERE supp_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, suppId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting supplier: