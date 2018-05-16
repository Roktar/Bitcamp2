package bitcamp.java106.pms.out;

import java.util.Scanner;

public class Console {
    public static String command(Scanner sc) {
        System.out.print("명령> ");

        return sc.nextLine().toLowerCase();
    }

    public static void printMenu() {
        System.out.println("팀 등록 명령 : team/add");
        System.out.println("팀 정보 제거 명령 : team/del 팀명");
        System.out.println("팀 정보 수정 명령 : team/update 팀명");
        System.out.println("팀 조회 명령 : team/list");
        System.out.println("팀 상세조회 명령 : team/view 팀명");
        System.out.println("회원 등록 명령 : member/add");
        System.out.println("회원 정보 제거 명령 : member/del 아이디");
        System.out.println("회원 정보 수정 명령 : member/update 아이디");
        System.out.println("회원 조회 명령 : member/list");
        System.out.println("회원 상세조회 명령 : member/view 아이디");
        System.out.println("게시글 등록 명령 : board/add");
        System.out.println("게시글 정보 제거 명령 : board/del 제목");
        System.out.println("게시글 정보 수정 명령 : board/update 제목");
        System.out.println("게시글 조회 명령 : board/list");
        System.out.println("게시글 상세조회 명령 : board/view 제목");
        System.out.println("종료 : quit\n");
    }
}