// 팀 멤버 관리 기능을 모아 둔 클래스
package bitcamp.java106.pms.controller.teammember;

import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.dao.TeamMemberDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;

@Component(value="team/member/list")
public class TeamMemberListController implements Controller {
    
    Scanner keyScan;
    TeamDao teamDao;
    MemberDao memberDao;
    TeamMemberDao teamMemberDao;
    
    public TeamMemberListController(Scanner scanner, TeamDao teamDao, 
            MemberDao memberDao, TeamMemberDao teamMemberDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
        this.memberDao = memberDao;
        this.teamMemberDao = teamMemberDao;
    }
    
    public void service(String menu, String option) {
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = teamDao.get(option);
        if (team == null) {
            System.out.printf("%s 팀은 존재하지 않습니다.", option);
            return;
        }

        System.out.println("[팀 멤버 목록]");
        System.out.print("회원들: ");
        
        Iterator<String> members = teamMemberDao.getMembers(option);

        if(members != null) {
            while(members.hasNext())
                System.out.printf("%s, ", members.next());
            System.out.println();
        }
/*        
        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) continue;
            System.out.printf("%s, ", members[i]);
        }*/
    }
}

//ver 17 - TeamMemberDao 클래스를 사용하여 팀 멤버의 아이디를 관리한다.
//ver 16 - 인스턴스 변수를 직접 사용하는 대신 겟터, 셋터 사용.
// ver 15 - 팀 멤버를 등록, 조회, 삭제할 수 있는 기능 추가. 
// ver 14 - TeamDao를 사용하여 팀 데이터를 관리한다.
// ver 13 - 시작일, 종료일을 문자열로 입력 받아 Date 객체로 변환하여 저장.