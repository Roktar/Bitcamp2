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
import bitcamp.java106.pms.servlet.InitServlet;

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    BoardDao boardDao;

    @Override
    public void init() throws ServletException {
        boardDao = InitServlet.getAppCtx().getBean(BoardDao.class);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>게시물 리스트</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>게시물 등록 결과</h1>");
        
        // 지정된 시간이 경과하면 특정 서블릿을 요청하도록 태그를 추가한다.
        // - 웹브라우저는 meta 태그 내용대로 동작.
        out.println("<meta http-equiv='Refresh' content='3;url=list'>");
        
        Board board = new Board();
        board.setTitle(request.getParameter("title"));
        board.setContent(request.getParameter("content"));
        board.setCreatedDate(new Date(System.currentTimeMillis()));
        
        try {
            boardDao.insert(board);
            out.println("<p>등록 성공!<p><br><p>잠시 후 게시물 목록을 표시합니다.</p>");
        } catch(Exception e) { 
            out.println("<p style='color:red;'>등록실패<p> ");
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</html>");
    }
}

//ver 28 - 네트워크 버전으로 변경
//ver 26 - BoardController에서 add() 메서드를 추출하여 클래스로 정의. 
