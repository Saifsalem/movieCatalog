package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.WorksOn;
import java.sql.*;
import java.util.*;

public class WorksOnDAO {
    
    public List<WorksOn> getAllWorksOn() {
        List<WorksOn> worksOnList = new ArrayList<>();
        String sql = "SELECT * FROM works_on";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                WorksOn worksOn = new WorksOn(
                    rs.getInt("emp_id"),
                    rs.getString("project_id"),
                    rs.getString("role")
                );
                worksOnList.add(worksOn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return worksOnList;
    }
    
    public boolean insertWorksOn(WorksOn worksOn) {
        String sql = "INSERT INTO works_on (emp_id, project_id, role) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, worksOn.getEmpId());
            stmt.setString(2, worksOn.getProjectId());
            stmt.setString(3, worksOn.getRole());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
    }
    
    public boolean updateWorksOn(WorksOn worksOn) {
        String sql = "UPDATE works_on SET role = ? WHERE emp_id = ? AND project_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, worksOn.getRole());
            stmt.setInt(2, worksOn.getEmpId());
            stmt.setString(3, worksOn.getProjectId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
    }
    
    public boolean deleteWorksOn(int empId, String projectId) {
        String sql = "DELETE FROM works_on WHERE emp_id = ? AND project_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, empId);
            stmt.setString(2, projectId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
    }
    
    public int getTotalWorksOnCount() {
        String sql = "SELECT COUNT(*) as count FROM works_on";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return 0;
    }
    
    public int getEmployeeCountByProject(String projectId) {
        String sql = "SELECT COUNT(*) as count FROM works_on WHERE project_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, projectId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return 0;
    }
    
    public int getProjectCountByEmployee(int empId) {
        String sql = "SELECT COUNT(*) as count FROM works_on WHERE emp_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, empId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return 0;
    }
    
    public int getCountByRole(String role) {
        String sql = "SELECT COUNT(*) as count FROM works_on WHERE role = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, role);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return 0;
    }
    
    public List<WorksOn> getWorksOnSortedByEmpIdAscending() {
        List<WorksOn> worksOnList = new ArrayList<>();
        String sql = "SELECT * FROM works_on ORDER BY emp_id ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                WorksOn worksOn = new WorksOn(
                    rs.getInt("emp_id"),
                    rs.getString("project_id"),
                    rs.getString("role")
                );
                worksOnList.add(worksOn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return worksOnList;
    }
    
    public List<WorksOn> getWorksOnSortedByEmpIdDescending() {
        List<WorksOn> worksOnList = new ArrayList<>();
        String sql = "SELECT * FROM works_on ORDER BY emp_id DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                WorksOn worksOn = new WorksOn(
                    rs.getInt("emp_id"),
                    rs.getString("project_id"),
                    rs.getString("role")
                );
                worksOnList.add(worksOn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return worksOnList;
    }
    
    public List<WorksOn> getWorksOnSortedByProjectAscending() {
        List<WorksOn> worksOnList = new ArrayList<>();
        String sql = "SELECT * FROM works_on ORDER BY project_id ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                WorksOn worksOn = new WorksOn(
                    rs.getInt("emp_id"),
                    rs.getString("project_id"),
                    rs.getString("role")
                );
                worksOnList.add(worksOn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return worksOnList;
    }
    
    public List<WorksOn> getWorksOnSortedByProjectDescending() {
        List<WorksOn> worksOnList = new ArrayList<>();
        String sql = "SELECT * FROM works_on ORDER BY project_id DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                WorksOn worksOn = new WorksOn(
                    rs.getInt("emp_id"),
                    rs.getString("project_id"),
                    rs.getString("role")
                );
                worksOnList.add(worksOn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return worksOnList;
    }
    
    public List<WorksOn> getWorksOnSortedByRoleAscending() {
        List<WorksOn> worksOnList = new ArrayList<>();
        String sql = "SELECT * FROM works_on ORDER BY role ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                WorksOn worksOn = new WorksOn(
                    rs.getInt("emp_id"),
                    rs.getString("project_id"),
                    rs.getString("role")
                );
                worksOnList.add(worksOn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return worksOnList;
    }
    
    public List<WorksOn> getWorksOnSortedByRoleDescending() {
        