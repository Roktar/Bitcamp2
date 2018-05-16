package bitcamp.java106.pms.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;

@Component
public class TeamDao extends AbstractDAO<Team>{
    public TeamDao() throws Exception {
        this.load();
    }
    
    public void load() throws Exception {
        // 한줄씩 읽어들이는 게 없기때문에 스캐너를 통해 한줄씩 처리
        Scanner in = new Scanner(new FileReader("data/team.csv"));
        
        while( true ) {
            try {
                String[] arr = in.nextLine().split(",");
                Team team = new Team();
                team.setName(arr[0]);
                team.setDescription(arr[1]);
                team.setMaxQty( Integer.parseInt(arr[2]) );
                team.setStartDate(Date.valueOf(arr[3]));
                team.setEndDate(Date.valueOf(arr[4]));
                insert(team);
            } catch (Exception e) { 
                break;
                // 1) 데이터를 다 읽었을 때
                // 2) 파일 형식에 문제 있을 때
            }
        }
        // 저장된 데이터를 한 줄씩 읽는다.
        // 한 줄에 한 개의 게시물 데이터를 갖는다.
        // 형식 : 번호, 제목, 내용, 등록일
        in.close();
    }
    
    public void save() throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("data/team.csv"));
        
        Iterator<Team> teams = this.list();
        
        // List에 보관된 데이터를 board.csv 파일에 저장한다.
        // 기존에 저장된 데이터를 덮어쓴다. 즉 처음부터 다시 저장한다.
        while (teams.hasNext()) {
            Team team = teams.next();
            out.printf("%s,%s,%d,%s~%s\n", team.getName(), team.getDescription(), 
                                           team.getMaxQty(),
                                           team.getStartDate(), team.getEndDate());
        }
        out.close();
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