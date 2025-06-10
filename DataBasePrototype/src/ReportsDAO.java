

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportsDAO {

    // Query 1: Total sales for a certain period
    public double getTotalSalesForPeriod(Date startDate, Date endDate) {
        String sql = "SELECT SUM(amount) as total_sales FROM Sales " +
                "WHERE issue_date BETWEEN ? AND ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total_sales");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error getting total sales: " + e.getMessage());
        }

        return 0.0;
    }

    // Query 2: Project profitability report
    public List<Map<String, Object>> getProjectProfitabilityReport() {
        List<Map<String, Object>> report = new ArrayList<>();
        String sql = "SELECT p.project_id, p.pname, p.cost, p.revenue, " +
                "(p.revenue - p.cost) as profit, " +
                "((p.revenue - p.cost) / p.cost * 100) as profit_margin " +
                "FROM Projects p " +
                "ORDER BY profit DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("project_id", rs.getInt("project_id"));
                row.put("project_name", rs.getString("pname"));
                row.put("cost", rs.getDouble("cost"));
                row.put("revenue", rs.getDouble("revenue"));
                row.put("profit", rs.getDouble("profit"));
                row.put("profit_margin", rs.getDouble("profit_margin"));
                report.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error generating profitability report: " + e.getMessage());
        }

        return report;
    }

    // Query 3: Employee workload report
    public List<Map<String, Object>> getEmployeeWorkloadReport() {
        List<Map<String, Object>> report = new ArrayList<>();
        String sql = "SELECT e.emp_id, e.emp_name, d.dept_name, " +
                "COUNT(w.project_id) as project_count, " +
                "GROUP_CONCAT(p.pname SEPARATOR ', ') as projects " +
                "FROM Employee e " +
                "LEFT JOIN Department d ON e.department_id = d.dept_id " +
                "LEFT JOIN Works_On w ON e.emp_id = w.emp_id " +
                "LEFT JOIN Projects p ON w.project_id = p.project_id " +
                "GROUP BY e.emp_id, e.emp_name, d.dept_name " +
                "ORDER BY project_count DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("emp_id", rs.getInt("emp_id"));
                row.put("emp_name", rs.getString("emp_name"));
                row.put("department", rs.getString("dept_name"));
                row.put("project_count", rs.getInt("project_count"));
                row.put("projects", rs.getString("projects"));
                report.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error generating workload report: " + e.getMessage());
        }

        return report;
    }

    // Query 4: Material usage report
    public List<Map<String, Object>> getMaterialUsageReport() {
        List<Map<String, Object>> report = new ArrayList<>();
        String sql = "SELECT m.material_id, m.mname, " +
                "SUM(pm.quantity) as total_used, " +
                "COUNT(DISTINCT pm.project_id) as projects_used_in, " +
                "AVG(pm.quantity) as avg_usage_per_project " +
                "FROM Material m " +
                "LEFT JOIN Project_Materials pm ON m.material_id = pm.material_id " +
                "GROUP BY m.material_id, m.mname " +
                "ORDER BY total_used DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("material_id", rs.getInt("material_id"));
                row.put("material_name", rs.getString("mname"));
                row.put("total_used", rs.getInt("total_used"));
                row.put("projects_used_in", rs.getInt("projects_used_in"));
                row.put("avg_usage_per_project", rs.getDouble("avg_usage_per_project"));
                report.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error generating material usage report: " + e.getMessage());
        }

        return report;
    }

    // Query 5: Branch performance report
    public List<Map<String, Object>> getBranchPerformanceReport() {
        List<Map<String, Object>> report = new ArrayList<>();
        String sql = "SELECT b.branch_id, b.location, " +
                "COUNT(DISTINCT p.project_id) as total_projects, " +
                "SUM(p.revenue) as total_revenue, " +
                "SUM(p.cost) as total_cost, " +
                "COUNT(DISTINCT e.emp_id) as employee_count " +
                "FROM Branch b " +
                "LEFT JOIN Projects p ON b.branch_id = p.branch_id " +
                "LEFT JOIN Employee e ON b.branch_id = e.branch_id " +
                "GROUP BY b.branch_id, b.location " +
                "ORDER BY total_revenue DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("branch_id", rs.getInt("branch_id"));
                row.put("location", rs.getString("location"));
                row.put("total_projects", rs.getInt("total_projects"));
                row.put("total_revenue", rs.getDouble("total_revenue"));
                row.put("total_cost", rs.getDouble("total_cost"));
                row.put("employee_count", rs.getInt("employee_count"));
                report.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error generating branch performance report: " + e.getMessage());
        }

        return report;
    }

    // Query 6: Supplier performance report
    public List<Map<String, Object>> getSupplierPerformanceReport() {
        List<Map<String, Object>> report = new ArrayList<>();
        String sql = "SELECT s.supp_id, s.supp_name, s.location, " +
                "COUNT(DISTINCT pu.purchase_id) as total_purchases, " +
                "SUM(pu.total_cost) as total_spent, " +
                "AVG(pu.total_cost) as avg_purchase_cost, " +
                "COUNT(DISTINCT ps.project_id) as projects_supplied " +
                "FROM Supplier s " +
                "LEFT JOIN Purchase pu ON s.supp_id = pu.supplier_id " +
                "LEFT JOIN Project_Suppliers ps ON s.supp_id = ps.supplier_id " +
                "GROUP BY s.supp_id, s.supp_name, s.location " +
                "ORDER BY total_spent DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("supplier_id", rs.getInt("supp_id"));
                row.put("supplier_name", rs.getString("supp_name"));
                row.put("location", rs.getString("location"));
                row.put("total_purchases", rs.getInt("total_purchases"));
                row.put("total_spent", rs.getDouble("total_spent"));
                row.put("avg_purchase_cost", rs.getDouble("avg_purchase_cost"));
                row.put("projects_supplied", rs.getInt("projects_supplied"));
                report.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error generating supplier performance report: " + e.getMessage());
        }

        return report;
    }

    // Query 7: Project timeline report
    public List<Map<String, Object>> getProjectTimelineReport() {
        List<Map<String, Object>> report = new ArrayList<>();
        String sql = "SELECT p.project_id, p.pname, " +
                "COUNT(ph.phase_id) as total_phases, " +
                "SUM(CASE WHEN ph.status = 'Completed' THEN 1 ELSE 0 END) as completed_phases, " +
                "MIN(ph.start_date) as project_start, " +
                "MAX(ph.end_date) as project_end, " +
                "DATEDIFF(MAX(ph.end_date), MIN(ph.start_date)) as duration_days " +
                "FROM Projects p " +
                "LEFT JOIN Phase ph ON p.project_id = ph.project_id " +
                "GROUP BY p.project_id, p.pname " +
                "ORDER BY project_start DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("project_id", rs.getInt("project_id"));
                row.put("project_name", rs.getString("pname"));
                row.put("total_phases", rs.getInt("total_phases"));
                row.put("completed_phases", rs.getInt("completed_phases"));
                row.put("project_start", rs.getDate("project_start"));
                row.put("project_end", rs.getDate("project_end"));
                row.put("duration_days", rs.getInt("duration_days"));
                report.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error generating timeline report: " + e.getMessage());
        }

        return report;
    }

    // Query 8: Monthly sales trend
    public List<Map<String, Object>> getMonthlySalesTrend() {
        List<Map<String, Object>> report = new ArrayList<>();
        String sql = "SELECT YEAR(issue_date) as year, MONTH(issue_date) as month, " +
                "SUM(amount) as monthly_sales, COUNT(*) as transaction_count " +
                "FROM Sales " +
                "GROUP BY YEAR(issue_date), MONTH(issue_date) " +
                "ORDER BY year DESC, month DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("year", rs.getInt("year"));
                row.put("month", rs.getInt("month"));
                row.put("monthly_sales", rs.getDouble("monthly_sales"));
                row.put("transaction_count", rs.getInt("transaction_count"));
                report.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error generating monthly sales trend: " + e.getMessage());
        }

        return report;
    }

    // Query 9: Top performing employees
    public List<Map<String, Object>> getTopPerformingEmployees() {
        List<Map<String, Object>> report = new ArrayList<>();
        String sql = "SELECT e.emp_id, e.emp_name, e.salary, d.dept_name, " +
                "COUNT(w.project_id) as projects_handled, " +
                "AVG(p.revenue) as avg_project_revenue " +
                "FROM Employee e " +
                "LEFT JOIN Department d ON e.department_id = d.dept_id " +
                "LEFT JOIN Works_On w ON e.emp_id = w.emp_id " +
                "LEFT JOIN Projects p ON w.project_id = p.project_id " +
                "GROUP BY e.emp_id, e.emp_name, e.salary, d.dept_name " +
                "HAVING projects_handled > 0 " +
                "ORDER BY avg_project_revenue DESC, projects_handled DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("emp_id", rs.getInt("emp_id"));
                row.put("emp_name", rs.getString("emp_name"));
                row.put("salary", rs.getDouble("salary"));
                row.put("department", rs.getString("dept_name"));
                row.put("projects_handled", rs.getInt("projects_handled"));
                row.put("avg_project_revenue", rs.getDouble("avg_project_revenue"));
                report.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error generating top performers report: " + e.getMessage());
        }

        return report;
    }

    // Query 10: Project cost overrun analysis
    public List<Map<String, Object>> getProjectCostOverrunAnalysis() {
        List<Map<String, Object>> report = new ArrayList<>();
        String sql = "SELECT p.project_id, p.pname, p.cost as budgeted_cost, " +
                "SUM(pu.total_cost) as actual_cost, " +
                "(SUM(pu.total_cost) - p.cost) as cost_overrun, " +
                "((SUM(pu.total_cost) - p.cost) / p.cost * 100) as overrun_percentage " +
                "FROM Projects p " +
                "LEFT JOIN Project_Suppliers ps ON p.project_id = ps.project_id " +
                "LEFT JOIN Purchase pu ON ps.supplier_id = pu.supplier_id " +
                "GROUP BY p.project_id, p.pname, p.cost " +
                "HAVING actual_cost > budgeted_cost " +
                "ORDER BY overrun_percentage DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("project_id", rs.getInt("project_id"));
                row.put("project_name", rs.getString("pname"));
                row.put("budgeted_cost", rs.getDouble("budgeted_cost"));
                row.put("actual_cost", rs.getDouble("actual_cost"));
                row.put("cost_overrun", rs.getDouble("cost_overrun"));
                row.put("overrun_percentage", rs.getDouble("overrun_percentage"));
                report.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error generating cost overrun analysis: " + e.getMessage());
        }

        return report;
    }
}