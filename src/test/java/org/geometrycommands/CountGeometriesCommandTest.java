package org.geometrycommands;

import org.geometrycommands.CountGeometriesCommand.CountGeometryOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The CountGeometriesCommand UnitTest
 * @author Jared Erickson
 */
public class CountGeometriesCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "MULTIPOINT ((0 0), (0 10), (10 10), (10 0), (0 0))";
        CountGeometryOptions options = new CountGeometryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        CountGeometriesCommand command = new CountGeometriesCommand();
        command.execute(options, reader, writer);
        assertEquals("5", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "count",
                "-g", "MULTIPOINT ((0 0), (0 10), (10 10), (10 0), (0 0))"
        }, null);
        assertEquals(5, Integer.parseInt(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "count"
        }, "MULTIPOINT ((0 0), (0 10), (10 10), (10 0), (0 0))");
        assertEquals(5, Integer.parseInt(result));
    }
}
