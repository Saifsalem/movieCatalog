package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Payment;
import java.sql.*;
import java.util.*;

public class PaymentDAO {
    
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getString("from_client"),
                    rs.getString("to_supplier"),
                    rs.getDouble("amount"),
                    rs.getString("pdate"),
                    rs.getString("pmethod")
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return payments;
    }
    
    public boolean insertPayment(Payment payment) {
        String sql = "INSERT INTO payment (from_client, to_supplier, amount, pdate, pmethod) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, payment.getFromClient());
            stmt.setString(2, payment.getToSupplier());
            stmt.setDouble(3, payment.getAmount());
            stmt.setString(4, payment.getPdate());
            stmt.setString(5, payment.getPmethod());
            
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
    
    public boolean updatePayment(Payment payment) {
        String sql = "UPDATE payment SET from_client = ?, to_supplier = ?, amount = ?, pdate = ?, pmethod = ? WHERE payment_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, payment.getFromClient());
            stmt.setString(2, payment.getToSupplier());
            stmt.setDouble(3, payment.getAmount());
            stmt.setString(4, payment.getPdate());
            stmt.setString(5, payment.getPmethod());
            stmt.setInt(6, payment.getPaymentId());
            
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
    
    public boolean deletePayment(int paymentId) {
        String sql = "DELETE FROM payment WHERE payment_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, paymentId);
            
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
    
    public double getMaxPaymentAmount() {
        String sql = "SELECT MAX(amount) as max_amount FROM payment";
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
    
    public double getMinPaymentAmount() {
        String sql = "SELECT MIN(amount) as min_amount FROM payment";
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
    
    public double getAveragePaymentAmount() {
        String sql = "SELECT AVG(amount) as avg_amount FROM payment";
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
    
    public double getTotalPaymentAmount() {
        String sql = "SELECT SUM(amount) as total_amount FROM payment";
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
    
    public int getPaymentCount() {
        String sql = "SELECT COUNT(*) as count FROM payment";
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
    
    public int getPaymentCountByMethod(String pmethod) {
        String sql = "SELECT COUNT(*) as count FROM payment WHERE pmethod = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pmethod);
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
    
    public List<Payment> getPaymentsSortedByAmountAscending() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment ORDER BY amount ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getString("from_client"),
                    rs.getString("to_supplier"),
                    rs.getDouble("amount"),
                    rs.getString("pdate"),
                    rs.getString("pmethod")
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return payments;
    }
    
    public List<Payment> getPaymentsSortedByAmountDescending() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment ORDER BY amount DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getString("from_client"),
                    rs.getString("to_supplier"),
                    rs.getDouble("amount"),
                    rs.getString("pdate"),
                    rs.getString("pmethod")
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return payments;
    }
    
    public List<Payment> getPaymentsSortedByDateAscending() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment ORDER BY pdate ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getString("from_client"),
                    rs.getString("to_supplier"),
                    rs.getDouble("amount"),
                    rs.getString("pdate"),
                    rs.getString("pmethod")
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return payments;
    }
    
    public List<Payment> getPaymentsSortedByDateDescending() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment ORDER BY pdate DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getString("from_client"),
                    rs.getString("to_supplier"),
                    rs.getDouble("amount"),
                    rs.getString("pdate"),
                    rs.getString("pmethod")
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return payments;
    }
}