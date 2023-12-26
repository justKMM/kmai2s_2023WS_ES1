package org.hbrs.se1.ws23.uebung8.IN;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class PersistenceStrategyMongoDB<E> implements PersistenceStrategy<E> {

    String uri = "mongodb+srv://khaimm:<password>@clustertest.15p1zpw.mongodb.net/?retryWrites=true&w=majority";
    MongoClient mongoClient;
    ServerApi serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build();
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(uri))
            .serverApi(serverApi)
            .build();

    @Override
    public void openConnection() throws PersistenceException {
        try {
            mongoClient = MongoClients.create(settings);
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() throws PersistenceException {
        mongoClient.close();
    }

    @Override
    public void save(List<E> member) {
        throw new UnsupportedOperationException("Not implemented!");

    }

    @Override
    public List<E> load() {
        try {
            openConnection();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {
        PersistenceStrategy persistenceStrategy = new PersistenceStrategyMongoDB();
        try {
            persistenceStrategy.openConnection();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
    }
}
