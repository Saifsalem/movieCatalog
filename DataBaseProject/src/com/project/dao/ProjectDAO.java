package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Project;
import java.sql.*;
import java.util.*;

public class ProjectDAO {

	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM project";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Project project = new Project(
					rs.getString("project_id"), 
					rs.getString("branch_id"),
					rs.getString("client_id"), 
					rs.getString("pname"), 
					rs.getString("location"),
					rs.getDouble("cost"), 
					rs.getDouble("revenue")
				);
				projects.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projects;
	}

	public boolean insertProject(Project project) {
		String sql = "INSERT INTO project (project_id, branch_id, client_id, pname, location, cost, revenue) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, project.getProjectId());
			stmt.setString(2, project.getBranchId());
			stmt.setString(3, project.getClientId());
			stmt.setString(4, project.getPname());
			stmt.setString(5, project.getLocation());
			stmt.setDouble(6, project.getCost());
			stmt.setDouble(7, project.getRevenue());

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

	public boolean updateProject(Project project) {
		String sql = "UPDATE project SET branch_id = ?, client_id = ?, pname = ?, location = ?, cost = ?, revenue = ? WHERE project_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, project.getBranchId());
			stmt.setString(2, project.getClientId());
			stmt.setString(3, project.getPname());
			stmt.setString(4, project.getLocation());
			stmt.setDouble(5, project.getCost());
			stmt.setDouble(6, project.getRevenue());
			stmt.setString(7, project.getProjectId());

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

	public boolean deleteProject(String projectId) {
		String sql = "DELETE FROM project WHERE project_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);

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

	public int getProjectCount() {
		String sql = "SELECT COUNT(*) as count FROM project";
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

	public double getTotalCost() {
		String sql = "SELECT SUM(cost) as total_cost FROM project";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("total_cost");
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

	public double getTotalRevenue() {
		String sql = "SELECT SUM(revenue) as total_revenue FROM project";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("total_revenue");
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

	public double getTotalProfit() {
		return getTotalRevenue() - getTotalCost();
	}

	public double getAverageCost() {
		String sql = "SELECT AVG(cost) as avg_cost FROM project";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("avg_cost");
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

	public double getAverageRevenue() {
		String sql = "SELECT AVG(revenue) as avg_revenue FROM project";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("avg_revenue");
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

	public List<Project> getProjectsSortedByNameAscending() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM project ORDER BY pname ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Project project = new Project(
					rs.getString("project_id"), 
					rs.getString("branch_id"),
					rs.getString("client_id"), 
					rs.getString("pname"), 
					rs.getString("location"),
					rs.getDouble("cost"), 
					rs.getDouble("revenue")
				);
				projects.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projects;
	}

	public List<Project> getProjectsSortedByNameDescending() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM project ORDER BY pname DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Project project = new Project(
					rs.getString("project_id"), 
					rs.getString("branch_id"),
					rs.getString("client_id"), 
					rs.getString("pname"), 
					rs.getString("location"),
					rs.getDouble("cost"), 
					rs.getDouble("revenue")
				);
				projects.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projects;
	}

	public List<Project> getProjectsSortedByCostAscending() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM project ORDER BY cost ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Project project = new Project(
					rs.getString("project_id"), 
					rs.getString("branch_id"),
					rs.getString("client_id"), 
					rs.getString("pname"), 
					rs.getString("location"),
					rs.getDouble("cost"), 
					rs.getDouble("revenue")
				);
				projects.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projects;
	}

	public List<Project> getProjectsSortedByCostDescending() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM project ORDER BY cost DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Project project = new Project(
					rs.getString("project_id"), 
					rs.getString("branch_id"),
					rs.getString("client_id"), 
					rs.getString("pname"), 
					rs.getString("location"),
					rs.getDouble("cost"), 
					rs.getDouble("revenue")
				);
				projects.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projects;
	}

	public List<Project> getProjectsSortedByRevenueAscending() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM project ORDER BY revenue ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Project project = new Project(
					rs.getString("project_id"), 
					rs.getString("branch_id"),
					rs.getString("client_id"), 
					rs.getString("pname"), 
					rs.getString("location"),
					rs.getDouble("cost"), 
					rs.getDouble("revenue")
				);
				projects.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projects;
	}

	public List<Project> getProjectsSortedByRevenueDescending() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM project ORDER BY revenue DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Project project = new Project(
					rs.getString("project_id"), 
					rs.getString("branch_id"),
					rs.getString("client_id"), 
					rs.getString("pname"), 
					rs.getString("location"),
					rs.getDouble("cost"), 
					rs.getDouble("revenue")
				);
				projects.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projects;
	}
}