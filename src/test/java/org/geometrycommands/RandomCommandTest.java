package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import org.geometrycommands.RandomCommand.RandomOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
        options.setConstrainedToCircle(false);
        options.setGutterFraction(Double.NaN);

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

        // Geometry from input stream
        result = runApp(new String[]{
                "random",
                "-n", "10",
                "-r",
                "-c",
                "-f", "0.25"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        geometry = new WKTReader().read(result);
        assertEquals("MultiPoint", geometry.getGeometryType());
        assertEquals(16, geometry.getNumGeometries());
    }

    @Test
    public void runWithWrongGeometryType() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "random",
                "-g", "POINT (1 1)",
                "-n", "10"
        }, null);
        assertEquals("Geometry must be a Polygon or MultiPolygon!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help                  : Print help message (default: false)" + NEW_LINE +
                " --web-help              : Open help in a web browser (default: false)" + NEW_LINE +
                " -c (--constrained)      : The flag for whether the random points should be" + NEW_LINE +
                "                           constrained to a circle when gridded. (default:" + NEW_LINE +
                "                           false)" + NEW_LINE +
                " -f (--gutterFraction) N : The gutter distance or padding for random points" + NEW_LINE +
                "                           when gridded. (default: NaN)" + NEW_LINE +
                " -g (--geometry) VAL     : The input geometry (default: POINT (1 1))" + NEW_LINE +
                " -n (--number) N         : The number of points" + NEW_LINE +
                " -r (--gridded)          : The flag for whether the random points should be" + NEW_LINE +
                "                           gridded. (default: false)", result.get("err"));
    }
}
