package org.geometrycommands;

import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.DimensionCommand.DimensionOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The DimensionCommand Unit Test
 * @author Jared Erickson
 */
public class DimensionCommandTest extends BaseTest {

    @Test
    public void executeWithOption() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        DimensionOptions options = new DimensionOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        DimensionCommand command = new DimensionCommand();
        command.execute(options, reader, writer);
        assertEquals("2.0", writer.getBuffer().toString());
    }

    @Test
    public void executeWithReader() throws Exception {
        String inputGeometry = "POINT (100 100)";
        DimensionOptions options = new DimensionOptions();

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        DimensionCommand command = new DimensionCommand();
        command.execute(options, reader, writer);
        assertEquals("0.0", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "dimension",
                "-g", "LINESTRING (0 0, 100 100)"
        }, null);
        assertEquals(1, Double.parseDouble(result), 0.1);

        // Geometry from input stream
        result = runApp(new String[]{
                "dimension",
        }, "GEOMETRYCOLLECTION (POINT (0 0), LINESTRING(0 0, 100 100))");
        assertEquals(1, Double.parseDouble(result), 0.1);
    }
}
