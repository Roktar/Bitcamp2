package bitcamp.java106.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.jdbc.DataSource;

@Component
public class TeamDao {
    
    SqlSessionFactory factory;
    
    public TeamDao(SqlSessionFactory factory) {
        this.factory = factory;
    }
    
    public int delete(String id) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.delete("bitcamp.java106.pms.dao.TeamDao.delete", id);
            session.commit();
            return count;
        }
    }
    
    public List<Team> selectList() throws Exception {
        try (SqlSession session = factory.openSession();) {
            return session.selectList("bitcamp.java106.pms.dao.TeamDao.selectList");
        }
    }
    
    public int insert(Team team) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.insert("bitcamp.java106.pms.dao.TeamDao.insert", team);
            session.commit();
            return count;
        }
    }
    
    public int update(Team team) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.update("bitcamp.java106.pms.dao.TeamDao.update", team);
            session.commit();
            return count;
        }
    }
    
    public Team selectOne(String id) throws Exception {
        try (SqlSession session = factory.openSession();) {
            return session.selectOne("bitcamp.java106.pms.dao.TeamDao.selectOne", id);
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





