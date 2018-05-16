package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.util.ArrayList;

public class BoardDao {
    ArrayList collection = new ArrayList();
    
    public void insert(Board board) {
        collection.add(board);
    }
    
    public Board[] list() {
        Board[] arr = new Board[this.collection.size()];
        
        for (int i = 0; i < collection.size(); i++) 
            arr[i] = (Board) this.collection.get(i);
        return arr;
    }
    
    public Board get(int no) {
        int idx = getBoardIndex(no);
        if(idx > -1)
            return (Board)collection.get(idx);
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
            if( ((Board)collection.get(i)).getNo() == no)
                return i;
        }
        return -1;
    }
}

// ver 14 - BoardController로부터 데이터 관리 기능을 분리하여 BoardDao 생성.





