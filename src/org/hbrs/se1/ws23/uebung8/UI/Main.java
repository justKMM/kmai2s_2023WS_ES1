package org.hbrs.se1.ws23.uebung8.UI;

import org.hbrs.se1.ws23.uebung8.AL.AbstractPublisher;
import org.hbrs.se1.ws23.uebung8.AL.SkillPublisher;

public class Main {
    public static void main(String[] args) {
        SkillPublisher publisher = (SkillPublisher) AbstractPublisher.getSkillPublisher();
        AbstractConsumer consumer = new SkillConsumer();
        publisher.register(consumer);
        publisher.produce(3, "Java", "Juan");
        consumer.dump();
    }
}
