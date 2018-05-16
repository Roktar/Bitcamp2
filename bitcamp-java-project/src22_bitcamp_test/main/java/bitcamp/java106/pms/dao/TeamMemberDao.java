package bitcamp.java106.pms.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TeamMemberDao {
    HashMap<String, ArrayList<String>> collection = new HashMap<String, ArrayList<String>>();
    
    // String 매개변수는 컨트롤러에서 각 dao에 접근해서 뽑아온 데이터로 세팅한다.
    public int addMember(String teamName, String memberId) {
        
        String tlc = teamName.toLowerCase();
        String mlc = memberId.toLowerCase();
        
        // TeamName으로 memberId가 들어있는 리스트를 가져온다.
        ArrayList<String> members = collection.get(tlc);
        if(members == null) {
            members = new ArrayList<String>();
            members.add(memberId);
            collection.put(teamName, members);
            return 1;
        }
        
        // 중복체크, contains는 해당 클래스 안에 그 값을 가진 객체가 있는 지를 찾는다.
        if(members.contains(mlc)) 
            return 0;
 
        members.add(memberId);
        
        return 1;
    }

    public int deleteMember(String teamName, String memberId) {
        
        String tlc = teamName.toLowerCase();
        String mlc = memberId.toLowerCase();
        
        ArrayList<String> members = collection.get(tlc);
        
        if(members == null || !members.contains(mlc))
            return 0;
        
        members.remove(mlc);
        
        return 1;
    }
    
    public boolean isExist(String teamName, String memberId) {        
        
        String tlc = teamName.toLowerCase();
        String mlc = memberId.toLowerCase();
        
        ArrayList<String> members = collection.get(tlc);
        
        if(members == null || !members.contains(mlc))
            return false;
        
        return true;
        
        //return ( collection.get(teamName.toLowerCase()).contains(memberId.toLowerCase()) );
    } // contains는 ArrayList 클래스단에서 오버라이딩 되잇을거임 아마도
        
    public Iterator<String> getMembers(String teamName) {
        ArrayList<String> members = collection.get(teamName.toLowerCase());
        
        if(members == null)
            return null;
        
        return collection.get(teamName.toLowerCase()).iterator();
    } // 컨트롤러에서 데이터를 건드리지않게 하기 위함.
    // remove가 있지만 객체를 새로 만들기때문에 기존 리스트와는 접점이 없다고 봐도 되겠다.
    
    private ArrayList<String> getTeamMembers(String teamName) {
        ArrayList<String> members = collection.get(teamName.toLowerCase());
        if(members == null) {
            members = new ArrayList<String>();
            collection.put(teamName, members);
        }
        return members;
    }
} 
