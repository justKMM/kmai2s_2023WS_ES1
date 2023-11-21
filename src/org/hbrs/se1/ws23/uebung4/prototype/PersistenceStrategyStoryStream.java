package org.hbrs.se1.ws23.uebung4.prototype;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;

import java.util.List;

public class PersistenceStrategyStoryStream implements PersistenceStrategy {
    @Override
    public void openConnection() throws PersistenceException {

    }

    @Override
    public void closeConnection() throws PersistenceException {

    }

    @Override
    public void save(List member) throws PersistenceException {

    }

    @Override
    public List load() throws PersistenceException {
        return null;
    }
}
