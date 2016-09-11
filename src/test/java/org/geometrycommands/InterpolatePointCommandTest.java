package org.geometrycommands;

import org.geometrycommands.InterpolatePointCommand.InterpolatePointOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The InterpolatePointCommand UnitTest
 * @author Jared Erickson
 */
public class InterpolatePointCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (0 0, 5 5, 10 10)";
        InterpolatePointOptions options = new InterpolatePointOptions();
        options.setGeometry(inputGeometry);
        options.setPosition(0.25);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        InterpolatePointCommand command = new InterpolatePointCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (2.5 2.5)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "interpolatepoint",
                "-g", "LINESTRING (0 0, 5 5, 10 10)",
                "-p", "0.25"
        }, null);
        assertEquals("POINT (2.5 2.5)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "interpolatepoint",
                "-p", "0.25"
        }, "LINESTRING (0 0, 5 5, 10 10)");
        assertEquals("POINT (2.5 2.5)", result);
    }

    @Test
    public void runWithWrongGeometryType() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "interpolatepoint",
                "-g", "POINT (1 1)",
                "-p", "0.25"
        }, null);
        assertEquals("The input geometry must be a LineString or a MultiLineString!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help              : Print help message (default: false)" + NEW_LINE +
                " --web-help          : Open help in a web browser (default: false)" + NEW_LINE +
                " -g (--geometry) VAL : The input geometry (default: POINT (1 1))" + NEW_LINE +
                " -p (--position) N   : The position between 0 and 1", result.get("err"));
    }
}
