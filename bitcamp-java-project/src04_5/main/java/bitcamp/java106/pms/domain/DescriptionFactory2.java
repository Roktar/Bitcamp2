package bitcamp.java106.pms.domain;

import bitcamp.java106.pms.domain.AbstractDescriptionFactory;

public class DescriptionFactory2 {
    AbstractDescriptionFactory gFactory, mFactory; // 추후 재적용
    
    // public static Description getDescription(AbstractDescriptionFactory d) {
    //      return d.createDescription();
    // } 

    public DescriptionFactory2() {
        gFactory = new GroupDescriptionFactory();
        mFactory = new MemberDescriptionFactory();
    }

    public Description getGroupDescription() {
        return gFactory.createDescription(); 
    }

    public Description getMemberDescription() {
        return mFactory.createDescription();
    }

    public static int getNumber(String s) {
        switch(s) {
            case "team" :
                return 0;
            case "member" :
                return 1;
            default : break;
        }
        return -1;
    }
}