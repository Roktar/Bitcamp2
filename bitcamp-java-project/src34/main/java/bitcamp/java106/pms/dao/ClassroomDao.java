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

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.jdbc.DataSource;

@Component
public class ClassroomDao {
    SqlSessionFactory factory;
    DataSource dataSource;
    
    public ClassroomDao(DataSource dataSource, SqlSessionFactory factory) {
        this.dataSource = dataSource;
        this.factory = factory;
    }
    
    public int delete(int no) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.delete("bitcamp.java106.pms.dao.ClassroomDao.delete", no);
            session.commit();
            return count;
        }
    }
    
    public List<Classroom> selectList() throws Exception {
        try (SqlSession session = factory.openSession();) {
            return session.selectList("bitcamp.java106.pms.dao.ClassroomDao.selectList");
        }
    }
    
    public int insert(Classroom classroom) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.insert("bitcamp.java106.pms.dao.ClassroomDao.insert", classroom);
            session.commit();
            return count;
        }
    }
    
    public int update(Classroom classroom) throws Exception {
        try (SqlSession session = factory.openSession();) {
            int count =  session.update("bitcamp.java106.pms.dao.ClassroomDao.update", classroom);
            session.commit();
            return count;
        }
    }
    
    public Classroom selectOne(int no) throws Exception {
        try (SqlSession session = factory.openSession();) {
            return session.selectOne("bitcamp.java106.pms.dao.ClassroomDao.selectOne", no);
        }
    }
}

//ver 24 - File I/O 적용
//ver 23 - @Component 애노테이션을 붙인다.
//ver 22 - 추상 클래스 AbstractDao를 상속 받는다.
//ver 20 - 클래스 추가




