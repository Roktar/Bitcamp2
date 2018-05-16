package bitcamp.java106.pms.controller;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.util.Console;

public class MemberController {
    public java.util.Scanner keyScan;

    Member[] members = new Member[1000];
    int memberIndex = 0;

    public void service(String menu, String option) {
        if (menu.equals("member/add"))
            onMemberAdd();
        else if (menu.equals("member/list"))
            onMemeberList();
        else if (menu.equals("member/view"))
            onMemberView(option);
        else if (menu.equals("member/delete"))
            onMemberDelete(option);
        else if (menu.equals("member/update"))
            onMemberUpdate(option);
        else
            System.out.println("명령어가 올바르지 않습니다.");
    }

    int getMemberIndex(String name) {
        for (int i = 0; i < memberIndex; i++) {
            if(members[i] == null)
                continue;
            if (name.toLowerCase().equals(members[i].id.toLowerCase())) 
                return i;
        }
        return -1;
    }

    void onMemberAdd() {
        System.out.println("[회원 정보 입력]");
        Member member = new Member();

        System.out.print("아이디? ");
        member.id = keyScan.nextLine();

        System.out.print("이메일? ");
        member.email = keyScan.nextLine();

        System.out.print("암호? ");
        member.password = keyScan.nextLine();

        members[memberIndex++] = member;
    }

    void onMemeberList() {
        System.out.println("[회원 목록]");
        for (int i = 0; i < memberIndex; i++) {
            System.out.printf("%s, %s, %s\n", 
                members[i].id, members[i].email, members[i].password);
        }
    }

    void onMemberView(String option) {
        System.out.println("[회원 정보 조회]");
        if (option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }

        int i = getMemberIndex(option);

        if (i == -1) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            System.out.printf("아이디: %s\n", members[i].id);
            System.out.printf("이메일: %s\n", members[i].email);
            System.out.printf("암호: %s\n", members[i].password);
        }
    }

    void onMemberDelete(String option) {
        System.out.println("[회원 정보 삭제]");
        if (option == null) {
            System.out.println("회원 이름을 입력하시기 바랍니다.");
            System.out.println();
            return;
        }

        int i = getMemberIndex(option);

        if (i == -1) {
            System.out.println("해당 이름의 회원이 없습니다.");
        } else {
            if(Console.confirm("정말 삭제하시겠습니까?")) {
                members[i] = null;
                System.out.println("삭제하였습니다.");
            } else
                System.out.println("삭제를 취소했습니다.");

        }
    }

    void onMemberUpdate(String option) {
        System.out.println("[회원 정보 조회]");
        if (option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        int i = getMemberIndex(option);

        if (i == -1) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            Member uMember = new Member();
            System.out.printf("아이디(%s)? ", members[i].id);
            uMember.id = keyScan.nextLine();
            System.out.printf("이메일(%s)? ", members[i].email);
            uMember.email = keyScan.nextLine();
            System.out.printf("암호? ");
            uMember.password = keyScan.nextLine();
            members[i] = uMember;
        }
    }
}