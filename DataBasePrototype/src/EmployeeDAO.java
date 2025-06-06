

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO Employee (emp_id, emp_name, position_id, salary, branch_id, manager_id, department_id, is_manager) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, employee.getEmpId());
            pstmt.setString(2, employee.getEmpName());
            pstmt.setInt(3, employee.getPositionId());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.setInt(5, employee.getBranchId());
            
            if (employee.getManagerId() != null) {
                pstmt.setInt(6, employee.getManagerId());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            
            pstmt.setInt(7, employee.getDepartmentId());
            pstmt.setBoolean(8, employee.isManager());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
            return false;
        }
    }
    
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmpId(rs.getInt("emp_id"));
                employee.setEmpName(rs.getString("emp_name"));
                employee.setPositionId(rs.getInt("position_id"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBranchId(rs.getInt("branch_id"));
                
                int managerId = rs.getInt("manager_id");
                if (!rs.wasNull()) {
                    employee.setManagerId(managerId);
                }
                
                employee.setDepartmentId(rs.getInt("department_id"));
                employee.setManager(rs.getBoolean("is_manager"));
                employees.add(employee);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving employees: " + e.getMessage());
        }
        
        return employees;
    }
    
    public Employee getEmployeeById(int empId) {
        String sql = "SELECT * FROM Employee WHERE emp_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, empId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmpId(rs.getInt("emp_id"));
                    employee.setEmpName(rs.getString("emp_name"));
                    employee.setPositionId(rs.getInt("position_id"));
                    employee.setSalary(rs.getDouble("salary"));
                    employee.setBranchId(rs.getInt("branch_id"));
                    
                    int managerId = rs.getInt("manager_id");
                    if (!rs.wasNull()) {
                        employee.setManagerId(managerId);
                    }
                    
                    employee.setDepartmentId(rs.getInt("department_id"));
                    employee.setManager(rs.getBoolean("is_manager"));
                    return employee;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving employee: " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE Employee SET emp_name = ?, position_id = ?, salary = ?, branch_id = ?, manager_id = ?, department_id = ?, is_manager = ? WHERE emp_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getEmpName());
            pstmt.setInt(2, employee.getPositionId());
            pstmt.setDouble(3, employee.getSalary());
            pstmt.setInt(4, employee.getBranchId());
            
            if (employee.getManagerId() != null) {
                pstmt.setInt(5, employee.getManagerId());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            
            pstmt.setInt(6, employee.getDepartmentId());
            pstmt.setBoolean(7, employee.isManager());
            pstmt.setInt(8, employee.getEmpId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteEmployee(int empId) {
        String sql = "DELETE FROM Employee WHERE emp_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, empId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
            return false;
        }
    }
    
    public List<Employee> getEmployeesByDepartment(int deptId) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE department_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, deptId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmpId(rs.getInt("emp_id"));
                    employee.setEmpName(rs.getString("emp_name"));
                    employee.setPositionId(rs.getInt("position_id"));
                    employee.setSalary(rs.getDouble("salary"));
                    employee.setBranchId(rs.getInt("branch_id"));
                    
                    int managerId = rs.getInt("manager_id");
                    if (!rs.wasNull()) {
                        employee.setManagerId(managerId);
                    }
                    
                    employee.setDepartmentId(rs.getInt("department_id"));
                    employee.setManager(rs.getBoolean("is_manager"));
                    employees.add(employee);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving employees by department: " + e.getMessage());
        }
        
        return employees;
    }
}
