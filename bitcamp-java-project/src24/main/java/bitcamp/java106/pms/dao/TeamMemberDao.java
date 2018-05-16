package bitcamp.java106.pms.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import bitcamp.java106.pms.annotation.Component;

@Component
public class TeamMemberDao {
    HashMap<String, ArrayList<String>> collection = new HashMap<String, ArrayList<String>>();
    
    public TeamMemberDao() throws Exception {
        this.load();
    }
    
    public void load() throws Exception {
        // 한줄씩 읽어들이는 게 없기때문에 스캐너를 통해 한줄씩 처리
        Scanner in = new Scanner(new FileReader("data/teamMember.csv"));

        while( true ) {
            try {
                String[] arr = in.nextLine().split(":");
                String[] members = arr[1].split(",");
                System.out.println("키값 : " + arr[0]);
                ArrayList<String> list = new ArrayList<>();
                
                for(String id : members)
                    list.add(id);
              
                collection.put(arr[0], list);
            } catch (Exception e) { 
                System.out.println("에러 : " + e.getMessage());
                break;
                // 1) 데이터를 다 읽었을 때
                // 2) 파일 형식에 문제 있을 때
            }
        }
        in.close();
        // 저장된 데이터를 한 줄씩 읽는다.
        // 한 줄에 한 개의 게시물 데이터를 갖는다.
        // 형식 : 번호, 제목, 내용, 등록일
    }
    
    public void save() throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("data/teamMember.csv"));
        
        Set<String> keys = collection.keySet();
        
        for(String key : keys) {
            List<String> idList = collection.get(key);
            
            out.printf("%s:", key);
            
            for(String id : idList)
                out.printf("%s,", id);
            out.println();
        }
        out.close();
    }
    
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
