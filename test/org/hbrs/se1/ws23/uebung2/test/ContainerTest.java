package org.hbrs.se1.ws23.uebung2.test;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    Container<Member> container;
    Member member;
    @BeforeEach
    void init()  {
        container = Container.getInstance();
        member = new ConcreteMember(38);
        try {
            container.addMember(member);
        } catch (ContainerException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addMemberTest() {
        Member newMember = new ConcreteMember(1200);
        Assertions.assertDoesNotThrow(() -> {container.addMember(newMember);});
    }

    @Test
    void addMemberWithExistingIDTest() {
        Member newMember = new ConcreteMember(38);
        assertThrows(ContainerException.class, () -> {container.addMember(newMember);});
    }

    @Test
    void addExistingMemberTest() {
        assertThrows(ContainerException.class, () -> {container.addMember(member);});
    }

    @Test
    void deleteExistingMemberTest() {
        assertDoesNotThrow(() -> {container.deleteMember(member.getID());});
    }

    @Test
    void deleteNonExistentMemberTest() {
        assertEquals("Das Member mit der ID" + 1000 + "ist nicht vorhanden!", container.deleteMember(1000));
    }

    @Test
    void deleteMemberInEmptyContainerTest() {
        container.deleteMember(38);
        assertThrows(NullPointerException.class, () -> {container.deleteMember(38);});
    }

    @Test
    void toStringTest() {
        assertEquals("Member (" +
                "ID = " + member.getID() +
                ')', member.toString());
    }

    @Test
    void sizeTest() {
        assertEquals(1, container.size());
    }

    @Test
    void sizeAfterAddTest() throws ContainerException {
        container.addMember(new ConcreteMember(1288));
        assertEquals(2, container.size());
    }

    @Test
    void sizeAfterDeleteTest() {
        container.deleteMember(38);
        assertEquals(0, container.size());
    }


}
