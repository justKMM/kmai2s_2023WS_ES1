package org.hbrs.se1.ws23.uebung8.UI;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConsumer {

    Map<Integer, String[]> newReports = new HashMap<>();
    public void update(int id, String topic, String entityID) {
        if (!newReports.containsKey(id)) {
            newReports.put(id, new String[]{topic, entityID});
        } else System.out.println("Consumer already notified about this report");
    }

    public void dump() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String result = "new reports:\n";
        for (Map.Entry<Integer, String[]> entry : newReports.entrySet()) {
            result += (entry.getKey() + ". Topic: " + entry.getValue()[0] + ", Name: " + entry.getValue()[1]);
        }
        return result;
    }
}
