package bitcamp.java106.pms.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedOutputStream;
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
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/task.data")));) {
            while( true ) {
                try {
                    this.insert((Task) in.readObject());
                } catch (Exception e) { 
                    break;
                }
            }
        }
    }
    
    public void save() throws Exception {        
        Iterator<Task> tasks = this.list();
        PipedOutputStream s = new PipedOutputStream();

        try( ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/task.data")));) {
            while (tasks.hasNext()) 
                out.writeObject(tasks.next());
        } 
    }
    
    public Iterator<Task> list(String teamName) {
        ArrayList<Task> arr = new ArrayList<>(); 
        
        for(int i=0; i<collection.size(); i++) {
            if (collection.get(i).getTeam().getName().equalsIgnoreCase(teamName))
                arr.add(collection.get(i));
        }
        
        return arr.iterator();
    }
    
    public int getIndex(Object key) {
        int taskNo = (int) key;
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getNo() == taskNo)
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
