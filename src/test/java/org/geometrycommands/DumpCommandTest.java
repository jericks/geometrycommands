package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The DumpCommand UnitTest
 * @author Jared Erickson
 */
public class DumpCommandTest {

    private final static String NEW_LINE = System.getProperty("line.separator");
    
    @Test 
    public void execute() throws Exception {
        
        // Single MultiPoint
        String inputGeometry = "MULTIPOINT ((1 1), (2 2))";
        GeometryOptions options = new GeometryOptions();
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
}
