package bitcamp.java106.pms;

import java.util.Scanner;
import bitcamp.java106.pms.App;

public class GroupDescription {
    String tName, tDes, tMax, tStart, tEnd;

    public boolean isContinue(int idx, Scanner sc) {
        if( idx < App.MAX_VALUE ) {
            System.out.print("계속 입력하시겠습니까? (Y/N) ");

            String loop= sc.nextLine();
            //if( loop.equals("N") || loop.equals("n") || !loop.equals("Y") && !loop.equals("y") )
            if( loop.toLowerCase().equals("n") || !loop.toLowerCase().equals("y"))
                return false;
        }
        return true;
    }

    public void setData(int idx, Scanner sc, String[] question) {
        System.out.print(question[0]);
        this.tName = sc.nextLine();
        System.out.print(question[1]);
        this.tDes = sc.nextLine();
        System.out.print(question[2]);
        this.tMax = sc.nextLine();
        System.out.print(question[3]);
        this.tStart = sc.nextLine();
        System.out.print(question[4]);
        this.tEnd = sc.nextLine();
    }

    public void printData() {
        System.out.printf("%s, %s, %s\n", this.tName, this.tMax, this.tStart + " ~ " + this.tEnd); 
    }
}