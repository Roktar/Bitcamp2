package bitcamp.java106.pms.server;

import java.io.PrintStream;
import java.io.PrintWriter;

// 역할 : 응답에 관련된 요청을 처리
public class ServerResponse {
    protected PrintWriter out;
    
    public ServerResponse(PrintWriter out) {
        this.out = out;
    }
    
    public PrintWriter getWriter() {
        return out;
    }
}
