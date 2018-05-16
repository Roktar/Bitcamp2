package bitcamp.java106.pms;


import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.domain.Member;
import java.util.Scanner;



// ver 0.2 - 사용자에게 yes/no를 묻는 코드를 메소드로 분리한다.

// ver 0.1 - 팀명으로 배열에서 팀 정보를 찾는 코드를 메소드로 분리한다.
//           => getTeamIndex() 추가
//           회원이름으로 배열에서 회원 정보를 찾는 코드를 메소드로 분리한다.
//           => getMemberIndex() 추가
public class App {
    static Scanner keyScan = new Scanner(System.in);

    static Team[] teams = new Team[1000];
    static int teamIndex = 0;
    static Member[] members = new Member[1000];
    static int memberIndex = 0;

    static String option = null; // 문자열 없음!

    static int getTeamIndex(String name) {
        for (int i = 0; i < teamIndex; i++) {
            if(teams[i] == null)
                continue;
            if (name.equals(teams[i].name.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    static int getMemberIndex(String name) {
        for (int i = 0; i < memberIndex; i++) {
            if(members[i] == null)
                continue;
            if (option.equals(members[i].id.toLowerCase())) 
                return i;
        }
    }

    static boolean confirm(String msg) {
        System.out.printf("%s (y/N) ", msg);

        String input = keyScan.nextLine().toLowerCase();

        if(input.equals("y")) 
            return true;
        
        return false;
    }

    static String[] prompt() {
        System.out.print("명령> ");
        return keyScan.nextLine().toLowerCase().split(" ");
    }

    static void onQuit() {
        System.out.println("안녕히 가세요!");
    }

    static void onHelp() {
        System.out.println("[도움말]");
        System.out.println("팀 등록 명령 : team/add");
        System.out.println("팀 조회 명령 : team/list");
        System.out.println("팀 상세조회 명령 : team/view 팀명");
        System.out.println("회원 등록 명령 : member/add");
        System.out.println("회원 조회 명령 : member/list");
        System.out.println("회원 상세조회 명령 : member/view 아이디");
        System.out.println("종료 : quit");
    }

    static void onTeamAdd() {
        System.out.println("[팀 정보 입력]");
        Team team = new Team();

        System.out.print("팀명? ");
        team.name = keyScan.nextLine();

        System.out.print("설명? ");
        team.description = keyScan.nextLine();

        System.out.print("최대인원? ");
        team.maxQty = keyScan.nextInt();
        keyScan.nextLine(); 

        System.out.print("시작일? ");
        team.startDate = keyScan.nextLine();

        System.out.print("종료일? ");
        team.endDate = keyScan.nextLine();

        teams[teamIndex++] = team;
    }

    static void onTeamView() {
        System.out.println("[팀 정보 조회]");
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            System.out.println();
            return;
        }
    
        int i = getTeamIndex(option);

        if (i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            Team team = teams[i];
            System.out.printf("팀명: %s\n", team.name);
            System.out.printf("설명: %s\n", team.description);
            System.out.printf("최대인원: %d\n", team.maxQty);
            System.out.printf("기간: %s ~ %s\n", 
                team.startDate, team.endDate);
        }
    }

    static void onTeamList() {
        System.out.println("[팀 목록]");
        for (int i = 0; i < teamIndex; i++) {
            if(teams[i] == null)
            continue;
            System.out.printf("%s, %d, %s ~ %s\n", 
                teams[i].name, teams[i].maxQty, 
                teams[i].startDate, teams[i].endDate);
        }
    }

    static void onMemberAdd() {
        System.out.println("[회원 정보 입력]");
        Member member = new Member();

        System.out.print("아이디? ");
        member.id = keyScan.nextLine();

        System.out.print("이메일? ");
        member.email = keyScan.nextLine();

        System.out.print("암호? ");
        member.password = keyScan.nextLine();

        members[memberIndex++] = member;
    }

    static void onMemeberList() {
        System.out.println("[회원 목록]");
        for (int i = 0; i < memberIndex; i++) {
            System.out.printf("%s, %s, %s\n", 
                members[i].id, members[i].email, members[i].password);
        }
    }

    static void onMemberView() {
        System.out.println("[회원 정보 조회]");
        if (option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }

        int i = getMemberIndex(option);

        if (i == -1) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            System.out.printf("아이디: %s\n", members[i].id);
            System.out.printf("이메일: %s\n", members[i].email);
            System.out.printf("암호: %s\n", members[i].password);
        }
    }

    static void onTeamDelete() {
        System.out.println("[팀 정보 삭제]");
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            System.out.println();
            return;
        }
    
        int i = getTeamIndex(option);

        if (i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            if(confirm("정말 삭제하시겠습니까?")) {
                teams[i] = null;
                System.out.println("삭제하였습니다.");
            } else
                System.out.println("삭제를 취소했습니다.");

        }
    }

    static void onTeamUpdate() {
        System.out.println("[팀 정보 변경]");
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }

        int i = getTeamIndex(option);

        if (i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            Team team = teams[i];
            System.out.printf("팀명(%s)? ", team.name);
            updateTeam = keyScan.nextLine();
            System.out.printf("설명(%s)? ", team.description);
            updateTeam = keyScan.nextLine();
            System.out.printf("최대인원(%d)? ", team.maxQty);
            updateTeam = keyScan.nextInt();
            keyScan.nextLine();
            System.out.printf("시작일(%s)? ", team.startDate);
            updateTeam = keyScan.nextLine();
            System.out.println("종료일(%s)? ", team.endDate );
            updateTeam = keyScan.nextLine();
            teams[i] = updateTeam;
            System.out.println("팀 정보를 변경하였습니다.");
        }
    }

