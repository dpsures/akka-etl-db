package org.etl.utils;

import akka.stream.alpakka.slick.javadsl.SlickSession;

public class SlickSessionUtils {

    public SlickSession getSourceSession(){
        return SlickSession.forConfig("slick-postgres-source");
    }
    public SlickSession getSinkSession(){
        return SlickSession.forConfig("slick-postgres-sink");
    }

}
