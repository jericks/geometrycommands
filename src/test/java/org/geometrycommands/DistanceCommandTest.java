package org.geometrycommands;

import org.junit.Test;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.DistanceCommand.DistanceOptions;

/**
 * The DistanceCommand UnitTest
 *
 * @author Jared Erickson
 */
public class DistanceCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (1 1)";
        String otherGeometry = "POINT (20 23)";
        DistanceOptions options = new DistanceOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        DistanceCommand command = new DistanceCommand();
        command.execute(options, reader, writer);
        assertEquals("29.068883707497267", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "distance",
                "-g", "POINT (1 1)",
                "-o", "POINT (20 23)"
        }, null);
        assertEquals(29.068883707497267, Double.parseDouble(result), 0.0001);

        // Geometry from input stream
        result = runApp(new String[]{
                "distance",
                "-o", "POINT (20 23)"
        }, "POINT (1 1)");
        assertEquals(29.068883707497267, Double.parseDouble(result), 0.0001);
    }
}
