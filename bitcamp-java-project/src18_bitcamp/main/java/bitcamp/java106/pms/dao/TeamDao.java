package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.util.ArrayList;

public class TeamDao {
    //Team[] teams = new Team[1000];
    ArrayList teams = new ArrayList();
    int teamIndex = 0;
    
    public void insert(Team team) {
        // 팀 정보가 담겨있는 객체의 주소를 배열에 보관한다.
        teams.add(team);
    }
    
    public Team[] list() {
        Team[] arr = new Team[teams.size()];
        
        for (int i = 0; i < this.teams.size(); i++) 
            arr[i] = (Team)teams.get(i);
        
        return arr;
    }
    
    public Team get(String name) {
        int i;
        if ( (i = this.getTeamIndex(name) ) == -1)
            return null;
        return (Team)teams.get(i);
    }
    
    public void update(Team team) {
        int i = this.getTeamIndex(team.getName());
        if (i != -1)
            teams.set(i, team);
    }
    
    public void delete(String name) {
        int i = this.getTeamIndex(name);
        if (i != -1) 
            teams.remove(i);
    }
    
    private int getTeamIndex(String name) {
        for (int i = 0; i < this.teams.size(); i++) {
            if (name.equals( ((Team)this.teams.get(i)).getName().toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
}