// 이 클래스는 회원 관련 기능을 모두 둔 클래스이다.
package bitcamp.java106.pms.controller.member;

import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;
import bitcamp.java106.pms.util.Console;

@Component(value="member/add")
public class MemberAddController implements Controller {
    // 이 클래스를 사용하려면 keyboard 스캐너가 있어야 한다.
    // 이 클래스를 사용하기 전에 스캐너를 설정하라!
    MemberDao memberDao;

    public MemberAddController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void service(String menu, String option) {

    }

    @Override
    public void service(ServerRequest req, ServerResponse res) {
        // TODO Auto-generated method stub
        Member member = new Member();
        member.setId(req.getParameter("id"));
        member.setEmail(req.getParameter("email"));
        member.setPassword(req.getParameter("password"));

        // 회원 정보가 담겨있는 객체의 주소를 배열에 보관한다.
        memberDao.insert(member);
        res.getWriter().println("등록되었습니다.");
    }
}