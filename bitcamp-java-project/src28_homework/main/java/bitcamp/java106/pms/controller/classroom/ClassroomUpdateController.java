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

@Component(value="/classroom/update")
public class ClassroomUpdateController implements Controller {
    ClassDao classDao;
    
    public ClassroomUpdateController(ClassDao classDao) {
        this.classDao = classDao;
    }
    
    @Override
    public void service(ServerRequest req, ServerResponse res) {
        // TODO Auto-generated method stub
        
        int idx = classDao.getIndex(Integer.parseInt(req.getParameter("no")));
         
        if( idx > 1) {
            Classroom updateClass = new Classroom();
            updateClass.setNo(Integer.parseInt(req.getParameter("no")));
            updateClass.setTitle( req.getParameter("title") );
            updateClass.setStartDate( Date.valueOf(req.getParameter("startDate")) );
            updateClass.setEndDate( Date.valueOf(req.getParameter("endDate")) );
            updateClass.setPlace( req.getParameter("place") );
            
            classDao.update(idx, updateClass);
            res.getWriter().println("변경하였습니다.");
        } else
            res.getWriter().println("해당 번호로 등록된 수업이 없습니다.");
    }
}

// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.