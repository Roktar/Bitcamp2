package bitcamp.java106.pms.controller.calculator;

import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;

@Component("calc")
public class CalculatorController implements Controller {
    Scanner keyScan;
    
    public CalculatorController(Scanner keyScan) {
        this.keyScan = keyScan;
    }
    
    public void service(String menu, String option) {        
        System.out.print("식을 입력하십시오 : ");
        int a = keyScan.nextInt();
        String op = keyScan.next();
        int b = keyScan.nextInt();

        switch(op) {
            case "+" : 
                System.out.printf("%d %s %d = %d", a, op, b, a+b); break;
            case "-" : 
                System.out.printf("%d %s %d = %d", a, op, b, a-b); break;
            case "/" : 
                System.out.printf("%d %s %d = %d", a, op, b, (float)a/b); break;
            case "*" :
                System.out.printf("%d %s %d = %d", a, op, b, a*b); break;
            default :
                System.out.println("잘못된 연산자입니다.");
        }
    }
}
