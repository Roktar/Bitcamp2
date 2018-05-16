package bitcamp.java106.pms.domain;

import java.sql.Date;

public class Classroom implements java.io.Serializable {
    public static final long serialVersionUID = 1L;
    private static int count = 1;
    private int no;
    private String title, place;
    private Date startDate, endDate;
    
    public Classroom() {
        this.no = count++;
    }
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
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
}
