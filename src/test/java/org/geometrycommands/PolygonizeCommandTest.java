package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.io.WKTReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The PolygonizeCommand UnitTest
 * @author Jared Erickson
 */
public class PolygonizeCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "MULTILINESTRING ((-5.70068359375 45.1416015625, "
                + "-4.6 46.4), (-4.6 46.4, 1 52.2), (1 52.2, 2.47314453125 "
                + "53.9306640625), (-1.21826171875 53.9306640625, 1 52.2), "
                + "(1 52.2, 5.6 48.6), (5.6 48.6, 8.88916015625 46.1962890625), "
                + "(0.71533203125 42.63671875, 1.8 44), (1.8 44, 5.6 48.6), "
                + "(5.6 48.6, 7.13134765625 50.37109375), (-5.83251953125 "
                + "46.943359375, -4.6 46.4), (-4.6 46.4, 1.8 44), (1.8 44, "
                + "4.45068359375 42.98828125))";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        PolygonizeCommand command = new PolygonizeCommand();
        command.execute(options, reader, writer);
        Geometry outputGeometry = new WKTReader().read(writer.getBuffer().toString());
        assertTrue(outputGeometry instanceof MultiPolygon);
        assertEquals(1, outputGeometry.getNumGeometries());
    }
}