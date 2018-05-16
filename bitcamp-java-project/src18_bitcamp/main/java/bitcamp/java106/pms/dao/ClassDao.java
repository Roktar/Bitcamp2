package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.util.ArrayList;

public class ClassDao {
    //Classroom[] classes = new Classroom[1000];
    ArrayList classes = new ArrayList();
    int classIndex = 0;
    
    public void insert(Classroom classroom) {
        //this.classes[this.classIndex++] = classroom;
        classes.add(classroom);
    }
    
    public Classroom[] list() {
        
        //Classroom[] arr = new Classroom[classIndex];
        Classroom[] arr = new Classroom[classes.size()];
        
        for (int i = 0; i < classes.size(); i++) 
            arr[i] = (Classroom)classes.get(i);
        return arr;
    }
    
    public Classroom get(int no) {
/*        if (i < 0 || i >= classIndex)
            return null;
        return classes[i];*/
        
        int idx = this.getIndex(no);
        
        if(idx > -1)
            return (Classroom)classes.get(idx);
        else
            return null;
    }
    
    public void update(Classroom classroom) {
        //classes[classroom.getNo()] = classroom;
        int idx = this.getIndex(classroom.getNo());
        
        if(idx > -1)
            classes.set(idx, classroom);
    }
    
    public void delete(Classroom classroom) {
        //classes[classroom.getNo()] = null;
        int idx = this.getIndex(classroom.getNo());
        
        if(idx > - 1)
            classes.remove(idx);
    }
    
    private int getIndex(int no) {
        for(int i=0; i < classes.size(); i++) {
            if( ((Classroom)classes.get(i)).getNo() == no)
                return i;
        }
        return -1;
    }
}