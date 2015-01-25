package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import org.geometrycommands.RandomCommand.RandomOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The RandomCommand UnitTest
 * @author Jared Erickson
 */
public class RandomCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        RandomOptions options = new RandomOptions();
        options.setGeometry(inputGeometry);
        options.setNumber(10);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        RandomCommand command = new RandomCommand();
        command.execute(options, reader, writer);
        String output = writer.getBuffer().toString();
        Geometry geometry = new WKTReader().read(output);
        assertEquals("MultiPoint", geometry.getGeometryType());
        assertEquals(10, geometry.getNumGeometries());
    }

    @Test
    public void executeGriddedWithLineString() throws Exception {
        String inputGeometry = "LINESTRING (0 0, 10 10)";
        RandomOptions options = new RandomOptions();
        options.setGeometry(inputGeometry);
        options.setGridded(true);
        options.setNumber(10);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        RandomCommand command = new RandomCommand();
        command.execute(options, reader, writer);
        String output = writer.getBuffer().toString();
        Geometry geometry = new WKTReader().read(output);
        assertEquals("MultiPoint", geometry.getGeometryType());
        assertEquals(16, geometry.getNumGeometries());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "random",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-n", "10"
        }, null);
        Geometry geometry = new WKTReader().read(result);
        assertEquals("MultiPoint", geometry.getGeometryType());
        assertEquals(10, geometry.getNumGeometries());

        // Geometry from input stream
        result = runApp(new String[]{
                "random",
                "-n", "10"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        geometry = new WKTReader().read(result);
        assertEquals("MultiPoint", geometry.getGeometryType());
        assertEquals(10, geometry.getNumGeometries());
    }
}
