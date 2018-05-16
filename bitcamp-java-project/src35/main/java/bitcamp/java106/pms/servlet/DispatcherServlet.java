package bitcamp.java106.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import bitcamp.java106.pms.AppConfig;
import bitcamp.java106.pms.controller.Controller;


// 업무
// - 클라이언트로부터 요청이 들어오면 그 요청을 컨트롤러에게 위임한다.
@WebServlet("/*")
public class DispatcherServlet implements Servlet {
    ServletConfig config;
    ApplicationContext iocContainer;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        this.config = config;
        
        iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);

    } // 요청이 들어오면 단 1회 실행하는 메소드. => 서블릿 실행에 필요한 자원을 준비하는 코드를 여기에 둔다.

    @Override
    public ServletConfig getServletConfig() {
        // TODO Auto-generated method stub
        return config;
    } // 서블릿 정보를 반환한다. => init() 메소드에서 넘겨받은 값을 보관하고 있다가 호출되면 반환.

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        
        // 매개변수의 값을 원래의 객체로 형변환한다.         
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        String path = request.getPathInfo();
        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Controller controller = (Controller) iocContainer.getBean(path);
        
        if (controller != null)
            controller.service(new ServerRequestAdapter(request), new ServerResponseAdapter(response));
        else
            out.println("해당 명령을 처리할 수 없습니다.");
        
        
    } // 클라이언트로부터 요청이 들어올 때마다 서블릿 컨테이너가 호출하는 메소드.
      // 클라이언트의 요청을 처리하는 코드를 위치시킨다.

    @Override
    public String getServletInfo() {
        return "요청 처리를 중계하는 서블릿";
    } // 서블릿에 대한 정보를 문자열로 리턴한다.

    @Override
    public void destroy() {
    } // 서버를 종료하기 전에 서블릿 컨테이너가 호출하는 메소드.
      // 서블릿이 실행되는 동안 만들었던 자원을 해제하는 코드를 위치시킨다.
}
