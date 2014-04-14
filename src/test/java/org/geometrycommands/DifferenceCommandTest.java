package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The DifferenceCommand UnitTest
 * @author Jared Erickson
 */
public class DifferenceCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((5 5, 5 20, 20 20, 20 5, 5 5))";
        OtherGeometryOptions options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        DifferenceCommand command = new DifferenceCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, 0 10, 5 10, 5 5, 10 5, 10 0, 0 0))",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "difference",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-o", "POLYGON ((5 5, 5 20, 20 20, 20 5, 5 5))"
        }, null);
        assertEquals("POLYGON ((0 0, 0 10, 5 10, 5 5, 10 5, 10 0, 0 0))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "difference",
                "-o", "POLYGON ((5 5, 5 20, 20 20, 20 5, 5 5))"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((0 0, 0 10, 5 10, 5 5, 10 5, 10 0, 0 0))", result);
    }
}
