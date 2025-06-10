import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    
    public boolean addMaterial(Material material) {
        String sql = "INSERT INTO Material (material_id, mname, price) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, material.getMaterialId());
            pstmt.setString(2, material.getMname());
            pstmt.setDouble(3, material.getPrice());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding material: " + e.getMessage());
            return false;
        }
    }
    
    public List<Material> getAllMaterials() {
        List<Material> materials = new ArrayList<Material>();
        String sql = "SELECT * FROM Material";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Material material = new Material();
                material.setMaterialId(rs.getInt("material_id"));
                material.setMname(rs.getString("mname"));
                material.setPrice(rs.getDouble("price"));
                materials.add(material);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving materials: " + e.getMessage());
        }
        
        return materials;
    }
    
    public Material getMaterialById(int materialId) {
        String sql = "SELECT * FROM Material WHERE material_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, materialId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Material material = new Material();
                    material.setMaterialId(rs.getInt("material_id"));
                    material.setMname(rs.getString("mname"));
                    material.setPrice(rs.getDouble("price"));
                    return material;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving material: " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean updateMaterial(Material material) {
        String sql = "UPDATE Material SET mname = ?, price = ? WHERE material_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, material.getMname());
            pstmt.setDouble(2, material.getPrice());
            pstmt.setInt(3, material.getMaterialId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating material: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteMaterial(int materialId) {
        String sql = "DELETE FROM Material WHERE material_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, materialId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting material: " + e.getMessage());
            return false;
        }
    }
}
