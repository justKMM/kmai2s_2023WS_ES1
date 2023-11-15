package org.hbrs.se1.ws23.uebung3.test;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PersistenceTest {
    Member member;
    Container<Member> container;
    PersistenceException noStrategyIsSet = new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No Persistence Strategy");
    PersistenceException implementationNotAvailable = new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Persistence Strategy not implemented correctly");
    @BeforeEach
    void init() {
        this.container = Container.getInstance();
        if(!container.getCurrentList().isEmpty()) {
            container.setPersistenceStrategy(null);
            container.setMembers(new ArrayList<>());
        }
        try {
            member = new ConcreteMember(38);
            container.addMember(member);
        } catch (ContainerException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void NoPersistenceStrategyTest() {
        Assertions.assertThrows(noStrategyIsSet.getClass(), () -> {container.store();});
    }

    @Test
    public void MongoDBStrategyTest() {
        PersistenceStrategy<Member> persistenceStrategy = new PersistenceStrategyMongoDB();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            container.setPersistenceStrategy(persistenceStrategy);
            container.load();
        });
    }

    @Test
    public void ErrorInLocationTest() {
        PersistenceStrategy<Member> persistenceStrategy = new PersistenceStrategyStream();
        ((PersistenceStrategyStream<Member>) persistenceStrategy).setLocation("");
        Assertions.assertThrows(implementationNotAvailable.getClass(), () -> {
            container.setPersistenceStrategy(persistenceStrategy);
            container.load();
        });
    }

    @Test
    public void RoundTripTest() {
        PersistenceStrategy<Member> persistenceStrategy = new PersistenceStrategyStream();
        container.setPersistenceStrategy(persistenceStrategy);
        try {
            container.store();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        container.setMembers(new ArrayList<>());
        try {
            container.load();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(member.getID(), container.getCurrentList().get(0).getID());
    }
}
