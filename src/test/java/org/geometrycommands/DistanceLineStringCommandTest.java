package org.geometrycommands;

import org.junit.jupiter.api.Test;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.geometrycommands.DistanceLineStringCommand.DistanceLineStringOptions;

/**
 * The DistanceLineStringCommand UnitTest
 * @author Jared Erickson
 */
public class DistanceLineStringCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (1 1)";
        String otherGeometry = "POINT (20 23)";
        DistanceLineStringOptions options = new DistanceLineStringOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        DistanceLineStringCommand command = new DistanceLineStringCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (1 1, 20 23)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "distanceline",
                "-g", "POINT (1 1)",
                "-o", "POINT (20 23)"
        }, null);
        assertEquals("LINESTRING (1 1, 20 23)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "distanceline",
                "-o", "POINT (20 23)"
        }, "POINT (1 1)");
        assertEquals("LINESTRING (1 1, 20 23)", result);
    }
}
