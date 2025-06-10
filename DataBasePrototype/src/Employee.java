
public class Employee {
    private int empId;
    private String empName;
    private int positionId;
    private double salary;
    private int branchId;
    private Integer managerId;
    private int departmentId;
    private boolean isManager;
    
    public Employee() {}
    
    public Employee(int empId, String empName, int positionId, double salary, 
                   int branchId, Integer managerId, int departmentId, boolean isManager) {
        this.empId = empId;
        this.empName = empName;
        this.positionId = positionId;
        this.salary = salary;
        this.branchId = branchId;
        this.managerId = managerId;
        this.departmentId = departmentId;
        this.isManager = isManager;
    }
    
    public int getEmpId() {
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public String getEmpName() {
        return empName;
    }
    
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    public int getPositionId() {
        return positionId;
    }
    
    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public int getBranchId() {
        return branchId;
    }
    
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
    
    public Integer getManagerId() {
        return managerId;
    }
    
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
    
    public int getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    
    public boolean isManager() {
        return isManager;
    }
    
    public void setManager(boolean manager) {
        isManager = manager;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", positionId=" + positionId +
                ", salary=" + salary +
                ", branchId=" + branchId +
                ", managerId=" + managerId +
                ", departmentId=" + departmentId +
                ", isManager=" + isManager +
                '}';
    }
}
