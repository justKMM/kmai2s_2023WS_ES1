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
    @Test   //Test Case for number 0
    void zeroTest() {
        number = 0;
        String value = c.translateNumber(number);
        assertEquals(value, "Übersetzung der Zahl " + number + " nicht möglich (v1.9)");
    }

    @Test   //Test Case for numbers > 10 (chosen number: 11)
    void biggerThan10Test() {
        number = 11;
        String value = c.translateNumber(number);
        assertEquals(value, "Übersetzung der Zahl " + number + " nicht möglich (v1.9)");
    }

    @Test   //Test Case for positive numbers <= 10 (chosen number: 3)
    void positiveTest() {
        number = 3;
        String value = c.translateNumber(number);
        assertEquals(value, "drei");
    }

    @Test   //Test Case for negative numbers (chosen number: -5)
    void negativeTest() {
        number = -5;
        String value = c.translateNumber(number);
        assertEquals(value, "Übersetzung der Zahl " + number + " nicht möglich (v1.9)");
    }
}