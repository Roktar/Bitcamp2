package bitcamp.java106.pms.domain;

import java.util.Scanner;
import bitcamp.java106.pms.App;

public class GroupDescription extends Description {
    String tName, tDes, tMax, tStart, tEnd;
    private int num = 0;

    public void add(Scanner sc) {
        System.out.print("팀명? ");
        this.tName = sc.nextLine();
        System.out.print("설명? ");
        this.tDes = sc.nextLine();
        System.out.print("최대인원? ");
        this.tMax = sc.nextLine();
        System.out.print("시작일? ");
        this.tStart = sc.nextLine();
        System.out.print("종료일? ");
        this.tEnd = sc.nextLine();
    }

    public void set(Scanner sc) {
        add(sc);
    }

    public int get(int n) {
        if(num == n) {
            System.out.printf("%s, %s, %s\n", this.tName, this.tMax, this.tStart + " ~ " + this.tEnd); 
            return 1;
        }
        return 0;
    }

    public String getBase() {
        return tName;
    }

    public int getNumber() {
        return num;
    }
}