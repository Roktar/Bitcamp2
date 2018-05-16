package bitcamp.java106.pms.controller;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;


public class TMController {
    TeamController tc;
    MemberController mc;
    Scanner keyScan;
        
    public TMController(Scanner keyScan, TeamController tc, MemberController mc) {
        this.keyScan = keyScan;
        this.tc = tc;
        this.mc = mc;
    }
    
    public void service(String menu, String option) {
        if (menu.equals("team/member/add")) {
            this.onTMAdd(option);
        } else if (menu.equals("team/member/delete")) {
            this.onTMDelete(option);
        } else if (menu.equals("team/member/list")) {
            this.onTMList(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
        
    void onTMAdd(String name) {
        if(name == null) {
            System.out.println("팀 이름을 입력해주십시오.");
            return;
        }
        Team team = this.tc.teamDao.get(name); // 팀 탐색
        if(team == null)
            System.out.println(name + " 팀은 존재하지 않습니다.");
        else {
            System.out.printf("추가할 멤버의 아이디는 ? ");
            
            String id = this.keyScan.nextLine();
            Member member = this.mc.memberDao.get(id);
            if(member == null)
                System.out.println(id + " 회원은 없습니다.");
            else {
                if(member.joinedTeam == null) {
                    mc.memberDao.joinTeam(team.name, member.id);
                    System.out.println("추가하였습니다.");
                } else {
                    if(member.joinedTeam.equals(name))
                        System.out.println("이미 등록된 회원입니다.");
                    else {
                        mc.memberDao.joinTeam(team.name, member.id);
                        System.out.println("추가하였습니다.");
                    }
                }
            }
        }
    }

    void onTMList(String name) {
        if(name == "" || name == null) {
            System.out.println("팀 이름을 입력하십시오.");
            return;
        }
        
        Team team = this.tc.teamDao.get(name); // 팀 탐색
        
        if(team != null) {
            System.out.println("회원들 : ");
            Member[] list = this.mc.memberDao.list();
            int len = list.length;
            
            
            for (int i = 0; i < len; i++) {
                if (list[i] == null || list[i].joinedTeam == null) 
                    continue;
                if(name.toLowerCase().equals(list[i].joinedTeam.toLowerCase())) {
                    System.out.printf("%s", list[i].id);
                    if( (i+1) < len )
                        System.out.print(", ");
                }
            }
        } else 
            System.out.println(name + " 팀은 존재하지 않습니다.");
    }

    void onTMDelete(String name) {
        
        if (name == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        Team team = this.tc.teamDao.get(name);
        
        if( team == null) {
            System.out.println("해당 팀은 존재하지 않습니다.");
            return;
        }
        
        System.out.println("삭제할 팀원은? ");
        String s = this.keyScan.nextLine();
        
        Member member = this.mc.memberDao.get(s);
        
        if (member == null) {
            System.out.println("이 팀의 회원이 아닙니다.");
        } else {
                this.mc.memberDao.deleteTeam(member);
                System.out.println("삭제하였습니다.");
        }
    }
}
