package bitcamp.java106.pms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.jdbc.DataSource;

@Component
public class BoardDao {
    
    // SqlSessionFactory를 받아온다.
    DataSource dataSource;
    SqlSessionFactory factory;
    
    public BoardDao(DataSource dataSource, SqlSessionFactory factory) {
        this.dataSource = dataSource;
        this.factory = factory;
    }
    
    public int delete(int no) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.delete("bitcamp.java106.pms.dao.BoardDao.delete", no);
            session.commit();
            return count;
        }
    }
    
    public List<Board> selectList() throws Exception {
        try (SqlSession session = factory.openSession();) {
            return session.selectList("bitcamp.java106.pms.dao.BoardDao.selectList");
        }
    }
    
    public int insert(Board board) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.insert("bitcamp.java106.pms.dao.BoardDao.insert", board);
            session.commit();
            return count;
        }
    }
    
    public int update(Board board) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count = session.update("bitcamp.java106.pms.dao.BoardDao.update", board);
            session.commit();
            return count;
        }
    }
    
    public Board selectOne(int no) throws Exception {
        try (SqlSession session = factory.openSession();) {
            return session.selectOne("bitcamp.java106.pms.dao.BoardDao.selectOne", no);
        }
    }
}

//ver 31 - JDBC 적용
//ver 24 - File I/O 적용
//ver 23 - @Component 애노테이션을 붙인다.
//ver 22 - 추상 클래스 AbstractDao를 상속 받는다.
//ver 19 - 우리 만든 ArrayList 대신 java.util.LinkedList를 사용하여 목록을 다룬다. 
//ver 18 - ArrayList를 이용하여 인스턴스(의 주소) 목록을 다룬다. 
// ver 16 - 인스턴스 변수를 직접 사용하는 대신 겟터, 셋터 사용.
// ver 14 - BoardController로부터 데이터 관리 기능을 분리하여 BoardDao 생성.





