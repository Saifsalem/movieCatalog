package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Role;
import java.sql.*;
import java.util.*;

public class RoleDAO {
    
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM role";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Role role = new Role(
                    rs.getString("role_id"),
                    rs.getString("title")
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return roles;
    }
    
    public boolean insertRole(Role role) {
        String sql = "INSERT INTO role (role_id, title) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, role.getRoleId());
            stmt.setString(2, role.getTitle());
            
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
    
    public boolean updateRole(Role role) {
        String sql = "UPDATE role SET title = ? WHERE role_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, role.getTitle());
            stmt.setString(2, role.getRoleId());
            
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
    
    public boolean deleteRole(String roleId) {
        String sql = "DELETE FROM role WHERE role_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, roleId);
            
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
    
    public int getRoleCount() {
        String sql = "SELECT COUNT(*) as count FROM role";
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
    
    public List<Role> getRolesSortedByTitleAscending() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM role ORDER BY title ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Role role = new Role(
                    rs.getString("role_id"),
                    rs.getString("title")
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return roles;
    }
    
    public List<Role> getRolesSortedByTitleDescending() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM role ORDER BY title DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Role role = new Role(
                    rs.getString("role_id"),
                    rs.getString("title")
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return roles;
    }
    
    public List<Role> getRolesSortedByIdAscending() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM role ORDER BY role_id ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Role role = new Role(
                    rs.getString("role_id"),
                    rs.getString("title")
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return roles;
    }
    
    public List<Role> getRolesSortedByIdDescending() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM role ORDER BY role_id DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Role role = new Role(
                    rs.getString("role_id"),
                    rs.getString("title")
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return roles;
    }
}