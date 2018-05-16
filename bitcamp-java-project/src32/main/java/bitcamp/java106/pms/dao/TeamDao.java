package bitcamp.java106.pms.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.jdbc.DataSource;

@Component
public class TeamDao {
    
    DataSource dataSource;
    
    public TeamDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public int delete(String name) throws Exception {
        try(
             java.sql.Connection con = dataSource.getConnection();     
             PreparedStatement stmt = con.prepareStatement("Delete FROM pms_team where name = ?");
            ) {
            
            stmt.setString(1, name);
            
            return stmt.executeUpdate();
        }
    }
    
    public List<Team> selectList() throws Exception {
        List<Team> arr = new ArrayList<>();

        try( 
            java.sql.Connection con = dataSource.getConnection();     
             PreparedStatement stmt = con.prepareStatement("select * from pms_team");
             ResultSet rs = stmt.executeQuery();
           ) {
            while(rs.next()) {
                Team team = new Team();
                team.setName(rs.getString("name"));
                team.setDescription(rs.getString("name"));
                team.setStartDate(rs.getDate("sdt"));
                team.setEndDate(rs.getDate("edt"));
                team.setMaxQty(rs.getInt("max_qty"));
                arr.add(team);
            }
        }
        return arr;
    }
    
    public int insert(Team team) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        try( 
            java.sql.Connection con = dataSource.getConnection();     
           PreparedStatement pstmt = con.prepareStatement("INSERT INTO pms_team VALUES(?, ?, ?, ?, ?)");
           ) {
            pstmt.setString(1, team.getName());
            pstmt.setString(2, team.getDescription());
            pstmt.setInt(3, team.getMaxQty());
            pstmt.setDate(4, team.getStartDate(), Calendar.getInstance(Locale.KOREAN));
            pstmt.setDate(5, team.getEndDate(), Calendar.getInstance(Locale.KOREAN));
            // or serverTimezone=Asia/Seoul을 줘도 된다.
            // 추후 접속지역에 따라 Locale 정보를 다르게 줘야하는 방법.
            // or 문자열로 날짜를 집어넣을 것.
    
            // Statement 객체를 사용하여 DBMS에 SQL문을 전송한다.
            return pstmt.executeUpdate();
        }
    }
    
    public int update(Team team) throws Exception {
        try (
            java.sql.Connection con = dataSource.getConnection();     
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
        try (
            java.sql.Connection con = dataSource.getConnection();     
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
//ver 16 - 인스턴스 변수를 직접 사용하는 대신 겟터, 셋터 사용.
//ver 14 - TeamController로부터 데이터 관리 기능을 분리하여 TeamDao 생성.





