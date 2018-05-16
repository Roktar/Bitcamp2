package bitcamp.java106.pms.dao;
import bitcamp.java106.pms.domain.Member;

public class MemberDao {
    Member[] members = new Member[1000];
    int memberIndex = 0;
    int currentIndex = -1;
    
    public void insert(Member member) {
        this.members[this.memberIndex++] = member;
    }
    
    public Member[] list() {
        Member[] arr = new Member[memberIndex];
        for (int i = 0; i < memberIndex; i++) 
            arr[i] = members[i];
        return arr;
    }
    
    public Member get(String id) {
        int i = this.getMemberIndex(id);
        
        if(i == -1)
            return null;
        
        return members[i];
    }
        
/*    public void update(int idx, Member member) {
        int i = this.getMemberIndex(member.id);
        if( i != -1)
            this.members[i] = member;
    }*/
    
    public void update(Member member) {
        members[currentIndex] = member;
        currentIndex = -1;
    }
    
/*    public void update(Member member) {
        for(int i=0; i<members.length; i++) {
            if(members[i].id.equals(member.id.toLowerCase())) {
                members[currentIndex] = member;
                break;
            }
        }
    }*/
    
/*    public void delete() {
        members[currentIndex] = null;
        currentIndex = -1;
    }*/
    
    public void delete(String id) {
        int i = this.getMemberIndex(id);
        if( i != -1)
            this.members[i] = null;
    }
    
    private int getMemberIndex(String id) {
        for(int i=0; i<members.length; i++) {
            if(members[i].id.equals(id.toLowerCase())) {
               return i;
            }
        }
        return -1;
    }
}
