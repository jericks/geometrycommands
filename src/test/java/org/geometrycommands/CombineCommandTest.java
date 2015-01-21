package org.geometrycommands;

import org.geometrycommands.CombineCommand.CombineOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The CombineCommand UnitTest
 * @author Jared Erickson
 */
public class CombineCommandTest extends BaseTest {

    private final static String NEW_LINE = System.getProperty("line.separator");
    
    @Test 
    public void execute() throws Exception {

        CombineOptions options = new CombineOptions();
        CombineCommand command = new CombineCommand();
        
        // Two Points
        String text = "POINT (1 1)" + NEW_LINE + "POINT (2 2)" + NEW_LINE;
        Reader reader = new StringReader(text);
        StringWriter writer = new StringWriter();
        command.execute(options, reader, writer);
        assertEquals("MULTIPOINT ((1 1), (2 2))", writer.getBuffer().toString());
        
        // Three Points
        text = "POINT (5 5)" + NEW_LINE + "POINT (1 1)" + NEW_LINE + "POINT (2 2)" + NEW_LINE;
        reader = new StringReader(text);
        writer = new StringWriter();
        command.execute(options, reader, writer);
        assertEquals("MULTIPOINT ((5 5), (1 1), (2 2))", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        String result = runApp(new String[]{
                "combine",
        }, "POINT (1 1)" + NEW_LINE + "POINT (2 2)" + NEW_LINE);
        assertEquals("MULTIPOINT ((1 1), (2 2))", result);
    }
}
