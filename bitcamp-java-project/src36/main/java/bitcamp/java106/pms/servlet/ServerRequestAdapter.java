package bitcamp.java106.pms.servlet;

import javax.servlet.http.HttpServletRequest;

import bitcamp.java106.pms.server.ServerRequest;

// 기존 소스를 건들지않고 필요기능을 추가하는 방법 : ADAPTER 패턴
public class ServerRequestAdapter extends ServerRequest{
    
    HttpServletRequest request;
    
    public ServerRequestAdapter(HttpServletRequest request) {
        super("");
        this.request=request;
    }
    
    // 상속받은 메소드를 현재 클래스의 역할에 맞게끔 재정의 - OVERRIDING
    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }
    
    @Override // 기존 기능을 사용하지않는다 : ADAPTER
    public String getServerPath() {
        return request.getPathInfo();
    }
}
