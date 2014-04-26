package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.io.WKTReader;
import org.geometrycommands.PolygonizeCommand.PolygonizeOptions;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The PolygonizeCommand UnitTest
 * @author Jared Erickson
 */
public class PolygonizeCommandTest extends BaseTest {

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
        PolygonizeOptions options = new PolygonizeOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        PolygonizeCommand command = new PolygonizeCommand();
        command.execute(options, reader, writer);
        Geometry outputGeometry = new WKTReader().read(writer.getBuffer().toString());
        assertTrue(outputGeometry instanceof MultiPolygon);
        assertEquals(1, outputGeometry.getNumGeometries());

        // Get full report
        options = new PolygonizeOptions();
        options.setGeometry(inputGeometry);
        options.setFull(true);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new PolygonizeCommand();
        command.execute(options, reader, writer);
        outputGeometry = new WKTReader().read(writer.getBuffer().toString());
        assertTrue(outputGeometry instanceof GeometryCollection);
        assertEquals(4, outputGeometry.getNumGeometries());
        assertFalse(outputGeometry.getGeometryN(0).isEmpty());
        assertTrue(outputGeometry.getGeometryN(1).isEmpty());
        assertFalse(outputGeometry.getGeometryN(2).isEmpty());
        assertTrue(outputGeometry.getGeometryN(3).isEmpty());
    }

    @Test
    public void run() throws Exception {

        String inputGeometry = "MULTILINESTRING ((-5.70068359375 45.1416015625, "
                + "-4.6 46.4), (-4.6 46.4, 1 52.2), (1 52.2, 2.47314453125 "
                + "53.9306640625), (-1.21826171875 53.9306640625, 1 52.2), "
                + "(1 52.2, 5.6 48.6), (5.6 48.6, 8.88916015625 46.1962890625), "
                + "(0.71533203125 42.63671875, 1.8 44), (1.8 44, 5.6 48.6), "
                + "(5.6 48.6, 7.13134765625 50.37109375), (-5.83251953125 "
                + "46.943359375, -4.6 46.4), (-4.6 46.4, 1.8 44), (1.8 44, "
                + "4.45068359375 42.98828125))";

        // Geometry from options
        String result = runApp(new String[]{
                "polygonize",
                "-g", inputGeometry
        }, null);
        Geometry outputGeometry = new WKTReader().read(result);
        assertTrue(outputGeometry instanceof MultiPolygon);
        assertEquals(1, outputGeometry.getNumGeometries());

        // Geometry from input stream
        result = runApp(new String[]{
                "polygonize",
                "-f"
        }, inputGeometry);
        outputGeometry = new WKTReader().read(result);
        assertTrue(outputGeometry instanceof GeometryCollection);
        assertEquals(4, outputGeometry.getNumGeometries());
        assertFalse(outputGeometry.getGeometryN(0).isEmpty());
        assertTrue(outputGeometry.getGeometryN(1).isEmpty());
        assertFalse(outputGeometry.getGeometryN(2).isEmpty());
        assertTrue(outputGeometry.getGeometryN(3).isEmpty());
    }
}