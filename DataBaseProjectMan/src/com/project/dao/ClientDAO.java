package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Client;
import java.sql.*;
import java.util.*;

public class ClientDAO {

	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<>();
		String sql = "SELECT * FROM client";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Client client = new Client(rs.getString("client_id"), rs.getString("name"), rs.getString("contact"),
						rs.getString("email"), rs.getString("address"));
				clients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return clients;
	}

	public boolean insertClient(Client client) {
		String sql = "INSERT INTO client (client_id, name, contact, email, address) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, client.getClientId());
			stmt.setString(2, client.getName());
			stmt.setString(3, client.getContact());
			stmt.setString(4, client.getEmail());
			stmt.setString(5, client.getAddress());

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

	public boolean updateClient(Client client) {
		String sql = "UPDATE client SET name = ?, contact = ?, email = ?, address = ? WHERE client_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, client.getName());
			stmt.setString(2, client.getContact());
			stmt.setString(3, client.getEmail());
			stmt.setString(4, client.getAddress());
			stmt.setString(5, client.getClientId());

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

	public boolean deleteClient(String clientId) {
		String sql = "DELETE FROM client WHERE client_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientId);

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

	public int getClientCount() {
		String sql = "SELECT COUNT(*) as count FROM client";
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

	public int getClientsWithEmailCount() {
		String sql = "SELECT COUNT(*) as count FROM client WHERE email IS NOT NULL AND email != ''";
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

	public int getClientsWithContactCount() {
		String sql = "SELECT COUNT(*) as count FROM client WHERE contact IS NOT NULL AND contact != ''";
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

	public List<Client> getClientsSortedByNameAscending() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client ORDER BY name ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Client client = new Client(
                    rs.getString("client_id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getString("address")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeResultSet(rs);
            DatabaseManager.closeStatement(stmt);
            DatabaseManager.closeConnection(conn);
        }
        return clients;
    }

public List<Client> getClientsSortedByNameDescending() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client ORDER BY name DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);
