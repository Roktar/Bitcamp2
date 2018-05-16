package bitcamp.java106.pms.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Task;
import bitcamp.java106.pms.jdbc.DataSource;

@Component
public class TaskDao {
    
    DataSource dataSource;
    SqlSessionFactory factory;
    
    public TaskDao(DataSource dataSource, SqlSessionFactory factory) {
        this.dataSource = dataSource;
        this.factory = factory;
    }
    
    
    public int delete(int no) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.delete("bitcamp.java106.pms.dao.TaskDao.delete", no);
            session.commit();
            return count;
        }
    }
    
    
    public List<Task> selectList(String teamName) throws Exception {
        try (SqlSession session = factory.openSession();) {
            return session.selectList("bitcamp.java106.pms.dao.TaskDao.selectList", teamName);
        }
    }

    public int insert(Task task) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.delete("bitcamp.java106.pms.dao.TaskDao.insert", task);
            session.commit();
            return count;
        }
    }

    public int update(Task task) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.delete("bitcamp.java106.pms.dao.TaskDao.update", task);
            session.commit();
            return count;
        }
    }

    public int selectOne(int no) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.delete("bitcamp.java106.pms.dao.TaskDao.selectOne", no);
            session.commit();
            return count;
        }
    }

    public int updateState(int no, int state) throws Exception {
        try (SqlSession session = factory.openSession();) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("no", no);
            map.put("state", state);
            
            int count =  session.delete("bitcamp.java106.pms.dao.TaskDao.update_state", map);
            session.commit();
            return count;
        }
    }
}

//ver 31 - JDBC API 적용
//ver 24 - File I/O 적용
//ver 23 - @Component 애노테이션을 붙인다.
//ver 22 - 추상 클래스 AbstractDao를 상속 받는다.
//ver 19 - 우리 만든 ArrayList 대신 java.util.LinkedList를 사용하여 목록을 다룬다. 
//ver 18 - ArrayList 클래스를 적용하여 객체(의 주소) 목록을 관리한다.
// ver 17 - 클래스 생성