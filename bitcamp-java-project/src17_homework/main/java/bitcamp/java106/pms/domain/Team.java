package bitcamp.java106.pms.domain;

import java.sql.Date;

// 팀 정보를 저장할 수 있는 메모리의 구조를 설계한 클래스
public class Team {
    private String name;
    private String description;
    private int maxQty;
    private Date startDate;
    private Date endDate;
    private Member[] members = new Member[10];
    private Task[] tasks = new Task[10];
    // 사용자 정의 데이터 타입에서의 메서드 정의
    // => 새 데이터 타입의 값을 다룰 연산자를 정의하는 것을 의미
    // ==> 여기서는 데이터를 실제로 다룰 메소드를 정의하는 것이 아닌 데이터간의 연산을 다룰 메소드를 정의한다./
    // 연산자는 외부에서 사용하기때문에 public.
    
    public int addMember(Member member) {
        for(int i=0; i<this.members.length; i++) {
            if(this.members[i] == null) {
                this.members[i] = member;
                return 1;
            }
        }
        return 0;
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(int maxQty) {
        this.maxQty = maxQty;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public Member[] getMembers() {
        return members;
    }

    public int deleteMember(String memberid) {
        for(int i=0; i<this.members.length; i++) {
            if(this.members[i] == null) continue;
            if(this.members[i].getId().equals(memberid)) {
                this.members[i] = null;
                return 1;
            }
        }
        return 0;
    }
    
    public boolean isExist(String memberId) {
        for (int i=0; i<this.members.length; i++) {
            if(this.members[i] == null) continue;
            if(this.members[i].getId().equals(memberId))
               return true;
        }
        return false;
    }
}

// ver 15 - 멤버를 저장할 인스턴스 변수를 추가한다.
//          연산자 재정의
// ver 13 - 시작일, 종료일의 데이터 타입을 String에서 Date으로 변경