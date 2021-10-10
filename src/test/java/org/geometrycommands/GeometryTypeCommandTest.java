package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.geometrycommands.GeometryTypeCommand.GeometryTypeOptions;

/**
 * The GeometryTypeCommand UnitTest
 * @author Jared Erickson
 */
public class GeometryTypeCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        // Polygon
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        GeometryTypeOptions options = new GeometryTypeOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        GeometryTypeCommand command = new GeometryTypeCommand();
        command.execute(options, reader, writer);
        assertEquals("Polygon", writer.getBuffer().toString());
        
        // Point
        inputGeometry = "POINT (5 5)";
        options = new GeometryTypeOptions();
        options.setGeometry(inputGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command = new GeometryTypeCommand();
        command.execute(options, reader, writer);
        assertEquals("Point", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "type",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertEquals("Polygon", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "type"
        }, "POINT (5 5)");
        assertEquals("Point", result);
    }
}
