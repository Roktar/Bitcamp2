package bitcamp.java106.pms.controller.classroom;

import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.ClassDao;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.util.Console;

@Component(value="classroom/list")
public class ClassroomListController implements Controller {
    Scanner keyScan;

    ClassDao classDao;
    
    public ClassroomListController(Scanner scanner, ClassDao classDao) {
        this.keyScan = scanner;
        this.classDao = classDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[수업 목록]");
        Iterator<Classroom> list = classDao.list();
        Classroom classroom = null;
        
        while (list.hasNext()) {
            classroom = list.next();
            System.out.printf("%d, %s, %s, %s, %s\n", classroom.getNo(), classroom.getTitle(),
                                                      classroom.getStartDate(), classroom.getEndDate(),
                                                      classroom.getPlace() );
        }
        System.out.println();
    }
}

// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.