package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.io.WKTReader;
import org.junit.Test;
import static org.junit.Assert.*;
import org.geometrycommands.SpokeCommand.SpokeOptions;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * The SpokeCommand Unit Test
 * @author Jared Erickson
 */
public class SpokeCommandTest extends BaseTest {

    private final String pt = "POINT (5 5)";

    private final String mpt = "MULTIPOINT ((5.875473869469681 1.0101660098606535), " +
            "(19.64273518313129 8.032868631563336), " +
            "(19.397302929472787 10.139284609662209), " +
            "(12.61792804667091 17.61654337241537), " +
            "(4.802498121787375 9.17962232316298))";

    @Test
    public void execute() throws Exception {
        SpokeCommand cmd = new SpokeCommand();
        SpokeOptions options = new SpokeOptions();
        options.setGeometry(mpt);
        options.setOtherGeometry(pt);
        StringReader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        cmd.execute(options, reader, writer);
        WKTReader wktReader = new WKTReader();
        Geometry outputGeometry = wktReader.read(writer.toString());
        assertTrue(outputGeometry instanceof MultiLineString);
        assertEquals(5, outputGeometry.getNumGeometries());
    }

    @Test
    public void run() throws Exception {
        String result = runApp(new String[]{
                "spoke",
                "-g", mpt,
                "-o", pt
        }, null);
        WKTReader wktReader = new WKTReader();
        Geometry outputGeometry = wktReader.read(result);
        assertTrue(outputGeometry instanceof MultiLineString);
        assertEquals(5, outputGeometry.getNumGeometries());
    }
}
