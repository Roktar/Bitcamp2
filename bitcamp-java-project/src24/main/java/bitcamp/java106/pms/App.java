package bitcamp.java106.pms;

import java.sql.Date;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java106.pms.context.ApplicationContext;
import bitcamp.java106.pms.controller.BoardController;
import bitcamp.java106.pms.controller.ClassController;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.controller.MemberController;
import bitcamp.java106.pms.controller.TaskController;
import bitcamp.java106.pms.controller.TeamController;
import bitcamp.java106.pms.controller.TeamMemberController;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.dao.ClassDao;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.TaskDao;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.dao.TeamMemberDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.util.Console;

public class App {
    static ApplicationContext iocContainer;
    static Scanner keyScan = new Scanner(System.in);
    public static String option = null; 
    
    static void onQuit() throws Exception { // 매개변수 주고 처리하는 게 괜찮을듯.
        System.out.println("안녕히 가세요!");
        BoardDao boardDao = (BoardDao) iocContainer.getBean(BoardDao.class);
        TeamDao teamDao = (TeamDao) iocContainer.getBean(BoardDao.class);
        MemberDao memberDao = (MemberDao) iocContainer.getBean(BoardDao.class);
        TaskDao taskDao = (TaskDao) iocContainer.getBean(BoardDao.class);
        TeamMemberDao teamMemberDao = (TeamMemberDao) iocContainer.getBean(BoardDao.class);
        ClassDao classDao = (ClassDao) iocContainer.getBean(BoardDao.class);
        
        try {
            boardDao.save();
            teamDao.save();
            memberDao.save();
            taskDao.save();
            teamMemberDao.save();
            classDao.save();
        } catch(Exception e) {
            System.out.println("저장 중 오류 발생");
        }
    }

    static void onHelp() {
        System.out.println("[도움말]");
        System.out.println("팀 등록 명령 : team/add");
        System.out.println("팀 조회 명령 : team/list");
        System.out.println("팀 상세조회 명령 : team/view 팀명");
        System.out.println("회원 등록 명령 : member/add");
        System.out.println("회원 조회 명령 : member/list");
        System.out.println("회원 상세조회 명령 : member/view 아이디");
        System.out.println("종료 : quit");
    }

    public static void main(String[] args) throws Exception {
        // 기본 객체 준비
        HashMap<String, Object> defaultBeans = new HashMap<>();
        defaultBeans.put("java.util.Scanner", keyScan);
        
        // 기본객체와 함께 어노테이션이 붙은 클래스의 객체를 준비
        iocContainer = new ApplicationContext("bitcamp.java106.pms", defaultBeans);

        Console.keyScan = keyScan;

        while (true) {
            String[] arr = Console.prompt();

            String menu = arr[0];
            if (arr.length == 2) 
                option = arr[1];
             else 
                option = null;
            
            if (menu.equals("quit")) {
                onQuit();
                break;
            } else if (menu.equals("help")) {
                onHelp();
            } else {
                int slashIndex = menu.lastIndexOf("/");
                String controllerKey = (slashIndex < 0) ? 
                                        menu : menu.substring(0, slashIndex);
                
                Controller controller = (Controller) iocContainer.getBean(controllerKey);
                
                if (controller != null) {
                    controller.service(menu, option);
                } else {
                    System.out.println("명령어가 올바르지 않습니다.");
                }
            }

            System.out.println(); 
        }
    }
}

//ver 17 - Task 관리 기능 추가
// ver 15 - TeamDao와 MemberDao 객체 생성. 
//          팀 멤버를 다루는 메뉴 추가.
