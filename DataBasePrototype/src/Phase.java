
// Phase.java
import java.sql.Date;

public class Phase {
    private int phaseId;
    private int projectId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String status;
    
    public Phase() {}
    
    public Phase(int phaseId, int projectId, String name, String description, 
                Date startDate, Date endDate, String status) {
        this.phaseId = phaseId;
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    
    public int getPhaseId() {
        return phaseId;
    }
    
    public void setPhaseId(int phaseId) {
        this.phaseId = phaseId;
    }
    
    public int getProjectId() {
        return projectId;
    }
    
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Phase{" +
                "phaseId=" + phaseId +
                ", projectId=" + projectId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}

// PhaseDAO.java
