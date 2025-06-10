package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Phase;
import java.sql.*;
import java.util.*;

public class PhaseDAO {
    
    public List<Phase> getAllPhases() {
        List<Phase> phases = new ArrayList<>();
        String sql = "SELECT * FROM phase";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Phase phase = new Phase(
                    rs.getString("phase_id"),
                    rs.getString("project_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getString("status")
                );
                phases.add(phase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return phases;
    }
    
    public boolean insertPhase(Phase phase) {
        String sql = "INSERT INTO phase (phase_id, project_id, name, description, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, phase.getPhaseId());
            stmt.setString(2, phase.getProjectId());
            stmt.setString(3, phase.getName());
            stmt.setString(4, phase.getDescription());
            stmt.setString(5, phase.getStartDate());
            stmt.setString(6, phase.getEndDate());
            stmt.setString(7, phase.getStatus());
            
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
    
    public boolean updatePhase(Phase phase) {
        String sql = "UPDATE phase SET project_id = ?, name = ?, description = ?, start_date = ?, end_date = ?, status = ? WHERE phase_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, phase.getProjectId());
            stmt.setString(2, phase.getName());
            stmt.setString(3, phase.getDescription());
            stmt.setString(4, phase.getStartDate());
            stmt.setString(5, phase.getEndDate());
            stmt.setString(6, phase.getStatus());
            stmt.setString(7, phase.getPhaseId());
            
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
    
    public boolean deletePhase(String phaseId) {
        String sql = "DELETE FROM phase WHERE phase_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, phaseId);
            
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
    
    public int getPhaseCount() {
        String sql = "SELECT COUNT(*) as count FROM phase";
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
    
    public int getPhaseCountByProject(String projectId) {
        String sql = "SELECT COUNT(*) as count FROM phase WHERE project_id = ?";
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
    
    public int getCompletedPhaseCount() {
        String sql = "SELECT COUNT(*) as count FROM phase WHERE status = 'Completed'";
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
    
    public int getActivePhaseCount() {
        String sql = "SELECT COUNT(*) as count FROM phase WHERE status = 'Active'";
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
    
    public List<Phase> getPhasesSortedByNameAscending() {
        List<Phase> phases = new ArrayList<>();
        String sql = "SELECT * FROM phase ORDER BY name ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Phase phase = new Phase(
                    rs.getString("phase_id"),
                    rs.getString("project_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getString("status")
                );
                phases.add(phase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return phases;
    }
    
    public List<Phase> getPhasesSortedByNameDescending() {
        List<Phase> phases = new ArrayList<>();
        String sql = "SELECT * FROM phase ORDER BY name DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Phase phase = new Phase(
                    rs.getString("phase_id"),
                    rs.getString("project_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getString("status")
                );
                phases.add(phase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return phases;
    }
    
    public List<Phase> getPhasesSortedByDateAscending() {
        List<Phase> phases = new ArrayList<>();
        String sql = "SELECT * FROM phase ORDER BY start_date ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Phase phase = new Phase(
                    rs.getString("phase_id"),
                    rs.getString("project_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getString("status")
                );
                phases.add(phase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return phases;
    }
    
    public List<Phase> getPhasesSortedByDateDescending() {
        List<Phase> phases = new ArrayList<>();
        String sql = "SELECT * FROM phase ORDER BY start_date DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Phase phase = new Phase(
                    rs.getString("phase_id"),
                    rs.getString("project_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getString("status")
                );
                phases.add(phase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return phases;
    }
}