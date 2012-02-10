package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The LineMergeCommand UnitTest
 * @author Jared Erickson
 */
public class LineMergeCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "MULTILINESTRING((-29 -27,-30 -29.7,-36 -31,-45 -33),(-45 -33,-46 -32))";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        LineMergeCommand command = new LineMergeCommand();
        command.execute(options, reader, writer);
        assertEquals("MULTILINESTRING ((-29 -27, -30 -29.7, -36 -31, -45 -33, -46 -32))",
                writer.getBuffer().toString());
    }
}