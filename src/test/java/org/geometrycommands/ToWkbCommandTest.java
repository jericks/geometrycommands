package org.geometrycommands;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.ToWkbCommand.ToWkbOptions;
import static org.junit.Assert.assertEquals;

/**
 * The ToWkbCommand Unit Test
 * @author Jared Erickson
 */
public class ToWkbCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        ToWkbOptions options = new ToWkbOptions();
        options.setGeometry(inputGeometry);
        options.setByteOrder(1);
        options.setOutputDimension(2);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        ToWkbCommand command = new ToWkbCommand();
        command.execute(options, reader, writer);
        assertEquals("0000000003000000010000000500000000000000000000000000000000000000000000000040240000" +
                "000000004024000000000000402400000000000040240000000000000000000000000000000000000000000" +
                "00000000000000000", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        // Geometry from options
        String result = runApp(new String[]{
                "towkb",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertEquals("000000000300000001000000050000000000000000000000000000000000000000000000004" +
                "02400000000000040240000000000004024000000000000402400000000000000000000000000000" +
                "0000000000000000000000000000000", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "towkb",
                "-b", "2",
                "-d", "4"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("0000000003000000010000000500000000000000000000000000000000000000000000000040" +
                "240000000000004024000000000000402400000000000040240000000000000000000000000000000" +
                "00000000000000000000000000000", result);
    }

}
