package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;
import org.geometrycommands.IsClosedCommand.IsClosedOptions;

/**
 * The IsClosedCommand UnitTest
 * @author Jared Erickson
 */
public class IsClosedCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        // true
        String inputGeometry = "LINESTRING (1 1, 1 5, 5 5, 5 1, 1 1)";
        IsClosedOptions options = new IsClosedOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        IsClosedCommand command = new IsClosedCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // false
        inputGeometry = "LINESTRING (1 1, 5 5, 10 10)";
        options = new IsClosedOptions();
        options.setGeometry(inputGeometry);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());

        // false
        inputGeometry = "MULTILINESTRING ((1 1, 5 5, 10 10), (0 0, 1 1, 2 2))";
        options = new IsClosedOptions();
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
                "isclosed",
                "-g", "LINESTRING (1 1, 1 5, 5 5, 5 1, 1 1)"
        }, null);
        assertTrue(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "isclosed"
        }, "LINESTRING (1 1, 5 5, 10 10)");
        assertFalse(Boolean.parseBoolean(result));
    }

    @Test
    public void runWithWrongGeometryType() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "isclosed",
                "-g", "POINT (1 1)"
        }, null);
        assertEquals("The input geometry must be a LineString or a MultiLineString!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help              : Print help message" + NEW_LINE +
                " -g (--geometry) VAL : The input geometry", result.get("err"));
    }
}
