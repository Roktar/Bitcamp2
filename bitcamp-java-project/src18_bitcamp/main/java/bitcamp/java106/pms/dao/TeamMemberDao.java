package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.util.ArrayList;

public class TeamMemberDao {
    
    // ArrayList 2개 사용
    //private Object[][] teamMembers = new Object[1000][2];
    ArrayList teams = new ArrayList();
    ArrayList members = new ArrayList();
    //private int rowIndex;
    // 0 : team, 1 : member
    
    // String 매개변수는 컨트롤러에서 각 dao에 접근해서 뽑아온 데이터로 세팅한다.
    public int addMember(String teamName, String memberId) {     
        
        if(isExist(teamName, memberId))
            return 0;
            
        teams.add(teamName);
        members.add(memberId);
        return 1;
    }

    public int deleteMember(String teamName, String memberid) {
        
        int index = this.getIndex(teamName, memberid);
        
        if(index < 0)
            return 0;
        
        teams.remove(index);
        members.remove(index);
        return 1;
    }
        
    public boolean isExist(String teamName, String memberid) {
        if(this.getIndex(teamName, memberid) > 0)
            return true;
        else
            return false;
    }
        
    private int getIndex(String teamName, String memberid) {
        String ptn = teamName.toLowerCase();
        String pmi = memberid.toLowerCase();
        
        for(int i=0; i < teams.size(); i++) {
            String tn = ((String)this.teams.get(i)).toLowerCase();
            String mi = ((String)this.members.get(i)).toLowerCase();
            
            if(tn.equals(ptn) && mi.equals(pmi))
                return i;
        }
        return -1;
    }
    
    
    public String[] getMembers(String teamName) {
        String ptn = teamName.toLowerCase();
        String[] members = new String[this.getMemberCount(teamName)];
        
        for(int i=0, x = 0; i < teams.size(); i++) {
            String tn = ((String)this.teams.get(i)).toLowerCase();
            if(tn.equals(ptn) )
                members[x++] = (String)this.members.get(i);
        }
        return members;
    }
    
    private int getMemberCount(String teamName) {
        String ptn = teamName.toLowerCase();
        int cnt = 0;
        for(int i=0; i < teams.size(); i++) {
            String tn = ((String)this.teams.get(i)).toLowerCase();
            if(tn.equals(ptn))
                cnt++;
        }
        return cnt;
    }
}
