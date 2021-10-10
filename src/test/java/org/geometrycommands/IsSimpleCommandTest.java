package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.geometrycommands.IsSimpleCommand.IsSimpleOptions;

/**
 * The IsSimpleCommand UnitTest
 * @author Jared Erickson
 */
public class IsSimpleCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        // true
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        IsSimpleOptions options = new IsSimpleOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        IsSimpleCommand command = new IsSimpleCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // false
        inputGeometry = "LINESTRING (8.14208984375 48.0419921875, "
                + "10.60302734375 51.6015625, 11.56982421875 47.91015625, "
                + "8.36181640625 50.72265625)";
        options = new IsSimpleOptions();
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
                "issimple",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertTrue(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "issimple"
        }, "LINESTRING (8.14208984375 48.0419921875, "
                + "10.60302734375 51.6015625, 11.56982421875 47.91015625, "
                + "8.36181640625 50.72265625)");
        assertFalse(Boolean.parseBoolean(result));
    }
}
