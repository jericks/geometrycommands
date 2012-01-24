package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The DistanceLineStringCommand UnitTest
 * @author Jared Erickson
 */
public class DistanceLineStringCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (1 1)";
        String otherGeometry = "POINT (20 23)";
        OtherGeometryOptions options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        DistanceLineStringCommand command = new DistanceLineStringCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (1 1, 20 23)", writer.getBuffer().toString());
    }
}
