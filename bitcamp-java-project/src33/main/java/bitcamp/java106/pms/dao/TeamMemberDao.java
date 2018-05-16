package bitcamp.java106.pms.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.jdbc.DataSource;

@Component
public class TeamMemberDao {
     
    DataSource dataSource;
    SqlSessionFactory factory;
    
    public TeamMemberDao(DataSource dataSource, SqlSessionFactory factory) {
        this.dataSource = dataSource;
        this.factory = factory;
    }
    
    public int insert(String teamName, String memberId) throws Exception { //insert
        
        try (SqlSession session = factory.openSession();) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("teamName", teamName);
            map.put("memberId", memberId);
            
            int count =  session.insert("bitcamp.java106.pms.dao.TeamMemberDao.insert", map);
            session.commit();
            return count;
        }
    }
    
    public int delete(String teamName, String memberId) throws Exception {
        try (SqlSession sqlSession = factory.openSession()) {
            HashMap<String,Object> paramMap = new HashMap<>();
            paramMap.put("teamName", teamName);
            paramMap.put("memberId", memberId);
            
            int count = sqlSession.delete(
                    "bitcamp.java106.pms.dao.TeamMemberDao.delete", paramMap);
            sqlSession.commit();
            return count;
        } 
    }
    
    public List<String> list(String teamName) throws Exception {
        
        try (SqlSession session = factory.openSession();) {            
            return session.selectList("bitcamp.java106.pms.dao.TeamMemberDao.selectList", teamName);
        }
    }
    
    public boolean isExist(String teamName, String memberId) throws Exception {
        try (SqlSession sqlSession = factory.openSession()) {
            HashMap<String,Object> paramMap = new HashMap<>();
            paramMap.put("teamName", teamName);
            paramMap.put("memberId", memberId);
            
            int count = sqlSession.selectOne("bitcamp.java106.pms.dao.TeamMemberDao.isExist", paramMap);
            if (count > 0)
                return true;
            else 
                return false;
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









