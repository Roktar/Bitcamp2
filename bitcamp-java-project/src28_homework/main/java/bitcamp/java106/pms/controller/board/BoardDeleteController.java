package bitcamp.java106.pms.controller.board;

import java.io.PrintWriter;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;
import bitcamp.java106.pms.util.Console;

// BoardController는 Controller 규칙을 이행한다. => 규칙에 따라 메소드 생성
@Component(value="/board/delete")
public class BoardDeleteController implements Controller {
    BoardDao boardDao;
    
    public BoardDeleteController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    
    @Override
    public void service(ServerRequest req, ServerResponse res) {
        // TODO Auto-generated method stub
                
        PrintWriter out = res.getWriter();
        int no = Integer.parseInt(req.getParameter("no"));
        System.out.println("클라이언트 요청 : board/delete " + no);

        Board board = boardDao.get(no);
        
        if (board == null) {
            out.println("유효하지 않은 게시물 번호입니다.");
        } else {
            boardDao.delete(no);
            out.println("삭제하였습니다.");
        }
    }
}

// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.