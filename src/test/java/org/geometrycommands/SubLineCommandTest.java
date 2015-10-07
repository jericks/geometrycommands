package org.geometrycommands;

import java.io.Reader;
import org.geometrycommands.SubLineCommand.SubLineOptions;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The SubLineCommand UnitTest
 * @author Jared Erickson
 */
public class SubLineCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (0 0, 10 10, 20 20)";
        SubLineOptions options = new SubLineOptions();
        options.setGeometry(inputGeometry);
        options.setStartPosition(0.25);
        options.setEndPosition(0.75);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        SubLineCommand command = new SubLineCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (5 5, 10 10, 15 15)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        // Geometry from options
        String result = runApp(new String[]{
                "subline",
                "-g", "LINESTRING (0 0, 10 10, 20 20)",
                "-s", "0.25",
                "-e", "0.75"
        }, null);
        assertEquals("LINESTRING (5 5, 10 10, 15 15)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "subline",
                "-s", "0.25",
                "-e", "0.75"
        }, "LINESTRING (0 0, 10 10, 20 20)");
        assertEquals("LINESTRING (5 5, 10 10, 15 15)", result);
    }

    @Test
    public void runWithWrongGeometryType() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "subline",
                "-g", "POINT (1 1)",
                "-s", "0.25",
                "-e", "0.75"
        }, null);
        assertEquals("The input geometry must be a LineString or a MultiLineString!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help               : Print help message (default: false)" + NEW_LINE +
                " -e (endPosition) N   : The end position between 0 and 1" + NEW_LINE +
                " -g (--geometry) VAL  : The input geometry (default: POINT (1 1))" + NEW_LINE +
                " -s (startPosition) N : The start position between 0 and 1", result.get("err"));
    }

    @Test
    public void runWithInvalidStartAndEnd() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "subline",
                "-g", "LINESTRING (1 1, 10 10)",
                "-s", "0.75",
                "-e", "0.25"
        }, null);
        assertEquals("The start position must be less than the end position!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help               : Print help message (default: false)" + NEW_LINE +
                " -e (endPosition) N   : The end position between 0 and 1" + NEW_LINE +
                " -g (--geometry) VAL  : The input geometry (default: LINESTRING (1 1, 10 10))" + NEW_LINE +
                " -s (startPosition) N : The start position between 0 and 1", result.get("err"));
    }
}
