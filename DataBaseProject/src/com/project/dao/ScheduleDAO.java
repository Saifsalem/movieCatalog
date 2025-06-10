package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Schedule;
import java.sql.*;
import java.util.*;

public class ScheduleDAO {
    
    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedule";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Schedule schedule = new Schedule(
                    rs.getInt("schedule_id"),
                    rs.getString("project_id"),
                    rs.getString("phase_id"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getString("task_details")
                );
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return schedules;
    }
    
    public boolean insertSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedule (project_id, phase_id, start_date, end_date, task_details) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, schedule.getProjectId());
            stmt.setString(2, schedule.getPhaseId());
            stmt.setString(3, schedule.getStartDate());
            stmt.setString(4, schedule.getEndDate());
            stmt.setString(5, schedule.getTaskDetails());
            
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
    
    public boolean updateSchedule(Schedule schedule) {
        String sql = "UPDATE schedule SET project_id = ?, phase_id = ?, start_date = ?, end_date = ?, task_details = ? WHERE schedule_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, schedule.getProjectId());
            stmt.setString(2, schedule.getPhaseId());
            stmt.setString(3, schedule.getStartDate());
            stmt.setString(4, schedule.getEndDate());
            stmt.setString(5, schedule.getTaskDetails());
            stmt.setInt(6, schedule.getScheduleId());
            
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
    
    public boolean deleteSchedule(int scheduleId) {
        String sql = "DELETE FROM schedule WHERE schedule_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, scheduleId);
            
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
    
    public int getScheduleCount() {
        String sql = "SELECT COUNT(*) as count FROM schedule";
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
    
    public int getScheduleCountByProject(String projectId) {
        String sql = "SELECT COUNT(*) as count FROM schedule WHERE project_id = ?";
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
    
    public int getScheduleCountByPhase(String phaseId) {
        String sql = "SELECT COUNT(*) as count FROM schedule WHERE phase_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, phaseId);
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
    
    public List<Schedule> getSchedulesSortedByStartDateAscending() {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedule ORDER BY start_date ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Schedule schedule = new Schedule(
                    rs.getInt("schedule_id"),
                    rs.getString("project_id"),
                    rs.getString("phase_id"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getString("task_details")
                );
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return schedules;
    }
    
    public List<Schedule> getSchedulesSortedByStartDateDescending() {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedule ORDER BY start_date DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Schedule schedule = new Schedule(
                    rs.getInt("schedule_id"),
                    rs.getString("project_id"),
                    rs.getString("phase_id"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getString("task_details")
                );
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return schedules;
    }
    
    public List<Schedule> getSchedulesSortedByProjectAscending() {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedule ORDER BY project_id ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Schedule schedule = new Schedule(
                    rs.getInt("schedule_id"),
                    rs.getString("project_id"),
                    rs.getString("phase_id"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getString("task_details")
                );
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return schedules;
    }
    
    public List<Schedule> getSchedulesSortedByProjectDescending() {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedule ORDER BY project_id DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Schedule schedule = new Schedule(
                    rs.getInt("schedule_id"),
                    rs.getString("project_id"),
                    rs.getString("phase_id"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getString("task_details")
                );
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return schedules;
    }
}