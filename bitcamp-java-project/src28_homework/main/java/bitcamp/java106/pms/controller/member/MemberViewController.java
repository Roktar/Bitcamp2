// 이 클래스는 회원 관련 기능을 모두 둔 클래스이다.
package bitcamp.java106.pms.controller.member;

import java.io.PrintStream;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

@Component(value="/member/view")
public class MemberViewController implements Controller {
    // 이 클래스를 사용하려면 keyboard 스캐너가 있어야 한다.
    // 이 클래스를 사용하기 전에 스캐너를 설정하라!
    Scanner keyScan;
    MemberDao memberDao;

    public MemberViewController(Scanner scanner, MemberDao memberDao) {
        this.keyScan = scanner;
        this.memberDao = memberDao;
    }


    @Override
    public void service(ServerRequest req, ServerResponse res) {
        // TODO Auto-generated method stub
        String id = req.getParameter("id");
        if (id == null) {
            return;
        }
        
        Member member = memberDao.get(id);
        PrintWriter out = res.getWriter();

        if (member == null) {
            out.println("해당 아이디의 회원이 없습니다.");
        } else {
            out.printf("아이디: %s\n", member.getId());
            out.printf("이메일: %s\n", member.getEmail());
            out.printf("암호: %s\n", member.getPassword());
        }
    }
}