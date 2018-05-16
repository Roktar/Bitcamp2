package bitcamp.java106.pms.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Task;
import bitcamp.java106.pms.domain.Team;

@Component
public class TaskDao extends AbstractDAO<Task> { 
    public TaskDao() throws Exception {
        this.load();
    }
    
    public void load() throws Exception {
        // 한줄씩 읽어들이는 게 없기때문에 스캐너를 통해 한줄씩 처리
        Scanner in = new Scanner(new FileReader("data/task.csv"));
        
        while( true ) {
            try {
                String[] arr = in.nextLine().split(",");
                
                Task task = new Task();
                task.setNo(Integer.parseInt(arr[0]));
                task.setTitle(arr[1]);
                task.setStartDate(Date.valueOf(arr[2]));
                task.setEndDate(Date.valueOf(arr[3]));
                task.setState(Integer.parseInt(arr[4]));
                task.setTeam(new Team(arr[5]));
                task.setWorker(new Member(arr[6]));
                
                insert(task);
            } catch (Exception e) { 
                break;
            }
        }
        in.close();
    }
    
    public void save() throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("data/task.csv"));
        
        Iterator<Task> tasks = this.list();
        
        // List에 보관된 데이터를 board.csv 파일에 저장한다.
        // 기존에 저장된 데이터를 덮어쓴다. 즉 처음부터 다시 저장한다.
        while (tasks.hasNext()) {
            Task task = tasks.next();
            out.printf("%d,%s,%s,%s,%d,%s,%s\n", task.getNo(), task.getTitle(),
                                                 task.getStartDate(), task.getEndDate(), 
                                                 task.getState(), task.getTeam().getName(),
                                                 task.getWorker().getId());
        }
        out.close();
    }
    
    public Iterator<Task> list(String teamName) {
        ArrayList<Task> arr = new ArrayList<>(); 
        
        for(int i=0; i<data.size(); i++) {
            if (data.get(i).getTeam().getName().equalsIgnoreCase(teamName))
                arr.add(data.get(i));
        }
        
        return arr.iterator();
    }
    
    public int getIndex(Object key) {
        int taskNo = (int) key;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getNo() == taskNo)
                    return i;
        }
        return -1;
    }
    
/*    public int getIndex(String teamName, int taskNo) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getTeam().getName().equalsIgnoreCase(teamName) && 
                data.get(i).getNo() == taskNo)
                    return i;
        }
        return -1;
    }*/
}

// ver 22 - 추상클래스 적용
// ver 17 - 클래스 생성
