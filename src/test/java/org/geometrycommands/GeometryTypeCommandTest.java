package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The GeometryTypeCommand UnitTest
 * @author Jared Erickson
 */
public class GeometryTypeCommandTest {

    @Test 
    public void execute() throws Exception {
        
        // Polygon
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        GeometryTypeCommand command = new GeometryTypeCommand();
        command.execute(options, reader, writer);
        assertEquals("Polygon", writer.getBuffer().toString());
        
        // Point
        inputGeometry = "POINT (5 5)";
        options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command = new GeometryTypeCommand();
        command.execute(options, reader, writer);
        assertEquals("Point", writer.getBuffer().toString());
    }
}
