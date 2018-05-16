package bitcamp.java106.pms;

import java.util.Scanner;
import bitcamp.java106.pms.App;

public class MemberDescription extends Description {
    String m_id, m_email, m_pw;
    private int num = 1;

    public void add(Scanner sc) {
        System.out.print("아이디? ");
        this.m_id = sc.nextLine();
        System.out.print("이메일? ");
        this.m_email = sc.nextLine();
        System.out.print("암호? ");
        this.m_pw = sc.nextLine();
    }

    public void update(Scanner sc) {
        System.out.print("아이디(" + this.m_id + ")? ");
        this.m_id = sc.nextLine();
        System.out.print("이메일(" + this.m_email + ")? ");
        this.m_email = sc.nextLine();
        System.out.print("암호(" + this.m_pw + ")? ");
        this.m_pw = sc.nextLine();
    }

    public int get(int n) {
        if(num == n) {
            System.out.printf("%s, %s, %s\n", this.m_id, this.m_email, this.m_pw); 
            return 1;
        }
        return 0; 
    }

    public String getBase() {
        return m_id;
    }

    public int getNumber() {
        return num;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public void setM_email(String m_email) {    
        this.m_email = m_email;
    }

    public void setM_pw(String m_pw) {
        this.m_pw = m_pw;
    }

    public Description call_by_value() {
        MemberDescription m = new MemberDescription();

        m.setM_id(this.m_id);
        m.setM_email(this.m_email);
        m.setM_pw(this.m_pw);

        return m;
    }
}