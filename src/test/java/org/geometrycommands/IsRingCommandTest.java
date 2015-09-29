package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;
import org.geometrycommands.IsRingCommand.IsRingOptions;

/**
 * The IsRingCommand UnitTest
 * @author Jared Erickson
 */
public class IsRingCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        // true
        String inputGeometry = "LINESTRING (1 1, 1 5, 5 5, 5 1, 1 1)";
        IsRingOptions options = new IsRingOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        IsRingCommand command = new IsRingCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // false
        inputGeometry = "LINESTRING (1 1, 5 5, 10 10)";
        options = new IsRingOptions();
        options.setGeometry(inputGeometry);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "isring",
                "-g", "LINESTRING (1 1, 1 5, 5 5, 5 1, 1 1)"
        }, null);
        assertTrue(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "isring"
        }, "LINESTRING (1 1, 5 5, 10 10)");
        assertFalse(Boolean.parseBoolean(result));
    }

    @Test
    public void runWithWrongGeometryType() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "isring",
                "-g", "POINT (1 1)"
        }, null);
        assertEquals("The input geometry must be a LineString!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help              : Print help message" + NEW_LINE +
                " -g (--geometry) VAL : The input geometry", result.get("err"));
    }
}
