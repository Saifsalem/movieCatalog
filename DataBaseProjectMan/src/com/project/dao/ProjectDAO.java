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
				Project project = new Project(rs.getString("project_id"), rs.getString("name"),
						rs.getString("description"), rs.getString("start_date"), rs.getString("end_date"),
						rs.getString("status"));
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
		String sql = "INSERT INTO project (project_id, name, description, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, project.getProjectId());
			stmt.setString(2, project.getName());
			stmt.setString(3, project.getDescription());
			stmt.setString(4, project.getStartDate());
			stmt.setString(5, project.getEndDate());
			stmt.setString(6, project.getStatus());

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
		String sql = "UPDATE project SET name = ?, description = ?, start_date = ?, end_date = ?, status = ? WHERE project_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, project.getName());
			stmt.setString(2, project.getDescription());
			stmt.setString(3, project.getStartDate());
			stmt.setString(4, project.getEndDate());
			stmt.setString(5, project.getStatus());
			stmt.setString(6, project.getProjectId());

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

	public int getActiveProjectCount() {
		String sql = "SELECT COUNT(*) as count FROM project WHERE status = 'Active'";
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

	public int getCompletedProjectCount() {
		String sql = "SELECT COUNT(*) as count FROM project WHERE status = 'Completed'";
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

	public List<Project> getProjectsSortedByNameAscending() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM project ORDER BY name ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Project project = new Project(rs.getString("project_id"), rs.getString("name"),
						rs.getString("description"), rs.getString("start_date"), rs.getString("end_date"),
						rs.getString("status"));
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
		String sql = "SELECT * FROM project ORDER BY name DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Project project = new Project(rs.getString("project_id"), rs.getString("name"),
						rs.getString("description"), rs.getString("start_date"), rs.getString("end_date"),
						rs.getString("status"));
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

	public List<Project> getProjectsSortedByDateAscending() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM project ORDER BY start_date ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Project project = new Project(rs.getString("project_id"), rs.getString("name"),
						rs.getString("description"), rs.getString("start_date"), rs.getString("end_date"),
						rs.getString("status"));
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

	public List<Project> getProjectsSortedByDateDescending() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM project ORDER BY start_date DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Project project = new Project(rs.getString("project_id"), rs.getString("name"),
						rs.getString("description"), rs.getString("start_date"), rs.getString("end_date"),
						rs.getString("status"));
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