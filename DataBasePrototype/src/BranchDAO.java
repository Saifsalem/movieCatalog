import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO {
    
    public boolean addBranch(Branch branch) {
        String sql = "INSERT INTO Branch (branch_id, location) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, branch.getBranchId());
            pstmt.setString(2, branch.getLocation());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding branch: " + e.getMessage());
            return false;
        }
    }
    
    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();
        String sql = "SELECT * FROM Branch";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Branch branch = new Branch();
                branch.setBranchId(rs.getInt("branch_id"));
                branch.setLocation(rs.getString("location"));
                branches.add(branch);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving branches: " + e.getMessage());
        }
        
        return branches;
    }
    
    public Branch getBranchById(int branchId) {
        String sql = "SELECT * FROM Branch WHERE branch_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, branchId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Branch branch = new Branch();
                    branch.setBranchId(rs.getInt("branch_id"));
                    branch.setLocation(rs.getString("location"));
                    return branch;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving branch: " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean updateBranch(Branch branch) {
        String sql = "UPDATE Branch SET location = ? WHERE branch_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, branch.getLocation());
            pstmt.setInt(2, branch.getBranchId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating branch: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteBranch(int branchId) {
        String sql = "DELETE FROM Branch WHERE branch_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, branchId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting branch: " + e.getMessage());
            return false;
        }
    }
}
