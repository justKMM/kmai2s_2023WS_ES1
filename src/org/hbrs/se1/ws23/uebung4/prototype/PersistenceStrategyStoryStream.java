package org.hbrs.se1.ws23.uebung4.prototype;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceStrategyStoryStream extends PersistenceStrategyStream {
    final static String LOCATION = "allStories.ser";
    ObjectOutputStream oos = null;
    FileOutputStream fos = null;
    ObjectInputStream ois = null;
    FileInputStream fis = null;
    List stories = null;
    @Override
    public void openConnection() throws PersistenceException {

    }

    @Override
    public void closeConnection() throws PersistenceException {

    }

    @Override
    public void save(List member) throws PersistenceException {
        try {
            if(fos == null) fos = new FileOutputStream(PersistenceStrategyStoryStream.LOCATION);
            if(oos == null) oos = new ObjectOutputStream(fos);
            oos.writeObject( member );
            System.out.println( member.size() + " UserStory wurden erfolgreich gespeichert!");
        }
        catch (IOException e) {
            e.printStackTrace();
            //  Delegation in den aufrufendem Context
            // (Anwendung Pattern "Chain Of Responsibility)
            throw new PersistenceException("Fehler beim Abspeichern");
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                throw new PersistenceException("Error with closing output connection");
            }
        }
    }

    /*
     * Methode zum Laden der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte geladen.
     *
     */
    @Override
    public List load() throws PersistenceException {
        if (fis == null || ois == null) openConnection();
        try {
            if(fis == null) fis = new FileInputStream(PersistenceStrategyStoryStream.LOCATION);
            if(ois == null) ois = new ObjectInputStream(fis);
            // Auslesen der Liste
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                stories = (List) obj;
            }
            System.out.println("Es wurden " + stories.size() + " UserStory erfolgreich reingeladen!");
        }
        catch (IOException e) {
            System.out.println("LOG (für Admin): Datei konnte nicht gefunden werden! Ein neuer Container wurde initialisiert");
            stories = new ArrayList<UserStory>();
        }
        catch (ClassNotFoundException e) {
            System.out.println("LOG (für Admin): Liste konnte nicht extrahiert werden (ClassNotFound)! Ein neuer Container wurde initialisiert");
            stories = new ArrayList<UserStory>();
        }
        finally {
            try {
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                throw new PersistenceException("Error with closing input connection");
            }
        }
        return stories;
    }
}
