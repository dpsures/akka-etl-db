package org.etl.vo;

import java.util.Date;

public class Patient {

    private String patientId;
    private Date visitDate;
    private Date dateOfBirth;
    private String name;

    public Patient(String patientId, Date visitDate, Date dateOfBirth, String name) {
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
