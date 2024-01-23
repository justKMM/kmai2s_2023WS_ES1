package org.hbrs.se1.ws23.uebung9;

public class CoreDocument implements Document{
    private int ID;
    CoreDocument() {
        
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int getID() {
        return ID;
    }
}
