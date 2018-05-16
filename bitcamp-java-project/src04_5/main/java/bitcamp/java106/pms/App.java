package bitcamp.java106.pms;

import java.util.Scanner;
import bitcamp.java106.pms.Management;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Management manage = Management.getInstance();
        
        String command = "";
        String[] sub, sub2, command_prefix;
        command_prefix = new String[] { "team", "member" };

        sub2 = new String[2];

        while( true ) {
            System.out.print("명령> ");
            command = sc.nextLine().toLowerCase();
            sub = command.split("/");

            if(command.equals("quit"))
                break;
            else if(command.equals("help")) {
                System.out.println("팀 등록 명령 : team/add");
                System.out.println("팀 정보 제거 명령 : team/del 팀명");
                System.out.println("팀 정보 수정 명령 : team/modi 팀명");
                System.out.println("팀 조회 명령 : team/list");
                System.out.println("팀 상세조회 명령 : team/view 팀명");
                System.out.println("회원 등록 명령 : member/add");
                System.out.println("회원 정보 제거 명령 : member/del 아이디");
                System.out.println("회원 정보 수정 명령 : member/modi 아이디");
                System.out.println("회원 조회 명령 : member/list");
                System.out.println("회원 상세조회 명령 : member/view 아이디");
                System.out.println("종료 : quit\n");
                continue;
            } else if( !is_equals_command(command_prefix, sub[0]) || getCount(command, '/') > 2 || sub.length < 2 ) {
                System.out.println("명령어가 올바르지 않습니다.\n");
                continue;
            }

            if(getCount(sub[1], ' ') > 2 || sub[1].indexOf(" ") <= -1 || sub[1].split(" ").length < 2) {
                sub2[0] = sub[1];
                sub2[1] = null;
            } else 
                sub2 = sub[1].split(" ");

            manage.print(sc, sub[0], sub2);
        }
        System.out.println("안녕히가세요!");
    }

    public static boolean is_equals_command(String[] list, String target) {
        for(int i=0; i<list.length; i++) {
            if(list[i].equals(target)) 
                return true;
        }
        return false;
    }

    public static int getCount(String str, char search) {
        int count = 0;
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == search)
                count++;
        }
        return count;
    }
}