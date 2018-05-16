package bitcamp.java106.pms.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;
import bitcamp.java106.pms.servlet.InitServlet;

@SuppressWarnings("serial")
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
    BoardDao boardDao;
    
    @Override
    public void init() throws ServletException {
        this.boardDao = InitServlet.getAppCtx().getBean(BoardDao.class);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>게시물 리스트</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>게시물 수정 결과</h1>");
        out.println("<meta http-equiv='Refresh' content='3;url=list'>");
        
        
        Board board = new Board();
        board.setNo(Integer.parseInt(req.getParameter("no")));
        board.setTitle(req.getParameter("title"));
        board.setContent(req.getParameter("content"));
        
        try {
            int count = boardDao.update(board);
            
            if(count==0)
                throw new Exception("해당 게시글이 존재하지 않습니다.");
            
            out.println("<p>변경하였습니다.</p><p>잠시 후 목록페이지로 이동합니다.</p>");

        } catch(Exception e) {
            out.println("<p> 수정 실패 </p>");
            e.printStackTrace(out);
        }
        
        out.println("</body>");
        out.println("</html>");
    }
}

//ver 28 - 네트워크 버전으로 변경
//ver 26 - BoardController에서 update() 메서드를 추출하여 클래스로 정의.
