package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.RectangleCommand.RectangleOptions;

/**
 * The RectangleCommand UnitTest
 * @author Jared Erickson
 */
public class RectangleCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (100 100)";
        RectangleOptions options = new RectangleOptions();
        options.setGeometry(inputGeometry);
        options.setWidth(50);
        options.setHeight(40);
        options.setNumberOfPoints(10);
        options.setRotation(0.0);
        options.setCenter(false);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        RectangleCommand command = new RectangleCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((100 100, 125 100, 150 100, 150 120, 150 140, "
                + "125 140, 100 140, 100 120, 100 100))",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "rectangle",
                "-g", "POINT (100 100)",
                "-w", "50",
                "-h", "40",
                "-p", "10"
        }, null);
        assertEquals("POLYGON ((100 100, 125 100, 150 100, 150 120, 150 140, "
                + "125 140, 100 140, 100 120, 100 100))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "rectangle",
                "-w", "50",
                "-h", "40",
                "-p", "10"
        }, "POINT (100 100)");
        assertEquals("POLYGON ((100 100, 125 100, 150 100, 150 120, 150 140, "
                + "125 140, 100 140, 100 120, 100 100))", result);

        result = runApp(new String[]{
                "rectangle",
                "-w", "50",
                "-h", "40",
                "-p", "10",
                "-c"
        }, "POINT (100 100)");
        assertEquals("POLYGON ((75 80, 100 80, 125 80, 125 100, " +
                "125 120, 100 120, 75 120, 75 100, 75 80))", result);

        result = runApp(new String[]{
                "rectangle",
                "-w", "90",
                "-h", "70",
                "-p", "10",
                "-c"
        }, "POLYGON ((75 80, 100 80, 125 80, 125 100, " +
                "125 120, 100 120, 75 120, 75 100, 75 80))");
        assertEquals("POLYGON ((75 80, 100 80, 125 80, 125 100, " +
                "125 120, 100 120, 75 120, 75 100, 75 80))", result);
    }

    @Test
    public void runWithInvalidAlgorithm() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "rectangle",
                "-g", "POINT (100 100)",
                "-p", "10"
        }, null);
        assertEquals("When the Geometry is a point, then width and height are required!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help                  : Print help message" + NEW_LINE +
                " -c (--center)           : The flag to use center (true) or the base (false)" + NEW_LINE +
                " -g (--geometry) VAL     : The input geometry" + NEW_LINE +
                " -h (--height) N         : The height" + NEW_LINE +
                " -p (--numberOfPoints) N : The number of points" + NEW_LINE +
                " -r (--rotation) N       : The rotation" + NEW_LINE +
                " -w (--width) N          : The width", result.get("err"));

        result = runAppWithOutAndErr(new String[]{
                "rectangle",
                "-g", "POINT (100 100)",
                "-p", "10",
                "-w", "10"
        }, null);
        assertEquals("When the Geometry is a point, then width and height are required!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help                  : Print help message" + NEW_LINE +
                " -c (--center)           : The flag to use center (true) or the base (false)" + NEW_LINE +
                " -g (--geometry) VAL     : The input geometry" + NEW_LINE +
                " -h (--height) N         : The height" + NEW_LINE +
                " -p (--numberOfPoints) N : The number of points" + NEW_LINE +
                " -r (--rotation) N       : The rotation" + NEW_LINE +
                " -w (--width) N          : The width", result.get("err"));

        result = runAppWithOutAndErr(new String[]{
                "rectangle",
                "-g", "POINT (100 100)",
                "-p", "10",
                "-h", "10"
        }, null);
        assertEquals("When the Geometry is a point, then width and height are required!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help                  : Print help message" + NEW_LINE +
                " -c (--center)           : The flag to use center (true) or the base (false)" + NEW_LINE +
                " -g (--geometry) VAL     : The input geometry" + NEW_LINE +
                " -h (--height) N         : The height" + NEW_LINE +
                " -p (--numberOfPoints) N : The number of points" + NEW_LINE +
                " -r (--rotation) N       : The rotation" + NEW_LINE +
                " -w (--width) N          : The width", result.get("err"));
    }
}
