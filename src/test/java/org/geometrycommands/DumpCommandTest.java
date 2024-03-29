package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.geometrycommands.DumpCommand.DumpOptions;

/**
 * The DumpCommand UnitTest
 * @author Jared Erickson
 */
public class DumpCommandTest extends BaseTest {

    private final static String NEW_LINE = System.getProperty("line.separator");
    
    @Test 
    public void execute() throws Exception {
        
        // Single MultiPoint
        String inputGeometry = "MULTIPOINT ((1 1), (2 2))";
        DumpOptions options = new DumpOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        DumpCommand command = new DumpCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (1 1)" + NEW_LINE + "POINT (2 2)" + NEW_LINE, writer.getBuffer().toString());
        
        // GeometryCollection with nested MultPoint
        inputGeometry = "GEOMETRYCOLLECTION (POINT (5 5), MULTIPOINT ((1 1), (2 2)))";
        options.setGeometry(inputGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("POINT (5 5)" + NEW_LINE + "POINT (1 1)" + NEW_LINE + "POINT (2 2)" + NEW_LINE, writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "dump",
                "-g", "MULTIPOINT ((1 1), (2 2))"
        }, null);
        assertEquals("POINT (1 1)" + NEW_LINE + "POINT (2 2)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "dump"
        }, "MULTIPOINT ((1 1), (2 2))");
        assertEquals("POINT (1 1)" + NEW_LINE + "POINT (2 2)", result);
    }
}
