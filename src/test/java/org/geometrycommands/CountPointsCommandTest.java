package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The CountPointsCommand UnitTest
 * @author Jared Erickson
 */
public class CountPointsCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "MULTIPOINT ((0 0), (0 10), (10 10), (10 0), (0 0))";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        CountPointsCommand command = new CountPointsCommand();
        command.execute(options, reader, writer);
        assertEquals("5", writer.getBuffer().toString());
    }
}
