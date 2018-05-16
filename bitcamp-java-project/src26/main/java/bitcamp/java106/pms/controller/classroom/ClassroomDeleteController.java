package bitcamp.java106.pms.controller.classroom;

import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.ClassDao;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.util.Console;

@Component(value="classroom/delete")
public class ClassroomDeleteController implements Controller {
    Scanner keyScan;

    ClassDao classDao;
    
    public ClassroomDeleteController(Scanner scanner, ClassDao classDao) {
        this.keyScan = scanner;
        this.classDao = classDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[수업 삭제]");
        
        Classroom classroom = classDao.get( inputNo() );
        
        if (classroom == null) {
            System.out.println("유효하지 않은 게시물 번호입니다.");
        } else {
            if (Console.confirm("정말 삭제하시겠습니까?")) {
                int idx = classDao.getIndex(classroom.getNo());
                
                if(idx < 0)
                    return;
                classDao.delete(idx);
                System.out.println("삭제하였습니다.");
            } else
                System.out.println("취소하였습니다.");
        }
    }
}

// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.