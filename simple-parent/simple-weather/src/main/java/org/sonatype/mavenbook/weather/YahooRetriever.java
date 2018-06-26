package org.sonatype.mavenbook.weather;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class YahooRetriever {

    private static Logger log = Logger.getLogger(YahooRetriever.class);

    public InputStream retriever(String woeid ) throws Exception {
        log.info("Retrieving Weather data");
        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20%3D%20" + woeid + "&format=xml&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        URLConnection conn = new URL(url).openConnection();
        return conn.getInputStream();
    }
}

