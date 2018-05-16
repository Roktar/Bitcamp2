// 이 클래스는 회원 관련 기능을 모두 둔 클래스이다.
package bitcamp.java106.pms.controller.member;

import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.util.Console;

@Component(value="member/list")
public class MemberListController implements Controller {
    // 이 클래스를 사용하려면 keyboard 스캐너가 있어야 한다.
    // 이 클래스를 사용하기 전에 스캐너를 설정하라!
    Scanner keyScan;
    MemberDao memberDao;

    public MemberListController(Scanner scanner, MemberDao memberDao) {
        this.keyScan = scanner;
        this.memberDao = memberDao;
    }

    public void service(String menu, String option) {
        System.out.println("[회원 목록]");
        Iterator<Member> list = memberDao.list();
        Member member = null;
        while(list.hasNext()) {
            member = list.next();
            System.out.printf("%s, %s, %s\n", 
                              member.getId(), member.getEmail(), member.getPassword());
        }
    }
}