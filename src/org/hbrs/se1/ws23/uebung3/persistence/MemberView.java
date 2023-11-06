package org.hbrs.se1.ws23.uebung3.persistence;

import org.hbrs.se1.ws23.uebung2.Member;

import java.util.List;

public class MemberView {


    public void dump(List<Member> members) {
        for (Member member : members) {
            System.out.println(member.toString());
        }
    }
}
