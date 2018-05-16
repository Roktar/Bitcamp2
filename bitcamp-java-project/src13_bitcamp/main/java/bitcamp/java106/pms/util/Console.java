package bitcamp.java106.pms.util;

import java.util.Scanner;

public class Console {
    public static java.util.Scanner keyScan;

    public Console(Scanner keyScan) {
        this.keyScan = Console.keyScan;
    }
    
    public static boolean confirm(String msg) {
        System.out.printf("%s (y/N) ", msg);

        String input = keyScan.nextLine().toLowerCase();

        if(input.equals("y")) 
            return true;
        
        return false;
    }

    public static String[] prompt() {
        System.out.print("명령> ");
        return keyScan.nextLine().toLowerCase().split(" ");
    }

    public static void onQuit() {
        System.out.println("안녕히 가세요!");
    }

    public static void onHelp() {
        System.out.println("[도움말]");
        System.out.println("팀 등록 명령 : team/add");
        System.out.println("팀 조회 명령 : team/list");
        System.out.println("팀 상세조회 명령 : team/view 팀명");
        System.out.println("회원 등록 명령 : member/add");
        System.out.println("회원 조회 명령 : member/list");
        System.out.println("회원 상세조회 명령 : member/view 아이디");
        System.out.println("종료 : quit");
    }
}