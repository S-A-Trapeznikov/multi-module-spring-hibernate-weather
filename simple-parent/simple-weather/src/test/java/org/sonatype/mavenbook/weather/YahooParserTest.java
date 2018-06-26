package org.sonatype.mavenbook.weather;

import junit.framework.TestCase;
import org.sonatype.mavenbook.weather.model.Weather;

import java.io.InputStream;

public class YahooParserTest extends TestCase {
    final private String xmlWeather = "msk-weather.xml";

    public YahooParserTest(String name) {
        super(name);
    }

    public void testParser() throws Exception {
        InputStream mskData = getClass().getClassLoader().getResourceAsStream(xmlWeather);

        Weather weather = new YahooParser().parse("834463", mskData);

        assertEquals("Minsk", weather.getLocation().getCity());
        assertEquals("Minsk", weather.getLocation().getRegion());
        assertEquals("Belarus", weather.getLocation().getCountry());
    }
}


