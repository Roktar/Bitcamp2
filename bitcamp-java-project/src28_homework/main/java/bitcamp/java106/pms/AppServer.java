package bitcamp.java106.pms;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import bitcamp.java106.pms.context.ApplicationContext;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.dao.ClassDao;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.TaskDao;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.dao.TeamMemberDao;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

public class AppServer {
    ApplicationContext iocContainer;

    public AppServer() throws Exception {
        init();
    }

    // 서버에서 작업하는데 필요한 객체를 준비한다.
    void init() throws Exception {
        // 의존 객체 주입
        iocContainer = new ApplicationContext("bitcamp.java106.pms");
    }

    void onQuit() throws Exception { // 매개변수 주고 처리하는 게 괜찮을듯.
        System.out.println("안녕히 가세요!");
        BoardDao boardDao = (BoardDao) iocContainer.getBean(BoardDao.class);
        TeamDao teamDao = (TeamDao) iocContainer.getBean(TeamDao.class);
        MemberDao memberDao = (MemberDao) iocContainer.getBean(MemberDao.class);
        TaskDao taskDao = (TaskDao) iocContainer.getBean(TaskDao.class);
        TeamMemberDao teamMemberDao = (TeamMemberDao) iocContainer.getBean(TeamMemberDao.class);
        ClassDao classDao = (ClassDao) iocContainer.getBean(ClassDao.class);

        // 각각의 데이터에 대해 예외처리를 분리
        // - 한 곳에 묶어두면 다른 데이터들은 처리가 안되기에 별도로 분리한다.
        try {
            boardDao.save();
        } catch (Exception e) {
            System.out.println("게시물 데이터 저장중 오류 발생");
        }
        try {
            teamDao.save();
        } catch (Exception e) {
            System.out.println("팀 데이터 저장중 오류 발생");
        }
        try {
            memberDao.save();
        } catch (Exception e) {
            System.out.println("회원 데이터 저장중 오류 발생");
        }
        try {
            taskDao.save();
        } catch (Exception e) {
            System.out.println("작업목록 데이터 저장중 오류 발생");
        }
        try {
            teamMemberDao.save();
        } catch (Exception e) {
            System.out.println("팀멤버 데이터 저장중 오류 발생");
        }
        try {
            classDao.save();
        } catch (Exception e) {
            System.out.println("저장 중 오류 발생");
        }
    }

    void onHelp() {
        System.out.println("[도움말]");
        System.out.println("팀 등록 명령 : team/add");
        System.out.println("팀 조회 명령 : team/list");
        System.out.println("팀 상세조회 명령 : team/view 팀명");
        System.out.println("회원 등록 명령 : member/add");
        System.out.println("회원 조회 명령 : member/list");
        System.out.println("회원 상세조회 명령 : member/view 아이디");
        System.out.println("종료 : quit");
    }

    void service() throws Exception {
        // ServerSocket 준비
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("서버 실행");

        // 클라이언트의 연결을 대기
        while (true) {
            Socket socket = serverSocket.accept();

            processRequest(socket);
        }
    }

    void processRequest(Socket socket) {
        PrintWriter out = null;
        Scanner in = null;

        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new Scanner(socket.getInputStream());
            // 클라이언트가 보낸 데이터에서 명령어와 데이터를 분리하여 객체를 준비
            ServerRequest req = new ServerRequest(in.nextLine());

            // 클라이언트 응답과 관련된 객체를 준비
            ServerResponse res = new ServerResponse(out);

            // 클라이언트가 보낸 명령어를 처리할 컨트롤러를 찾는다.
            String path = req.getServerPath();

            System.out.println("요청 작업 : " + path);
            Controller controller = (Controller) iocContainer.getBean(path);

            if (controller != null)
                controller.service(req, res);
            else
                System.out.println("해당 명령어를 처리할 수 없습니다.");
            
            out.println();
        } catch (Exception e) {
            out.println("Server Error : " + e.getMessage());
            out.println(); // 보낼 문자가 더이상 없다는 뜻으로 클라이언트로 보내는 바이트를 끊는다.
        } finally {
            out.close();
            in.close();
            try { socket.close(); } catch (Exception e) { }
        }
    }

    public static void main(String[] args) throws Exception {
        AppServer appServer = new AppServer();
        appServer.service();
    }
}

// ver 17 - Task 관리 기능 추가
// ver 15 - TeamDao와 MemberDao 객체 생성.
// 팀 멤버를 다루는 메뉴 추가.
