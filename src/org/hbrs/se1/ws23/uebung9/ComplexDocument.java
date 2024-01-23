package org.hbrs.se1.ws23.uebung9;

import java.util.ArrayList;
import java.util.List;

public class ComplexDocument implements Document{
    private int ID;
    private List<Document> documents = new ArrayList<>();
    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int getID() {
        return ID;
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public Document getDocument(int i) {
        return documents.get(i);
    }

    public List<Document> dump() {
        return documents;
    }
}
