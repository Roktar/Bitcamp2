// Controller 규칙에 따라 메서드 작성
package bitcamp.java106.pms.controller.classroom;

import java.io.PrintWriter;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.ClassDao;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

@Component("/classroom/delete")
public class ClassroomDeleteController implements Controller {
    ClassDao classroomDao;
    
    public ClassroomDeleteController(ClassDao classroomDao) {
        this.classroomDao = classroomDao;
    }
    
    @Override
    public void service(ServerRequest req, ServerResponse res) {
        // TODO Auto-generated method stub
        
        PrintWriter out = res.getWriter();
        int no = Integer.parseInt(req.getParameter("no"));
        System.out.println("클라이언트 요청 : classroom/delete " + no);

        Classroom classroom = classroomDao.get(no);
        
        if (classroom == null) {
            out.println("유효하지 않은 게시물 번호입니다.");
        } else {
            classroomDao.delete(no);
            out.println("삭제하였습니다.");
        }
    }
}