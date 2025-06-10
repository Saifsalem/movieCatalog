package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.ProjectMaterials;
import java.sql.*;
import java.util.*;

public class ProjectMaterialsDAO {

	public List<ProjectMaterials> getAllProjectMaterials() {
		List<ProjectMaterials> projectMaterials = new ArrayList<>();
		String sql = "SELECT * FROM project_materials";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				ProjectMaterials pm = new ProjectMaterials(rs.getString("project_id"), rs.getInt("material_id"),
						rs.getInt("quantity"));
				projectMaterials.add(pm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projectMaterials;
	}

	public boolean insertProjectMaterial(ProjectMaterials projectMaterial) {
		String sql = "INSERT INTO project_materials (project_id, material_id, quantity) VALUES (?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, projectMaterial.getProjectId());
			stmt.setInt(2, projectMaterial.getMaterialId());
			stmt.setInt(3, projectMaterial.getQuantity());

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

	public boolean updateProjectMaterial(ProjectMaterials projectMaterial) {
		String sql = "UPDATE project_materials SET quantity = ? WHERE project_id = ? AND material_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, projectMaterial.getQuantity());
			stmt.setString(2, projectMaterial.getProjectId());
			stmt.setInt(3, projectMaterial.getMaterialId());

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

	public boolean deleteProjectMaterial(String projectId, int materialId) {
		String sql = "DELETE FROM project_materials WHERE project_id = ? AND material_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			stmt.setInt(2, materialId);

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

	public int getTotalMaterialsCount() {
		String sql = "SELECT COUNT(*) as count FROM project_materials";
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

	public int getTotalQuantity() {
		String sql = "SELECT SUM(quantity) as total_quantity FROM project_materials";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("total_quantity");
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

	public int getMaxQuantity() {
		String sql = "SELECT MAX(quantity) as max_quantity FROM project_materials";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("max_quantity");
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

	public int getMinQuantity() {
		String sql = "SELECT MIN(quantity) as min_quantity FROM project_materials";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("min_quantity");
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

	public double getAverageQuantity() {
		String sql = "SELECT AVG(quantity) as avg_quantity FROM project_materials";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("avg_quantity");
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

	public List<ProjectMaterials> getProjectMaterialsSortedByQuantityAscending() {
		List<ProjectMaterials> projectMaterials = new ArrayList<>();
		String sql = "SELECT * FROM project_materials ORDER BY quantity ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				ProjectMaterials pm = new ProjectMaterials(rs.getString("project_id"), rs.getInt("material_id"),
						rs.getInt("quantity"));
				projectMaterials.add(pm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projectMaterials;
	}

	public List<ProjectMaterials> getProjectMaterialsSortedByQuantityDescending() {
		List<ProjectMaterials> projectMaterials = new ArrayList<>();
		String sql = "SELECT * FROM project_materials ORDER BY quantity DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				ProjectMaterials pm = new ProjectMaterials(rs.getString("project_id"), rs.getInt("material_id"),
						rs.getInt("quantity"));
				projectMaterials.add(pm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projectMaterials;
	}

	public List<ProjectMaterials> getProjectMaterialsSortedByProjectAscending() {
		List<ProjectMaterials> projectMaterials = new ArrayList<>();
		String sql = "SELECT * FROM project_materials ORDER BY project_id ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				ProjectMaterials pm = new ProjectMaterials(rs.getString("project_id"), rs.getInt("material_id"),
						rs.getInt("quantity"));
				projectMaterials.add(pm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projectMaterials;
	}

	public List<ProjectMaterials> getProjectMaterialsSortedByProjectDescending() {
		List<ProjectMaterials> projectMaterials = new ArrayList<>();
		String sql = "SELECT * FROM project_materials ORDER BY project_id DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				ProjectMaterials pm = new ProjectMaterials(rs.getString("project_id"), rs.getInt("material_id"),
						rs.getInt("quantity"));
				projectMaterials.add(pm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return projectMaterials;
	}
}