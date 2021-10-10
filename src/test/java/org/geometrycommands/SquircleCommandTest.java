package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.geometrycommands.SquircleCommand.SquircleOptions;

/**
 * The SquircleCommand UnitTest
 * @author Jared Erickson
 */
public class SquircleCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (100 100)";
        SquircleOptions options = new SquircleOptions();
        options.setGeometry(inputGeometry);
        options.setWidth(50);
        options.setHeight(40);
        options.setNumberOfPoints(10);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        SquircleCommand command = new SquircleCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((125 140, 141.8179283050743 136.8179283050743, "
                + "145 120, 141.8179283050743 103.18207169492571, "
                + "125 100, 108.18207169492571 103.18207169492571, 105 120, "
                + "108.18207169492571 136.8179283050743, 125 140))",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        // Geometry from options
        String result = runApp(new String[]{
                "squircle",
                "-g", "POINT (100 100)",
                "-w", "50",
                "-h", "40",
                "-p", "10"
        }, null);
        assertEquals("POLYGON ((125 140, 141.8179283050743 136.8179283050743, "
                + "145 120, 141.8179283050743 103.18207169492571, "
                + "125 100, 108.18207169492571 103.18207169492571, 105 120, "
                + "108.18207169492571 136.8179283050743, 125 140))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "squircle",
                "-w", "50",
                "-h", "40",
                "-p", "10"
        }, "POINT (100 100)");
        assertEquals("POLYGON ((125 140, 141.8179283050743 136.8179283050743, "
                + "145 120, 141.8179283050743 103.18207169492571, "
                + "125 100, 108.18207169492571 103.18207169492571, 105 120, "
                + "108.18207169492571 136.8179283050743, 125 140))", result);
    }
}
