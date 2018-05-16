package bitcamp.java106.pms.controller;

import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

public interface Controller {
    // 사용자가 명령어를 입력하면 App 클래스는 해당 명령어를 처리할 컨트롤러를 찾는다.
    // 그리고 다음에 선언된 메소드를 호출.
    /*public abstract */void service(ServerRequest req, ServerResponse res);    
}

//ver 28 - service() 규칙 변경
//ver 21 - 인터페이스 추가
