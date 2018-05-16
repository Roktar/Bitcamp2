package bitcamp.java106.pms;


import bitcamp.java106.pms.util.Console;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.controller.TeamController;
import bitcamp.java106.pms.controller.MemberController;
import bitcamp.java106.pms.domain.Member;
import java.util.Scanner;

// ver 0.2 - 회원 관리 기능을 별도의 클래스로 옮기고 그 클래스를 통해 활용한다.
// ver 0.1 - 팀 관리 기능(메소드)을 별도의 클래스로 옮기고 그 클래스를 통해 활용한다.
//          = controller.TeamController 클래스 만들고 여기로 이동
//          - 출력 기능을 별도의 클래스로 이동시킨다.
//          = util.Consol 클래스 추가.
public class App {
    static Scanner keyScan = new Scanner(System.in);

    static String option = null; // 문자열 없음!

    public static void main(String[] args) {
        TeamController.keyScan = keyScan;
        Console.keyScan = keyScan;

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
                else if (menu.equals("team/add"))
                    TeamController.onTeamAdd();
                else if (menu.equals("team/list"))
                    TeamController.onTeamList();
                else if (menu.equals("team/view"))
                    TeamController.onTeamView(option);
                else if (menu.equals("team/delete"))
                    TeamController.onTeamDelete(option);
                else if (menu.equals("team/update"))
                    TeamController.onTeamUpdate(option);
                else if (menu.equals("member/add"))
                    MemberController.onMemberAdd();
                else if (menu.equals("member/list"))
                    MemberController.onMemeberList();
                else if (menu.equals("member/view"))
                    MemberController.onMemberView(option);
                else if (menu.equals("member/delete"))
                    MemberController.onMemberDelete(option);
                else if (menu.equals("member/update"))
                    MemberController.onMemberUpdate(option);
                else
                    System.out.println("명령어가 올바르지 않습니다.");
            } catch(Exception e) {
                System.out.println("에러 발생");
            }

            System.out.println();
        }
    }
}