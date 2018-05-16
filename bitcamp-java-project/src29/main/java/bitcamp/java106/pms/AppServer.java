// App을 서버로 만들기
package bitcamp.java106.pms;

public class AppServer {
    HttpServer httpServer;
    ApplicationContainer aContainer;
    
    public AppServer(int port) throws Exception {        
        // 클라이언트 요청을 처리할 객체 준비
        aContainer = new DefaultApplicationContainer();
        
        // 웹 서버 준비
        httpServer = new HttpServer(port, aContainer);
    }
    
/*    void onQuit() {
        System.out.println("안녕히 가세요!");
        BoardDao boardDao = (BoardDao) iocContainer.getBean(BoardDao.class);
        ClassroomDao classroomDao = (ClassroomDao) iocContainer.getBean(ClassroomDao.class);
        MemberDao memberDao = (MemberDao) iocContainer.getBean(MemberDao.class);
        TaskDao taskDao = (TaskDao) iocContainer.getBean(TaskDao.class);
        TeamDao teamDao = (TeamDao) iocContainer.getBean(TeamDao.class);
        TeamMemberDao teamMemberDao = (TeamMemberDao) iocContainer.getBean(TeamMemberDao.class);
        
        // 각각의 데이터 저장에 대해 예외 처리를 분리해야 한다.
        // 그래야만 예외가 발생하더라도 다른 데이터 저장은 정상적으로 수행할 것이다.
        try {boardDao.save();} 
        catch (Exception e) { System.out.println("게시물 데이터 저장 중 오류 발생!");}
        
        try {classroomDao.save();} 
        catch (Exception e) { System.out.println("수업 데이터 저장 중 오류 발생!");}
        
        try {memberDao.save();} 
        catch (Exception e) { System.out.println("회원 데이터 저장 중 오류 발생!");}
        
        try {taskDao.save();} 
        catch (Exception e) { System.out.println("작업 데이터 저장 중 오류 발생!");}
            
        try {teamDao.save();} 
        catch (Exception e) { System.out.println("팀 데이터 저장 중 오류 발생!");}    
        
        try {teamMemberDao.save();} 
        catch (Exception e) { System.out.println("팀멤버 데이터 저장 중 오류 발생!");}
    }*/

    void service() throws Exception {
        httpServer.execute();
    }
        
    public static void main(String[] args) throws Exception {
        AppServer appServer = new AppServer(8888);
        appServer.service();
    }
}

//ver 29 - 웹서버와 어플리케이션 실행기능을 별도 클래스로 분리
//ver 28 - 서버 만들기






