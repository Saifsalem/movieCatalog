import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    
    public boolean addRole(Role role) {
        String sql = "INSERT INTO Role (role_id, title) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, role.getRoleId());
            pstmt.setString(2, role.getTitle());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding role: " + e.getMessage());
            return false;
        }
    }
    
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<Role>();
        String sql = "SELECT * FROM Role";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getInt("role_id"));
                role.setTitle(rs.getString("title"));
                roles.add(role);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving roles: " + e.getMessage());
        }
        
        return roles;
    }
    
    public Role getRoleById(int roleId) {
        String sql = "SELECT * FROM Role WHERE role_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, roleId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Role role = new Role();
                    role.setRoleId(rs.getInt("role_id"));
                    role.setTitle(rs.getString("title"));
                    return role;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving role: " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean updateRole(Role role) {
        String sql = "UPDATE Role SET title = ? WHERE role_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, role.getTitle());
            pstmt.setInt(2, role.getRoleId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating role: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteRole(int roleId) {
        String sql = "DELETE FROM Role WHERE role_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, roleId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting role: " + e.getMessage());
            return false;
        }
    }
}
