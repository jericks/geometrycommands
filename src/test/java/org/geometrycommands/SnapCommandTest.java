package org.geometrycommands;

import org.geometrycommands.SnapCommand.SnapOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The SnapCommand UnitTest
 * @author Jared Erickson
 */
public class SnapCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((11 11, 11 20, 20 20, 20 11, 11 11))";
        SnapOptions options = new SnapOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setDistance(1.5);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        SnapCommand command = new SnapCommand();
        command.execute(options, reader, writer);
        assertEquals("GEOMETRYCOLLECTION ("
                + "POLYGON ((0 0, 0 10, 11 11, 10 0, 0 0)), "
                + "POLYGON ((11 11, 11 20, 20 20, 20 11, 11 11)))",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((11 11, 11 20, 20 20, 20 11, 11 11))";
        String resultGeometry = "GEOMETRYCOLLECTION ("
                + "POLYGON ((0 0, 0 10, 11 11, 10 0, 0 0)), "
                + "POLYGON ((11 11, 11 20, 20 20, 20 11, 11 11)))";

        // Geometry from options
        String result = runApp(new String[]{
                "snap",
                "-g", inputGeometry,
                "-o", otherGeometry,
                "-d", "1.5"
        }, null);
        assertEquals(resultGeometry, result);

        // Geometry from input stream
        result = runApp(new String[]{
                "snap",
                "-o", otherGeometry,
                "-d", "1.5"
        }, inputGeometry);
        assertEquals(resultGeometry, result);
    }
}
