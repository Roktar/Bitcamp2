package bitcamp.java106.pms;

import bitcamp.java106.pms.*;

public class DescriptionFactory {
    public static Description createDescription(int n) {
        switch(n) {
            case 0 :
                return new GroupDescription();
            case 1 :
                return new MemberDescription();
        }
        return null;
    }

    public static int getNumber(String s) {
        switch(s) {
            case "team" :
                return 0;
            case "member" :
                return 1;
        }
        return -1;
    }
}