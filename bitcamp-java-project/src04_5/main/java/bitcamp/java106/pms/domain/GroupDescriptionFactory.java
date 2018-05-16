package bitcamp.java106.pms.domain;

import bitcamp.java106.pms.domain.AbstractDescriptionFactory;
import bitcamp.java106.pms.domain.GroupDescription;
import bitcamp.java106.pms.domain.Description;

public class GroupDescriptionFactory implements AbstractDescriptionFactory {
    public Description createDescription() {
        return new GroupDescription();
    }
}