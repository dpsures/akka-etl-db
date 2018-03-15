package org.etl.source;

import akka.NotUsed;
import akka.stream.alpakka.slick.javadsl.Slick;
import akka.stream.alpakka.slick.javadsl.SlickRow;
import akka.stream.javadsl.Source;
import org.etl.utils.SlickSessionUtils;
import org.etl.vo.Patient;

public class PatientHistorySource {

    public Source<Patient, NotUsed> getPatientHistoryInfo(){
        SlickSessionUtils sessionUtils = new SlickSessionUtils();
        return Slick.source(sessionUtils.getSourceSession(),
                "select patient_id, registered_on, dob, patient_name from rcp_patient_head",
                (SlickRow row) -> new Patient(row.nextString(), row.nextDate(), row.nextDate(), row.nextString()));
    }
}
