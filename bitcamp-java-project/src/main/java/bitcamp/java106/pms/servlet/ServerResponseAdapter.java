package bitcamp.java106.pms.servlet;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import bitcamp.java106.pms.server.ServerResponse;

public class ServerResponseAdapter extends ServerResponse {
    HttpServletResponse response;
    public ServerResponseAdapter(HttpServletResponse response) {
        super(null);
        this.response = response;
    }
    
    @Override
    public PrintWriter getWriter() {
        try {
            return response.getWriter();
        } catch(Exception e) {
            return null;
        }
    }
}
