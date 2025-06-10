
public class Project {
    private int projectId;
    private int branchId;
    private int clientId;
    private String pname;
    private String location;
    private double cost;
    private double revenue;
    
    public Project() {}
    
    public Project(int projectId, int branchId, int clientId, String pname, 
                  String location, double cost, double revenue) {
        this.projectId = projectId;
        this.branchId = branchId;
        this.clientId = clientId;
        this.pname = pname;
        this.location = location;
        this.cost = cost;
        this.revenue = revenue;
    }
    
    public int getProjectId() {
        return projectId;
    }
    
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    public int getBranchId() {
        return branchId;
    }
    
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
    
    public int getClientId() {
        return clientId;
    }
    
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    
    public String getPname() {
        return pname;
    }
    
    public void setPname(String pname) {
        this.pname = pname;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public double getRevenue() {
        return revenue;
    }
    
    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
    
    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", branchId=" + branchId +
                ", clientId=" + clientId +
                ", pname='" + pname + '\'' +
                ", location='" + location + '\'' +
                ", cost=" + cost +
                ", revenue=" + revenue +
                '}';
    }
}
