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

@Component(value="team/add")
public class TeamAddController implements Controller {
    // 이 클래스를 사용하기 전에 App 클래스에서 준비한 Scanner 객체를
    // keyScan 변수에 저장하라!
    Scanner keyScan;
    TeamDao teamDao;
    
    public TeamAddController(Scanner scanner, TeamDao teamdao) {
        this.keyScan = scanner;
        this.teamDao = teamdao;
    }
    

    public void service(String menu, String option) {
        System.out.println("[팀 정보 입력]");
        Team team = new Team();

        System.out.print("팀명? ");
        team.setName(this.keyScan.nextLine());

        System.out.print("설명? ");
        team.setDescription(this.keyScan.nextLine());

        System.out.print("최대인원? ");
        team.setMaxQty(this.keyScan.nextInt());
        this.keyScan.nextLine(); 

        System.out.print("시작일? ");
        team.setStartDate(Date.valueOf(this.keyScan.nextLine()));

        System.out.print("종료일? ");
        team.setEndDate(Date.valueOf(this.keyScan.nextLine()));

        // 팀 정보가 담겨있는 객체의 주소를 배열에 보관한다.
        teamDao.insert(team);
    }
}

// ver 13 - 시작일, 종료일을 문자열로 입력 받아 Date 객체로 변환하여 저장.