package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Task;
import bitcamp.java106.pms.util.ArrayList;

public class TaskDao {
    //Task[] tasks = new Task[1000];
    //int taskIndex = 0;
    
    ArrayList tasks = new ArrayList();
    
    public void insert(Task task) {
        tasks.add(task);
        //this.tasks[this.taskIndex++] = task;
    }
    
    private int count(String teamName) {
        int cnt = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) continue;
            if ( ((Task)tasks.get(i)).getTeam().getName().toLowerCase().equals(teamName))
                cnt++;
        }
        return cnt;
    }
    
    public Task[] list(String teamName) {
        Task[] arr = new Task[tasks.size()];
        
        for(int i=0, x=0; i<tasks.size(); i++) {
            if (((Task)tasks.get(i)).getTeam().getName().toLowerCase().equals(teamName))
                arr[x++] = (Task)tasks.get(i);
        }
        
        return arr;
/*        Task[] arr = new Task[this.count(teamName)];
        for (int i = 0, x = 0; i < taskIndex; i++) {
            if (tasks[i] == null) continue;
            if (tasks[i].getTeam().getName().toLowerCase().equals(teamName)) {
                arr[x++] = tasks[i];
            }
        }
        return arr;*/
    }
    
    public Task get(String teamName, int taskNo) {
        int idx = getTaskIndex(teamName, taskNo);
        
        if(idx > -1)
            return (Task)tasks.get(idx);
        return null;
    }
    
    public void update(Task task) {
        int idx = getTaskIndex(task.getTeam().getName(), task.getNo());
        
        if(idx > -1)
            tasks.set(idx, task);
    }
    
    public void delete(int taskNo) {
        int idx = getTaskIndex(taskNo);
        
        if(idx > -1)
            tasks.remove(idx);
    }
    
    public int getTaskIndex(String teamName, int taskNo) {
        for (int i = 0; i < tasks.size(); i++) {
            if (((Task)tasks.get(i)).getTeam().getName().toLowerCase().equals(teamName) && 
                ((Task)tasks.get(i)).getNo() == taskNo)
                    return i;
        }
        return -1;
    }
    
    public int getTaskIndex(int taskNo) {
        for (int i = 0; i < tasks.size(); i++) {
            if ( ((Task)tasks.get(i)).getNo() == taskNo )
                    return i;
        }
        return -1;
    }
}

// ver 17 - 클래스 생성
