package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The CoordinatesCommand UnitTest
 * @author Jared Erickson
 */
public class CoordinatesCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        CoordinatesCommand command = new CoordinatesCommand();
        command.execute(options, reader, writer);
        assertEquals("MULTIPOINT ((0 0), (0 10), (10 10), (10 0), (0 0))", writer.getBuffer().toString());
    }
}
