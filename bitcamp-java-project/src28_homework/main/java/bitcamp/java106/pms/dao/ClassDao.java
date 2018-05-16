package bitcamp.java106.pms.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.domain.Classroom;

@Component
public class ClassDao extends AbstractDAO<Classroom> {
    int baseIdx = 0;
    
    public ClassDao() throws Exception {
        this.load();
    }
    
    public void load()  throws Exception {
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/classroom.data")));) {
            while( true ) {
                try {
                    this.insert((Classroom) in.readObject());
                } catch (Exception e) { 
                    break;
                }
            }
        } catch(Exception e) {
            System.out.println("로딩 에러 : " + e.getMessage());
        }
        
        baseIdx = this.collection.get( this.collection.size() -1).getNo();
    }
    
    public void save() throws Exception {        
        Iterator<Classroom> classes = this.list();
        
        try( ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/classroom.data")));) {
            while (classes.hasNext()) 
                out.writeObject(classes.next());
        }
    }
    
    
    public int getIndex(Object key) {
        int classNo = (int) key; // classroom은 int값으로 비교한다.
        
        for(int i=0; i < collection.size(); i++) {
            if( collection.get(i).getNo() == classNo)
                return i;
        }
        return -1;
    }
    
    public int getBaseIdx() {
        return baseIdx;
    }
}