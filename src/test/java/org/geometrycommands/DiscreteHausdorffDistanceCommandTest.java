package org.geometrycommands;

import org.geometrycommands.DiscreteHausdorffDistanceCommand.DiscreteHausdorffDistanceOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The DiscreteHausdorffDistanceCommand UnitTest
 * @author Jared Erickson
 */
public class DiscreteHausdorffDistanceCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (1 1)";
        String otherGeometry = "POINT (20 23)";
        DiscreteHausdorffDistanceOptions options = new DiscreteHausdorffDistanceOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        DiscreteHausdorffDistanceCommand command = new DiscreteHausdorffDistanceCommand();
        command.execute(options, reader, writer);
        assertEquals("29.068883707497267", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "hausdorffdistance",
                "-g", "POINT (1 1)",
                "-o", "POINT (20 23)"
        }, null);
        assertEquals(29.068883707497267, Double.parseDouble(result), 0.00001);

        // Geometry from input stream
        result = runApp(new String[]{
                "hausdorffdistance",
                "-o", "POINT (20 23)"
        }, "POINT (1 1)");
        assertEquals(29.068883707497267, Double.parseDouble(result), 0.00001);
    }
}
