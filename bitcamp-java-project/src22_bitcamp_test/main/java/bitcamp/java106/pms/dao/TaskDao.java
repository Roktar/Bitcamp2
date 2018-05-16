package bitcamp.java106.pms.dao;

import java.util.Iterator;

import bitcamp.java106.pms.domain.Task;
import java.util.ArrayList;

public class TaskDao extends AbstractDAO<Task>{

    public Iterator<Task> list(String teamName) {
        ArrayList<Task> tasks = new ArrayList<>();
        
/*        for(int i=0, x=0; i < collection.size(); i++) {
            Task task = collection.get(i);
            if(task.getTeam().getName().equalsIgnoreCase(teamName)) 
                tasks.add(task);
        }*/

        for(Task task : collection) {
            if(task.getTeam().getName().equalsIgnoreCase(teamName)) 
                tasks.add(task);
        }
        
        return tasks.iterator();
    }
    // 부모클래스의 list는 모든 객체를 반환하지만
    // task의 list는 특정 조건을 만족한 객체만 반환한다.
    // 그렇기때문에 부모클래스의 list는 부적합하며,
    // 프로그래밍의 일관성을 유지하기위해 오버로딩한다.
    // 부모클래스는 매개변수가 없으나 여기서는 매개변수를 하나 추가했다.

    public int indexOf(Object key) {
        int taskNo = (int) key;
        for (int i = 0; i < collection.size(); i++) {
            Task task = collection.get(i);
            if (task.getNo() == taskNo) {
                return i;
            }
        }
        return -1;
    }
}

//ver 19 - 우리 만든 ArrayList 대신 java.util.LinkedList를 사용하여 목록을 다룬다. 
//ver 18 - ArrayList 클래스를 적용하여 객체(의 주소) 목록을 관리한다.
// ver 17 - 클래스 생성