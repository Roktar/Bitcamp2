package bitcamp.java106.pms.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.domain.Member;

@Component
public class MemberDao extends AbstractDAO<Member>{
    public MemberDao() throws Exception {
        this.load();
    }
    
    public void load() throws Exception {
        // 한줄씩 읽어들이는 게 없기때문에 스캐너를 통해 한줄씩 처리
        Scanner in = new Scanner(new FileReader("data/member.csv"));
        
        while( true ) {
            try {
                String[] arr = in.nextLine().split(",");
                Member member = new Member();
                member.setId(arr[0]);
                member.setEmail(arr[1]);
                member.setPassword(arr[2]);
                insert(member);
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
        PrintWriter out = new PrintWriter(new FileWriter("data/member.csv"));
        
        Iterator<Member> members = this.list();
        
        // List에 보관된 데이터를 board.csv 파일에 저장한다.
        // 기존에 저장된 데이터를 덮어쓴다. 즉 처음부터 다시 저장한다.
        while (members.hasNext()) {
            Member member = members.next();
            out.printf("%s,%s,%s\n", member.getId(), member.getEmail(), member.getPassword());
        }
        out.close();
    }
    
    public int getIndex(Object key) {
        String id = (String) key;
        for(int i=0; i<data.size(); i++) {    
            if( data.get(i).getId().equalsIgnoreCase(id) )
                return i;
        }
        return -1;
    }
}

//ver 16 - 인스턴스 변수를 직접 사용하는 대신 겟터, 셋터 사용.
//ver 14 - MemberController로부터 데이터 관리 기능을 분리하여 MemberDao 생성.