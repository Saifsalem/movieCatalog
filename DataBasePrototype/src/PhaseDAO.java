import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhaseDAO {

	public boolean addPhase(Phase phase) {
		String sql = "INSERT INTO Phase (phase_id, project_id, name, description, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(2, phase.getProjectId());
			pstmt.setString(3, phase.getName());
			pstmt.setString(4, phase.getDescription());
			pstmt.setDate(5, phase.getStartDate());
			pstmt.setDate(6, phase.getEndDate());
			pstmt.setString(7, phase.getStatus());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error adding phase: " + e.getMessage());
			return false;
		}
	}

	public List<Phase> getAllPhases() {
		List<Phase> phases = new ArrayList<Phase>();
		String sql = "SELECT * FROM Phase ORDER BY phase_id";

		try (Connection conn = DatabaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Phase phase = new Phase();
				phase.setPhaseId(rs.getInt("phase_id"));
				phase.setProjectId(rs.getInt("project_id"));
				phase.setName(rs.getString("name"));
				phase.setDescription(rs.getString("description"));
				phase.setStartDate(rs.getDate("start_date"));
				phase.setEndDate(rs.getDate("end_date"));
				phase.setStatus(rs.getString("status"));
				phases.add(phase);
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving phases: " + e.getMessage());
		}

		return phases;
	}

	public Phase getPhaseById(int phaseId) {
		String sql = "SELECT * FROM Phase WHERE phase_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, phaseId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Phase phase = new Phase();
					phase.setPhaseId(rs.getInt("phase_id"));
					phase.setProjectId(rs.getInt("project_id"));
					phase.setName(rs.getString("name"));
					phase.setDescription(rs.getString("description"));
					phase.setStartDate(rs.getDate("start_date"));
					phase.setEndDate(rs.getDate("end_date"));
					phase.setStatus(rs.getString("status"));
					return phase;
				}
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving phase: " + e.getMessage());
		}

		return null;
	}

	public boolean updatePhase(Phase phase) {
		String sql = "UPDATE Phase SET project_id = ?, name = ?, description = ?, start_date = ?, end_date = ?, status = ? WHERE phase_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, phase.getProjectId());
			pstmt.setString(2, phase.getName());
			pstmt.setString(3, phase.getDescription());
			pstmt.setDate(4, phase.getStartDate());
			pstmt.setDate(5, phase.getEndDate());
			pstmt.setString(6, phase.getStatus());
			pstmt.setInt(7, phase.getPhaseId());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error updating phase: " + e.getMessage());
			return false;
		}
	}

	public boolean deletePhase(int phaseId) {
		String sql = "DELETE FROM Phase WHERE phase_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, phaseId);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error deleting phase: " + e.getMessage());
			return false;
		}
	}

	public List<Phase> getPhasesByProject(int projectId) {
		List<Phase> phases = new ArrayList<Phase>();
		String sql = "SELECT * FROM Phase WHERE project_id = ? ORDER BY start_date";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, projectId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Phase phase = new Phase();
					phase.setPhaseId(rs.getInt("phase_id"));
					phase.setProjectId(rs.getInt("project_id"));
					phase.setName(rs.getString("name"));
					phase.setDescription(rs.getString("description"));
					phase.setStartDate(rs.getDate("start_date"));
					phase.setEndDate(rs.getDate("end_date"));
					phase.setStatus(rs.getString("status"));
					phases.add(phase);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving phases by project: " + e.getMessage());
		}

		return phases;
	}

	public List<Phase> getPhasesByStatus(String status) {
		List<Phase> phases = new ArrayList<Phase>();
		String sql = "SELECT * FROM Phase WHERE status = ? ORDER BY start_date";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, status);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Phase phase = new Phase();
					phase.setPhaseId(rs.getInt("phase_id"));
					phase.setProjectId(rs.getInt("project_id"));
					phase.setName(rs.getString("name"));
					phase.setDescription(rs.getString("description"));
					phase.setStartDate(rs.getDate("start_date"));
					phase.setEndDate(rs.getDate("end_date"));
					phase.setStatus(rs.getString("status"));
					phases.add(phase);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving phases by status: " + e.getMessage());
		}

		return phases;
	}

	public int getPhaseCount() {
		String sql = "SELECT COUNT(*) as count FROM Phase";
		try (Connection conn = DatabaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			if (rs.next()) {
				return rs.getInt("count");
			}

		} catch (SQLException e) {
			System.err.println("Error getting phase count: " + e.getMessage());
		}

		return 0;
	}
