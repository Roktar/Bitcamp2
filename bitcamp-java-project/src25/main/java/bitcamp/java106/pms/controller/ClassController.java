package bitcamp.java106.pms.controller;

import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.dao.ClassDao;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.util.Console;

@Component(value="classroom")
public class ClassController implements Controller {
    Scanner keyScan;

    ClassDao classDao;
    
    public ClassController(Scanner scanner, ClassDao classDao) {
        this.keyScan = scanner;
        this.classDao = classDao;
    }
    
    public void service(String menu, String option) {
        if (menu.equals("classroom/add")) {
            this.onClassAdd();
        } else if (menu.equals("classroom/list")) {
            this.onClassList();
        } else if (menu.equals("classroom/update")) {
            this.onClassUpdate();
        } else if (menu.equals("classroom/delete")) {
            this.onClassDelete();
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }

    void onClassAdd() {
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

    void onClassList() {
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

    void onClassUpdate() {        
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
        
        classDao.update(updateClass);
        System.out.println("변경하였습니다.");
    }

    void onClassDelete() {
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