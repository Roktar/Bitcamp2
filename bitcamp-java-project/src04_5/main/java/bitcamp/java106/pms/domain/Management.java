package bitcamp.java106.pms.domain;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import bitcamp.java106.pms.domain.Description;
import bitcamp.java106.pms.domain.DescriptionFactory2;
import bitcamp.java106.pms.domain.GroupDescription;
import bitcamp.java106.pms.domain.GroupDescriptionFactory;
import bitcamp.java106.pms.domain.MemberDescription;
import bitcamp.java106.pms.domain.MemberDescriptionFactory;

public class Management {
    private static Management manage;
    private List<Description> data;
    private DescriptionFactory2 f;

    private Management() {
        data = new ArrayList<Description>();
        f = new DescriptionFactory2();
    }

    public static Management getInstance() {
        if(manage == null)
            manage = new Management();
        return manage;
    }

    public void print(Scanner sc, String cat1, String[] cat2) {
        int n = getNumber(cat1);

        switch(cat2[0]) {
            case "add" :
                addData(sc, n);
                break;
            case "list" :
                getData(n);
                break;
            case "view" :
                if(cat2[1] == null)
                    System.out.println("찾을 데이터가 입력되지 않았습니다.");
                else
                    findData(cat2[1], n);
                break;
            case "modi" :
                if(cat2[1] == null)
                    System.out.println("수정할 데이터가 입력되지 않았습니다.");
                else
                    setData(sc, cat2[1], n);
                break;
            case "del" :
                if(cat2[1] == null)
                    System.out.println("삭제할 데이터가 입력되지 않았습니다.");
                else
                    delData(cat2[1], n);
                break;
            default :
                System.out.println("명령어가 올바르지 않습니다.");
        }
        System.out.println();
    }

    public boolean isDuplicate(String target, int n) {
        for(int i=0; i<data.size(); i++) {
            if(data.get(i).getBase().toLowerCase().equals(target.toLowerCase())) {
                if(data.get(i).getNumber() == n)
                    return true;
            }
        }
        return false;
    }

    public void addData(Scanner sc, int n) {
        Description sub;

        // switch(n) {
        //     case 0 :
        //         sub = DescriptionFactory2.getDescription(new GroupDescriptionFactory());
        //         break;
        //     case 1 :
        //         sub = DescriptionFactory2.getDescription(new MemberDescriptionFactory());
        //         break;
        //     default:
        //         return;
        // }
        
        switch(n) {
            case 0 :
                sub = f.getGroupDescription();
                break;
            case 1 : 
                sub = f.getMemberDescription();
                break;
            default :
                return;
        }


        if(sub != null) {
            sub.add(sc);
            if(!isDuplicate(sub.getBase(), n))
                data.add(sub);
            else
                System.out.println("이미 등록된 데이터입니다.");
        } else 
            System.out.print("명령어가 올바르지 않습니다.");
    }

    public void setData(Scanner sc, String target, int n) {
        Description d;

        // switch(n) {
        //     case 0 :
        //         d = DescriptionFactory2.getDescription(new GroupDescriptionFactory());
        //         break;
        //     case 1 :
        //         d = DescriptionFactory2.getDescription(new MemberDescriptionFactory());
        //         break;
        //     default:
        //         return;
        // }

        switch(n) {
            case 0 :
                d = f.getGroupDescription();
                break;
            case 1 : 
                d = f.getMemberDescription();
                break;
            default :
                return;
        }

        for(int i=0; i<data.size(); i++) {
            if(data.get(i).getBase().toLowerCase().equals(target.toLowerCase())) {
                if(data.get(i).getNumber() == n) {
                    d.add(sc); 
                    if(isDuplicate(d.getBase(), n))
                        System.out.println("이미 등록되어있어 수정할 수 없습니다.");
                    else
                        data.set(i, d);
                }
            }
        }
    }

    public void getData(int n) {
        int cnt = 0;

        for(int i=0; i<data.size(); i++)
            cnt += data.get(i).get(n);
        if(cnt <= 0)
            System.out.println("데이터가 없습니다.");
    }

    public void findData(String target, int n) {      
        for(int i=0; i<data.size(); i++) {
            if(data.get(i).getBase().toLowerCase().equals(target.toLowerCase())) {
                data.get(i).get(n);
                return;
            }
        }
        System.out.println( "데이터가 없습니다." );
    }

    public void delData(String target, int n) {      
        for(int i=0; i<data.size(); i++) {
            if(data.get(i).getBase().toLowerCase().equals(target.toLowerCase())) {
                if(data.get(i).getNumber() == n) {
                    System.out.println(data.get(i).getBase() + " 데이터를 삭제했습니다.");
                    data.remove(i);
                    return;
                }
            }
        }
        System.out.println( "데이터가 없습니다." );
    }

    public int getNumber(String s) {
        return DescriptionFactory2.getNumber(s);
    }
}