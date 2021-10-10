package org.geometrycommands;

import org.junit.jupiter.api.Test;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.geometrycommands.UnionCommand.UnionOptions;

/**
 * The UnionCommand UnitTest
 * @author Jared Erickson
 */
public class UnionCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((10 10, 10 14, 14 14, 14 10, 10 10))";
        UnionOptions options = new UnionOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        UnionCommand command = new UnionCommand();
        command.execute(options, reader, writer);
        assertEquals("MULTIPOLYGON (((0 0, 0 10, 10 10, 10 0, 0 0)), "
                + "((10 10, 10 14, 14 14, 14 10, 10 10)))", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((10 10, 10 14, 14 14, 14 10, 10 10))";
        String resultGeometry = "MULTIPOLYGON (((0 0, 0 10, 10 10, 10 0, 0 0)), "
                + "((10 10, 10 14, 14 14, 14 10, 10 10)))";

        // Geometry from options
        String result = runApp(new String[]{
                "union",
                "-g", inputGeometry,
                "-o", otherGeometry
        }, null);
        assertEquals(resultGeometry, result);

        // Geometry from input stream
        result = runApp(new String[]{
                "union",
                "-o", otherGeometry
        }, inputGeometry);
        assertEquals(resultGeometry, result);
    }
}
