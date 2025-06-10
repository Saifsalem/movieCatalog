
// Payment.java
import java.sql.Date;

public class Payment {
    private int paymentId;
    private int fromClient;
    private int toSupplier;
    private double amount;
    private Date pdate;
    private String pmethod;
    
    public Payment() {}
    
    public Payment(int paymentId, int fromClient, int toSupplier, double amount, 
                  Date pdate, String pmethod) {
        this.paymentId = paymentId;
        this.fromClient = fromClient;
        this.toSupplier = toSupplier;
        this.amount = amount;
        this.pdate = pdate;
        this.pmethod = pmethod;
    }
    
    public int getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
    public int getFromClient() {
        return fromClient;
    }
    
    public void setFromClient(int fromClient) {
        this.fromClient = fromClient;
    }
    
    public int getToSupplier() {
        return toSupplier;
    }
    
    public void setToSupplier(int toSupplier) {
        this.toSupplier = toSupplier;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public Date getPdate() {
        return pdate;
    }
    
    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }
    
    public String getPmethod() {
        return pmethod;
    }
    
    public void setPmethod(String pmethod) {
        this.pmethod = pmethod;
    }
    
    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", fromClient=" + fromClient +
                ", toSupplier=" + toSupplier +
                ", amount=" + amount +
                ", pdate=" + pdate +
                ", pmethod='" + pmethod + '\'' +
                '}';
    }
}

// PaymentDAO.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

	public boolean addPayment(Payment payment) {
		String sql = "INSERT INTO Payment (payment_id, from_client, to_supplier, amount, pdate, pmethod) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, payment.getPaymentId());
			pstmt.setInt(2, payment.getFromClient());
			pstmt.setInt(3, payment.getToSupplier());
			pstmt.setDouble(4, payment.getAmount());
			pstmt.setDate(5, payment.getPdate());
			pstmt.setString(6, payment.getPmethod());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error adding payment: " + e.getMessage());
			return false;
		}
	}

	public List<Payment> getAllPayments() {
		List<Payment> payments = new ArrayList<Payment>();
		String sql = "SELECT * FROM Payment ORDER BY payment_id";

		try (Connection conn = DatabaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Payment payment = new Payment();
				payment.setPaymentId(rs.getInt("payment_id"));
				payment.setFromClient(rs.getInt("from_client"));
				payment.setToSupplier(rs.getInt("to_supplier"));
				payment.setAmount(rs.getDouble("amount"));
				payment.setPdate(rs.getDate("pdate"));
				payment.setPmethod(rs.getString("pmethod"));
				payments.add(payment);
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving payments: " + e.getMessage());
		}

		return payments;
	}

	public Payment getPaymentById(int paymentId) {
		String sql = "SELECT * FROM Payment WHERE payment_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, paymentId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Payment payment = new Payment();
					payment.setPaymentId(rs.getInt("payment_id"));
					payment.setFromClient(rs.getInt("from_client"));
					payment.setToSupplier(rs.getInt("to_supplier"));
					payment.setAmount(rs.getDouble("amount"));
					payment.setPdate(rs.getDate("pdate"));
					payment.setPmethod(rs.getString("pmethod"));
					return payment;
				}
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving payment: " + e.getMessage());
		}

		return null;
	}

	public boolean updatePayment(Payment payment) {
		String sql = "UPDATE Payment SET from_client = ?, to_supplier = ?, amount = ?, pdate = ?, pmethod = ? WHERE payment_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, payment.getFromClient());
			pstmt.setInt(2, payment.getToSupplier());
			pstmt.setDouble(3, payment.getAmount());
			pstmt.setDate(4, payment.getPdate());
			pstmt.setString(5, payment.getPmethod());
			pstmt.setInt(6, payment.getPaymentId());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error updating payment: " + e.getMessage());
			return false;
		}
	}

	public boolean deletePayment(int paymentId) {
		String sql = "DELETE FROM Payment WHERE payment_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, paymentId);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error deleting payment: " + e.getMessage());
			return false;
		}
	}

	public List<Payment> getPaymentsByClient(int clientId) {
		List<Payment> payments = new ArrayList<Payment>();
		String sql = "SELECT * FROM Payment WHERE from_client = ? ORDER BY pdate DESC";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, clientId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Payment payment = new Payment();
					payment.setPaymentId(rs.getInt("payment_id"));
					payment.setFromClient(rs.getInt("from_client"));
					payment.setToSupplier(rs.getInt("to_supplier"));
					payment.setAmount(rs.getDouble("amount"));
					payment.setPdate(rs.getDate("pdate"));
					payment.setPmethod(rs.getString("pmethod"));
					payments.add(payment);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving payments by client: " + e.getMessage());
		}

		return payments;
	}

	public List<Payment> getPaymentsBySupplier(int supplierId) {
		List<Payment> payments = new ArrayList<Payment>();
		String sql = "SELECT * FROM Payment WHERE to_supplier = ? ORDER BY pdate DESC";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, supplierId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Payment payment = new Payment();
					payment.setPaymentId(rs.getInt("payment_id"));
					payment.setFromClient(rs.getInt("from_client"));
					payment.setToSupplier(rs.getInt("to_supplier"));
					payment.setAmount(rs.getDouble("amount"));
					payment.setPdate(rs.getDate("pdate"));
					payment.setPmethod(rs.getString("pmethod"));
					payments.add(payment);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving payments by supplier: " + e.getMessage());
		}

		return payments;
	}

	public double getTotalPaymentAmount() {
		String sql = "SELECT SUM(amount) as total FROM Payment";
		try (Connection conn = DatabaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			if (rs.next()) {
				return rs.getDouble("total");
			}

		} catch (SQLException e) {
			System.err.println("Error getting total payment amount: " + e.getMessage());
		}

		return 0.0;
	}

	public double getMaxPaymentAmount() {
		String sql = "SELECT MAX(amount) as max_amount FROM Payment";
		try (Connection conn = DatabaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			if (rs.next()) {
				return rs.getDouble("max_amount");
			}

		} catch (SQLException e) {
			System.err.println("Error getting max payment amount: " + e.getMessage());
		}

		return 0.0;
	}

	public double getMinPaymentAmount() {
		String sql = "SELECT MIN(amount) as min_amount FROM Payment";
		try (Connection conn = DatabaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			if (rs.next()) {
				return rs.getDouble("min_amount");
			}

		} catch (SQLException e) {
			System.err.println("Error getting min payment amount: " + e.getMessage());
		}

		return 0.0;
	}

	public int getPaymentCount() {
		String sql = "SELECT COUNT(*) as count FROM Payment";
		try (Connection conn = DatabaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			if (rs.next()) {
				return rs.getInt("count");
			}

		} catch (SQLException e) {
			System.err.println("Error getting payment count: " + e.getMessage());
		}

		return 0;
	}
}