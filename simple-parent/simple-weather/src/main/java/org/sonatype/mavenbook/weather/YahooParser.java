package org.sonatype.mavenbook.weather;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;
import org.sonatype.mavenbook.weather.model.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class YahooParser {

    private static Logger log = Logger.getLogger(YahooParser.class);

    public Weather parse (String zip, InputStream inputStream) throws Exception {
        Weather weather = new Weather ();

        log.info("Creating xml reader");
        SAXReader xmlReader = createXmlReader();
        Document doc = xmlReader.read(inputStream);

        log.info("Parsing xml response");
        Location location = new Location();
        location.setCity(doc.valueOf("/query/results/channel/yweather:location/@city"));
        location.setRegion(doc.valueOf("/query/results/channel/yweather:location/@region"));
        location.setCountry(doc.valueOf("/query/results/channel/yweather:location/@country"));
        location.setZip(zip);
        weather.setLocation(location);

        Condition condition = new Condition();
        condition.setText(doc.valueOf("/query/results/channel/item/yweather:condition/@text"));
        condition.setTemp(doc.valueOf("/query/results/channel/item/yweather:condition/@temp"));
        weather.setCondition(condition);

        Wind wind = new Wind();
        wind.setChill(doc.valueOf("/query/results/channel/yweather:wind/@chill"));
        weather.setWind(wind);

        Atmosphere atmosphere = new Atmosphere();
        atmosphere.setHumidity(doc.valueOf("/query/results/channel/yweather:atmosphere/@humidity"));
        atmosphere.setHumidity(doc.valueOf("/query/results/channel/yweather:atmosphere/@pressure"));
        atmosphere.setHumidity(doc.valueOf("/query/results/channel/yweather:atmosphere/@rising"));
        atmosphere.setHumidity(doc.valueOf("/query/results/channel/yweather:atmosphere/@visibility"));
        weather.setAtmosphere(atmosphere);

        log.info(weather.toString());

        return weather;
    }

    private SAXReader createXmlReader() {
        Map<String, String> uris = new HashMap<String, String>();
        uris.put("yweather", "http://xml.weather.yahoo.com/ns/rss/1.0");

        DocumentFactory factory = new DocumentFactory();
        factory.setXPathNamespaceURIs(uris);

        SAXReader xmlReader = new SAXReader();
        xmlReader.setDocumentFactory(factory);
        return xmlReader;
    }
}
