package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Purchase;
import java.sql.*;
import java.util.*;

public class PurchaseDAO {

	public List<Purchase> getAllPurchases() {
		List<Purchase> purchases = new ArrayList<>();
		String sql = "SELECT * FROM purchase";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Purchase purchase = new Purchase(rs.getInt("purchase_id"), rs.getString("supplier_id"),
						rs.getInt("material_id"), rs.getInt("quantity"), rs.getString("purchase_date"),
						rs.getDouble("unit_price"));
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return purchases;
	}

	public boolean insertPurchase(Purchase purchase) {
		String sql = "INSERT INTO purchase (supplier_id, material_id, quantity, purchase_date, unit_price) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, purchase.getSupplierId());
			stmt.setInt(2, purchase.getMaterialId());
			stmt.setInt(3, purchase.getQuantity());
			stmt.setString(4, purchase.getPurchaseDate());
			stmt.setDouble(5, purchase.getTotalCost()); // Changed from getUnitPrice() to getTotalCost()

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

	public boolean updatePurchase(Purchase purchase) {
		String sql = "UPDATE purchase SET supplier_id = ?, material_id = ?, quantity = ?, purchase_date = ?, unit_price = ? WHERE purchase_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, purchase.getSupplierId());
			stmt.setInt(2, purchase.getMaterialId());
			stmt.setInt(3, purchase.getQuantity());
			stmt.setString(4, purchase.getPurchaseDate());
			stmt.setDouble(5, purchase.getTotalCost()); // Changed from getUnitPrice() to getTotalCost()
			stmt.setInt(6, purchase.getPurchaseId());

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

	public boolean deletePurchase(int purchaseId) {
		String sql = "DELETE FROM purchase WHERE purchase_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, purchaseId);

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

	public double getMaxUnitPrice() {
		String sql = "SELECT MAX(unit_price) as max_price FROM purchase";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("max_price");
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

	public double getMinUnitPrice() {
		String sql = "SELECT MIN(unit_price) as min_price FROM purchase";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("min_price");
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

	public double getAverageUnitPrice() {
		String sql = "SELECT AVG(unit_price) as avg_price FROM purchase";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("avg_price");
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

	public double getTotalPurchaseValue() {
		String sql = "SELECT SUM(quantity * unit_price) as total_value FROM purchase";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("total_value");
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

	public int getTotalQuantity() {
		String sql = "SELECT SUM(quantity) as total_quantity FROM purchase";
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

	public int getPurchaseCount() {
		String sql = "SELECT COUNT(*) as count FROM purchase";
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

	public List<Purchase> getPurchasesSortedByPriceAscending() {
		List<Purchase> purchases = new ArrayList<>();
		String sql = "SELECT * FROM purchase ORDER BY unit_price ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Purchase purchase = new Purchase(rs.getInt("purchase_id"), rs.getString("supplier_id"),
						rs.getInt("material_id"), rs.getInt("quantity"), rs.getString("purchase_date"),
						rs.getDouble("unit_price"));
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return purchases;
	}

	public List<Purchase> getPurchasesSortedByPriceDescending() {
		List<Purchase> purchases = new ArrayList<>();
		String sql = "SELECT * FROM purchase ORDER BY unit_price DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Purchase purchase = new Purchase(rs.getInt("purchase_id"), rs.getString("supplier_id"),
						rs.getInt("material_id"), rs.getInt("quantity"), rs.getString("purchase_date"),
						rs.getDouble("unit_price"));
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return purchases;
	}

	public List<Purchase> getPurchasesSortedByQuantityAscending() {
		List<Purchase> purchases = new ArrayList<>();
		String sql = "SELECT * FROM purchase ORDER BY quantity ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Purchase purchase = new Purchase(rs.getInt("purchase_id"), rs.getString("supplier_id"),
						rs.getInt("material_id"), rs.getInt("quantity"), rs.getString("purchase_date"),
						rs.getDouble("unit_price"));
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return purchases;
	}

	public List<Purchase> getPurchasesSortedByQuantityDescending() {
		List<Purchase> purchases = new ArrayList<>();
		String sql = "SELECT * FROM purchase ORDER BY quantity DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Purchase purchase = new Purchase(rs.getInt("purchase_id"), rs.getString("supplier_id"),
						rs.getInt("material_id"), rs.getInt("quantity"), rs.getString("purchase_date"),
						rs.getDouble("unit_price"));
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return purchases;
	}

	public List<Purchase> getPurchasesSortedByDateAscending() {
		List<Purchase> purchases = new ArrayList<>();
		String sql = "SELECT * FROM purchase ORDER BY purchase_date ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Purchase purchase = new Purchase(rs.getInt("purchase_id"), rs.getString("supplier_id"),
						rs.getInt("material_id"), rs.getInt("quantity"), rs.getString("purchase_date"),
						rs.getDouble("unit_price"));
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return purchases;
	}

	public List<Purchase> getPurchasesSortedByDateDescending() {
		List<Purchase> purchases = new ArrayList<>();
		String sql = "SELECT * FROM purchase ORDER BY purchase_date DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Purchase purchase = new Purchase(rs.getInt("purchase_id"), rs.getString("supplier_id"),
						rs.getInt("material_id"), rs.getInt("quantity"), rs.getString("purchase_date"),
						rs.getDouble("unit_price"));
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return purchases;
	}
}