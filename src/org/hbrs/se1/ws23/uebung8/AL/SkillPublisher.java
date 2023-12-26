package org.hbrs.se1.ws23.uebung8.AL;

public class SkillPublisher extends AbstractPublisher {
    SkillPublisher() {}
    @Override
    public void produce(int reportID, String skill, String studentID) {
        AbstractReport report = new SkillReport(reportID, skill, studentID);
        notify(reportID, skill, studentID);
    }
    void notify(int reportID, String topic, String studentID) {
        super.notify(reportID, topic, studentID);
    }
}
