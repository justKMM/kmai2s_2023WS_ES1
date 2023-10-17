package org.hbrs.se1.ws23.uebung1.test;

import org.hbrs.se1.ws23.uebung1.view.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GermanTranslatorTest {

    @Test
    void aPositiveTest() {
        Client c = new Client();
        int number = 0;
        String value = c.display(number);
        assertEquals(value, "Übersetzung der Zahl 0 nicht möglich (v1.9)");
    }
}