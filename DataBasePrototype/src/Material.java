public class Material {
    private int materialId;
    private String mname;
    private double price;
    
    public Material() {}
    
    public Material(int materialId, String mname, double price) {
        this.materialId = materialId;
        this.mname = mname;
        this.price = price;
    }
    
    public int getMaterialId() {
        return materialId;
    }
    
    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }
    
    public String getMname() {
        return mname;
    }
    
    public void setMname(String mname) {
        this.mname = mname;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Material{" +
                "materialId=" + materialId +
                ", mname='" + mname + '\'' +
                ", price=" + price +
                '}';
    }
}
