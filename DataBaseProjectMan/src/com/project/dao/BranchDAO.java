package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Branch;
import java.sql.*;
import java.util.*;

public class BranchDAO {
    
    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();
        String sql = "SELECT * FROM branch";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Branch branch = new Branch(
                    rs.getString("branch_id"),
                    rs.getString("location")
                );
                branches.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return branches;
    }
    
    public boolean insertBranch(Branch branch) {
        String sql = "INSERT INTO branch (branch_id, location) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, branch.getBranchId());
            stmt.setString(2, branch.getLocation());
            
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
    
    public boolean updateBranch(Branch branch) {
        String sql = "UPDATE branch SET location = ? WHERE branch_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, branch.getLocation());
            stmt.setString(2, branch.getBranchId());
            
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
    
    public boolean deleteBranch(String branchId) {
        String sql = "DELETE FROM branch WHERE branch_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, branchId);
            
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
    
    public int getBranchCount() {
        String sql = "SELECT COUNT(*) as count FROM branch";
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
    
    public int getEmployeeCountByBranch(String branchId) {
        String sql = "SELECT COUNT(*) as count FROM employee WHERE branch_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, branchId);
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
    
    public List<Branch> getBranchesSortedByLocationAscending() {
        List<Branch> branches = new ArrayList<>();
        String sql = "SELECT * FROM branch ORDER BY location ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Branch branch = new Branch(
                    rs.getString("branch_id"),
                    rs.getString("location")
                );
                branches.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return branches;
    }
    
    public List<Branch> getBranchesSortedByLocationDescending() {
        List<Branch> branches = new ArrayList<>();
        String sql = "SELECT * FROM branch ORDER BY location DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Branch branch = new Branch(
                    rs.getString("branch_id"),
                    rs.getString("location")
                );
                branches.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return branches;
    }
    
    public List<Branch> getBranchesSortedByIdAscending() {
        List<Branch> branches = new ArrayList<>();
        String sql = "SELECT * FROM branch ORDER BY branch_id ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Branch branch = new Branch(
                    rs.getString("branch_id"),
                    rs.getString("location")
                );
                branches.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return branches;
    }
    
    public List<Branch> getBranchesSortedByIdDescending() {
        List<Branch> branches = new ArrayList<>();
        String sql = "SELECT * FROM branch ORDER BY branch_id DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Branch branch = new Branch(
                    rs.getString("branch_id"),
                    rs.getString("location")
                );
                branches.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return branches;
    }
}