package org.geometrycommands;

import org.geometrycommands.CountPointsCommand.CountPointsOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The CountPointsCommand UnitTest
 * @author Jared Erickson
 */
public class CountPointsCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "MULTIPOINT ((0 0), (0 10), (10 10), (10 0), (0 0))";
        CountPointsOptions options = new CountPointsOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        CountPointsCommand command = new CountPointsCommand();
        command.execute(options, reader, writer);
        assertEquals("5", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "countpoints",
                "-g", "MULTIPOINT ((0 0), (0 10), (10 10), (10 0), (0 0))"
        }, null);
        assertEquals(5, Integer.parseInt(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "countpoints"
        }, "MULTIPOINT ((0 0), (0 10), (10 10), (10 0), (0 0))");
        assertEquals(5, Integer.parseInt(result));
    }
}
