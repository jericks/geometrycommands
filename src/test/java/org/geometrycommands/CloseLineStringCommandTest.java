package org.geometrycommands;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

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

        // Geometry from input stream
        result = runApp(new String[]{
                "closelinestring"
        }, "LINESTRING (0 0, 0 4, 4 4, 4 0, 0 0)");
        assertEquals("LINEARRING (0 0, 0 4, 4 4, 4 0, 0 0)", result);
    }

    @Test
    public void runWithWrongGeometry() throws Exception {
        Map<String,String> outputs = runAppWithOutAndErr(new String[]{
                "closelinestring",
                "-g", "POINT (0 0)"
        }, null);
        assertEquals("Input geometry must be a LineString!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help              : Print help message (default: false)" + NEW_LINE +
                " --web-help          : Open help in a web browser (default: false)" + NEW_LINE +
                " -g (--geometry) VAL : The input geometry (default: POINT (0 0))", outputs.get("err"));
    }

    @Test
    public void runWithLineStringTooShort() throws Exception {
        Map<String,String> outputs = runAppWithOutAndErr(new String[]{
                "closelinestring",
                "-g", "LINESTRING (0 0, 10 10)"
        }, null);
        assertEquals("You need at least three points to close a LineString!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help              : Print help message (default: false)" + NEW_LINE +
                " --web-help          : Open help in a web browser (default: false)" + NEW_LINE +
                " -g (--geometry) VAL : The input geometry (default: LINESTRING (0 0, 10 10))", outputs.get("err"));
    }
}
