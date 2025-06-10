public class Supplier {
    private int suppId;
    private String suppName;
    private String location;
    
    public Supplier() {}
    
    public Supplier(int suppId, String suppName, String location) {
        this.suppId = suppId;
        this.suppName = suppName;
        this.location = location;
    }
    
    public int getSuppId() {
        return suppId;
    }
    
    public void setSuppId(int suppId) {
        this.suppId = suppId;
    }
    
    public String getSuppName() {
        return suppName;
    }
    
    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    @Override
    public String toString() {
        return "Supplier{" +
                "suppId=" + suppId +
                ", suppName='" + suppName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
