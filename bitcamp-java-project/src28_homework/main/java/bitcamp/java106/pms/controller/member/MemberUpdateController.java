// 이 클래스는 회원 관련 기능을 모두 둔 클래스이다.
package bitcamp.java106.pms.controller.member;

import java.io.PrintWriter;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

@Component(value="/member/update")
public class MemberUpdateController implements Controller {
    // 이 클래스를 사용하려면 keyboard 스캐너가 있어야 한다.
    // 이 클래스를 사용하기 전에 스캐너를 설정하라!
    Scanner keyScan;
    MemberDao memberDao;

    public MemberUpdateController(Scanner scanner, MemberDao memberDao) {
        this.keyScan = scanner;
        this.memberDao = memberDao;
    }



    @Override
    public void service(ServerRequest req, ServerResponse res) {
        // TODO Auto-generated method stub
        PrintWriter out = res.getWriter();

        String id = req.getParameter("id");
        
        if (id == null) {
            out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        Member member = memberDao.get(id);

        if (member == null) {
            out.println("해당 아이디의 회원이 없습니다.");
        } else {
            Member updateMember = new Member();
            updateMember.setId(member.getId());
            updateMember.setEmail(req.getParameter("email"));
            updateMember.setPassword(req.getParameter("password"));
            
            int index = memberDao.getIndex(member.getId());
            memberDao.update(index, updateMember);
            
            out.println("변경하였습니다.");
        }
    }
}