package org.geometrycommands;

import org.geometrycommands.GetGeometryCommand.GetGeometryOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The GetGeometryCommand UnitTest
 * @author Jared Erickson
 */
public class GetGeometryCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        // 0
        String inputGeometry = "MULTIPOINT ((0 0), (0 10), (10 10), (10 0))";
        GetGeometryOptions options = new GetGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setIndex(0);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        GetGeometryCommand command = new GetGeometryCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (0 0)", writer.getBuffer().toString());

        // 2
        options = new GetGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setIndex(2);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("POINT (10 10)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "get",
                "-g", "MULTIPOINT ((0 0), (0 10), (10 10), (10 0))",
                "-n", "0"
        }, null);
        assertEquals("POINT (0 0)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "get",
                "-n", "2"
        }, "MULTIPOINT ((0 0), (0 10), (10 10), (10 0))");
        assertEquals("POINT (10 10)", result);
    }
}
