// 팀 관련 기능을 모아 둔 클래스
package bitcamp.java106.pms.controller;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.util.Console;

public class TeamMemberController {
    // 이 클래스를 사용하기 전에 App 클래스에서 준비한 Scanner 객체를
    // keyScan 변수에 저장하라!
    Scanner keyScan;
    TeamDao teamDao;
    MemberDao memberDao;
    
    public TeamMemberController(Scanner scanner, TeamDao teamDao, MemberDao memberDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
        this.memberDao = memberDao;
    }
    
    public void service(String menu, String option) {
        if (menu.equals("team/member/add")) {
            this.onTeamMemberAdd(option);
        } else if (menu.equals("team/member/list")) {
            this.onTeamMemberList(option);
        } else if (menu.equals("team/member/delete")) {
            this.onTeamMemberDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }

    void onTeamMemberAdd(String name) {
        System.out.println("[팀 멤버 추가]");
        
        if (name == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = teamDao.get(name);
        if(team == null) {
            System.out.printf("%s 팀은 존재하지 않습니다.", name);
            return;
        } 
        
        System.out.print("추가할 멤버의 아이디는? ");
        String memberId = keyScan.nextLine();
        
        Member member = memberDao.get(memberId);
        if(member == null) {
            System.out.printf("%s 회원은 없습니다.", memberId);
            return;
        }

        if(team.isExist(memberId)) {
            System.out.println("이미 등록된 회원입니다.");
        }
        
        team.addMember(member);
        System.out.println("추가하였습니다.");

    }

    void onTeamMemberList(String name) {
        System.out.println("[팀 멤버 목록]");
        
        if (name == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }

        Team team = teamDao.get(name);
        System.out.print("회원들 : ");
        if(team == null) {
            System.out.printf("%s 팀은 존재하지 않습니다.", name);
            return;
        }
        for (int i = 0; i < team.members.length; i++) {
            if (team.members[i] == null) continue;
            System.out.printf("%s, ",team.members[i].id);
        }
    }

    void onTeamMemberDelete(String name) {
        System.out.println("[팀 멤버 삭제]");

        if (name == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = teamDao.get(name);

        if (team == null) {
            System.out.printf("%s 팀은 존재하지 않습니다.", name);
            return;
        }
        
        System.out.print("삭제할 팀원은? ");
        String memberId = keyScan.nextLine();
        
        if(!team.isExist(memberId)) {
            System.out.println("이 팀의 회원이 아닙니다.");
            return;
        }
        
        team.deleteMember(memberId);
        System.out.println("삭제하였습니다.");
        

        System.out.println("이 팀의 회원이 아닙니다.");
    }
}

// ver 13 - 시작일, 종료일을 문자열로 입력 받아 Date 객체로 변환하여 저장.