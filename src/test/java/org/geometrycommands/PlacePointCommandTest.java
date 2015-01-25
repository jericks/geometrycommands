package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.PlacePointCommand.PlacePointOptions;

/**
 * The PlacePointCommand UnitTest
 * @author Jared Erickson
 */
public class PlacePointCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (0 0, 5 5, 10 10)";
        String otherGeometry = "POINT (3 4.5)";
        PlacePointOptions options = new PlacePointOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        PlacePointCommand command = new PlacePointCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (3.75 3.75)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        String inputGeometry = "LINESTRING (0 0, 5 5, 10 10)";
        String otherGeometry = "POINT (3 4.5)";
        String resultGeometry = "POINT (3.75 3.75)";

        // Geometry from options
        String result = runApp(new String[]{
                "placepoint",
                "-g", inputGeometry,
                "-o", otherGeometry
        }, null);
        assertEquals(resultGeometry, result);

        // Geometry from input stream
        result = runApp(new String[]{
                "placepoint",
                "-o", otherGeometry
        }, inputGeometry);
        assertEquals(resultGeometry, result);
    }
}
