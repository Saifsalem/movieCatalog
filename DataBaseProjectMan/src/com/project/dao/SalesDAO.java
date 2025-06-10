package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Sales;
import java.sql.*;
import java.util.*;

public class SalesDAO {
    
    public List<Sales> getAllSales() {
        List<Sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM sales";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Sales sales = new Sales(
                    rs.getInt("sale_id"),
                    rs.getString("project_id"),
                    rs.getString("client_id"),
                    rs.getDouble("amount"),
                    rs.getString("issue_date"),
                    rs.getString("due_date")
                );
                salesList.add(sales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return salesList;
    }
    
    public boolean insertSales(Sales sales) {
        String sql = "INSERT INTO sales (project_id, client_id, amount, issue_date, due_date) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, sales.getProjectId());
            stmt.setString(2, sales.getClientId());
            stmt.setDouble(3, sales.getAmount());
            stmt.setString(4, sales.getIssueDate());
            stmt.setString(5, sales.getDueDate());
            
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
    
    public boolean updateSales(Sales sales) {
        String sql = "UPDATE sales SET project_id = ?, client_id = ?, amount = ?, issue_date = ?, due_date = ? WHERE sale_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, sales.getProjectId());
            stmt.setString(2, sales.getClientId());
            stmt.setDouble(3, sales.getAmount());
            stmt.setString(4, sales.getIssueDate());
            stmt.setString(5, sales.getDueDate());
            stmt.setInt(6, sales.getSaleId());
            
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
    
    public boolean deleteSales(int saleId) {
        String sql = "DELETE FROM sales WHERE sale_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, saleId);
            
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
    
    public double getMaxSalesAmount() {
        String sql = "SELECT MAX(amount) as max_amount FROM sales";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("max_amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return 0.0;
    }
    
    public double getMinSalesAmount() {
        String sql = "SELECT MIN(amount) as min_amount FROM sales";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("min_amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return 0.0;
    }
    
    public double getAverageSalesAmount() {
        String sql = "SELECT AVG(amount) as avg_amount FROM sales";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("avg_amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return 0.0;
    }
    
    public double getTotalSalesAmount() {
        String sql = "SELECT SUM(amount) as total_amount FROM sales";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("total_amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return 0.0;
    }
    
    public int getSalesCount() {
        String sql = "SELECT COUNT(*) as count FROM sales";
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
    
    public List<Sales> getSalesSortedByAmountAscending() {
        List<Sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM sales ORDER BY amount ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Sales sales = new Sales(
                    rs.getInt("sale_id"),
                    rs.getString("project_id"),
                    rs.getString("client_id"),
                    rs.getDouble("amount"),
                    rs.getString("issue_date"),
                    rs.getString("due_date")
                );
                salesList.add(sales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return salesList;
    }
    
    public List<Sales> getSalesSortedByAmountDescending() {
        List<Sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM sales ORDER BY amount DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Sales sales = new Sales(
                    rs.getInt("sale_id"),
                    rs.getString("project_id"),
                    rs.getString("client_id"),
                    rs.getDouble("amount"),
                    rs.getString("issue_date"),
                    rs.getString("due_date")
                );
                salesList.add(sales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return salesList;
    }
    
    public List<Sales> getSalesSortedByDateAscending() {
        List<Sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM sales ORDER BY issue_date ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Sales sales = new Sales(
                    rs.getInt("sale_id"),
                    rs.getString("project_id"),
                    rs.getString("client_id"),
                    rs.getDouble("amount"),
                    rs.getString("issue_date"),
                    rs.getString("due_date")
                );
                salesList.add(sales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return salesList;
    }
    
    public List<Sales> getSalesSortedByDateDescending() {
        List<Sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM sales ORDER BY issue_date DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Sales sales = new Sales(
                    rs.getInt("sale_id"),
                    rs.getString("project_id"),
                    rs.getString("client_id"),
                    rs.getDouble("amount"),
                    rs.getString("issue_date"),
                    rs.getString("due_date")
                );
                salesList.add(sales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return salesList;
    }
}