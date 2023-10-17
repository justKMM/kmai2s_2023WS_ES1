package org.hbrs.se1.ws23.uebung1.control;

public class
/**
 * Anwendung des Factory Method Pattern (Kapitel 6; [GoF])
 * Problem: Inkonsistente Objekt-Erzeugung und -Parametrisierung
 * Lösung: Service-Klasse für die zentrale und konsistente
 */
TranslatorFactory {
    public static Translator createGermanTranslator() {
        return new GermanTranslator();
    }
}
