package org.hbrs.se1.ws23.uebung3.persistence;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.hbrs.se1.ws23.uebung3.persistence.Client;

public class Main<T extends Member> {

    // Facade Pattern
    public static void main(String[] args) {
        Container container = Container.getInstance(); // Ensure only one instance

        // Create a Client instance and use it to interact with the Container
        Client client = new Client(container);
        try {
            client.addMembers(new Member[]{new ConcreteMember(38), new ConcreteMember(8333)});
        } catch (ContainerException e) {
            throw new RuntimeException(e);
        }

        // Create a MemberView instance and use it to display the current list from the Container
        MemberView memberView = new MemberView();
        memberView.dump(container.getCurrentList());
    }

    // Implementing the Persistence Strategy - Strategy Pattern

}
