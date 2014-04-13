package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.CoordinatesCommand.CoordinatesOptions;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The CoordinatesCommand UnitTest
 * @author Jared Erickson
 */
public class CoordinatesCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {

        // Default (all coordinates)
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        CoordinatesOptions options = new CoordinatesOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        CoordinatesCommand command = new CoordinatesCommand();
        command.execute(options, reader, writer);
        assertEquals("MULTIPOINT ((0 0), (0 10), (10 10), (10 0), (0 0))", writer.getBuffer().toString());

        // Unique coordinates only
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        options = new CoordinatesOptions();
        options.setUnique(true);
        options.setGeometry(inputGeometry);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new CoordinatesCommand();
        command.execute(options, reader, writer);
        assertEquals("MULTIPOINT ((0 0), (0 10), (10 10), (10 0))", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "coordinates",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertEquals("MULTIPOINT ((0 0), (0 10), (10 10), (10 0), (0 0))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "coordinates",
                "-u"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("MULTIPOINT ((0 0), (0 10), (10 10), (10 0))", result);
    }
}
