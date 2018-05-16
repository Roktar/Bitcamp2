package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Member;

public class MemberDao extends AbstractDAO<Member>{
        
    public int indexOf(Object key) {
        String id = (String) key;
        
        for(int i=0; i<collection.size(); i++) {    
            if( collection.get(i).getId().toLowerCase().equals(id) )
                return i;
        }
        return -1;
    }
}