package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Supplier;
import java.sql.*;
import java.util.*;

public class SupplierDAO {
    
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Supplier supplier = new Supplier(
                    rs.getString("supp_id"),
                    rs.getString("supp_name"),
                    rs.getString("location")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return suppliers;
    }
    
    public boolean insertSupplier(Supplier supplier) {
        String sql = "INSERT INTO supplier (supp_id, supp_name, location) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, supplier.getSuppId());
            stmt.setString(2, supplier.getSuppName());
            stmt.setString(3, supplier.getLocation());
            
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
    
    public boolean updateSupplier(Supplier supplier) {
        String sql = "UPDATE supplier SET supp_name = ?, location = ? WHERE supp_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, supplier.getSuppName());
            stmt.setString(2, supplier.getLocation());
            stmt.setString(3, supplier.getSuppId());
            
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
    
    public boolean deleteSupplier(String suppId) {
        String sql = "DELETE FROM supplier WHERE supp_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, suppId);
            
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
    
    public int getSupplierCount() {
        String sql = "SELECT COUNT(*) as count FROM supplier";
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
    
    public int getSuppliersByLocationCount(String location) {
        String sql = "SELECT COUNT(*) as count FROM supplier WHERE location = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, location);
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
    
    public List<Supplier> getSuppliersSortedByNameAscending() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier ORDER BY supp_name ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Supplier supplier = new Supplier(
                    rs.getString("supp_id"),
                    rs.getString("supp_name"),
                    rs.getString("location")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return suppliers;
    }
    
    public List<Supplier> getSuppliersSortedByNameDescending() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier ORDER BY supp_name DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Supplier supplier = new Supplier(
                    rs.getString("supp_id"),
                    rs.getString("supp_name"),
                    rs.getString("location")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return suppliers;
    }
    
    public List<Supplier> getSuppliersSortedByLocationAscending() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier ORDER BY location ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Supplier supplier = new Supplier(
                    rs.getString("supp_id"),
                    rs.getString("supp_name"),
                    rs.getString("location")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return suppliers;
    }
    
    public List<Supplier> getSuppliersSortedByLocationDescending() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier ORDER BY location DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Supplier supplier = new Supplier(
                    rs.getString("supp_id"),
                    rs.getString("supp_name"),
                    rs.getString("location")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return suppliers;
    }
}