package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The AreaCommand UnitTest
 * @author Jared Erickson
 */
public class AreaCommandTest extends BaseTest {
    
    @Test 
    public void executeWithOption() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        
        AreaCommand command = new AreaCommand();
        command.execute(options, reader, writer);
        assertEquals("100.0", writer.getBuffer().toString());
    }

    @Test
    public void executeWithReader() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        GeometryOptions options = new GeometryOptions();

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        AreaCommand command = new AreaCommand();
        command.execute(options, reader, writer);
        assertEquals("100.0", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "area",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertEquals(100.0, Double.parseDouble(result), 0.001);

        // Geometry from input stream
        result = runApp(new String[]{
                "area",
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals(100.0, Double.parseDouble(result), 0.001);
    }
}
