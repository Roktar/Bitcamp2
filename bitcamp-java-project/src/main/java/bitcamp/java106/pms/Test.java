package bitcamp.java106.pms;

import bitcamp.java106.pms.server.ServerRequest;

public class Test {
    
    public static void main(String[] args) {
        // 프로그램을 짜다 보면 특정 API를 사용할 때가 있다.
        // 그 API를 적용하기 전에 간단한 예제를 만들어 동작을 확인하라!
        String str = "/board/add?title=aaaa&content=bbb";
<<<<<<< HEAD
=======
        System.out.println("dddd");
>>>>>>> branch 'master' of https://github.com/Roktar/Bitcamp2.git
        System.out.println("dd");
<<<<<<< HEAD
        
=======
>>>>>>> branch 'master' of https://github.com/Roktar/Bitcamp2.git
        ServerRequest request = new ServerRequest(str);
        System.out.println(request.getServerPath());
        System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("content"));
        System.out.println(request.getParameter("age"));
<<<<<<< HEAD
        System.out.println("장하은 바보"); 
=======
>>>>>>> branch 'master' of https://github.com/Roktar/Bitcamp2.git
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> branch 'master' of https://github.com/Roktar/Bitcamp2.git
