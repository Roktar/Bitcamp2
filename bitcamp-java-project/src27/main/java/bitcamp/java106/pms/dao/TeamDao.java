package bitcamp.java106.pms.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Team;

@Component
public class TeamDao extends AbstractDAO<Team>{
    public TeamDao() throws Exception {
        this.load();
    }
    
    public void load() throws Exception {
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/team.data")));) {
            while( true ) {
                try {
                    this.insert((Team) in.readObject());
                } catch (Exception e) { 
                    break;
                }
            }
        }
    }
    
    public void save() throws Exception {        
        Iterator<Team> teams = this.list();
        
        try( ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/team.data")));) {
            while (teams.hasNext()) 
                out.writeObject(teams.next());
        }
    }
    

    public int getIndex(Object key) {
        String teamName = (String) key;
        
        for(int i=0; i<collection.size(); i++) {
            if(collection.get(i).getName().equalsIgnoreCase(teamName))
                return i;
        }
        
        return -1;
    }
}