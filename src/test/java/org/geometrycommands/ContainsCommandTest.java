package org.geometrycommands;

import org.geometrycommands.ContainsCommand.ContainsOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The ContainsCommand UnitTest
 * @author Jared Erickson
 */
public class ContainsCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        // true
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POINT (5 5)";
        ContainsOptions options = new ContainsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        ContainsCommand command = new ContainsCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());
        
        // false
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POINT (15 15)";
        options = new ContainsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command = new ContainsCommand();
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "contains",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-o", "POINT (5 5)"
        }, null);
        assertTrue(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "contains",
                "-o", "POINT (15 15)"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertFalse(Boolean.parseBoolean(result));
    }
}
