package bitcamp.java106.pms.controller.board;


import java.io.PrintWriter;
import java.util.Iterator;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

// BoardController는 Controller 규칙을 이행한다. => 규칙에 따라 메소드 생성
@Component(value="/board/list")
public class BoardListController implements Controller {
    BoardDao boardDao;
    
    public BoardListController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    
    @Override
    public void service(ServerRequest req, ServerResponse res) {
        
        PrintWriter out = res.getWriter();
        
        Iterator<Board> list = boardDao.list();
        Board board = null;
        
        out.println("[게시물 목록]");

        while (list.hasNext()) {
            board = list.next();
            out.printf("%d, %s, %s\n",
                              board.getNo(), board.getTitle(), board.getCreatedDate());
        }
        
        System.out.println("클라이언트 요청 : board/list");
    }
}

// ver 26 - List 작업만 수행하도록 변경
// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.