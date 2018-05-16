package bitcamp.java106.pms.controller.board;


import java.io.PrintWriter;
import java.sql.Date;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

// BoardController는 Controller 규칙을 이행한다. => 규칙에 따라 메소드 생성
@Component(value="/board/add")
public class BoardAddController implements Controller {
    BoardDao boardDao;
    
    public BoardAddController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    
    @Override
    public void service(ServerRequest req, ServerResponse res) {
        // TODO Auto-generated method stub
        Board board = new Board();
        
        board.setTitle(req.getParameter("title"));
        board.setContent(req.getParameter("content"));
        board.setCreatedDate(new Date(System.currentTimeMillis()));
        
        boardDao.insert(board);
        
        PrintWriter out = res.getWriter();
        out.println("등록 성공");
        out.close();
    }
}

// ver 28 - 네트워크로 변경
// ver 26 - Add 작업만 수행하도록 변경
// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.