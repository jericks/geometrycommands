package org.geometrycommands;

import org.geometrycommands.CoveredByCommand.CoveredByOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The CoveredByCommand UnitTest
 * @author Jared Erickson
 */
public class CoveredByCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        // true
        String inputGeometry = "POINT (5 5)";
        String otherGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        CoveredByOptions options = new CoveredByOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        CoveredByCommand command = new CoveredByCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());
        
        // false
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POINT (15 15)";
        options = new CoveredByOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command = new CoveredByCommand();
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "coveredby",
                "-g", "POINT (5 5)",
                "-o", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertTrue(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "coveredby",
                "-o", "POINT (15 15)"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertFalse(Boolean.parseBoolean(result));
    }
}
