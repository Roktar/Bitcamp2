package bitcamp.java106.pms.controller.board;


import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.util.Console;

// BoardController는 Controller 규칙을 이행한다. => 규칙에 따라 메소드 생성
@Component(value="board/add")
public class BoardAddController implements Controller {
    Scanner keyScan;

    BoardDao boardDao;
    
    public BoardAddController(Scanner scanner, BoardDao boardDao) {
        this.keyScan = scanner;
        this.boardDao = boardDao;
    }
    
    public void service(String menu, String option)  {
        System.out.println("[게시물 입력]");
        Board board = new Board();

        System.out.print("제목? ");
        board.setTitle(this.keyScan.nextLine());

        System.out.print("내용? ");
        board.setContent(this.keyScan.nextLine());

        System.out.print("등록일? ");
        board.setCreatedDate(Date.valueOf(this.keyScan.nextLine()));

        boardDao.insert(board);
    }
}

// ver 26 - Add 작업만 수행하도록 변경
// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.