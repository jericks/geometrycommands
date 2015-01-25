package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.RectangleCommand.RectangleOptions;

/**
 * The RectangleCommand UnitTest
 * @author Jared Erickson
 */
public class RectangleCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (100 100)";
        RectangleOptions options = new RectangleOptions();
        options.setGeometry(inputGeometry);
        options.setWidth(50);
        options.setHeight(40);
        options.setNumberOfPoints(10);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        RectangleCommand command = new RectangleCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((100 100, 125 100, 150 100, 150 120, 150 140, "
                + "125 140, 100 140, 100 120, 100 100))",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "rectangle",
                "-g", "POINT (100 100)",
                "-w", "50",
                "-h", "40",
                "-p", "10"
        }, null);
        assertEquals("POLYGON ((100 100, 125 100, 150 100, 150 120, 150 140, "
                + "125 140, 100 140, 100 120, 100 100))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "rectangle",
                "-w", "50",
                "-h", "40",
                "-p", "10"
        }, "POINT (100 100)");
        assertEquals("POLYGON ((100 100, 125 100, 150 100, 150 120, 150 140, "
                + "125 140, 100 140, 100 120, 100 100))", result);
    }
}
