package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The LocatePointCommand UnitTest
 * @author Jared Erickson
 */
public class LocatePointCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (0 0, 5 5, 10 10)";
        String otherGeometry = "POINT (2.5 2.5)";
        OtherGeometryOptions options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        LocatePointCommand command = new LocatePointCommand();
        command.execute(options, reader, writer);
        assertEquals("0.25", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "locatepoint",
                "-g", "LINESTRING (0 0, 5 5, 10 10)",
                "-o", "POINT (2.5 2.5)"
        }, null);
        assertEquals(0.25, Double.parseDouble(result), 0.01);

        // Geometry from input stream
        result = runApp(new String[]{
                "locatepoint",
                "-o", "POINT (2.5 2.5)"
        }, "LINESTRING (0 0, 5 5, 10 10)");
        assertEquals(0.25, Double.parseDouble(result), 0.01);
    }
}
