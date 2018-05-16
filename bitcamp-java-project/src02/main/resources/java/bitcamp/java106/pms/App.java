package bitcamp.java106.pms;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String[] data = new String[5], question = {"팀명? ", "설명? ", "최대인원? ", "시작일? ", "종료일? "};
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<question.length; i++) {
            System.out.print(question[i]);
            data[i] = sc.nextLine();
        }

        data[3] += " ~ " + data[4];

        for(int i=0; i<question.length-1; i++) {
            System.out.print(question[i]);
            System.out.println( (i==1 ? "\n" : "") + data[i]);
        }
    }
}