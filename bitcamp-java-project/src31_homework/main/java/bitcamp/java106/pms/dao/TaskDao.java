package bitcamp.java106.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Task;
import bitcamp.java106.pms.domain.Team;

@Component
public class TaskDao {    
    public int insert(Task task) throws Exception {        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try( 
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=Asia/Seoul&useSSL=false", "java106", "1111");
           PreparedStatement pstmt = con.prepareStatement("INSERT INTO pms_task VALUES(?, ?, ?, ?, ?, ?)");
           ) {

            pstmt.setString(1, task.getTitle());
            pstmt.setDate(2, task.getStartDate());
            pstmt.setDate(3, task.getEndDate());
            pstmt.setInt(4, task.getState());
            pstmt.setString(5, task.getWorker().getId());
            pstmt.setString(6, task.getTeam().getName());
    
            // Statement 객체를 사용하여 DBMS에 SQL문을 전송한다.
            return pstmt.executeUpdate();
        }
    }

    public int delete(int no) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try(
             java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false", "java106", "1111");    
             PreparedStatement stmt = con.prepareStatement("Delete FROM pms_task where tno = ?");
            ) {
            
            stmt.setInt(1, no);
            
            return stmt.executeUpdate();
        }
    }
    
    public List<Task> selectList() throws Exception {
        List<Task> arr = new ArrayList<>();
        TeamDao teamDao = new TeamDao();
        MemberDao memberDao = new MemberDao();
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try( 
             java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false", "java106", "1111");
             PreparedStatement stmt = con.prepareStatement("select * from pms_task");
             ResultSet rs = stmt.executeQuery();
           ) {
            while(rs.next()) {
                
                Team team = teamDao.selectOne(rs.getString("tnm"));
                Member member = memberDao.selectOne(rs.getString("mid"));
                
                team.getName();
                Task task = new Task(team);
                task.setTitle(rs.getString("titl"));
                task.setStartDate( rs.getDate("sdt") );
                task.setWorker(member);
                task.setTeam(team);
            }
        }
        return arr;
    }
    
    public int update(Team team) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try (
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false", "java106", "1111");
            PreparedStatement stmt = con.prepareStatement("Update pms_Team SET dscrt=?, max_qty=?, sdt=?, edt=? where name = ?"); 
            ) {        
            stmt.setString(1, team.getDescription());
            stmt.setInt(2, team.getMaxQty());
            stmt.setDate(3, team.getStartDate());
            stmt.setDate(4, team.getEndDate());
            stmt.setString(5, team.getName());
                    
            // Statement 객체를 사용하여 DBMS에 SQL문을 전송한다.
            return stmt.executeUpdate();
        }
    }
    
    public Team selectOne(String name) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false","java106", "1111");
            PreparedStatement stmt = con.prepareStatement("select * from pms_team where name=?");) {
            
                stmt.setString(1, name);
                
                try (ResultSet rs = stmt.executeQuery();) {
                    if (!rs.next()) 
                        return null;
                
                    Team team = new Team();
                    team.setName(name);
                    team.setDescription(rs.getString("dscrt"));
                    team.setMaxQty(rs.getInt("max_qty"));
                    team.setStartDate(rs.getDate("sdt"));
                    team.setEndDate(rs.getDate("edt"));
                    
                    return team;
            }
        }
    }
    
    
    
}

//ver 24 - File I/O 적용
//ver 23 - @Component 애노테이션을 붙인다.
//ver 22 - 추상 클래스 AbstractDao를 상속 받는다.
//ver 19 - 우리 만든 ArrayList 대신 java.util.LinkedList를 사용하여 목록을 다룬다. 
//ver 18 - ArrayList 클래스를 적용하여 객체(의 주소) 목록을 관리한다.
// ver 17 - 클래스 생성





