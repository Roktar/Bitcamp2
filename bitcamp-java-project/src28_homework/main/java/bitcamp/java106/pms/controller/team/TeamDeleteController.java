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

@Component(value="team/delete")
public class TeamDeleteController implements Controller {
    // 이 클래스를 사용하기 전에 App 클래스에서 준비한 Scanner 객체를
    // keyScan 변수에 저장하라!
    Scanner keyScan;
    TeamDao teamDao;
    
    public TeamDeleteController(Scanner scanner, TeamDao teamdao) {
        this.keyScan = scanner;
        this.teamDao = teamdao;
    }
    

    public void service(String menu, String option) {
        System.out.println("[팀 정보 삭제]");
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = teamDao.get(option);

        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            if (Console.confirm("정말 삭제하시겠습니까?")) {
                teamDao.delete(team.getName());
                System.out.println("삭제하였습니다.");
            }
        }
    }
}

// ver 13 - 시작일, 종료일을 문자열로 입력 받아 Date 객체로 변환하여 저장.