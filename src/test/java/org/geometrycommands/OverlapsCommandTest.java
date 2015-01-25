package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.*;
import org.geometrycommands.OverlapsCommand.OverlapsOptions;

/**
 * The OverlapsCommand UnitTest
 * @author Jared Erickson
 */
public class OverlapsCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        // true
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((2 2, 2 14, 14 14, 14 2, 2 2))";
        OverlapsOptions options = new OverlapsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        OverlapsCommand command = new OverlapsCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());
        
        // false
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POINT (15 15)";
        options = new OverlapsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry1 = "POLYGON ((2 2, 2 14, 14 14, 14 2, 2 2))";
        String otherGeometry2 = "POINT (15 15)";

        // Geometry from options
        String result = runApp(new String[]{
                "overlaps",
                "-g", inputGeometry,
                "-o", otherGeometry1
        }, null);
        assertTrue(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "overlaps",
                "-o", otherGeometry2
        }, inputGeometry);
        assertFalse(Boolean.parseBoolean(result));
    }

}
