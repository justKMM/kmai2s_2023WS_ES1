package org.hbrs.se1.ws23.uebung4.prototype;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User-Stories.
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * erstellt von Julius P., H-BRS 2023, Version 1.0
 *
 * Strategie für die Wiederverwendung (Reuse):
 * - Anlegen der Klasse UserStory
 * - Anpassen des Generic in der List-Klasse (ALT: Member, NEU: UserStory)
 * - Anpassen der Methodennamen
 *
 * ToDo: Was ist ihre Strategie zur Wiederverwendung? (F1)
 *
 * Alternative 1:
 * Klasse UserStory implementiert Interface Member (UserStory implements Member)
 * Vorteil: Wiederverwendung von Member, ID verwenden; Strenge Implementierung gegen Interface
 * Nachteil: Viele Casts notwendig, um auf die anderen Methoden zuzugreifen
 *
 * Alternative 2:
 * Container mit Generic entwickeln (z.B. Container<E>))
 *
 * Entwurfsentscheidung: Die wichtigsten Zuständigkeiten (responsibilities) sind in einer Klasse, d.h. Container,
 * diese liegt in einem Package.
 * ToDo: Wie bewerten Sie diese Entscheidung? (F2, F6)
 * 
 */

public class Container {

	class UserStoryComparator implements Comparator<UserStory> {

		@Override
		public int compare(UserStory o1, UserStory o2) {
			if ( o1.getPrio() == o2.getPrio() )return 0;
			if( o1.getPrio() < o2.getPrio() ) return 1;
			return -1;
		}
	}
	 
	// Interne ArrayList zur Abspeicherung der Objekte vom Type UserStory
	private List<UserStory> liste = null;
	
	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	// Diese Variante sei thread-safe, so hat Hr. P. es gehört... stimmt das?
	// Todo: Bewertung Thread-Safeness (F1)
	// Nachteil: ggf. geringer Speicherbedarf, da Singleton zu Programmstart schon erzeugt wird
	// Todo: Bewertung Speicherbedarf (F1)
	private static Container instance = new Container();
	
	// URL der Datei, in der die Objekte gespeichert werden 
	final static String LOCATION = "allStories.ser";

	/**
	 * Liefert ein Singleton zurück.
	 * @return
	 */
	public static Container getInstance() {
		return instance;
	}
	
	/**
	 * Vorschriftsmäßiges Ueberschreiben des Konstruktors (private) gemaess Singleton-Pattern (oder?)
	 * Nun auf private gesetzt! Vorher ohne Access Qualifier (--> dann package-private)
	 */
	Container(){
		liste = new ArrayList<UserStory>();
	}
	
	/**
	 * Start-Methoden zum Starten des Programms 
	 * (hier koennen ggf. weitere Initialisierungsarbeiten gemacht werden spaeter)
	 */
	public static void main (String[] args) throws ContainerException {
		// ToDo: Bewertung Exception-Handling (F3, F7)
		try {
			Container con = Container.getInstance();
			con.startEingabe();
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
	public void startEingabe() throws Exception {
		String strInput = null;
		
		// Initialisierung des Eingabe-View
		// ToDo: Funktionsweise des Scanners erklären (F3)
		Scanner scanner = new Scanner( System.in );
		// Ausgabe eines Texts zur Begruessung
		System.out.println("UserStory-Tool V1.0 by Julius P. (dedicated to all my friends)");

		boolean bool = true;
		while ( bool ) {
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
							"exit - End the programm");
					break;

				// Auswahl der bisher implementierten Befehle:
				case  ( "dump" ) :
					startAusgabe();
					break;

				// Auswahl der bisher implementierten Befehle:
				case  ( "enter" ) :
					System.out.println("Please type in UserStory datas (int id, String titel, int mehrwert, int strafe, "
							+ "int aufwand, int risk):");
					Scanner sc = new Scanner(scanner.nextLine());
					int id = sc.nextInt();
					String titel = sc.next();
					int mehrwert = sc.nextInt();
					int strafe = sc.nextInt();
					int aufwand = sc.nextInt();
					int risk = sc.nextInt();
					sc.close();
					float prio = (float) (mehrwert + strafe) / (aufwand + risk);

					if (prio > 0 && prio < 20){
						this.addUserStory(new UserStory(id, titel, mehrwert, strafe, aufwand, risk, prio)); // um das Objekt in die Liste einzufügen.
					}
					break;

				// Die Objekte speichern:
				case  ( "store" ) :
					this.store();
					break;

				case ( "exit" ) :
					scanner.close();
					bool = false;
					scanner.close();
					break;

				default:
					System.out.println("False command. For list of available commands, type \"help\" ");

			}

		}
	}

