package bitcamp.java106.pms.controller.board;


import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.util.Console;

// BoardController는 Controller 규칙을 이행한다. => 규칙에 따라 메소드 생성
@Component(value="board/list")
public class BoardListController implements Controller {
    Scanner keyScan;

    BoardDao boardDao;
    
    public BoardListController(Scanner scanner, BoardDao boardDao) {
        this.keyScan = scanner;
        this.boardDao = boardDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[게시물 목록]");
        Iterator<Board> list = boardDao.list();
        Board board = null;
        
        while (list.hasNext()) {
            board = list.next();
            System.out.printf("%d, %s, %s\n",
                              board.getNo(), board.getTitle(), board.getCreatedDate());
        }
    }
}

// ver 26 - List 작업만 수행하도록 변경
// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.