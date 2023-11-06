package org.hbrs.se1.ws23.uebung3.persistence;

import org.hbrs.se1.ws23.uebung2.*;

import java.util.List;

public class Client {

    private Container container;
    private MemberView memberView;

    public Client(Container container) {
        this.container = container;
        this.memberView = new MemberView();
    }

    public void addMembers(Member[] members) throws ContainerException {
        for(Member member : members) container.addMember(member);
        memberView.dump(container.getCurrentList());
    }
}
