package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.geometrycommands.TouchesCommand.TouchesOptions;

/**
 * The TouchesCommand UnitTest
 * @author Jared Erickson
 */
public class TouchesCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        // true
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((10 10, 10 14, 14 14, 14 10, 10 10))";
        TouchesOptions options = new TouchesOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        TouchesCommand command = new TouchesCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());
        
        // false
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POINT (15 15)";
        options = new TouchesOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        // Geometry from options
        String result = runApp(new String[]{
                "touches",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-o", "POLYGON ((10 10, 10 14, 14 14, 14 10, 10 10))"
        }, null);
        assertEquals("true", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "touches",
                "-o", "POINT (15 15)"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("false", result);
    }
}
