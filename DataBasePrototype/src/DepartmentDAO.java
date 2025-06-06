import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    
    public boolean addDepartment(Department department) {
        String sql = "INSERT INTO Department (dept_id, dept_name) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, department.getDeptId());
            pstmt.setString(2, department.getDeptName());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding department: " + e.getMessage());
            return false;
        }
    }
    
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<Department>();
        String sql = "SELECT * FROM Department";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Department department = new Department();
                department.setDeptId(rs.getInt("dept_id"));
                department.setDeptName(rs.getString("dept_name"));
                departments.add(department);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving departments: " + e.getMessage());
        }
        
        return departments;
    }
    
    public Department getDepartmentById(int deptId) {
        String sql = "SELECT * FROM Department WHERE dept_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, deptId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Department department = new Department();
                    department.setDeptId(rs.getInt("dept_id"));
                    department.setDeptName(rs.getString("dept_name"));
                    return department;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving department: " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean updateDepartment(Department department) {
        String sql = "UPDATE Department SET dept_name = ? WHERE dept_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, department.getDeptName());
            pstmt.setInt(2, department.getDeptId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating department: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteDepartment(int deptId) {
        String sql = "DELETE FROM Department WHERE dept_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, deptId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting department: " + e.getMessage());
            return false;
        }
    }
}
