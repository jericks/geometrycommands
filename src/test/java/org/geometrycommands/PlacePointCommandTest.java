package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The PlacePointCommand UnitTest
 * @author Jared Erickson
 */
public class PlacePointCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (0 0, 5 5, 10 10)";
        String otherGeometry = "POINT (3 4.5)";
        OtherGeometryOptions options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        PlacePointCommand command = new PlacePointCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (3.75 3.75)", writer.getBuffer().toString());
    }
}
