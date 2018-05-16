package bitcamp.java106.pms.controller;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.TaskDao;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Task;
import bitcamp.java106.pms.domain.Team;

public class TaskController {
    TaskDao taskDao;
    MemberDao memberDao;
    TeamDao teamDao;
    Scanner keyScan;
    
    public TaskController(Scanner keyScan, MemberDao memberDao, TeamDao teamDao) {
        this.taskDao = new TaskDao();
        this.keyScan = keyScan;
        this.memberDao = memberDao;
        this.teamDao = teamDao;
    }
    
    public void service(String menu, String option) {
        if (menu.equals("task/add")) {
            this.onTaskAdd(option);
        } else if (menu.equals("task/list")) {
            this.onTaskList(option);
        } else if (menu.equals("task/view")) {
            this.onTaskView(option);
        } else if (menu.equals("task/update")) {
            this.onTaskUpdate(option);
        } else if (menu.equals("task/delete")) {
            this.onTaskDelete(option);
        } else if (menu.equals("task/state")) {
        	this.onTaskState(option);
    	} else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    public void onTaskAdd(String teamName) {
        Task task = new Task();
        
        if(teamName == null) {
            System.out.println("팀 이름을 입력하십시오.");
            return;
        }
        
        Team team = teamDao.get(teamName);
        
        if(team == null) {
            System.out.println("해당 팀이 없습니다.");
            return;
        }
        
        System.out.print("작업명? ");
        task.setTeamName(teamName);
        task.setTaskName(keyScan.nextLine());
        task.setStartDate( inputDate("시작일? ", teamName, 1) );
        task.setEndDate( inputDate("종료일? ", teamName, 2) );
        System.out.print("작업자? ");
        
        String id = keyScan.nextLine();
        Member member = memberDao.get(id);
        
        if(member == null) {
            System.out.println("해당 회원이 없습니다.");
            return;
        }
        task.setWorkerName(id);
        task.setState(0);
        
        taskDao.insert(task);
        System.out.println("작업을 등록했습니다.");
    }
    
    public void onTaskList(String teamName) {
    	if(teamName == null) {
    		System.out.println("팀 이름이 입력되지 않았습니다.");
    		return;
    	}
    	
        Task[] tasks = taskDao.list();
        
        for(int i=0; i<tasks.length; i++) {
            if(tasks[i].getTeamName().toLowerCase().equals(teamName.toLowerCase())) 
                System.out.printf("%d, %s, %s, %s, %s", tasks[i].getTaskNo(), tasks[i].getTaskName(), tasks[i].getStartDate(),
                                                        tasks[i].getEndDate(), tasks[i].getWorkerName());
        }
    }
    
    void onTaskView(String name) {
        if (name == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        System.out.print("작업번호? ");
        int taskNo = keyScan.nextInt();
        keyScan.nextLine();
        
        Task task = taskDao.get(taskNo);

        if (task == null) {
            System.out.println("해당 작업이 없습니다.");
        } else {
            System.out.printf("작업명: %s\n", task.getTaskName());
            System.out.printf("시작일: %s\n", task.getStartDate());
            System.out.printf("종료일: %s\n", task.getEndDate());
            System.out.printf("작업자: %s\n", task.getWorkerName());
        }
    }
    
    void onTaskUpdate(String teamName) {
        if (teamName == null) {
            System.out.println("팀 이름이 입력되지 않았습니다.");
            return;
        }
        Team team = teamDao.get(teamName);
        
        if(team == null) {
        	System.out.println("팀이 존재하지 않습니다.");
        	return;
        }
        
        System.out.print("변경할 작업 번호? ");
        int taskNo = keyScan.nextInt();
        keyScan.nextLine();
        
        Task task = taskDao.get(taskNo);

        if (task == null) {
            System.out.println("해당 작업이 없습니다.");
        } else {
            Task updateTask = new Task();
            
            updateTask.setTeamName(task.getTeamName());
            System.out.printf("작업명(%s)? ", task.getTaskName());
            updateTask.setTaskName(this.keyScan.nextLine());
            System.out.printf("시작일(%s)? ", task.getStartDate());
            updateTask.setStartDate( inputDate("", task.getTeamName(), 1));
            System.out.printf("종료일(%s)? ", task.getEndDate());
            updateTask.setEndDate(inputDate("", task.getTeamName(), 2));
            System.out.printf("작업자(%s)? ", task.getWorkerName());
            
            String id = keyScan.nextLine();
            Member member;
            
            if(id == null)
            	updateTask.setWorkerName(task.getWorkerName());
            else {
            	member = memberDao.get(id);
            
	            if(member == null) {
	            	System.out.println("등록되지 않은 작업자입니다.");
	            	return;
	            }
	            updateTask.setWorkerName(member.getId());
            }
            
            System.out.print("변경하시겠습니까(y/N)? ");
            if(keyScan.nextLine().toLowerCase().equals("y")) {
                taskDao.update(updateTask);
                System.out.println("변경하였습니다.");
            } else
            	System.out.println("취소하였습니다.");
        }
    }
    
    void onTaskDelete(String teamName) {
    	if(teamName == null) {
    		System.out.println("팀 이름이 입력되지 않았습니다.");
    		return;
    	}
    	
        Team team = teamDao.get(teamName);
        
        if(team == null) {
        	System.out.println("팀이 존재하지 않습니다.");
        	return;
        }
        
        System.out.printf("삭제할 작업 번호? ");
        int taskNo = keyScan.nextInt();
        keyScan.nextLine();
        Task task = taskDao.get(taskNo);
        
        if (task == null) {
            System.out.println("없는 작업번호입니다.");
            return;
        }
        System.out.print("정말 삭제하시겠습니까(y/N)? ");
        if(keyScan.nextLine().toLowerCase().equals("y")) {
        	taskDao.delete(taskNo);
        	System.out.println("삭제하였습니다.");
        }
    }
    
    void onTaskState(String teamName) {
        if (teamName == null) {
            System.out.println("팀 이름이 입력되지 않았습니다.");
            return;
        }
        Team team = teamDao.get(teamName);
        
        if(team == null) {
        	System.out.println("팀이 존재하지 않습니다.");
        	return;
        }
        
        System.out.print("상태를 변경할 작업의 번호? ");
        int taskNo = keyScan.nextInt();
        keyScan.nextLine();
        
        Task task = taskDao.get(taskNo);

        if (task == null) {
            System.out.println("해당 작업이 없습니다.");
        } else {
            System.out.printf("'%s' 작업의 상태 : %s\n", task.getTaskName(), printState(task.getState()));
            
            System.out.print("변경할 상태(0:작업대기, 1:작업중, 9:작업완료)? ");
            int state = keyScan.nextInt();
            keyScan.nextLine();
            
            if(state == 0 || state == 1 || state == 9) {
            	task.setState(state);
            	System.out.printf("작업의 상태를 '%s'(으)로 변경하였습니다.", printState(state)));
            } else
            	System.out.println("올바르지않은 값입니다. 이전 상태를 유지합니다.");
        }
    }
    
    public String printState(int state) {
        switch(state) {
	    	case 0 : 
	    		return "작업대기";
	    	case 1 : 
	    		return "작업중";
	    	case 9 : 
	    		return "작업완료";
        }
    }
    
    public Date inputDate(String category, String teamName, int type) {
        String date;
        Date retVal;
        Team team = teamDao.get(teamName);
        
        System.out.print(category);
        date = keyScan.nextLine();
        
        try {
            retVal = Date.valueOf(date);

            if(type == 1) {
                if(team.getStartDate().compareTo(retVal) < 0)
                    return retVal;
                else
                    return team.getStartDate();
            } else {
                if(team.getEndDate().compareTo(retVal) > 0)
                    return team.getEndDate();
                else
                    return retVal;
            }
        } catch(Exception e) {
            if(type == 1)
                return team.getStartDate();
            else
                return team.getEndDate();
        }
    }
}