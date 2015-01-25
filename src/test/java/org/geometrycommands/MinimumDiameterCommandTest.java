package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.MinimumDiameterCommand.MinimumDiameterOptions;

/**
 * The MinimumDiameterCommand UnitTest
 * @author Jared Erickson
 */
public class MinimumDiameterCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((15.92041015625 51.25, 6.91162109375 "
                + "52.5244140625, 12.36083984375 45.9765625, 5.46142578125 "
                + "40.7470703125, 11.56982421875 41.1865234375, "
                + "15.92041015625 51.25))";
        MinimumDiameterOptions options = new MinimumDiameterOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        MinimumDiameterCommand command = new MinimumDiameterCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (14.966024044129165 49.042379599235645, "
                + "6.91162109375 52.5244140625)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "mindiameter",
                "-g", "POLYGON ((15.92041015625 51.25, 6.91162109375 "
                + "52.5244140625, 12.36083984375 45.9765625, 5.46142578125 "
                + "40.7470703125, 11.56982421875 41.1865234375, "
                + "15.92041015625 51.25))",
        }, null);
        assertEquals("LINESTRING (14.966024044129165 49.042379599235645, "
                + "6.91162109375 52.5244140625)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "mindiameter"
        }, "POLYGON ((15.92041015625 51.25, 6.91162109375 "
                + "52.5244140625, 12.36083984375 45.9765625, 5.46142578125 "
                + "40.7470703125, 11.56982421875 41.1865234375, "
                + "15.92041015625 51.25))");
        assertEquals("LINESTRING (14.966024044129165 49.042379599235645, "
                + "6.91162109375 52.5244140625)", result);
    }
}