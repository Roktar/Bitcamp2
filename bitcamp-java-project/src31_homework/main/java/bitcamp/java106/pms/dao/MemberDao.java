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
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.domain.Member;

@Component
public class MemberDao {
    
    public int delete(String id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try(
             java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false", "java106", "1111");    
             PreparedStatement stmt = con.prepareStatement("Delete FROM pms_member where mid = ?");
            ) {
            
            stmt.setString(1, id);
            
            return stmt.executeUpdate();
        }
    }
    
    public List<Member> selectList() throws Exception {
        List<Member> arr = new ArrayList<>();
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try( 
             java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false", "java106", "1111");
             PreparedStatement stmt = con.prepareStatement("select mid, email from pms_member");
             ResultSet rs = stmt.executeQuery();
           ) {
            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getString("mid"));
                member.setEmail(rs.getString("email"));
                arr.add(member);
            }
        }
        return arr;
    }
    
    public int insert(Member member) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try( 
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false", "java106", "1111");
           PreparedStatement pstmt = con.prepareStatement("INSERT INTO pms_member VALUES(?, ?, SHA2(?,224))");
           ) {
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getPassword());
    
            // Statement 객체를 사용하여 DBMS에 SQL문을 전송한다.
            return pstmt.executeUpdate();
        }
    }
    
    public int update(Member member) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try (
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false", "java106", "1111");
            PreparedStatement stmt = con.prepareStatement("Update pms_member SET email=?, pwd=SHA2(?,224) where mid = ?"); 
            ) {        
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getPassword());
            stmt.setString(3, member.getId());
                    
            // Statement 객체를 사용하여 DBMS에 SQL문을 전송한다.
            return stmt.executeUpdate();
        }
    }
    
    public Member selectOne(String id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false","java106", "1111");
            PreparedStatement stmt = con.prepareStatement("select mid, email from pms_member where mid=?");) {
            
                stmt.setString(1, id);
                
                try (ResultSet rs = stmt.executeQuery();) {
                    if (!rs.next()) 
                        return null;
                
                    Member member = new Member();
                    member.setId(rs.getString("mid"));
                    member.setEmail(rs.getString("email"));
                    return member;
            }
        }
    }
}

//ver 24 - File I/O 적용
//ver 23 - @Component 애노테이션을 붙인다.
//ver 22 - 추상 클래스 AbstractDao를 상속 받는다.
//ver 19 - 우리 만든 ArrayList 대신 java.util.LinkedList를 사용하여 목록을 다룬다. 
//ver 18 - ArrayList를 사용하여 객체(의 주소) 목록을 관리한다.
//ver 16 - 인스턴스 변수를 직접 사용하는 대신 겟터, 셋터 사용.
//ver 14 - MemberController로부터 데이터 관리 기능을 분리하여 MemberDao 생성.






