package bitcamp.java106.pms.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    BoardDao boardDao;
    
    @Override
    public void init() throws ServletException {
        boardDao = InitServlet.getAppCtx().getBean(BoardDao.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>게시물 리스트</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> 게시물 목록 </h1>");
        
        try {
            List<Board> list = boardDao.selectList();
            
            out.println("<table border=1>");
            out.println("<tr>");
            out.println("<th>번호</th><th>제목</th><th>등록일</th>");
            out.println("</tr>");

            for(Board board : list) {
                out.println("<tr>");
                out.printf("<td>%d</td><td><a href='view?no=%d'>%s</a></td><td>%s</td>",
                        board.getNo(), board.getNo(), board.getTitle(), board.getCreatedDate());
                out.println("</tr>");
            }
            out.println("</table>");
        } catch(Exception e) {
            out.println("<p> 게시글 가져오기 실패 </p>");
            e.printStackTrace(out);
        }
        out.println("<a href='../board/form.html'><button> 데이터 등록 </button></a>");
        out.println("</body>");
        out.println("</html>");
    }
}

//ver 28 - 네트워크 버전으로 변경
//ver 26 - BoardController에서 list() 메서드를 추출하여 클래스로 정의. 
