package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Classroom;

@Component
public class ClassDao extends AbstractDAO<Classroom> {
    public int getIndex(Object key) {
        int classNo = (int) key; // classroom은 int값으로 비교한다.
        
        for(int i=0; i < data.size(); i++) {
            if( data.get(i).getNo() == classNo)
                return i;
        }
        return -1;
    }
}