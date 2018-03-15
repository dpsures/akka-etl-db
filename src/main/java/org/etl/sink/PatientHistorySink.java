package org.etl.sink;

import akka.Done;
import akka.stream.alpakka.slick.javadsl.Slick;
import akka.stream.javadsl.Sink;
import org.etl.utils.SlickSessionUtils;
import org.etl.vo.Patient;

import java.util.concurrent.CompletionStage;

public class PatientHistorySink {

    public Sink<Patient, CompletionStage<Done>> loadPatientHistory(){
        SlickSessionUtils sessionUtils = new SlickSessionUtils();
        return Slick.<Patient>sink(sessionUtils.getSinkSession(),
                patient ->  "insert into rcp_patient_head values ("+patient.getPatientId()+", '"+patient.getVisitDate()+"' , '"+patient.getDateOfBirth()+"' , '"+patient.getName()+"')");
    }
}
