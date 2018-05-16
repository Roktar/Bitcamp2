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
@Component(value="/board/update")
public class BoardUpdateController implements Controller {
    BoardDao boardDao;
    
    public BoardUpdateController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    
    @Override
    public void service(ServerRequest req, ServerResponse res) {
        // TODO Auto-generated method stub
        
        PrintWriter out = res.getWriter();
        int no = Integer.parseInt(req.getParameter("no"));
        
        Board updateBoard = new Board();
        updateBoard.setNo(Integer.parseInt(req.getParameter("no")));
        updateBoard.setTitle(req.getParameter("title"));
        updateBoard.setContent(req.getParameter("content"));
        updateBoard.setCreatedDate(new Date(System.currentTimeMillis()));
        
        Board board = boardDao.get(updateBoard.getNo());
        
        if(board == null)
            out.println("유효하지않은 게시물번호입니다.");
        else {
            int idx = boardDao.getIndex(updateBoard.getNo());
            boardDao.update(idx, updateBoard);
            out.println("변경하였습니다.");
        }
    }
}

// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.