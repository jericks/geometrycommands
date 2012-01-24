package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The MinimumDiameterCommand UnitTest
 * @author Jared Erickson
 */
public class MinimumDiameterCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((15.92041015625 51.25, 6.91162109375 "
                + "52.5244140625, 12.36083984375 45.9765625, 5.46142578125 "
                + "40.7470703125, 11.56982421875 41.1865234375, "
                + "15.92041015625 51.25))";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        MinimumDiameterCommand command = new MinimumDiameterCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (14.966024044129165 49.042379599235645, "
                + "6.91162109375 52.5244140625)", writer.getBuffer().toString());
    }
}