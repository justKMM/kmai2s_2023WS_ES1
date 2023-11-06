package org.hbrs.se1.ws23.uebung3.test;

import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersistenceTest {
    Container container;
    @BeforeEach
    void init() {
        this.container = Container.getInstance();
    }
    @Test
    public void NoPersistenceStrategyTest() {
        Assertions.assertThrows(PersistenceException.getExceptionTypeType(), () -> {container.store();});
    }

    @Test
    public void MongoDBStrategyTest() {
        // Write your test code to verify the behavior with the MongoDB persistence strategy.
    }

    @Test
    public void ErrorInLocationTest() {
        // Write your test code to verify the behavior when there's an error in the file location.
    }

    @Test
    public void RoundTripTest() {
        // Write your test code for the "round-trip" test.
    }
}
