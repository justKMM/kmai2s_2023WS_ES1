package org.hbrs.se1.ws23.uebung4.prototype;

public class UserStoryView {
    /**
     * Diese Methode realisiert die Ausgabe.
     */
    public void startAusgabe(Container container) {

        // Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
        // ausgeben. Allerdings weiss der Student hier nicht weiter

        // [Sortierung ausgelassen]
        // Todo: Implementierung Sortierung (F4)
        container.sort();


        System.out.printf("--------------------------------%n");
        System.out.printf(" Vorhandene UserStories         %n");

        System.out.printf("--------------------------------%n");
        System.out.printf("| %s | %-10s | %s | %s | %s | %s | %s |%n",
                "ID", "TITEL", "MEHRWERT", "STRAFE", "AUFWAND", "RISK", "PRIO");
        System.out.printf("--------------------------------%n");


        // Klassische Ausgabe ueber eine For-Each-Schleife
        for (UserStory story : container.getCurrentList()) {
            System.out.printf("| %d | %-10s | %d | %d | %d | %d | %.2f |%n ",
                    story.getId(), story.getTitel(), story.getMehrwert(), story.getStrafe(), story.getAufwand(), story.getRisk(), story.getPrio());
        }
        System.out.printf("--------------------------------%n");

        // [Variante mit forEach-Methode / Streams (--> Kapitel 9, Lösung Übung Nr. 2)?
        //  Gerne auch mit Beachtung der neuen US1
        // (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" und Risiko >=5
        // Todo: Implementierung Filterung mit Lambda (F5)
        container.filter();
    }
}
