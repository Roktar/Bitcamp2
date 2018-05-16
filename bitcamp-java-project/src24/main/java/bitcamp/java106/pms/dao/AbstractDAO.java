package bitcamp.java106.pms.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class AbstractDAO<E> { // Generalization, 공통된 기능을 상위 클래스에 정의한다.
    LinkedList<E> data = new LinkedList<>();
    
    public void insert(E val) {
        data.add(val);
    }
    
    public Iterator<E> list() {
        ArrayList<E> list = new ArrayList<>();
        
        for (int i = 0; i < data.size(); i++) 
            list.add(this.data.get(i));
        return list.iterator();
    } // 다른 방법을 생각해볼 것.
    
    public E get(Object key) {
        int idx = getIndex(key);
        
        if(idx > -1)
            return data.get(idx);
        return null;
    }
    
    public void update(int i, E val) {

        int idx = getIndex(i);
        if(idx > -1)
            data.set(idx, val);
    }
    
    public void delete(int no) {
        int idx = getIndex(no);
        
        if(idx > -1)
            data.remove(idx);
    }
    
    public abstract int getIndex(Object key);
    
}