	/**
	 * Diese Methode realisiert die Ausgabe.
	 */
	public void startAusgabe() {

		// Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
		// ausgeben. Allerdings weiss der Student hier nicht weiter

		// [Sortierung ausgelassen]
		// Todo: Implementierung Sortierung (F4)
		this.sort();


		System.out.printf("--------------------------------%n");
		System.out.printf(" Vorhandene UserStories         %n");

		System.out.printf("--------------------------------%n");
		System.out.printf("| %s | %-10s | %s | %s | %s | %s | %s |%n",
				"ID", "TITEL", "MEHRWERT", "STRAFE", "AUFWAND", "RISK", "PRIO");
		System.out.printf("--------------------------------%n");


		// Klassische Ausgabe ueber eine For-Each-Schleife
		for (UserStory story : liste) {
			System.out.printf("| %d | %-10s | %d | %d | %d | %d | %.2f |%n ",
					story.getId(), story.getTitel(), story.getMehrwert(), story.getStrafe(), story.getAufwand(), story.getRisk(), story.getPrio());
		}
		System.out.printf("--------------------------------%n");

		// [Variante mit forEach-Methode / Streams (--> Kapitel 9, Lösung Übung Nr. 2)?
		//  Gerne auch mit Beachtung der neuen US1
		// (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" und Risiko >=5
		// Todo: Implementierung Filterung mit Lambda (F5)
		this.filter();
	}

	/*
	 * Methode zum Speichern der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
	 * 
	 */
	private void store() throws ContainerException {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream( Container.LOCATION );
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject( this.liste );
			System.out.println( this.size() + " UserStory wurden erfolgreich gespeichert!");
		}
		catch (IOException e) {
			e.printStackTrace();
		  //  Delegation in den aufrufendem Context
		  // (Anwendung Pattern "Chain Of Responsibility)
		  throw new ContainerException("Fehler beim Abspeichern");
		}
	}

	/*
	 * Methode zum Laden der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte geladen.
	 * 
	 */
	public void load() {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
		  fis = new FileInputStream( Container.LOCATION );
		  ois = new ObjectInputStream(fis);
		  
		  // Auslesen der Liste
		  Object obj = ois.readObject();
		  if (obj instanceof List<?>) {
			  this.liste = (List) obj;
		  }
		  System.out.println("Es wurden " + this.size() + " UserStory erfolgreich reingeladen!");
		}
		catch (IOException e) {
			System.out.println("LOG (für Admin): Datei konnte nicht gefunden werden!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("LOG (für Admin): Liste konnte nicht extrahiert werden (ClassNotFound)!");
		}
		finally {
		  if (ois != null) try { ois.close(); } catch (IOException e) {}
		  if (fis != null) try { fis.close(); } catch (IOException e) {}
		}
	}

	/**
	 * Methode zum Hinzufügen eines Mitarbeiters unter Wahrung der Schlüsseleigenschaft
	 * @param userStory
	 * @throws ContainerException
	 */
	public void addUserStory ( UserStory userStory ) throws ContainerException {
		if ( contains(userStory) == true ) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(userStory);
	}

	/**
	 * Prüft, ob eine UserStory bereits vorhanden ist
	 * @param userStory
	 * @return
	 */
	private boolean contains( UserStory userStory) {
		int ID = userStory.getId();
		for ( UserStory userStory1 : liste) {
			if ( userStory1.getId() == ID ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ermittlung der Anzahl von internen UserStory
	 * -Objekten
	 * @return
	 */
	public int size() {
		return liste.size();
	}

	/**
	 * Methode zur Rückgabe der aktuellen Liste mit Stories
	 * Findet aktuell keine Anwendung bei Hr. P.
	 * @return
	 */
	public List<UserStory> getCurrentList() {
		return this.liste;
	}

	/**
	 * Liefert eine bestimmte UserStory zurück
	 * @param id
	 * @return
	 */
	private UserStory getUserStory(int id) {
		for ( UserStory userStory : liste ) {
			if (id == userStory.getId() ){
				return userStory;
			}
		}
		return null;
	}

	public void sort() {
		Comparator<UserStory> userStoryComparator = new UserStoryComparator();
		this.liste.sort(userStoryComparator);
	}

	public void filter() {
		List<UserStory> filteredList = this.liste.stream()
				.filter(userStory -> userStory.getPrio() > 0)
				.filter(userStory -> userStory.getPrio() < 20)
				.collect(Collectors.toList());
	}
}
