package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.geometrycommands.MinimumClearanceCommand.MinimumClearanceOptions;

/**
 * The MinimumClearanceCommand UnitTest
 * @author Jared Erickson
 */
public class MinimumClearanceCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((15.92041015625 51.25, 6.91162109375 "
                + "52.5244140625, 12.36083984375 45.9765625, 5.46142578125 "
                + "40.7470703125, 11.56982421875 41.1865234375, "
                + "15.92041015625 51.25))";
        MinimumClearanceOptions options = new MinimumClearanceOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        MinimumClearanceCommand command = new MinimumClearanceCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (12.36083984375 45.9765625, "
                + "13.439104121814852 45.510413314286374)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "minclearance",
                "-g", "POLYGON ((15.92041015625 51.25, 6.91162109375 "
                + "52.5244140625, 12.36083984375 45.9765625, 5.46142578125 "
                + "40.7470703125, 11.56982421875 41.1865234375, "
                + "15.92041015625 51.25))"
        }, null);
        assertEquals("LINESTRING (12.36083984375 45.9765625, "
                + "13.439104121814852 45.510413314286374)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "minclearance"
        }, "POLYGON ((15.92041015625 51.25, 6.91162109375 "
                + "52.5244140625, 12.36083984375 45.9765625, 5.46142578125 "
                + "40.7470703125, 11.56982421875 41.1865234375, "
                + "15.92041015625 51.25))");
        assertEquals("LINESTRING (12.36083984375 45.9765625, "
                + "13.439104121814852 45.510413314286374)", result);
    }
}