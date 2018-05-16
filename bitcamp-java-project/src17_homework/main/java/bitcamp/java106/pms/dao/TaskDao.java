package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Task;
import bitcamp.java106.pms.domain.Team;

public class TaskDao {
        Task[] tasks = new Task[1000];
        int taskIndex = 0;
    
        public void insert(Task task) {
            task.setTaskNo(taskIndex);
            this.tasks[this.taskIndex++] = task;
        }
        
        public Task[] list() {
            Task[] arr = new Task[taskIndex];
            for (int i = 0; i < taskIndex; i++) 
                arr[i] = tasks[i];
            return arr;
        }
        
        public Task get(int taskNo) {
            int i = this.getTaskIndex(taskNo);
            
            if(i != -1)
                return tasks[i];
            else
                return null;
        }
        
        public void update(Task task) {
            int i = this.getTaskIndex(task.getTaskNo());
            
            if( i != -1)
                tasks[i] = task;
        }
        
        public void delete(int taskNo) {
            int i = this.getTaskIndex(taskNo);
            
            if( i != -1)
                tasks[i] = null;
        }
        
        private int getTaskIndex(int taskNo) {
            for(int i=0; i<tasks.length; i++) {
                if(tasks[i].getTaskNo() == taskNo) {
                   return i;
                }
            }
            return -1;
        }
}