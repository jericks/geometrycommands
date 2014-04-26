package org.geometrycommands;

import org.geometrycommands.TranslateCommand.TranslateOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The TranslateCommand UnitTest
 * @author Jared Erickson
 */
public class TranslateCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        TranslateOptions options = new TranslateOptions();
        options.setGeometry(inputGeometry);
        options.setX(4);
        options.setY(2);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        TranslateCommand command = new TranslateCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((4 2, 4 12, 14 12, 14 2, 4 2))", 
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        // Geometry from options
        String result = runApp(new String[]{
                "translate",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-x", "4",
                "-y", "2"
        }, null);
        assertEquals("POLYGON ((4 2, 4 12, 14 12, 14 2, 4 2))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "translate",
                "-x", "4",
                "-y", "2"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((4 2, 4 12, 14 12, 14 2, 4 2))", result);
    }
}
