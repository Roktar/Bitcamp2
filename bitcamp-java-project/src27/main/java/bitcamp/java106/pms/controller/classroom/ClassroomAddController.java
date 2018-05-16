package bitcamp.java106.pms.controller.classroom;

import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.ClassDao;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.util.Console;

@Component(value="classroom/Add")
public class ClassroomAddController implements Controller {
    Scanner keyScan;

    ClassDao classDao;
    
    public ClassroomAddController(Scanner scanner, ClassDao classDao) {
        this.keyScan = scanner;
        this.classDao = classDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[수업 등록]");
        Classroom classroom = new Classroom();

        System.out.print("수업명? ");
        classroom.setTitle(this.keyScan.nextLine());

        System.out.print("시작일? ");
        classroom.setStartDate( Date.valueOf(this.keyScan.nextLine()) );

        System.out.print("종료일? ");
        classroom.setEndDate( Date.valueOf(this.keyScan.nextLine()) );
        
        System.out.print("교실 번호? ");
        classroom.setPlace(this.keyScan.nextLine());

        System.out.println("등록되었습니다.");
        classDao.insert(classroom);
    }
}

// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.