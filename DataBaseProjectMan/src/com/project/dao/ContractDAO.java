package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Contract;
import java.sql.*;
import java.util.*;

public class ContractDAO {
    
    public List<Contract> getAllContracts() {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM contract";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Contract contract = new Contract(
                    rs.getInt("contract_id"),
                    rs.getString("project_id"),
                    rs.getString("client_id"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getDouble("total_value"),
                    rs.getString("status")
                );
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return contracts;
    }
    
    public boolean insertContract(Contract contract) {
        String sql = "INSERT INTO contract (project_id, client_id, start_date, end_date, total_value, status) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, contract.getProjectId());
            stmt.setString(2, contract.getClientId());
            stmt.setString(3, contract.getStartDate());
            stmt.setString(4, contract.getEndDate());
            stmt.setDouble(5, contract.getTotalValue());
            stmt.setString(6, contract.getStatus());
            
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
    
    public boolean updateContract(Contract contract) {
        String sql = "UPDATE contract SET project_id = ?, client_id = ?, start_date = ?, end_date = ?, total_value = ?, status = ? WHERE contract_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, contract.getProjectId());
            stmt.setString(2, contract.getClientId());
            stmt.setString(3, contract.getStartDate());
            stmt.setString(4, contract.getEndDate());
            stmt.setDouble(5, contract.getTotalValue());
            stmt.setString(6, contract.getStatus());
            stmt.setInt(7, contract.getContractId());
            
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
    
    public boolean deleteContract(int contractId) {
        String sql = "DELETE FROM contract WHERE contract_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, contractId);
            
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
    
    public double getMaxTotalValue() {
        String sql = "SELECT MAX(total_value) as max_value FROM contract";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("max_value");
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
    
    public double getMinTotalValue() {
        String sql = "SELECT MIN(total_value) as min_value FROM contract";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("min_value");
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
    
    public double getAverageTotalValue() {
        String sql = "SELECT AVG(total_value) as avg_value FROM contract";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("avg_value");
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
    
    public double getSumTotalValue() {
        String sql = "SELECT SUM(total_value) as sum_value FROM contract";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("sum_value");
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
    
    public int getContractCount() {
        String sql = "SELECT COUNT(*) as count FROM contract";
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
    
    public List<Contract> getContractsSortedAscending() {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM contract ORDER BY total_value ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Contract contract = new Contract(
                    rs.getInt("contract_id"),
                    rs.getString("project_id"),
                    rs.getString("client_id"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getDouble("total_value"),
                    rs.getString("status")
                );
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return contracts;
    }
    
    public List<Contract> getContractsSortedDescending() {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM contract ORDER BY total_value DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Contract contract = new Contract(
                    rs.getInt("contract_id"),
                    rs.getString("project_id"),
                    rs.getString("client_id"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getDouble("total_value"),
                    rs.getString("status")
                );
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return contracts;
    }
}