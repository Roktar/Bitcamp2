package bitcamp.java106.pms.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;

@Component
public class TeamMemberDao {
     
    public int addMember(String teamName, String memberId) throws Exception { //insert
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try(
             java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false", "java106", "1111");    
             PreparedStatement stmt = con.prepareStatement("INSERT INTO pms_team_member VALUES(?, ?)");
            ) {
            
            stmt.setString(1, teamName);
            stmt.setString(2, memberId);
            
            return stmt.executeUpdate();
        }
    }
    
    public int deleteMember(String teamName, String memberId) throws Exception { //delete
        try(
             java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false", "java106", "1111");    
             PreparedStatement stmt = con.prepareStatement("DELETE FROM pms_team_member where tnm = ? and mid = ?");
            ) {
            
            stmt.setString(1, teamName);
            stmt.setString(2, memberId);
            
            return stmt.executeUpdate();
        }
    }
    
    public ArrayList<Member> getMembers(String teamName) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        ArrayList<Member> members = new ArrayList<>();
        MemberDao memberDao = new MemberDao();
        
        try(
             java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false", "java106", "1111");    
             PreparedStatement stmt = con.prepareStatement("SELECT mid FROM pms_team_member where tnm = ?");
            ) {
            
            stmt.setString(1, teamName);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                Member member = memberDao.selectOne(rs.getString("mid"));
                members.add(member);
            }
        }
        return members;
    }
    
    public boolean isExist(String teamName, String memberId) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try(
             java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false", "java106", "1111");    
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM pms_team_member where tnm = ? and mid = ?");
            ) {
            
            stmt.setString(1, teamName);
            stmt.setString(2, memberId);
            
            ResultSet rs = stmt.executeQuery();
            
            return ( rs.next() ? true : false);
        }
    }
}

// 용어 정리!
// 메서드 시그너처(method signature) = 함수 프로토타입(function prototype)
// => 메서드의 이름과 파라미터 형식, 리턴 타입에 대한 정보를 말한다.

//ver 24 - File I/O 적용
//ver 23 - @Component 애노테이션을 붙인다.
//ver 19 - 우리 만든 ArrayList 대신 java.util.LinkedList를 사용하여 목록을 다룬다. 
//ver 18 - ArrayList를 적용하여 객체(의 주소) 목록을 관리한다.
//ver 17 - 클래스 추가









