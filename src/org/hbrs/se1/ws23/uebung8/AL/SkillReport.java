package org.hbrs.se1.ws23.uebung8.AL;

public class SkillReport extends AbstractReport {
    private String studentID;
    private String skill;
    public SkillReport(int reportID, String skill, String studentID) {
        super(reportID);
        this.skill = skill;
        this.studentID = studentID;
    }
}
