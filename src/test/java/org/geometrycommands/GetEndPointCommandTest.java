package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The GetEndPointCommand UnitTest
 * @author Jared Erickson
 */
public class GetEndPointCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (1 1, 5 5, 10 10)";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        GetEndPointCommand command = new GetEndPointCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (10 10)", writer.getBuffer().toString());
        
        inputGeometry = "MULTILINESTRING ((1 1, 5 5, 10 10), (20 20, 30 30, 40 40))";
        options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("POINT (40 40)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "endpoint",
                "-g", "LINESTRING (1 1, 5 5, 10 10)"
        }, null);
        assertEquals("POINT (10 10)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "endpoint"
        }, "MULTILINESTRING ((1 1, 5 5, 10 10), (20 20, 30 30, 40 40))");
        assertEquals("POINT (40 40)", result);
    }
}
