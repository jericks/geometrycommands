package org.geometrycommands;

import org.geometrycommands.RotateCommand.RotateOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The RotateCommand UnitTest
 * @author Jared Erickson
 */
public class RotateCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        RotateOptions options = new RotateOptions();
        options.setGeometry(inputGeometry);
        options.setTheta(Math.toRadians(45));
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        RotateCommand command = new RotateCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, -7.071067811865475 7.0710678118654755, "
                + "0.0000000000000009 14.142135623730951, 7.0710678118654755 "
                + "7.071067811865475, 0 0))", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "rotate",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-t", String.valueOf(Math.toRadians(45))
        }, null);
        assertEquals("POLYGON ((0 0, -7.071067811865475 7.0710678118654755, "
                + "0.0000000000000009 14.142135623730951, 7.0710678118654755 "
                + "7.071067811865475, 0 0))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "rotate",
                "-t", String.valueOf(Math.toRadians(45))
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((0 0, -7.071067811865475 7.0710678118654755, "
                + "0.0000000000000009 14.142135623730951, 7.0710678118654755 "
                + "7.071067811865475, 0 0))", result);
    }
}
