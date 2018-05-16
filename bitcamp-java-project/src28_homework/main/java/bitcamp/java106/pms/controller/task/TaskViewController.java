// 팀 작업 관리 기능을 모아 둔 클래스
package bitcamp.java106.pms.controller.task;

import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.TaskDao;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.dao.TeamMemberDao;
import bitcamp.java106.pms.domain.Task;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.util.Console;

@Component(value="task/view")
public class TaskViewController implements Controller {
    
    Scanner keyScan;
    TeamDao teamDao;
    TaskDao taskDao;
    MemberDao memberDao;
    TeamMemberDao teamMemberDao;
    
    public TaskViewController(Scanner scanner, TeamDao teamDao, 
                          MemberDao memberDao, TaskDao taskDao, TeamMemberDao teamMemberDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
        this.taskDao = taskDao;
        this.teamMemberDao = teamMemberDao;
        this.memberDao = memberDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[작업 정보]");
        System.out.print("작업 번호? ");
        int taskNo = Integer.parseInt(keyScan.nextLine());
        
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = teamDao.get(option);
        if (team == null) {
            System.out.printf("'%s' 팀은 존재하지 않습니다.", option);
            return;
        }
        
        Task task = taskDao.get(taskNo);
        if (task == null) {
            System.out.printf("'%s'팀의 %d번 작업을 찾을 수 없습니다.\n",
                    team.getName(), taskNo);
            return;
        }
        
        System.out.printf("작업명: %s\n", task.getTitle());
        System.out.printf("시작일: %s\n", task.getStartDate());
        System.out.printf("종료일: %s\n", task.getEndDate());
        System.out.printf("작업자: %s\n", 
                (task.getWorker() == null) ? "-" : task.getWorker().getId());
        System.out.printf("작업상태: %s\n", getStateLabel(task.getState()));
    }
    
    public static String getStateLabel(int state) {
        switch (state) {
        case Task.READY: return "작업대기";
        case Task.WORKING: return "작업중";
        case Task.COMPLETE: return "작업완료";
        default:
            return null;
        }
    }
}

//ver 17 - 클래스 생성