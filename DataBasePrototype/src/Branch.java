
public class Branch {
    private int branchId;
    private String location;
    
    public Branch() {}
    
    public Branch(int branchId, String location) {
        this.branchId = branchId;
        this.location = location;
    }
    
    public int getBranchId() {
        return branchId;
    }
    
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    @Override
    public String toString() {
        return "Branch{" +
                "branchId=" + branchId +
                ", location='" + location + '\'' +
                '}';
    }
}
