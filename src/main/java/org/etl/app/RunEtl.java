package org.etl.app;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.RunnableGraph;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import org.etl.sink.PatientHistorySink;
import org.etl.source.PatientHistorySource;
import org.etl.vo.Patient;

import java.util.concurrent.CompletionStage;

public class RunEtl {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("patient-history-etl");

        ActorMaterializer materializer = ActorMaterializer.create(actorSystem);

        final LoggingAdapter log = Logging.getLogger(actorSystem.eventStream(), "etl-log");

        PatientHistorySource source = new PatientHistorySource();
        final Source<Patient, NotUsed> patientHistorySource = source.getPatientHistoryInfo();

        PatientHistorySink sink = new PatientHistorySink();
        final Sink<Patient, CompletionStage<Done>> patientHistorySink = sink.loadPatientHistory();

        //final CompletionStage<Done> done = patientHistorySource.runWith(patientHistorySink, materializer);

        final RunnableGraph<CompletionStage<Done>> runnableGraph = patientHistorySource.toMat(patientHistorySink, Keep.right());

        final CompletionStage<Done> done = runnableGraph.run(materializer);

        done.whenComplete((value, exception) -> {
            actorSystem.terminate();
            //System.out.println("ETL process done ....");
            log.debug("ETL process done ....");
        });

        log.debug("Main thread completed ...");
        //System.out.println("Main thread completed ...");
    }
}
