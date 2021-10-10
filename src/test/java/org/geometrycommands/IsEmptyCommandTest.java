package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.geometrycommands.IsEmptyCommand.IsEmptyOptions;

/**
 * The IsEmptyCommand UnitTest
 * @author Jared Erickson
 */
public class IsEmptyCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        // true
        String inputGeometry = "POINT EMPTY";
        IsEmptyOptions options = new IsEmptyOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        IsEmptyCommand command = new IsEmptyCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // false
        inputGeometry = "POINT (15 15)";
        options = new IsEmptyOptions();
        options.setGeometry(inputGeometry);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new IsEmptyCommand();
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "isempty",
                "-g", "POINT EMPTY"
        }, null);
        assertTrue(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "isempty"
        }, "LINESTRING (1 1, 5 5, 10 10)");
        assertFalse(Boolean.parseBoolean(result));
    }
}
