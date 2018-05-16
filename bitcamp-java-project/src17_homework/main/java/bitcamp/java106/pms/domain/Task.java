package bitcamp.java106.pms.domain;

import java.sql.Date;

public class Task {
    private String taskName, workerName, teamName;
    private Date startDate, endDate;
    private int taskNo, state;
    
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTaskName() {
        return taskName;
    }
    
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    public String getWorkerName() {
        return workerName;
    }
    
    public void setWorkerName(String workerName) {
        this.workerName = workerName;
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
    
    public int getTaskNo() {
        return taskNo;
    }
    
    public void setTaskNo(int taskNo) {
        this.taskNo = taskNo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}