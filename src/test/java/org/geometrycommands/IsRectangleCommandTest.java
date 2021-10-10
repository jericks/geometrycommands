package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.geometrycommands.IsRectangleCommand.IsRectangleOptions;

/**
 * The IsRectangleCommand UnitTest
 * @author Jared Erickson
 */
public class IsRectangleCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        // true
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        IsRectangleOptions options = new IsRectangleOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        IsRectangleCommand command = new IsRectangleCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // false
        inputGeometry = "POLYGON ((0 0, 0 10, 10 0, 0 0))";
        options = new IsRectangleOptions();
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
                "isrectangle",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertTrue(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "isrectangle"
        }, "POLYGON ((0 0, 0 10, 10 0, 0 0))");
        assertFalse(Boolean.parseBoolean(result));
    }
}
