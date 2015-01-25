package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.SymDifferenceCommand.SymDifferenceOptions;

/**
 * The SymDifferenceCommand UnitTest
 * @author Jared Erickson
 */
public class SymDifferenceCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((5 5, 5 20, 20 20, 20 5, 5 5))";
        SymDifferenceOptions options = new SymDifferenceOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        SymDifferenceCommand command = new SymDifferenceCommand();
        command.execute(options, reader, writer);
        assertEquals("MULTIPOLYGON (((0 0, 0 10, 5 10, 5 5, 10 5, 10 0, 0 0)), "
                + "((10 5, 10 10, 5 10, 5 20, 20 20, 20 5, 10 5)))",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((5 5, 5 20, 20 20, 20 5, 5 5))";
        String resultGeometry = "MULTIPOLYGON (((0 0, 0 10, 5 10, 5 5, 10 5, 10 0, 0 0)), "
                + "((10 5, 10 10, 5 10, 5 20, 20 20, 20 5, 10 5)))";

        // Geometry from options
        String result = runApp(new String[]{
                "symdifference",
                "-g", inputGeometry,
                "-o", otherGeometry
        }, null);
        assertEquals(resultGeometry, result);

        // Geometry from input stream
        result = runApp(new String[]{
                "symdifference",
                "-o", otherGeometry
        }, inputGeometry);
        assertEquals(resultGeometry, result);
    }
}
