package org.hbrs.se1.ws23.uebung4.prototype;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    static Container container;
    public static void main (String[] args) throws ContainerException {
        Client client = new Client();
        // ToDo: Bewertung Exception-Handling (F3, F7)
        try {
            container = Container.getInstance();
            client.startEingabe(container);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ContainerException("Container Exception: Please checkie check");
        }
    }

    /*
     * Diese Methode realisiert eine Eingabe ueber einen Scanner
     * Alle Exceptions werden an den aufrufenden Context (hier: main) weitergegeben (throws)
     * Das entlastet den Entwickler zur Entwicklungszeit und den Endanwender zur Laufzeit
     */
    public void startEingabe(Container container) throws Exception {
        String strInput = null;

        // Initialisierung des Eingabe-View
        // ToDo: Funktionsweise des Scanners erklären (F3)
        Scanner scanner = new Scanner( System.in );
        // Ausgabe eines Texts zur Begruessung
        System.out.println("UserStory-Tool V1.0 by Julius P. (dedicated to all my friends)");
        UserStoryView view = new UserStoryView();

        boolean appRunning = true;
        while ( appRunning ) {
            System.out.print( "> befehl "  );

            strInput = scanner.nextLine();

            // Extrahiert ein Array aus der Eingabe
            String[] strings = strInput.split(" ");
            switch (strInput) {
                // 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
                case  ( "help" ) :
                    System.out.println("Available commands: \n" +
                            "help - Show available commands \n" +
                            "enter - Add an User Story \n" +
                            "dump - Print out saved User Stories \n" +
                            "dump project [project name] - Print out User Stories with the corresponding Project \n" +
                            "search - Search User Stories with the corresponding Project \n" +
                            "exit - End the programm");
                    break;

                // Auswahl der bisher implementierten Befehle:
                case  ( "dump" ) :
                    view.startAusgabe(container.getCurrentList());
                    break;
                case ( "dump project" ):
                    List<UserStory> stories = new ArrayList<>();
                    if(scanner.hasNext()) { // if 1st extra parameter is "project"
                        // then check if there's an extra extra parameter
                        String projectName = scanner.next();
                        for (UserStory story : container.getCurrentList()) {
                            if(story.getProject().equals(projectName)) stories.add(story);
                        }
                    }
                    view.startAusgabe(stories);
                    break;

                // Auswahl der bisher implementierten Befehle:
                case  ( "enter" ) :
                    try {
                        System.out.println("Please type in UserStory datas [int id, String titel (can be multiple words), int mehrwert, int strafe, "
                                + "int aufwand, int risk, String projectName]:");
                        Scanner sc = new Scanner(scanner.nextLine());
                        int id = sc.nextInt();
                        String titel = sc.next();
                        while (!sc.hasNextInt()) titel += (" " + sc.next());
                        int mehrwert = sc.nextInt();
                        int strafe = sc.nextInt();
                        int aufwand = sc.nextInt();
                        int risk = sc.nextInt();
                        String projectName = sc.next();
                        sc.close();
                        float prio = (float) (mehrwert + strafe) / (aufwand + risk);

                        if (projectName != null && id > 0 && mehrwert > 0 && strafe > 0 && aufwand > 0 && risk > 0) {
                            container.addUserStory(new UserStory(id, titel, mehrwert, strafe, aufwand, risk, prio, projectName)); // um das Objekt in die Liste einzufügen.
                        } else System.out.println("User story invalid. Inputed values can not be negative");
                    } catch (NoSuchElementException e) {
                        System.out.println("User story invalid. Inputed values can not be null.");
                    }
                    break;

                // Die Objekte speichern:
                case  ( "store" ) :
                    container.store();
                    break;

                case  ( "load" ) :
                    container.load();
                    break;

                case ( "exit" ) :
                    scanner.close();
                    appRunning = false;
                    scanner.close();
                    break;

                default:
                    System.out.println("False command. For list of available commands, type \"help\" ");

            }

        }
    }
}
