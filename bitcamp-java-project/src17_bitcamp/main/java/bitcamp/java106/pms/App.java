package bitcamp.java106.pms;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.controller.BoardController;
import bitcamp.java106.pms.controller.MemberController;
import bitcamp.java106.pms.controller.TaskController;
import bitcamp.java106.pms.controller.TeamController;
import bitcamp.java106.pms.controller.TeamMemberController;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.TaskDao;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.util.Console;

public class App {
    static Scanner keyScan = new Scanner(System.in);
    public static String option = null; 
   
    
    static void onQuit() {
        System.out.println("안녕히 가세요!");
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
    // 컨트롤러들은 DAO를 공유할 수 있다. 그리고 그렇게 만들기위해서는 DAO를 별도로 생성한 후 같은 DAO의 주소를 각각 넘겨준다.
    public static void main(String[] args) {
        TeamDao teamDao = new TeamDao();
        MemberDao memberDao = new MemberDao();
        TaskDao taskDao = new TaskDao();
        
        prepareMemberData(memberDao);
        prepareTeamData(teamDao, memberDao);

        TeamController teamController = new TeamController(keyScan, teamDao);
        MemberController memberController = new MemberController(keyScan, memberDao);
        TaskController taskController = new TaskController(keyScan, teamDao, taskDao);
        BoardController boardController = new BoardController(keyScan);
        TeamMemberController teamMemberController = new TeamMemberController(keyScan, teamDao, memberDao);

        Console.keyScan = keyScan;
        
        while (true) {
            String[] arr = Console.prompt();

            String menu = arr[0];
            if (arr.length == 2) {
                option = arr[1];
            } else {
                option = null;
            }

            if (menu.equals("quit")) {
                onQuit();
                break;
            } else if (menu.equals("help")) {
                onHelp();
            } else if(menu.startsWith("team/member/")) {
                teamMemberController.service(menu, option);
            } else if (menu.startsWith("team/")) {
                teamController.service(menu, option);
            } else if (menu.startsWith("member/")) {
                memberController.service(menu, option);
            } else if (menu.startsWith("board/")) {
                boardController.service(menu, option);
            } else if(menu.startsWith("task/")) {
                taskController.service(menu, option);
            } else {
                System.out.println("명령어가 올바르지 않습니다.");
            }

            System.out.println(); 
        }
    }
    static void prepareMemberData(MemberDao mDao) {
        
        Member member = new Member();
        member.setId("aaa");
        member.setEmail("aaa@test.com");
        member.setPassword("1111");
        mDao.insert(member);
        
        member = new Member();
        member.setId("bbb");
        member.setEmail("bbb@test.com");
        member.setPassword("1111");
        mDao.insert(member);
        
        member = new Member();
        member.setId("ccc");
        member.setEmail("ccc@test.com");
        member.setPassword("1111");
        mDao.insert(member);
        
        member = new Member();
        member.setId("ddd");
        member.setEmail("ddd@test.com");
        member.setPassword("1111");
        mDao.insert(member);
        
        member = new Member();
        member.setId("eee");
        member.setEmail("eee@test.com");
        member.setPassword("1111");
        mDao.insert(member);
        
    }
    
    static void prepareTeamData(TeamDao teamDao, MemberDao memberDao) {
        Team team = new Team();
        team.setName("t1");
        team.setMaxQty(5);
        team.setStartDate(Date.valueOf("2018-01-01"));
        team.setEndDate(Date.valueOf("2018-05-30"));
        team.addMember(memberDao.get("aaa"));
        team.addMember(memberDao.get("bbb"));
        
        teamDao.insert(team);
        
        team = new Team();
        team.setName("t2");
        team.setMaxQty(5);
        team.setStartDate(Date.valueOf("2018-02-01"));
        team.setEndDate(Date.valueOf("2018-06-30"));
        team.addMember(memberDao.get("ccc"));
        team.addMember(memberDao.get("ddd"));        
        team.addMember(memberDao.get("eee"));
        
        teamDao.insert(team);
    }
}