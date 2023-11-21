package org.hbrs.se1.ws23.uebung4.prototype;

import java.util.List;

public class UserStoryView {
    /**
     * Diese Methode realisiert die Ausgabe.
     */
    public void startAusgabe(List<UserStory> stories) {

        // Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
        // ausgeben. Allerdings weiss der Student hier nicht weiter

        // [Sortierung ausgelassen]
        // Implementierung Sortierung (F4) - wurde in Klasse Container implementiert



        System.out.printf("---------------------------------------------------------------------------------------%n");
        System.out.printf("                          Vorhandene UserStories                             %n");

        System.out.printf("---------------------------------------------------------------------------------------%n");
        System.out.printf("|%-8s|%-20s|%-8s|%-8s|%-8s|%-8s|%-8s|%-10s|%n",
                "ID", "TITEL", "MEHRWERT", "STRAFE", "AUFWAND", "RISK", "PRIO", "PROJECT");
        System.out.printf("---------------------------------------------------------------------------------------%n");


        // Klassische Ausgabe ueber eine For-Each-Schleife
        for (UserStory story : stories) {
            System.out.printf("|%-8d|%-20s|%-8d|%-8d|%-8d|%-8d|%-8.2f|%-10s|%n",
                    story.getId(), story.getTitel(), story.getMehrwert(), story.getStrafe(), story.getAufwand(), story.getRisk(), story.getPrio(), story.getProject());
        }
        System.out.printf("---------------------------------------------------------------------------------------%n");

        // [Variante mit forEach-Methode / Streams (--> Kapitel 9, Lösung Übung Nr. 2)?
        //  Gerne auch mit Beachtung der neuen US1
        // (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" und Risiko >=5
        // Implementierung Filterung mit Lambda (F5) - wurde in Klasse Container implementiert
    }
}
