package bitcamp.java106.pms;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

// HTTP 프로토콜에 따라 요청을 처리할 서버

public class HttpServer {
    int port;
    ApplicationContainer aContainer;

    public HttpServer(int port, ApplicationContainer aContainer) {
        this.port = port;
        this.aContainer = aContainer;
    }
 
    public void execute() throws Exception {
        ServerSocket serverSocket = new ServerSocket(this.port);
        System.out.println("Server On");

        while (true) {
            Socket socket = serverSocket.accept();
            
            System.out.println("요청 처리중");
            new RequestProcessThread(socket).start(); 
        }
    }

    // 기존 실행 흐름과 분리하여 동작할 쓰레드 생성
    class RequestProcessThread extends Thread {
        Socket socket;

        public RequestProcessThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            PrintWriter out = null;
            Scanner in = null;

            try {
                out = new PrintWriter(socket.getOutputStream());
                in = new Scanner(socket.getInputStream());

                // HTTP 프로토콜에서 요청 정보 추출
                String line = null;
                String requestURI = null;
                boolean firstLine = true;

                while (true) {
                    line = in.nextLine();

                    //System.out.println(line);

                    if (line.equals("")) // CRLF를 의미
                        break;

                    // 첫 줄에는 사용자의 명령이 들어있다.
                    // 따라서 첫 줄이 분리되어 명령어를 꺼냈다면 다시 꺼낼 필요가 없다.
                    if (!firstLine)
                        continue;

                    // 도메인(서버주소)과 분리하고 그 뒷부분을 기준으로 처리한다.
                    // 그 분리는 아마 요청 도중에 자동으로 분리되는 듯.
                    // 단, 도중에 데이터 전송 방식과 HTTP/1.1이 붙음.
                    // split()[0] : 데이터 전송 방식(GET/POST)
                    // split()[1] : 사용자가 요청한 명령(페이지)
                    // split()[2] : HTTP/1.1, HTTP 방식으로 요청했다는 걸 뜻하는 듯.
                    requestURI = line.split(" ")[1];
                    firstLine = false;
                }

                // AppContainer로 실행을 요청
                String result = aContainer.execute(requestURI);

                // HTTP프로토콜에 따라 응답
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/plain;charset=UTF-8");
                out.println();
                out.println(result);

            } catch (Exception e) {
                
                out.println("HTTP/1.1 500 Internal Server Error");
                out.println("Content-Type: text/plain;charset=UTF-8");
                out.println();
                
                out.println("서버 오류!");
                e.printStackTrace(out);
                out.println();
            } finally {
                out.close();
                in.close();
                try {
                    socket.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
