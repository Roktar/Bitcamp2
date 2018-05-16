// 이 클래스는 명령창에서 사용할 기능을 모아 둔 클래스이다.
package bitcamp.java106.pms.util;

import java.util.Scanner;

public class Console {
    // 이 클래스를 사용하기 전에 반드시 Scanner 객체를 설정하라!
    public static Scanner keyScan;
    
/*    private static Console console;
    
    public static void getInstance() {
        if(console == null)
            console = new Console();
        
        return console;
    }*/

    public static boolean confirm(String message) {
        System.out.printf("%s (y/N)", message);
        String input = keyScan.nextLine().toLowerCase();
        if (input.equals("y")) 
            return true;
        else
            return false;
    }

    public static String[] prompt() {
        System.out.print("명령> ");
        return keyScan.nextLine().toLowerCase().split(" ");
    }
}

//ver 16 1) 기존의 Console은 keyScan이 static변수기에 오직 한 기기?에 대해서만 읽기 수행이 가능했다.
//       - 이것은 static 변수가 지니고있는 한계다.
//       - 동시에 여러 입력 장치로부터 읽고싶을 때, static 변수의 한계로 인해 불가능.
//       - 입력장치를 여러개 개별적으로 다루고싶다면 keyScan 변수를 인스턴스 변수로 만들어라.
//       2) Console을 사용하려면 무조건 Scanner 객체가 필요하다.
//       - 현재 static 변수를 강제적으로 설정하게 만드는 방법은 없다.
//       - 강제로 설정하는 방법
//        - 생성자를 도입하라
//        - Console 객체를 한 번만 생성하게 만드는 방법 : singleton 패턴 적용