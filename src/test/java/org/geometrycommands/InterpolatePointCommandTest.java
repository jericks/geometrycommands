package org.geometrycommands;

import org.geometrycommands.InterpolatePointCommand.InterpolatePointOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The InterpolatePointCommand UnitTest
 * @author Jared Erickson
 */
public class InterpolatePointCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (0 0, 5 5, 10 10)";
        InterpolatePointOptions options = new InterpolatePointOptions();
        options.setGeometry(inputGeometry);
        options.setPosition(0.25);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        InterpolatePointCommand command = new InterpolatePointCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (2.5 2.5)", writer.getBuffer().toString());
    }
}
