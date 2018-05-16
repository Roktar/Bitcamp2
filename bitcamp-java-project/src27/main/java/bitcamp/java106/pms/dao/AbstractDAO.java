package bitcamp.java106.pms.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class AbstractDAO<E> { // Generalization, 공통된 기능을 상위 클래스에 정의한다.
    protected LinkedList<E> collection = new LinkedList<>();
    
    public void insert(E value) {
        collection.add(value);
    }
    
    public Iterator<E> list() {
        return collection.iterator();
    }
    
    public E get(Object key) {
        int index = this.getIndex(key);
        if (index == -1)
            return null;
        return collection.get(index);
    }
    
    public void update(int index, E value) {
        collection.set(index, value);
    }
    
    public void delete(Object key) {
        int index = this.getIndex(key);
        if (index == -1)
            return;
        collection.remove(index);
    }
        
    public abstract int getIndex(Object key);
    
}
