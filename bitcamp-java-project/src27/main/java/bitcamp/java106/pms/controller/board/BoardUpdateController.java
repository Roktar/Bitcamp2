package bitcamp.java106.pms.controller.board;

import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.util.Console;

// BoardController는 Controller 규칙을 이행한다. => 규칙에 따라 메소드 생성
@Component(value="board/update")
public class BoardUpdateController implements Controller {
    Scanner keyScan;

    BoardDao boardDao;
    
    public BoardUpdateController(Scanner scanner, BoardDao boardDao) {
        this.keyScan = scanner;
        this.boardDao = boardDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[게시물 변경]");
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        Board board = boardDao.get(Integer.parseInt(option));
        
        if (board == null) {
            System.out.println("유효하지 않은 게시물 번호입니다.");
        } else {
            Board updateBoard = new Board();
            System.out.printf("제목(%s)? ", board.getTitle());
            updateBoard.setTitle(this.keyScan.nextLine());
            System.out.printf("설명(%s)? ", board.getContent());
            updateBoard.setContent(this.keyScan.nextLine());
            updateBoard.setCreatedDate(board.getCreatedDate());
            updateBoard.setNo(board.getNo());
            
            int idx = boardDao.getIndex(board.getNo());
            
            if(idx < 0)
                return;
            boardDao.update(idx, updateBoard);
            System.out.println("변경하였습니다.");
        }
    }
}

// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.