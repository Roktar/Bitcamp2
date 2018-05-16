package bitcamp.java106.pms.dao;

import java.util.LinkedList;

import bitcamp.java106.pms.domain.Board;

public class BoardDao {
    LinkedList<Board> collection = new LinkedList<Board>();
    
    public void insert(Board board) {
        collection.add(board);
    }
    
    public Board[] list() {
        Board[] arr = new Board[this.collection.size()];
        
        for (int i = 0; i < collection.size(); i++) 
            arr[i] = this.collection.get(i);
        return arr;
    }
    
    public Board get(int no) {
        int idx = getBoardIndex(no);
        if(idx > -1)
            return collection.get(idx);
        return null;
    }
    
    public void update(Board board) {

        int idx = getBoardIndex(board.getNo());
        if(idx > -1)
            collection.set(idx, board);
    }
    
    public void delete(int no) {
        int idx = getBoardIndex(no);
        
        if(idx > -1)
            collection.remove(idx);
    }
    
    private int getBoardIndex(int no) {
        for(int i=0; i< collection.size(); i++) {            
            if( collection.get(i).getNo() == no)
                return i;
        }
        return -1;
    }
}

// ver 19 - 컬렉션 클래스 및 제네릭 적용
// ver 14 - BoardController로부터 데이터 관리 기능을 분리하여 BoardDao 생성.





