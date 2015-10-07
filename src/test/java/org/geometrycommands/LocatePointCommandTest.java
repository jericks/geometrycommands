package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.LocatePointCommand.LocatePointOptions;

/**
 * The LocatePointCommand UnitTest
 * @author Jared Erickson
 */
public class LocatePointCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (0 0, 5 5, 10 10)";
        String otherGeometry = "POINT (2.5 2.5)";
        LocatePointOptions options = new LocatePointOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        LocatePointCommand command = new LocatePointCommand();
        command.execute(options, reader, writer);
        assertEquals("0.25", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "locatepoint",
                "-g", "LINESTRING (0 0, 5 5, 10 10)",
                "-o", "POINT (2.5 2.5)"
        }, null);
        assertEquals(0.25, Double.parseDouble(result), 0.01);

        // Geometry from input stream
        result = runApp(new String[]{
                "locatepoint",
                "-o", "POINT (2.5 2.5)"
        }, "LINESTRING (0 0, 5 5, 10 10)");
        assertEquals(0.25, Double.parseDouble(result), 0.01);

        result = runApp(new String[]{
                "locatepoint",
                "-o", "LINESTRING (0 0, 5 5, 10 10)"
        }, "POINT (2.5 2.5)");
        assertEquals(0.25, Double.parseDouble(result), 0.01);
    }

    @Test
    public void runWithWrongGeometryType() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "locatepoint",
                "-g", "POLYGON EMPTY",
                "-o", "POINT EMPTY"
        }, null);
        assertEquals("Please provide a Point and a Linear Geometry!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help                   : Print help message (default: false)" + NEW_LINE +
                " -g (--geometry) VAL      : The input geometry (default: POLYGON EMPTY)" + NEW_LINE +
                " -o (--otherGeometry) VAL : The other geometry", result.get("err"));

        result = runAppWithOutAndErr(new String[]{
                "locatepoint",
                "-g", "LINESTRING EMPTY",
                "-o", "POLYGON EMPTY"
        }, null);
        assertEquals("Please provide a Point and a Linear Geometry!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help                   : Print help message (default: false)" + NEW_LINE +
                " -g (--geometry) VAL      : The input geometry (default: LINESTRING EMPTY)" + NEW_LINE +
                " -o (--otherGeometry) VAL : The other geometry", result.get("err"));
    }
}
