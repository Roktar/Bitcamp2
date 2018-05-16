package bitcamp.java106.pms.controller.classroom;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.ClassDao;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

@Component(value="/classroom/list")
public class ClassroomListController implements Controller {
    ClassDao classDao;
    
    public ClassroomListController(ClassDao classDao) {
        this.classDao = classDao;
    }
    
    @Override
    public void service(ServerRequest req, ServerResponse res) {
        // TODO Auto-generated method stub
        Iterator<Classroom> list = classDao.list();
        Classroom classroom = null;
        PrintWriter out = res.getWriter();
         
        while (list.hasNext()) {
            classroom = list.next();
            out.printf("%d, %s, %s, %s, %s\n", classroom.getNo(), classroom.getTitle(),
                                                      classroom.getStartDate(), classroom.getEndDate(),
                                                      classroom.getPlace() );
        }
    }
}

// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.