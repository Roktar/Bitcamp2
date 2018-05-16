package bitcamp.java106.pms.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.domain.Classroom;

@Component
public class ClassDao extends AbstractDAO<Classroom> {
    public ClassDao() throws Exception {
        this.load();
    }
    
    public void load() throws Exception {
        // 한줄씩 읽어들이는 게 없기때문에 스캐너를 통해 한줄씩 처리
        Scanner in = new Scanner(new FileReader("data/classroom.csv"));
        
        while( true ) {
            try {
                String[] arr = in.nextLine().split(",");
                Classroom classroom = new Classroom();
                
                System.out.println("클래스 : " + arr[0]);

                
                classroom.setNo( Integer.parseInt(arr[0]) );
                classroom.setTitle(arr[1]);
                classroom.setStartDate( Date.valueOf(arr[2]) );
                classroom.setEndDate(Date.valueOf(arr[3]));
                classroom.setPlace( (arr[4] == null ? "" : arr[4]) );
                insert(classroom);
            } catch (Exception e) { 
                System.out.println("클래스 - 에러 : " + e.getMessage());
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
        PrintWriter out = new PrintWriter(new FileWriter("data/classroom.csv"));
        
        Iterator<Classroom> classes = this.list();
        
        // List에 보관된 데이터를 board.csv 파일에 저장한다.
        // 기존에 저장된 데이터를 덮어쓴다. 즉 처음부터 다시 저장한다.
        while (classes.hasNext()) {
            Classroom classroom = classes.next();
            out.printf("%d,%s,%s,%s,%s\n", classroom.getNo(), classroom.getTitle(),
                                        classroom.getTitle(), 
                                        classroom.getStartDate(), classroom.getEndDate() );
        }
        out.close();
    }
    
    
    public int getIndex(Object key) {
        int classNo = (int) key; // classroom은 int값으로 비교한다.
        
        for(int i=0; i < data.size(); i++) {
            if( data.get(i).getNo() == classNo)
                return i;
        }
        return -1;
    }
}