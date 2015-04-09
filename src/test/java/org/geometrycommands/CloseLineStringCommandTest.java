package org.geometrycommands;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.CloseLineStringCommand.CloseLineStringOptions;
import static org.junit.Assert.assertEquals;

/**
 * The CloseLineStringCommand Unit Test
 * @author Jared Erickson
 */
public class CloseLineStringCommandTest extends BaseTest {

    @Test
    public void executeWithOption() throws Exception {
        String inputGeometry = "LINESTRING (0 0, 0 4, 4 4, 4 0)";
        CloseLineStringOptions options = new CloseLineStringOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        CloseLineStringCommand command = new CloseLineStringCommand();
        command.execute(options, reader, writer);
        assertEquals("LINEARRING (0 0, 0 4, 4 4, 4 0, 0 0)", writer.getBuffer().toString());
    }

    @Test
    public void executeWithReader() throws Exception {
        String inputGeometry = "LINESTRING (0 0, 0 4, 4 4, 4 0)";
        CloseLineStringOptions options = new CloseLineStringOptions();

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        CloseLineStringCommand command = new CloseLineStringCommand();
        command.execute(options, reader, writer);
        assertEquals("LINEARRING (0 0, 0 4, 4 4, 4 0, 0 0)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "closelinestring",
                "-g", "LINESTRING (0 0, 0 4, 4 4, 4 0)"
        }, null);
        assertEquals("LINEARRING (0 0, 0 4, 4 4, 4 0, 0 0)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "closelinestring"
        }, "LINESTRING (0 0, 0 4, 4 4, 4 0)");
        assertEquals("LINEARRING (0 0, 0 4, 4 4, 4 0, 0 0)", result);
    }

}
