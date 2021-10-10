package org.geometrycommands;

import org.junit.jupiter.api.Test;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.*;
import org.geometrycommands.DisjointCommand.DisjointOptions;

/**
 * The DisjointCommand UnitTest
 *
 * @author Jared Erickson
 */
public class DisjointCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        // false
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "LINESTRING (5 5, 5 15)";
        DisjointOptions options = new DisjointOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        DisjointCommand command = new DisjointCommand();
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());

        // true
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "LINESTRING (15 15, 20 20)";
        options = new DisjointOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new DisjointCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "disjoint",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-o", "LINESTRING (5 5, 5 15)"
        }, null);
        assertFalse(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "disjoint",
                "-o", "LINESTRING (15 15, 20 20)"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertTrue(Boolean.parseBoolean(result));
    }
}
