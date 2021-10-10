package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.geometrycommands.LineMergeCommand.LineMergeOptions;

/**
 * The LineMergeCommand UnitTest
 * @author Jared Erickson
 */
public class LineMergeCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "MULTILINESTRING((-29 -27,-30 -29.7,-36 -31,-45 -33),(-45 -33,-46 -32))";
        LineMergeOptions options = new LineMergeOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        LineMergeCommand command = new LineMergeCommand();
        command.execute(options, reader, writer);
        assertEquals("MULTILINESTRING ((-29 -27, -30 -29.7, -36 -31, -45 -33, -46 -32))",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "linemerge",
                "-g", "MULTILINESTRING((-29 -27,-30 -29.7,-36 -31,-45 -33),(-45 -33,-46 -32))"
        }, null);
        assertEquals("MULTILINESTRING ((-29 -27, -30 -29.7, -36 -31, -45 -33, -46 -32))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "linemerge",
        }, "MULTILINESTRING((-29 -27,-30 -29.7,-36 -31,-45 -33),(-45 -33,-46 -32))");
        assertEquals("MULTILINESTRING ((-29 -27, -30 -29.7, -36 -31, -45 -33, -46 -32))", result);
    }
}