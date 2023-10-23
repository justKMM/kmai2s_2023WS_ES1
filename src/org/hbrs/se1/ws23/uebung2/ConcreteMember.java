package org.hbrs.se1.ws23.uebung2;

public class ConcreteMember implements Member{
    private Integer ID;
    public ConcreteMember() {}

    public ConcreteMember(Integer ID) {
        this.ID = ID;
    }

    @Override
    public Integer getID() {
        return ID;
    }


    @Override
    public String toString() {
        return "Member (" +
                "ID = " + ID +
                ')';
    }
}
