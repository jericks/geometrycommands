package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The ReverseCommand UnitTest
 * @author Jared Erickson
 */
public class ReverseCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (0 0, 5 5, 10 10)";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        ReverseCommand command = new ReverseCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (10 10, 5 5, 0 0)", writer.getBuffer().toString());
    }
}
