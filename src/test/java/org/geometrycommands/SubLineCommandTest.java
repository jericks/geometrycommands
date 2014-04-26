package org.geometrycommands;

import java.io.Reader;
import org.geometrycommands.SubLineCommand.SubLineOptions;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The SubLineCommand UnitTest
 * @author Jared Erickson
 */
public class SubLineCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (0 0, 10 10, 20 20)";
        SubLineOptions options = new SubLineOptions();
        options.setGeometry(inputGeometry);
        options.setStartPosition(0.25);
        options.setEndPosition(0.75);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        SubLineCommand command = new SubLineCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (5 5, 10 10, 15 15)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        // Geometry from options
        String result = runApp(new String[]{
                "subline",
                "-g", "LINESTRING (0 0, 10 10, 20 20)",
                "-s", "0.25",
                "-e", "0.75"
        }, null);
        assertEquals("LINESTRING (5 5, 10 10, 15 15)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "subline",
                "-s", "0.25",
                "-e", "0.75"
        }, "LINESTRING (0 0, 10 10, 20 20)");
        assertEquals("LINESTRING (5 5, 10 10, 15 15)", result);
    }
}
