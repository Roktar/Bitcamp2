// Controller 규칙에 따라 메서드 작성
package bitcamp.java106.pms.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet("/board/view")
public class BoardViewServlet extends HttpServlet {
    BoardDao boardDao;
    
    @Override
    public void init() throws ServletException {
        this.boardDao = InitServlet.getAppCtx().getBean(BoardDao.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int no = Integer.parseInt(req.getParameter("no"));
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>게시물 상세보기 - " + no + "번 게시물</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>게시물 상세보기</h1>");
        out.println("<form action='update' method='post'>");
        
        try {
            Board board = boardDao.selectOne(no);
            
            if (board == null) {
                throw new Exception("유효하지 않은 게시물 번호입니다.");
            }
            
            out.println("<table border=1>");
            out.println("<tr>");
            out.printf("<th>번호</th><td><input type='text' name='no' value='%d' readonly'></td>", board.getNo());
            out.println("</tr>");
            out.println("<tr>");
            out.printf("<th>제목</th><td><input type='text' name='title' value='%s'></td>", board.getTitle());
            out.println("</tr>");
            out.println("<tr>");
            out.printf("<th>내용</th><td><textarea rows='10' cols='60' name='content' >%s</textarea></td>", board.getContent());
            out.println("</tr>");            
            out.println("<tr>");
            out.printf("<th>등록일</th><td><input type='text' value='%s'></td>", board.getCreatedDate());
            out.println("</tr>");
            out.println("</table>");
        } catch(Exception e) {
            out.print("<p>상세 조회 실패</p>");
            e.printStackTrace(out);
        }
        out.printf(
                  "<p>"
                +  "<a href='list'>목록</a>"
                + "<button>변경</button>"
                + "<a href='delete?no=%d'> 삭제 </a> "
                + "</p>"
                , no);
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}

//ver 28 - 네트워크 버전으로 변경
//ver 26 - BoardController에서 view() 메서드를 추출하여 클래스로 정의.
