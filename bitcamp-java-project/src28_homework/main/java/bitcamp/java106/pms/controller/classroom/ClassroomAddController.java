package bitcamp.java106.pms.controller.classroom;

import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.ClassDao;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;
import bitcamp.java106.pms.util.Console;

@Component(value="/classroom/add")
public class ClassroomAddController implements Controller {
    Scanner keyScan;

    ClassDao classDao;
     
    public ClassroomAddController(ClassDao classDao) {
        this.classDao = classDao;
    }
    
    @Override
    public void service(ServerRequest req, ServerResponse res) {
        Classroom classroom = new Classroom();
        
        if(classroom.getNo() < classDao.getBaseIdx())
            classroom.setNo( classDao.getBaseIdx() +1 );
        
        classroom.setTitle(req.getParameter("title"));
        classroom.setStartDate( Date.valueOf(req.getParameter("startDate")) );
        classroom.setEndDate( Date.valueOf(req.getParameter("endDate")) );
        classroom.setPlace(req.getParameter("place"));
 
        res.getWriter().println("등록되었습니다.");
        classDao.insert(classroom);
    }
}

// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.