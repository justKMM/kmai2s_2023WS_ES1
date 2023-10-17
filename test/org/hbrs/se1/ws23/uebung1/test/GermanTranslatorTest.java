package org.hbrs.se1.ws23.uebung1.test;

import org.hbrs.se1.ws23.uebung1.view.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GermanTranslatorTest {
    Client c;
    int number;
    @BeforeEach
    void init() {
        c =  new Client();
    }
    @Test
    void zeroTest() {
        number = 0;
        String value = c.display(number);
        assertEquals(value, "Übersetzung der Zahl 0 nicht möglich (v1.9)");
    }

    @Test
    void biggerThan10Test() {
        number = 11;
        String value = c.display(number);
        assertEquals(value, "Übersetzung der Zahl 11 nicht möglich (v1.9)");
    }

    @Test
    void positiveTest() {
        number = 3;
        String value = c.display(number);
        assertEquals(value, "drei");
    }

    @Test
    void negativeTest() {
        number = -5;
        String value = c.display(number);
        assertEquals(value, "Übersetzung der Zahl -5 nicht möglich (v1.9)");
    }
}