    static void onMemberDelete() {
        System.out.println("[회원 정보 삭제]");
        if (option == null) {
            System.out.println("회원 이름을 입력하시기 바랍니다.");
            System.out.println();
            return;
        }

        int i = getMemberIndex(option);

        if (i == -1) {
            System.out.println("해당 이름의 회원이 없습니다.");
        } else {
            if(confirm("정말 삭제하시겠습니까?")) {
                members[i] = null;
                System.out.println("삭제하였습니다.");
            } else
                System.out.println("삭제를 취소했습니다.");

        }
    }

    static void onMemberUpdate() {
        System.out.println("[회원 정보 조회]");
        if (option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        int i = getMemberIndex(option);

        if (i == -1) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            Member uMember = new Member();
            System.out.printf("아이디(%s)? ", member.id);
            uMember.id = keyScan.nextLine();
            System.out.printf("이메일(%s)? ", member.email);
            uMember.email = keyScan.nextLine();
            System.out.printf("암호? ");
            uMember.password = keyScan.nextLine();
            members[i] = uMember;
        }
    }

    public static void main(String[] args) {

        while (true) {
            String[] arr = prompt();
            String menu = arr[0];

            if (arr.length == 2)
                option = arr[1];
            try {
                if (menu.equals("quit")) {
                    onQuit();
                    break;
                } else if (menu.equals("help"))
                    onHelp();
                else if (menu.equals("team/add"))
                    onTeamAdd();
                else if (menu.equals("team/list"))
                    onTeamList();
                else if (menu.equals("team/view"))
                    onTeamView();
                else if (menu.equals("team/delete"))
                    onTeamDelete();
                else if (menu.equals("team/view"))
                    onTeamUpdate();
                else if (menu.equals("member/add"))
                    onMemberAdd();
                else if (menu.equals("member/list"))
                    onMemeberList();
                else if (menu.equals("member/view"))
                    onMemberView();
                else if (menu.equals("member/delete"))
                    onMemberDelete();
                else if (menu.equals("member/update"))
                    onMemberUpdate();
                else
                    System.out.println("명령어가 올바르지 않습니다.");
            } catch(Exception e) {
                System.out.println("에러 발생");
            }

            System.out.println();
        }
    }
}