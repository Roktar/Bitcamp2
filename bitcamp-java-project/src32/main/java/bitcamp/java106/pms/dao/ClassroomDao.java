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
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.jdbc.DataSource;

@Component
public class ClassroomDao {
    DataSource dataSource;
    
    public ClassroomDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public int delete(int no) throws Exception {

        try(
             java.sql.Connection con = dataSource.getConnection();       
             PreparedStatement stmt = con.prepareStatement("Delete FROM pms_classroom where crno = ?");
            ) {
            
            stmt.setInt(1, no);
            
            return stmt.executeUpdate();
        }
    }
    
    public List<Classroom> selectList() throws Exception {
        List<Classroom> arr = new ArrayList<>();

        try( 
             java.sql.Connection con = dataSource.getConnection();     
             PreparedStatement stmt = con.prepareStatement("select * from pms_classroom");
             ResultSet rs = stmt.executeQuery();
           ) {
            while(rs.next()) {
                Classroom classroom = new Classroom();
                classroom.setNo(rs.getInt("crno"));
                classroom.setTitle(rs.getString("titl"));
                classroom.setStartDate(rs.getDate("sdt"));
                classroom.setEndDate(rs.getDate("edt"));
                classroom.setRoom(rs.getString("room"));
                arr.add(classroom);
            }
        }
        return arr;
    }
    
    public int insert(Classroom classroom) throws Exception {
        
        Scanner sc = new Scanner(System.in);

        try( 
           java.sql.Connection con = dataSource.getConnection();     
           PreparedStatement pstmt = con.prepareStatement("INSERT INTO pms_classroom VALUES(?, ?, ?, ?)");
           ) {
            pstmt.setString(1, classroom.getTitle());
            pstmt.setDate(2, classroom.getStartDate());
            pstmt.setDate(3, classroom.getEndDate());
            pstmt.setString(4, classroom.getRoom());
    
            // Statement 객체를 사용하여 DBMS에 SQL문을 전송한다.
            return pstmt.executeUpdate();
        }
    }
    
    public int update(Classroom classroom) throws Exception {
        try (
            java.sql.Connection con = dataSource.getConnection();     
            PreparedStatement stmt = con.prepareStatement("Update pms_classroom SET titl=?, sdt=?, edt=?, room=? where no = ?"); 
            ) {        
            stmt.setString(1, classroom.getTitle());
            stmt.setDate(2, classroom.getStartDate());
            stmt.setDate(3, classroom.getEndDate());
            stmt.setString(4, classroom.getRoom());
            stmt.setInt(5, classroom.getNo());
                    
            // Statement 객체를 사용하여 DBMS에 SQL문을 전송한다.
            return stmt.executeUpdate();
        }
    }
    
    public Classroom selectOne(int no) throws Exception {
       
        try (
            java.sql.Connection con = dataSource.getConnection();     
            PreparedStatement stmt = con.prepareStatement("select * from pms_classroom where crno=?");) {
            
                stmt.setInt(1, no);
                
                try (ResultSet rs = stmt.executeQuery();) {
                    if (!rs.next()) 
                        return null;
                
                    Classroom classroom = new Classroom();
                    classroom.setNo(rs.getInt("crno"));
                    classroom.setTitle(rs.getString("titl"));
                    classroom.setStartDate(rs.getDate("sdt"));
                    classroom.setEndDate(rs.getDate("edt"));
                    classroom.setTitle(rs.getString("room"));
                    return classroom;
            }
        }
    }
}

//ver 24 - File I/O 적용
//ver 23 - @Component 애노테이션을 붙인다.
//ver 22 - 추상 클래스 AbstractDao를 상속 받는다.
//ver 20 - 클래스 추가




