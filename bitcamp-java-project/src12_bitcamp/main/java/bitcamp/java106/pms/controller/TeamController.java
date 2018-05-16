package bitcamp.java106.pms.controller;

import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.util.Console;
import java.util.Scanner;

public class TeamController {
    java.util.Scanner keyScan;

    Team[] teams = new Team[1000];
    int teamIndex = 0;
    
    public TeamController(Scanner keyScan) {
        this.keyScan = keyScan;
    }

    // 클래스 내에서 전부 처리하게 만듬.
    public void service(String menu, String option) {
        if (menu.equals("team/add"))
            onTeamAdd();
        else if (menu.equals("team/list"))
            onTeamList();
        else if (menu.equals("team/view"))
            onTeamView(option);
        else if (menu.equals("team/delete"))
            onTeamDelete(option);
        else if (menu.equals("team/update"))
            onTeamUpdate(option);
        else
            System.out.println("명령어가 올바르지 않습니다.");
    } // 멤버함수들을 이제 숨길 수 있게 됐다. => 캡슐화

    int getTeamIndex(String name) {
        for (int i = 0; i < teamIndex; i++) {
            if(teams[i] == null)
                continue;
            if (name.toLowerCase().equals(teams[i].name.toLowerCase()))
                return i;
        }
        return -1;
    }

    void onTeamAdd() {
        System.out.println("[팀 정보 입력]");
        Team team = new Team();

        System.out.print("팀명? ");
        team.name = keyScan.nextLine();

        System.out.print("설명? ");
        team.description = keyScan.nextLine();

        System.out.print("최대인원? ");
        team.maxQty = keyScan.nextInt();
        keyScan.nextLine(); 

        System.out.print("시작일? ");
        team.startDate = keyScan.nextLine();

        System.out.print("종료일? ");
        team.endDate = keyScan.nextLine();

        teams[teamIndex++] = team;
    }

    void onTeamView(String option) {
        System.out.println("[팀 정보 조회]");
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            System.out.println();
            return;
        }
    
        int i = getTeamIndex(option);

        if (i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            Team team = teams[i];
            System.out.printf("팀명: %s\n", team.name);
            System.out.printf("설명: %s\n", team.description);
            System.out.printf("최대인원: %d\n", team.maxQty);
            System.out.printf("기간: %s ~ %s\n", 
                team.startDate, team.endDate);
        }
    }

    void onTeamList() {
        System.out.println("[팀 목록]");
        for (int i = 0; i < teamIndex; i++) {
            if(teams[i] == null)
            continue;
            System.out.printf("%s, %d, %s ~ %s\n", 
                teams[i].name, teams[i].maxQty, 
                teams[i].startDate, teams[i].endDate);
        }
    }

    void onTeamDelete(String option) {
        System.out.println("[팀 정보 삭제]");
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            System.out.println();
            return;
        }
    
        int i = getTeamIndex(option);

        if (i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            if(Console.confirm("정말 삭제하시겠습니까?")) {
                teams[i] = null;
                System.out.println("삭제하였습니다.");
            } else
                System.out.println("삭제를 취소했습니다.");

        }
    }

    void onTeamUpdate(String option) {
        System.out.println("[팀 정보 변경]");
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }

        int i = getTeamIndex(option);

        if (i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            Team team = teams[i];
            Team updateTeam = new Team();

            System.out.printf("팀명(%s)? ", team.name);
            updateTeam.name = keyScan.nextLine();
            System.out.printf("설명(%s)? ", team.description);
            updateTeam.description = keyScan.nextLine();
            System.out.printf("최대인원(%d)? ", team.maxQty);
            updateTeam.maxQty = keyScan.nextInt();
            keyScan.nextLine();
            System.out.printf("시작일(%s)? ", team.startDate);
            updateTeam.startDate = keyScan.nextLine();
            System.out.printf("종료일(%s)? ", team.endDate );
            updateTeam.endDate = keyScan.nextLine();
            teams[i] = updateTeam;
            System.out.println("팀 정보를 변경하였습니다.");
        }
    }
}