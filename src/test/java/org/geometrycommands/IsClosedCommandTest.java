package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
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
}
