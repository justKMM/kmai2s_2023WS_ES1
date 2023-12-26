package org.hbrs.se1.ws23.uebung8.AL;

import org.hbrs.se1.ws23.uebung8.UI.AbstractConsumer;

import java.util.ArrayList;
import java.util.List;
abstract public class AbstractPublisher {
    // List of subscribers
    List<AbstractConsumer> subscribers;

    private static AbstractPublisher publisher;

    AbstractPublisher() {
        subscribers = new ArrayList<>();
    }

    public static AbstractPublisher getSkillPublisher() {
        if (publisher == null || ! (publisher instanceof  SkillPublisher)) publisher = new SkillPublisher();
        return publisher;
    }

    public void register(AbstractConsumer consumer) {
        subscribers.add(consumer);
    }

    private void deregister(AbstractConsumer consumer) {
        subscribers.remove(consumer);
        System.out.println("AbstractConsumer " + consumer + " removed from subscribed list.");
    }
    abstract void produce(int reportID, String topic, String entityID);

    void notify(int reportID, String topic, String entityID) {
        for(AbstractConsumer subscriber : subscribers) {
            subscriber.update(reportID, topic, entityID);
        }
    }
}
