package org.geometrycommands;

import org.geometrycommands.ScaleCommand.ScaleOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
