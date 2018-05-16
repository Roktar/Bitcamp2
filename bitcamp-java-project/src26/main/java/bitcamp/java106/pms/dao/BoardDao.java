package bitcamp.java106.pms.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Board;

@Component
public class BoardDao extends AbstractDAO<Board>{
    
    public BoardDao() throws Exception {
        this.load();
    }
    
    public void load() throws Exception {
        // 한줄씩 읽어들이는 게 없기때문에 스캐너를 통해 한줄씩 처리
        Scanner in = new Scanner(new FileReader("data/board.csv"));

        while( true ) {
            try {
                String line = in.nextLine();
                System.out.println(line);
                String[] arr = line.split(",");
                Board board = new Board();
                board.setNo( Integer.parseInt(arr[0]) );
                board.setTitle(arr[1]);
                board.setContent(arr[2]);
                board.setCreatedDate(Date.valueOf(arr[3]));
                insert(board);
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
        PrintWriter out = new PrintWriter(new FileWriter("data/board.csv"));
        
        Iterator<Board> boards = this.list();
        
        // List에 보관된 데이터를 board.csv 파일에 저장한다.
        // 기존에 저장된 데이터를 덮어쓴다. 즉 처음부터 다시 저장한다.
        while (boards.hasNext()) {
            Board board = boards.next();
            out.printf("%d,%s,%s,%s\n", board.getNo(), board.getTitle(),
                        board.getContent(), board.getCreatedDate());
        }
        out.close();
    }

    public int getIndex(Object key) { // 부모클래스에 선언된 리스트를 돌아보며 값을 찾는다.
        int no = (int) key; // board는 글번호(int)로 검색한다.
        
        for(int i=0; i<data.size(); i++) {
            if( data.get(i).getNo() == no )
                return i;
        }
        return -1;
    }
}

// ver 24 - 데이터 로드 & 세이브
// ver 23 - @Component Annotation 추가
// ver 22 - 추상클래스 상속
// ver 19 - 컬렉션 클래스 및 제네릭 적용
// ver 14 - BoardController로부터 데이터 관리 기능을 분리하여 BoardDao 생성.





