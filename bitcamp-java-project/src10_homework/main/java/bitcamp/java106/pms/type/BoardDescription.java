package bitcamp.java106.pms.type;

import java.util.Scanner;
import bitcamp.java106.pms.origin_type.Description;

public class BoardDescription extends Description {
    String m_title, m_body, m_date;
    private int num = 2;

    public void add(Scanner sc) {
        System.out.print("제목? ");
        this.m_title = sc.nextLine();
        System.out.print("내용? ");
        this.m_body = sc.nextLine();
        System.out.print("등록일? ");
        this.m_date = sc.nextLine();
    }

    public void update(Scanner sc) {
        System.out.print("제목(" + this.m_title + ")? ");
        this.m_title = sc.nextLine();
        System.out.print("내용(" + this.m_body + ")? ");
        this.m_body = sc.nextLine();
        System.out.print("등록일(" + this.m_date + ")? ");
        this.m_date = sc.nextLine();
    }

    public int get(int n) {
        if(num == n) {
            System.out.printf("%s, %s, %s\n", this.m_title, this.m_body, this.m_date); 
            return 1;
        }
        return 0; 
    }

    public String getBase() {
        return m_title;
    }

    public int getNumber() {
        return num;
    }

    public void setM_title(String m_title) {
        this.m_title = m_title;
    }

    public void setM_body(String m_body) {    
        this.m_body = m_body;
    }

    public void setM_date(String m_date) {
        this.m_date = m_date;
    }

    public Description call_by_value() {
        BoardDescription b = new BoardDescription();

        b.setM_title(this.m_title);
        b.setM_body(this.m_body);
        b.setM_date(this.m_date);

        return b;
    }
}