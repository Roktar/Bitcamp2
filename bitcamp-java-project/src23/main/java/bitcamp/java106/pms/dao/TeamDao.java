package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Team;

@Component
public class TeamDao extends AbstractDAO<Team>{

    public int getIndex(Object key) {
        String teamName = (String) key;
        
        for(int i=0; i<data.size(); i++) {
            if(data.get(i).getName().equalsIgnoreCase(teamName))
                return i;
        }
        
        return -1;
    }
}