package org.geometrycommands;

import org.geometrycommands.FixCommand.FixOptions;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * The FixCommand UnitTest
 * @author Jared Erickson
 */
public class FixCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "LINESTRING (0 0, 0 0, 0 0, 1 1)";
        FixOptions options = new FixOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        FixCommand command = new FixCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (0 0, 1 1)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "fix",
                "-g", "LINESTRING (0 0, 0 0, 0 0, 1 1)"
        }, null);
        assertEquals("LINESTRING (0 0, 1 1)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "fix"
        }, "LINESTRING (0 0, 0 0, 0 0, 1 1)");
        assertEquals("LINESTRING (0 0, 1 1)", result);
    }
}
