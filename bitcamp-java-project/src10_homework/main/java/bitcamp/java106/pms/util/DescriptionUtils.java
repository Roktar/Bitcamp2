package bitcamp.java106.pms.util;

import java.util.List;
import bitcamp.java106.pms.origin_type.Description;

public class DescriptionUtils {
    
    static String[] command_prefix = { "team", "member", "board" };
    
    public static int getNumber(String s) {
        switch(s) {
            case "team" :
                return 0;
            case "member" :
                return 1;
            case "board" :
                return 2;
        }
        return -1;
    }
    
    public static String[] getCommandPrefix() {
        return command_prefix;
    }
    
    public static boolean isDuplicate(List<Description> data, String target, int n) {
        for(int i=0; i<data.size(); i++) {
            if(data.get(i).getBase().toLowerCase().equals(target.toLowerCase())) {
                if(data.get(i).getNumber() == n)
                    return true;
            }
        }
        return false;
    }
}
