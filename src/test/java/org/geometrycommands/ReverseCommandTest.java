package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.ReverseCommand.ReverseOptions;

/**
 * The ReverseCommand UnitTest
 * @author Jared Erickson
 */
public class ReverseCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (0 0, 5 5, 10 10)";
        ReverseOptions options = new ReverseOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        ReverseCommand command = new ReverseCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (10 10, 5 5, 0 0)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "reverse",
                "-g", "LINESTRING (0 0, 5 5, 10 10)"
        }, null);
        assertEquals("LINESTRING (10 10, 5 5, 0 0)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "reverse",
        }, "LINESTRING (0 0, 5 5, 10 10)");
        assertEquals("LINESTRING (10 10, 5 5, 0 0)", result);
    }
}
