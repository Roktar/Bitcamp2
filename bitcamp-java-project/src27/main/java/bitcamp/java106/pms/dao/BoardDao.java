package bitcamp.java106.pms.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Board;

@Component
public class BoardDao extends AbstractDAO<Board>{

    public BoardDao()  throws Exception {
        this.load();
    }
        
    public void load() throws Exception {
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/board.data")));) {
            while( true ) {
                try {
                    this.insert((Board) in.readObject());
                } catch (Exception e) { 
                    break;
                }
            }
        } catch(Exception e) {
            System.out.println("로딩 에러 : " + e.getMessage());
        }
    }
    
    public void save() throws Exception {        
        Iterator<Board> boards = this.list();
        
        // List에 보관된 데이터를 board.csv 파일에 저장한다.
        // 기존에 저장된 데이터를 덮어쓴다. 즉 처음부터 다시 저장한다.
        try( ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/board.data")));) {
            while (boards.hasNext()) 
                out.writeObject(boards.next());
        } // 예외가 발생하면 메소드단에서 예외를 던진다. 
          // 호출하는 클래스단(App)에서 예외를 받기때문에 처리는 거기서 하면된다.
    }

    public int getIndex(Object key) { // 부모클래스에 선언된 리스트를 돌아보며 값을 찾는다.
        int no = (int) key; // board는 글번호(int)로 검색한다.
        
        for(int i=0; i<collection.size(); i++) {
            if( collection.get(i).getNo() == no )
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





