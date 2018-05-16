// Dispatcher와 컨트롤러 사이의 호출 규칙
package bitcamp.java106.pms.controller;

import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

public interface Controller {
    void service(ServerRequest request, ServerResponse response);
}

//ver 36 - Servlet 규칙에 따라 파라미터 타입 변경
//ver 28 - service() 의 규칙 변경
//ver 21 - 인터페이스 추가