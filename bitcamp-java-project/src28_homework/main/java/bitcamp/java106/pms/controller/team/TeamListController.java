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

@Component(value="team/list")
public class TeamListController implements Controller {
    // 이 클래스를 사용하기 전에 App 클래스에서 준비한 Scanner 객체를
    // keyScan 변수에 저장하라!
    Scanner keyScan;
    TeamDao teamDao;
    
    public TeamListController(Scanner scanner, TeamDao teamdao) {
        this.keyScan = scanner;
        this.teamDao = teamdao;
    }
    

    public void service(String menu, String option) {
        System.out.println("[팀 목록]");
        
        Iterator<Team> list = teamDao.list();
        Team team = null;
        
        while (list.hasNext()) {
            team = list.next();
            System.out.printf("%s, %d, %s ~ %s\n", 
                                team.getName(), team.getMaxQty(), 
                                team.getStartDate(), team.getEndDate());
        }
    }
}

// ver 13 - 시작일, 종료일을 문자열로 입력 받아 Date 객체로 변환하여 저장.