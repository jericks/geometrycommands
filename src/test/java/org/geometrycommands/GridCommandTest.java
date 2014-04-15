package org.geometrycommands;

import org.geometrycommands.GridCommand.GridOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The GridCommand UnitTest
 * @author Jared Erickson
 */
public class GridCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        GridOptions options = new GridOptions();
        options.setGeometry(inputGeometry);
        options.setNumberOfColumns(2);
        options.setNumberOfRows(2);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        GridCommand command = new GridCommand();
        command.execute(options, reader, writer);
        assertEquals("GEOMETRYCOLLECTION ("
                + "POLYGON ((0 0, 0 5, 5 5, 5 0, 0 0)), "
                + "POLYGON ((5 0, 5 5, 10 5, 10 0, 5 0)), "
                + "POLYGON ((0 5, 0 10, 5 10, 5 5, 0 5)), "
                + "POLYGON ((5 5, 5 10, 10 10, 10 5, 5 5)))",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "grid",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-c", "2",
                "-r", "2"
        }, null);
        assertEquals("GEOMETRYCOLLECTION ("
                + "POLYGON ((0 0, 0 5, 5 5, 5 0, 0 0)), "
                + "POLYGON ((5 0, 5 5, 10 5, 10 0, 5 0)), "
                + "POLYGON ((0 5, 0 10, 5 10, 5 5, 0 5)), "
                + "POLYGON ((5 5, 5 10, 10 10, 10 5, 5 5)))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "grid",
                "-c", "2",
                "-r", "2"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("GEOMETRYCOLLECTION ("
                + "POLYGON ((0 0, 0 5, 5 5, 5 0, 0 0)), "
                + "POLYGON ((5 0, 5 5, 10 5, 10 0, 5 0)), "
                + "POLYGON ((0 5, 0 10, 5 10, 5 5, 0 5)), "
                + "POLYGON ((5 5, 5 10, 10 10, 10 5, 5 5)))", result);
    }
}
