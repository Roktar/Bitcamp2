package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Team;

public class TeamDao {
    Team[] teams = new Team[1000];
    int teamIndex = 0;
    int currentIndex = -1;
    
    public void insert(Team team) {
        this.teams[this.teamIndex++] = team;
    }
    
    public Team[] list() {
        Team[] arr = new Team[teamIndex];
        for (int i = 0; i < teamIndex; i++) 
            arr[i] = teams[i];
        return arr;
    }
    
    public Team get(String name) {
        int i = this.getTeamIndex(name);
        
        if(i != -1)
            return teams[i];
        else
            return null;
    }

    public void update(Team team) {
        int i = this.getTeamIndex(team.getName());
        
        if( i != -1)
            teams[i] = team;
    }
    
    public void delete(String name) {
        int i = this.getTeamIndex(name);
        
        if( i != -1)
            teams[i] = null;
    }
    
    private int getTeamIndex(String name) {
        for(int i=0; i<teams.length; i++) {
            if(teams[i].getName().equals(name.toLowerCase())) {
               return i;
            }
        }
        return -1;
    }
}
