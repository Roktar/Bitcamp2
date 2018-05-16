package bitcamp.java106.pms.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.servlet.InitServlet;

@SuppressWarnings("serial")
@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
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
        
        int no = Integer.parseInt(req.getParameter("no"));
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>게시물 리스트</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>게시물 삭제 결과</h1>");
        out.println("<meta http-equiv='Refresh' content='3;url=list'>");
        
        try {
            int count = boardDao.delete(no);
            
            if(count==0)
                throw new Exception("해당 게시물이 없습니다.");
            
            out.println("<p>삭제하였습니다.</p>");

        } catch(Exception e) {
            out.println("<p>삭제 실패</p>");
            e.printStackTrace(out);
        }
        
        out.println("</body>");
        out.println("</html>");
    }
}

//ver 28 - 네트워크 버전으로 변경
//ver 26 - BoardController에서 delete() 메서드를 추출하여 클래스로 정의. 
