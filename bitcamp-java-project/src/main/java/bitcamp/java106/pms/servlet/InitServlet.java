package bitcamp.java106.pms.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import bitcamp.java106.pms.AppConfig;


// 업무
// - 서블릿들이 사용할 자원을 미리 준비시킨다.
// - IoC 컨테이너(Bean 컨테이너)를 준비한다.
// 즉, 직접 작업을 처리할 서블릿이 아니기때문에 httpservlet을 상속받을 필요가 없다.
@SuppressWarnings("serial")
@WebServlet(
             urlPatterns="/initServlet", // 어떤 문자를 넣어도 상관이 없다.
             loadOnStartup=1)
public class InitServlet extends HttpServlet {
    static ApplicationContext iocContainer;
    
    @Override
    public void init() throws ServletException {
        iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);
    } // 서블릿 실행에 필요한 자원을 준비하는 코드를 여기에 둔다.
    // 다른 서블릿이 사용할 자원을 준비하는 일만 하기때문에 요청처리 코드가 필요없다.
    
    public static ApplicationContext getAppCtx() {
        return iocContainer;
    }
    // 단, 다른 서블릿이 사용할 수 있게하기위해 해당 객체는 던질 수 있도록 처리한다.
}
