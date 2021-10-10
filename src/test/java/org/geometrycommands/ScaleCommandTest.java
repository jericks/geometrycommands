package org.geometrycommands;

import org.geometrycommands.ScaleCommand.ScaleOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The ScaleCommand UnitTest
 * @author Jared Erickson
 */
public class ScaleCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        ScaleOptions options = new ScaleOptions();
        options.setGeometry(inputGeometry);
        options.setXScale(2);
        options.setYScale(5);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        ScaleCommand command = new ScaleCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, 0 50, 20 50, "
                + "20 0, 0 0))", writer.getBuffer().toString());

        options = new ScaleOptions();
        options.setGeometry(inputGeometry);
        options.setXScale(2);
        options.setYScale(5);
        options.setX(35);
        options.setY(30);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("POLYGON ((-35 -120, -35 -70, -15 -70, " +
                "-15 -120, -35 -120))", writer.getBuffer().toString());

        options = new ScaleOptions();
        options.setGeometry(inputGeometry);
        options.setXScale(2);
        options.setYScale(5);
        options.setX(Double.NaN);
        options.setY(30);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, 0 50, 20 50, 20 0, 0 0))", writer.getBuffer().toString());

        options = new ScaleOptions();
        options.setGeometry(inputGeometry);
        options.setXScale(2);
        options.setYScale(5);
        options.setX(30);
        options.setY(Double.NaN);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, 0 50, 20 50, 20 0, 0 0))", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "scale",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-s", "2",
                "-t", "5"
        }, null);
        assertEquals("POLYGON ((0 0, 0 50, 20 50, "
                + "20 0, 0 0))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "scale",
                "-s", "2",
                "-t", "5"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((0 0, 0 50, 20 50, "
                + "20 0, 0 0))", result);
    }
}
