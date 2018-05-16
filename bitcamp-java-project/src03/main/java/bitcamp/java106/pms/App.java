package bitcamp.java106.pms;

import java.util.Scanner;
import bitcamp.java106.pms.GroupDescription;

public class App {
    public static int MAX_VALUE = 5;

    public static void main(String[] args) {
        String[] question = {"팀명? ", "설명? ", "최대인원? ", "시작일? ", "종료일? "};
        Scanner sc = new Scanner(System.in);
        GroupDescription[] c_data = new GroupDescription[MAX_VALUE];

        for(int i=0; i < MAX_VALUE; i++) {
            c_data[i] = new GroupDescription();
            c_data[i].setData(i, sc, question);

            if( !c_data[i].isContinue(i, sc) )
                break; 
        }

        System.out.println("----------------------------------------------------");

        try {
            for(int i=0; i<MAX_VALUE; i++)
                c_data[i].printData();
        } catch(NullPointerException e) { }
        
        sc.close();
    }
}