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

@Component(value="task/list")
public class TaskListController implements Controller {
    
    Scanner keyScan;
    TeamDao teamDao;
    TaskDao taskDao;
    MemberDao memberDao;
    TeamMemberDao teamMemberDao;
    
    public TaskListController(Scanner scanner, TeamDao teamDao, 
                          MemberDao memberDao, TaskDao taskDao, TeamMemberDao teamMemberDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
        this.taskDao = taskDao;
        this.teamMemberDao = teamMemberDao;
        this.memberDao = memberDao;
    }
    
    public void service(String menu, String option) {
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = teamDao.get(option);
        if (team == null) {
            System.out.printf("'%s' 팀은 존재하지 않습니다.", option);
            return;
        }
        
        System.out.println("[팀 작업 목록]");
        
        Iterator<Task> list = taskDao.list(team.getName());
        Task task = null;
        
        while(list.hasNext()) {
            task = list.next();
                System.out.printf("%d,%s,%s,%s,%s\n", 
                        task.getNo(), task.getTitle(), 
                        task.getStartDate(), task.getEndDate(),
                        (task.getWorker() == null) ? 
                                "-" : task.getWorker().getId());
        }
        System.out.println();
    }
}

//ver 17 - 클래스 생성