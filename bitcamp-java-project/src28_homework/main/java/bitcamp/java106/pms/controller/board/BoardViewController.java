package bitcamp.java106.pms.controller.board;


import java.io.PrintWriter;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

// BoardController는 Controller 규칙을 이행한다. => 규칙에 따라 메소드 생성
@Component(value="/board/view")
public class BoardViewController implements Controller {
    BoardDao boardDao;
    
    public BoardViewController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    
    @Override
    public void service(ServerRequest req, ServerResponse res) {
        // TODO Auto-generated method stub
        PrintWriter out = res.getWriter();
        int no = Integer.parseInt(req.getParameter("no"));
        
        Board board = boardDao.get(no);
        System.out.println("클라이언트 요청 : board/view " + no);
        out.println("[게시물 조회]");

        if (board == null) {
            System.out.println("유효하지 않은 게시물 번호입니다.");
        } else {
            out.printf("팀명: %s\n", board.getTitle());
            out.printf("설명: %s\n", board.getContent());
            out.printf("등록일: %s\n", board.getCreatedDate());
        }
    }
}

// ver 14 - BoardDao를 사용하여 게시물 데이터를 관리한다.
// ver 13 - 게시물 등록할 때 등록일의 문자열을 Date 객체로 만들어 저장.