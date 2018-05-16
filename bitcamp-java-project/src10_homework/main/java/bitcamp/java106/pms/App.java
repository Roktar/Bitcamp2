package bitcamp.java106.pms;

import java.util.Scanner;
import bitcamp.java106.pms.out.Console;
import bitcamp.java106.pms.util.Tools;
import bitcamp.java106.pms.util.DescriptionUtils;
import bitcamp.java106.pms.manage.Management;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Management manage = Management.getInstance();
        
        String command = "";
        String[] sub, sub2;
                
        sub2 = new String[2];

        while( true ) {
            command = Console.command(sc);
            sub = command.split("/");

            if(command.equals("quit"))
                break;
            else if(command.equals("help")) {
                Console.printMenu();
                continue;
            } else if( !Tools.is_equals_command(DescriptionUtils.getCommandPrefix() , sub[0]) || Tools.getCount(command, '/') > 2 || sub.length < 2 ) {
                System.out.println("명령어가 올바르지 않습니다.\n");
                continue;
            }

            if(Tools.getCount(sub[1], ' ') > 2 || sub[1].indexOf(" ") <= -1 || sub[1].split(" ").length < 2) {
                sub2[0] = sub[1];
                sub2[1] = null;
            } else 
                sub2 = sub[1].split(" ");

            manage.print(sc, sub[0], sub2);
        }
        System.out.println("안녕히가세요!");
    }
}