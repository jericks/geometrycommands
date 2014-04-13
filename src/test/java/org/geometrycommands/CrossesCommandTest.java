package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The CrossesCommand UnitTest
 * @author Jared Erickson
 */
public class CrossesCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        // true
        String inputGeometry = "LINESTRING (5 5, 5 15)";
        String otherGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        OtherGeometryOptions options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        CrossesCommand command = new CrossesCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());
        
        // false
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "LINESTRING (15 15, 20 20)";
        options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command = new CrossesCommand();
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "crosses",
                "-g", "LINESTRING (5 5, 5 15)",
                "-o", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertTrue(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "crosses",
                "-o", "LINESTRING (15 15, 20 20)"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertFalse(Boolean.parseBoolean(result));
    }
}
