package bitcamp.java106.pms;

import bitcamp.java106.pms.util.Console;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.controller.TeamController;
import bitcamp.java106.pms.controller.MemberController;
import bitcamp.java106.pms.controller.BoardController;
import java.util.Scanner;

// ver 0.2 - 멤버 메뉴를 처리하는 코드를 관련 클래스인 MemberController로 옮긴다.
// ver 0.1 - 팀 메뉴를 처리하는 코드를 관련 클래스인 TeamController로 옮긴다.
public class App {
    static Scanner keyScan = new Scanner(System.in);

    static String option = null; // 문자열 없음!

    public static void main(String[] args) {
        TeamController teamController = new TeamController  (keyScan);
        MemberController memberController = new MemberController(keyScan);
        BoardController boardController = new BoardController(keyScan);
        Console console = new Console(keyScan);

        while (true) {
            String[] arr = Console.prompt();
            String menu = arr[0];

            if (arr.length == 2)
                option = arr[1];
            try {
                if (menu.equals("quit")) {
                    Console.onQuit();
                    break;
                } else if (menu.equals("help"))
                    Console.onHelp();
                else if(menu.startsWith("team/"))
                    teamController.service(menu, option);
                else if(menu.startsWith("member/"))
                    memberController.service(menu, option);
                else if(menu.startsWith("board/"))
                    boardController.service(menu, option);
                else
                    System.out.println("명령어가 올바르지 않습니다.");
            } catch(Exception e) {
                System.out.println("에러 발생");
            }

            System.out.println();
        }
    }
}