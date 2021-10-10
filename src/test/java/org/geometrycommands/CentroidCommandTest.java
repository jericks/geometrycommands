package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.CentroidCommand.CentroidOptions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The CentroidCommand UnitTest
 *
 * @author Jared Erickson
 */
public class CentroidCommandTest extends BaseTest {

    @Test
    public void executeWithOption() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        CentroidOptions options = new CentroidOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        CentroidCommand command = new CentroidCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (5 5)", writer.getBuffer().toString());
    }

    @Test
    public void executeWithReader() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        CentroidOptions options = new CentroidOptions();

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        CentroidCommand command = new CentroidCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (5 5)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "centroid",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertEquals("POINT (5 5)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "centroid"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POINT (5 5)", result);
    }
}
