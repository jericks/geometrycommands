package org.geometrycommands;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.FromWkbCommand.FromWkbOptions;
import static org.junit.Assert.assertEquals;

/**
 * The FromWkbCommand Unit Test
 * @author Jared Erickson
 */
public class FromWkbCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String wkb = "0000000003000000010000000500000000000000000000000000000000000000000000000040240000" +
                "000000004024000000000000402400000000000040240000000000000000000000000000000000000000000" +
                "00000000000000000";
        FromWkbOptions options = new FromWkbOptions();

        Reader reader = new StringReader(wkb);
        StringWriter writer = new StringWriter();

        FromWkbCommand command = new FromWkbCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        // Geometry from options
        String result = runApp(new String[]{
                "fromwkb",
                "-b", "000000000300000001000000050000000000000000000000000000000000000000000000004" +
                "02400000000000040240000000000004024000000000000402400000000000000000000000000000" +
                "0000000000000000000000000000000"
        }, null);
        assertEquals("POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))", result);

        result = runApp(new String[]{
                "fromwkb",
                "--wkb", "000000000300000001000000050000000000000000000000000000000000000000000000004" +
                "02400000000000040240000000000004024000000000000402400000000000000000000000000000" +
                "0000000000000000000000000000000"
        }, null);
        assertEquals("POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "fromwkb",
        }, "0000000003000000010000000500000000000000000000000000000000000000000000000040" +
                "240000000000004024000000000000402400000000000040240000000000000000000000000000000" +
                "00000000000000000000000000000");
        assertEquals("POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))", result);
    }

}
