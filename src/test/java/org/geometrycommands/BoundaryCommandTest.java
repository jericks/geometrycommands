package org.geometrycommands;

import org.geometrycommands.BoundaryCommand.BoundaryOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The BoundaryCommand UnitTest
 * @author Jared Erickson
 */
public class BoundaryCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        BoundaryOptions options = new BoundaryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        BoundaryCommand command = new BoundaryCommand();
        command.execute(options, reader, writer);
        assertEquals("LINEARRING (0 0, 0 10, 10 10, 10 0, 0 0)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "boundary",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertEquals("LINEARRING (0 0, 0 10, 10 10, 10 0, 0 0)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "boundary",
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("LINEARRING (0 0, 0 10, 10 10, 10 0, 0 0)", result);
    }
}
