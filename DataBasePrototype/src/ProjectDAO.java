
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    
    public boolean addProject(Project project) {
        String sql = "INSERT INTO Projects (project_id, branch_id, client_id, pname, location, cost, revenue) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, project.getProjectId());
            pstmt.setInt(2, project.getBranchId());
            pstmt.setInt(3, project.getClientId());
            pstmt.setString(4, project.getPname());
            pstmt.setString(5, project.getLocation());
            pstmt.setDouble(6, project.getCost());
            pstmt.setDouble(7, project.getRevenue());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding project: " + e.getMessage());
            return false;
        }
    }
    
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM Projects";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getInt("project_id"));
                project.setBranchId(rs.getInt("branch_id"));
                project.setClientId(rs.getInt("client_id"));
                project.setPname(rs.getString("pname"));
                project.setLocation(rs.getString("location"));
                project.setCost(rs.getDouble("cost"));
                project.setRevenue(rs.getDouble("revenue"));
                projects.add(project);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving projects: " + e.getMessage());
        }
        
        return projects;
    }
    
    public Project getProjectById(int projectId) {
        String sql = "SELECT * FROM Projects WHERE project_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, projectId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Project project = new Project();
                    project.setProjectId(rs.getInt("project_id"));
                    project.setBranchId(rs.getInt("branch_id"));
                    project.setClientId(rs.getInt("client_id"));
                    project.setPname(rs.getString("pname"));
                    project.setLocation(rs.getString("location"));
                    project.setCost(rs.getDouble("cost"));
                    project.setRevenue(rs.getDouble("revenue"));
                    return project;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving project: " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean updateProject(Project project) {
        String sql = "UPDATE Projects SET branch_id = ?, client_id = ?, pname = ?, location = ?, cost = ?, revenue = ? WHERE project_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, project.getBranchId());
            pstmt.setInt(2, project.getClientId());
            pstmt.setString(3, project.getPname());
            pstmt.setString(4, project.getLocation());
            pstmt.setDouble(5, project.getCost());
            pstmt.setDouble(6, project.getRevenue());
            pstmt.setInt(7, project.getProjectId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating project: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteProject(int projectId) {
        String sql = "DELETE FROM Projects WHERE project_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, projectId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting project: " + e.getMessage());
            return false;
        }
    }
    
    public List<Project> getProjectsByBranch(int branchId) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM Projects WHERE branch_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, branchId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setProjectId(rs.getInt("project_id"));
                    project.setBranchId(rs.getInt("branch_id"));
                    project.setClientId(rs.getInt("client_id"));
                    project.setPname(rs.getString("pname"));
                    project.setLocation(rs.getString("location"));
                    project.setCost(rs.getDouble("cost"));
                    project.setRevenue(rs.getDouble("revenue"));
                    projects.add(project);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving projects by branch: " + e.getMessage());
        }
        
        return projects;
    }
    
    public List<Project> getProjectsByClient(int clientId) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM Projects WHERE client_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setProjectId(rs.getInt("project_id"));
                    project.setBranchId(rs.getInt("branch_id"));
                    project.setClientId(rs.getInt("client_id"));
                    project.setPname(rs.getString("pname"));
                    project.setLocation(rs.getString("location"));
                    project.setCost(rs.getDouble("cost"));
                    project.setRevenue(rs.getDouble("revenue"));
                    projects.add(project);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving projects by client: " + e.getMessage());
        }
        
        return projects;
    }
}
