package com.project.dao;

import com.project.database.DatabaseManager;
import com.project.model.Employee;
import java.sql.*;
import java.util.*;

public class EmployeeDAO {

	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT * FROM employee";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("emp_id"), rs.getString("emp_name"),
						rs.getString("position"), rs.getDouble("salary"), rs.getString("branch_id"),
						rs.getString("manager_id"), rs.getString("department_id"), rs.getString("is_manager"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return employees;
	}

	public boolean insertEmployee(Employee employee) {
		String sql = "INSERT INTO employee (emp_name, position, salary, branch_id, manager_id, department_id, is_manager) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, employee.getEmpName());
			stmt.setString(2, employee.getPosition());
			stmt.setDouble(3, employee.getSalary());
			stmt.setString(4, employee.getBranchId());
			stmt.setString(5, employee.getManagerId());
			stmt.setString(6, employee.getDepartmentId());
			stmt.setString(7, employee.getIsManager());

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

	public boolean updateEmployee(Employee employee) {
		String sql = "UPDATE employee SET emp_name = ?, position = ?, salary = ?, branch_id = ?, manager_id = ?, department_id = ?, is_manager = ? WHERE emp_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, employee.getEmpName());
			stmt.setString(2, employee.getPosition());
			stmt.setDouble(3, employee.getSalary());
			stmt.setString(4, employee.getBranchId());
			stmt.setString(5, employee.getManagerId());
			stmt.setString(6, employee.getDepartmentId());
			stmt.setString(7, employee.getIsManager());
			stmt.setInt(8, employee.getEmpId());

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

	public boolean deleteEmployee(int empId) {
		String sql = "DELETE FROM employee WHERE emp_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, empId);

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

	public double getMaxSalary() {
		String sql = "SELECT MAX(salary) as max_salary FROM employee";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("max_salary");
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

	public double getMinSalary() {
		String sql = "SELECT MIN(salary) as min_salary FROM employee";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("min_salary");
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

	public double getAverageSalary() {
		String sql = "SELECT AVG(salary) as avg_salary FROM employee";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("avg_salary");
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

	public double getTotalSalaryExpense() {
		String sql = "SELECT SUM(salary) as total_salary FROM employee";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("total_salary");
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

	public int getEmployeeCount() {
		String sql = "SELECT COUNT(*) as count FROM employee";
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

	public int getManagerCount() {
		String sql = "SELECT COUNT(*) as count FROM employee WHERE is_manager = 'Yes'";
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

	public List<Employee> getEmployeesSortedBySalaryAscending() {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT * FROM employee ORDER BY salary ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("emp_id"), rs.getString("emp_name"),
						rs.getString("position"), rs.getDouble("salary"), rs.getString("branch_id"),
						rs.getString("manager_id"), rs.getString("department_id"), rs.getString("is_manager"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return employees;
	}

	public List<Employee> getEmployeesSortedBySalaryDescending() {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT * FROM employee ORDER BY salary DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("emp_id"), rs.getString("emp_name"),
						rs.getString("position"), rs.getDouble("salary"), rs.getString("branch_id"),
						rs.getString("manager_id"), rs.getString("department_id"), rs.getString("is_manager"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return employees;
	}

	public List<Employee> getEmployeesSortedByNameAscending() {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT * FROM employee ORDER BY emp_name ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("emp_id"), rs.getString("emp_name"),
						rs.getString("position"), rs.getDouble("salary"), rs.getString("branch_id"),
						rs.getString("manager_id"), rs.getString("department_id"), rs.getString("is_manager"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return employees;
	}

	public List<Employee> getEmployeesSortedByNameDescending() {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT * FROM employee ORDER BY emp_name DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseManager.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("emp_id"), rs.getString("emp_name"),
						rs.getString("position"), rs.getDouble("salary"), rs.getString("branch_id"),
						rs.getString("manager_id"), rs.getString("department_id"), rs.getString("is_manager"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		}
		return employees;
	}
}