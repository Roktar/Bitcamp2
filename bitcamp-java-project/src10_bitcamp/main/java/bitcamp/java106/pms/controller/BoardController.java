package bitcamp.java106.pms.controller;

import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.util.Console;

public class BoardController {
    public static java.util.Scanner keyScan;

    static Board[] boards = new Board[1000];
    static int boardIndex = 0;

    public static void service(String menu, String option) {
        if (menu.equals("board/add"))
            onBoardAdd();
        else if (menu.equals("board/list"))
            onBoardList();
        else if (menu.equals("board/view"))
            onBoardView(option);
        else if (menu.equals("board/delete"))
            onBoardDelete(option);
        else if (menu.equals("board/update"))
            onBoardUpdate(option);
        else
            System.out.println("명령어가 올바르지 않습니다.");
    } // 멤버함수들을 이제 숨길 수 있게 됐다. => 캡슐화

    static int getBoardIndex(String name) {
        for (int i = 0; i < boardIndex; i++) {
            if(boards[i] == null)
                continue;
            if (name.toLowerCase().equals(boards[i].title.toLowerCase()))
                return i;
        }
        return -1;
    }

    static void onBoardAdd() {
        System.out.println("[게시글 정보 입력]");
        Board board = new Board();

        System.out.print("제목? ");
        board.title = keyScan.nextLine();

        System.out.print("설명? ");
        board.body = keyScan.nextLine();

        System.out.print("등록일? ");
        board.date = keyScan.nextLine(); 
        
        board.idx = boardIndex;

        boards[boardIndex++] = board;
    }
    

    // static void onBoardView(String option) {
    //     System.out.println("[게시글 정보 조회]");
    //     if (option == null) {
    //         System.out.println("게시글명을 입력하시기 바랍니다.");
    //         System.out.println();
    //         return;
    //     }
    
    //     int i = getBoardIndex(option);

    //     if (i == -1) {
    //         System.out.println("해당 이름의 게시글이 없습니다.");
    //     } else {
    //         Board board = boards[i];
    //         System.out.printf("제목: %s\n", board.title);
    //         System.out.printf("내용: %s\n", board.body);
    //         System.out.printf("등록일: %s\n", board.date);
    //     }
    // }

        static void onBoardView(String option) {
        System.out.println("[게시글 정보 조회]");
        if (option == null) {
            System.out.println("게시글명을 입력하시기 바랍니다.");
            System.out.println();
            return;
        }
    
        int i = String.parseInt(option);

        if (i == -1) {
            System.out.println("해당 이름의 게시글이 없습니다.");
        } else {
            Board board = boards[i];
            System.out.printf("제목: %s\n", board.title);
            System.out.printf("내용: %s\n", board.body);
            System.out.printf("등록일: %s\n", board.date);
        }
    }

    static void onBoardList() {
        System.out.println("[게시글 목록]");
        for (int i = 0; i < boardIndex; i++) {
            if(boards[i] == null)
                continue;
            System.out.printf("%d, %s, %s, %s\n", i, boards[i].title, boards[i].body, boards[i].date);
        }
    }

    static void onBoardDelete(String option) {
        System.out.println("[게시글 정보 삭제]");
        if (option == null) {
            System.out.println("게시글명을 입력하시기 바랍니다.");
            System.out.println();
            return;
        }
    
        int i = getBoardIndex(option);

        if (i == -1) {
            System.out.println("해당 이름의 게시글이 없습니다.");
        } else {
            if(Console.confirm("정말 삭제하시겠습니까?")) {
                boards[i] = null;
                System.out.println("삭제하였습니다.");
            } else
                System.out.println("삭제를 취소했습니다.");

        }
    }

    static void onBoardUpdate(String option) {
        System.out.println("[게시글 정보 변경]");
        if (option == null) {
            System.out.println("게시글명을 입력하시기 바랍니다.");
            return;
        }

        int i = getBoardIndex(option);

        if (i == -1) {
            System.out.println("해당 이름의 게시글이 없습니다.");
        } else {
            Board board = boards[i];
            Board updateBoard = new Board();

            System.out.printf("제목(%s)? ", board.title);
            updateBoard.title = keyScan.nextLine();
            System.out.printf("내용(%s)? ", board.body);
            updateBoard.body = keyScan.nextLine();
            System.out.printf("등록일(%s)? ", board.date);
            updateBoard.date = keyScan.nextLine();
            System.out.println("게시글 정보를 변경하였습니다.");
        }
    }
}