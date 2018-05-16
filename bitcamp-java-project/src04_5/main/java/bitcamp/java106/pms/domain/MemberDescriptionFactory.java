package bitcamp.java106.pms.domain;

import bitcamp.java106.pms.domain.AbstractDescriptionFactory;
import bitcamp.java106.pms.domain.MemberDescription;
import bitcamp.java106.pms.domain.Description;

public class MemberDescriptionFactory implements AbstractDescriptionFactory {
    public Description createDescription() {
        return new MemberDescription();
    }
}