package org.hbrs.se1.ws23.uebung2;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Container<T extends Member> {

    private static Container container;
    private List<T> members = new ArrayList<>();
    private PersistenceStrategy persistenceStrategy;
    private Container() {
    }

    public static synchronized Container getInstance() {
        if(container == null){
            container = new Container();
        }

        // Alternativ: Syncronized Blocks verwenden
        return container;
    }

    public List<T> getCurrentList() {
        return members;
    }

    public void setMembers(List<T> members) {
        this.members = members;
    }

    public void addMember(T member) throws ContainerException {
        for (Member m : members) if (m.getID() == member.getID()) throw new ContainerException("Das Member Object mit der ID"
                                                                                                        + member.getID()
                                                                                                        + "ist bereits vorhanden!");

        members.add(members.size(), member);
    }

    public String deleteMember(Integer ID) {
        if (members.isEmpty()) throw new NullPointerException();
        for (Member member : members) if (member.getID() == ID) {
            members.remove(member);
            return "";
        }
        return "Das Member mit der ID" + ID + "ist nicht vorhanden!";
    }

    public void setPersistenceStrategy(PersistenceStrategy persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    public int size() {
        return members.size();
    }

    public void store() throws PersistenceException {
        if (persistenceStrategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "PersistenceStrategy empty!");
        persistenceStrategy.save(members);
    }

    public void load() throws PersistenceException {
        if (persistenceStrategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "PersistenceStrategy empty!");
        members = persistenceStrategy.load();
    }
}
