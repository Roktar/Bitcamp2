// 팀 관련 기능을 모아 둔 클래스
package bitcamp.java106.pms.controller.team;

import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.util.Console;

@Component(value="team/update")
public class TeamUpdateController implements Controller {
    // 이 클래스를 사용하기 전에 App 클래스에서 준비한 Scanner 객체를
    // keyScan 변수에 저장하라!
    Scanner keyScan;
    TeamDao teamDao;
    
    public TeamUpdateController(Scanner scanner, TeamDao teamdao) {
        this.keyScan = scanner;
        this.teamDao = teamdao;
    }
    

    public void service(String menu, String option) {
        System.out.println("[팀 정보 변경]");
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        Team team = teamDao.get(option);

        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            Team updateTeam = new Team();
            System.out.printf("팀명(%s)? ", team.getName());
            updateTeam.setName(this.keyScan.nextLine());
            System.out.printf("설명(%s)? ", team.getDescription());
            updateTeam.setDescription(this.keyScan.nextLine());
            System.out.printf("최대인원(%d)? ", team.getMaxQty());
            updateTeam.setMaxQty(this.keyScan.nextInt());
            this.keyScan.nextLine();
            System.out.printf("시작일(%s)? ", team.getStartDate());
            updateTeam.setStartDate(Date.valueOf(this.keyScan.nextLine()));
            System.out.printf("종료일(%s)? ", team.getEndDate());
            updateTeam.setEndDate(Date.valueOf(this.keyScan.nextLine()));
            
            int idx = teamDao.getIndex(team.getName());
            
            if(idx < 0)
                return;
            
            teamDao.update(idx, updateTeam);
            System.out.println("변경하였습니다.");
        }
    }
}

// ver 13 - 시작일, 종료일을 문자열로 입력 받아 Date 객체로 변환하여 저장.