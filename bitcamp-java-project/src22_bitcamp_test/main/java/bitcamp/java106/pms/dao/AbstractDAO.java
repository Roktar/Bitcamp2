package bitcamp.java106.pms.dao;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class AbstractDAO<E> {
    
    protected LinkedList<E> collection = new LinkedList<>();
    
    public void insert(E value) {
        collection.add(value);
    } // Object로 하면 언박싱해야하는데 언박싱할거면 이렇게 하나로 묶을 수가 없다.
      // 그래서 제네릭으로 타입을 확실하게 정해주면 언박싱이 필요없다.
    
    public Iterator<E> list() {
        return collection.iterator();
    } // 해당 타입으로 정확하게 반환
    
    public E get(Object key) {
        int index = this.indexOf(key);
        if (index == -1)
            return null;
        return collection.get(index);
    } // board, classroom은 int, 나머지는 String형식으로 처리하기때문에 그 둘을 받아들일 수 있는 Object를 인수로 받아온다.
    
    public void update(int index, E value) {
        collection.set(index, value);
    } // 위치를 구한다음 수정. 
    
    public void delete(Object key) {
        int index = this.indexOf(key);
        if (index == -1)
            return;
        collection.remove(index);
    } // 위 리스트에서 해당 키값을 가진 위치를 찾아서 삭제.
    
    public abstract int indexOf(Object key); // 서브클래스의 특성에 맞게 구현하도록 강제한다.
}
