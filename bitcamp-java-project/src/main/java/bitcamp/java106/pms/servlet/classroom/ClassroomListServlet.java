// Controller 규칙에 따라 메서드 작성
package bitcamp.java106.pms.servlet.classroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java106.pms.dao.ClassroomDao;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;
import bitcamp.java106.pms.servlet.InitServlet;

@SuppressWarnings("serial")
@WebServlet("/classroom/list")
public class ClassroomListServlet extends HttpServlet {
    ClassroomDao classroomDao;
    
    @Override
    public void init() throws ServletException {
    	classroomDao = InitServlet.getApplicationContext().getBean(ClassroomDao.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>강의실 목록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>강의실 목록</h1>");
        try {
            List<Classroom> list = classroomDao.selectList();
            out.println("<p><a href='form.html'>새 강의실</a></p>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("    <th>번호</th><th>제목</th><th>시작일</th><th>종료일</th><th>강의실</th><th colspan=2>작업</th>");
            out.println("</tr>");
            for (Classroom classroom : list) {
            	out.println("<tr>");
            	out.println("<form action='update' method='post'>");
                out.printf("<td><input type='text' name='no' value='%d' size='3' style='text-align:center'readonly></td><td><input type='text' name='title' value='%s'/></td><td>"
                		+ "<input type='text' name='startDate' value='%s'/></td>"
                		+ "<td><input type='text' name='endDate' value='%s'/></td><td>"
                		+ "<input type='text' name='room' value='%s'/></td>"
                		+ "<td> <button>변경</button> </td>", 
                		classroom.getNo(), classroom.getTitle(), 
                        classroom.getStartDate(), classroom.getEndDate(),
                        classroom.getRoom(), classroom.getNo());
        		out.println("</form>");
        		out.printf("<form action='delete' method='get'>"
                		+ "<td> <button>삭제</button> </td>\n",
                		classroom.getNo());
                out.println("</form>");
                out.println("</tr>");
            }
        } catch (Exception e) {
            out.println("<p>목록 가져오기 실패!</p>");
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</html>");
    }
}

//ver 31 - JDBC API가 적용된 DAO 사용
//ver 28 - 네트워크 버전으로 변경
//ver 26 - ClassroomController에서 list() 메서드를 추출하여 클래스로 정의.

