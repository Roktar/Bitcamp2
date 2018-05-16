package bitcamp.java106.pms.dao;

import java.util.ArrayList;
import java.util.Iterator;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Task;

@Component
public class TaskDao extends AbstractDAO<Task> { 
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
