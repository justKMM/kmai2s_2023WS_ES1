package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;
import java.util.Objects;

public class Container {
    private ArrayList<Member> members = new ArrayList<>();
    public Container() {
    }

    public void addMember(Member member) throws ContainerException {
        for (Member m : members) if (m.getID() == member.getID()) throw new ContainerException("Das Member Object mit der ID"
                                                                                                        + member.getID()
                                                                                                        + "ist bereits vorhanden!");

        members.add(members.size(), member);
    }

    public String deleteMember(Integer ID) {
        if (members.isEmpty()) throw new NullPointerException();
        for (Member member : members) if (member.getID() == ID) {
            members.remove(member);
            return "";
        }
        return "Das Member mit der ID" + ID + "ist nicht vorhanden!";
    }

    public void dump() {
        for (Member member : members) {
            System.out.println(member.toString());
        }
    }

    public int size() {
        return members.size();
    }
}
