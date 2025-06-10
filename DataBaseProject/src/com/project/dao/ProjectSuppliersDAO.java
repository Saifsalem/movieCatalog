package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.ProjectSuppliers;
import java.sql.*;
import java.util.*;

public class ProjectSuppliersDAO {
    
    public List<ProjectSuppliers> getAllProjectSuppliers() {
        List<ProjectSuppliers> projectSuppliers = new ArrayList<>();
        String sql = "SELECT * FROM project_suppliers";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ProjectSuppliers ps = new ProjectSuppliers(
                    rs.getString("project_id"),
                    rs.getString("supplier_id")
                );
                projectSuppliers.add(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return projectSuppliers;
    }
    
    public boolean insertProjectSupplier(ProjectSuppliers projectSupplier) {
        String sql = "INSERT INTO project_suppliers (project_id, supplier_id) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, projectSupplier.getProjectId());
            stmt.setString(2, projectSupplier.getSupplierId());
            
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
    
    public boolean deleteProjectSupplier(String projectId, String supplierId) {
        String sql = "DELETE FROM project_suppliers WHERE project_id = ? AND supplier_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, projectId);
            stmt.setString(2, supplierId);
            
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
    
    public int getTotalProjectSuppliersCount() {
        String sql = "SELECT COUNT(*) as count FROM project_suppliers";
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
    
    public int getSupplierCountByProject(String projectId) {
        String sql = "SELECT COUNT(*) as count FROM project_suppliers WHERE project_id = ?";
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
    
    public int getProjectCountBySupplier(String supplierId) {
        String sql = "SELECT COUNT(*) as count FROM project_suppliers WHERE supplier_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, supplierId);
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
    
    public List<ProjectSuppliers> getProjectSuppliersSortedByProjectAscending() {
        List<ProjectSuppliers> projectSuppliers = new ArrayList<>();
        String sql = "SELECT * FROM project_suppliers ORDER BY project_id ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ProjectSuppliers ps = new ProjectSuppliers(
                    rs.getString("project_id"),
                    rs.getString("supplier_id")
                );
                projectSuppliers.add(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return projectSuppliers;
    }
    
    public List<ProjectSuppliers> getProjectSuppliersSortedByProjectDescending() {
        List<ProjectSuppliers> projectSuppliers = new ArrayList<>();
        String sql = "SELECT * FROM project_suppliers ORDER BY project_id DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ProjectSuppliers ps = new ProjectSuppliers(
                    rs.getString("project_id"),
                    rs.getString("supplier_id")
                );
                projectSuppliers.add(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return projectSuppliers;
    }
    
    public List<ProjectSuppliers> getProjectSuppliersSortedBySupplierAscending() {
        List<ProjectSuppliers> projectSuppliers = new ArrayList<>();
        String sql = "SELECT * FROM project_suppliers ORDER BY supplier_id ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ProjectSuppliers ps = new ProjectSuppliers(
                    rs.getString("project_id"),
                    rs.getString("supplier_id")
                );
                projectSuppliers.add(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return projectSuppliers;
    }
    
    public List<ProjectSuppliers> getProjectSuppliersSortedBySupplierDescending() {
        List<ProjectSuppliers> projectSuppliers = new ArrayList<>();
        String sql = "SELECT * FROM project_suppliers ORDER BY supplier_id DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ProjectSuppliers ps = new ProjectSuppliers(
                    rs.getString("project_id"),
                    rs.getString("supplier_id")
                );
                projectSuppliers.add(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return projectSuppliers;
    }
}