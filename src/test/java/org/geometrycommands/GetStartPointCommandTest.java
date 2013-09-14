package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The GetStartPointCommand UnitTest
 * @author Jared Erickson
 */
public class GetStartPointCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (1 1, 5 5, 10 10)";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        GetStartPointCommand command = new GetStartPointCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (1 1)", writer.getBuffer().toString());
        
        inputGeometry = "MULTILINESTRING ((1 1, 5 5, 10 10), (20 20, 30 30, 40 40))";
        options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("POINT (1 1)", writer.getBuffer().toString());
    }
}