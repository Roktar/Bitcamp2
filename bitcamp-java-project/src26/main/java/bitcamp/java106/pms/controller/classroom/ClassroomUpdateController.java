package bitcamp.java106.pms.controller.classroom;

import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.ClassDao;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.util.Console;

@Component(value="classroom/update")
public class ClassroomUpdateController implements Controller {
    Scanner keyScan;

    ClassDao classDao;
    
    public ClassroomUpdateController(Scanner scanner, ClassDao classDao) {
        this.keyScan = scanner;
        this.classDao = classDao;
    }
    
    public void service(String menu, String option) {        
        System.out.println("[수업 정보 변경]");
        
        Classroom classroom = classDao.get( inputNo() );
        
        if(classroom == null) {
            System.out.print("해당 번호로 등록된 수업이 없습니다.");
            return;
        }
                
        Classroom updateClass = new Classroom();
        updateClass.setNo(classroom.getNo());
        System.out.printf("제목(%s)? ", classroom.getTitle());
        updateClass.setTitle( inputData(classroom, 1) );
        System.out.printf("시작일(%s)? ", classroom.getStartDate());
        updateClass.setStartDate( inputDate(classroom, 1) );
        System.out.printf("종료일(%s)? ", classroom.getEndDate());
        updateClass.setEndDate( inputDate(classroom, 2) );
        System.out.printf("교실번호(%s)? ", classroom.getPlace());
        updateClass.setPlace( inputData(classroom, 2) );
        
        int index = classDao.getIndex(classroom.getNo());
        
        if(index < 0)
            return;
        
        classDao.update(index, updateClass);
        System.out.println("변경하였습니다.");
    }
    
    private String inputData(Classroom classroom, int type) {
        String txt = keyScan.nextLine();
        
        switch(type) {
            case 1 :
                if(txt.length() == 0)
                    return classroom.getTitle();
                else
                    return txt;
            case 2 :
                if(txt.length() == 0)
                    return classroom.getPlace();
                else
                    return txt;
            default :
                    break;
        }
        return "";
    }
    
    private int inputNo() {
        System.out.printf("변경할 수업 번호? ");
        return Integer.parseInt(keyScan.nextLine());
    }
    
    private Date inputDate(Classroom classroom, int type) {
        String date;
        Date retVal;
        
        date = keyScan.nextLine();
        
        try {
            retVal = Date.valueOf(date);

            if(type == 1) {
                if(classroom.getStartDate().compareTo(retVal) < 0)
                    return retVal;
                else
                    return classroom.getStartDate();
            } else {
                if(classroom.getEndDate().compareTo(retVal) > 0)
                    return classroom.getEndDate();
                else
                    return retVal;
            }
        } catch(Exception e) {
            if(type == 1)
                return classroom.getStartDate();
            else
                return classroom.getEndDate();
        }
    }
}

// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.