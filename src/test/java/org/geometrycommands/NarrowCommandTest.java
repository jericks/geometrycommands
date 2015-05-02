package org.geometrycommands;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.NarrowCommand.NarrowOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * The NarrowCommand Unit Test
 * @author Jared Erickson
 */
public class NarrowCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {
        NarrowCommand cmd = new NarrowCommand();
        NarrowOptions options = new NarrowOptions();
        options.setGeometry("GEOMETRYCOLLECTION (POINT (1 1), POINT(10 10))");
        Reader reader = new StringReader("");
        Writer writer = new StringWriter();
        cmd.execute(options,reader, writer);
        assertEquals("MULTIPOINT ((1 1), (10 10))", writer.toString());
    }

    @Test
    public void run() throws Exception {
        String result = runApp(new String[] {
                "narrow"
        }, "GEOMETRYCOLLECTION (POINT (1 1), POINT(10 10))");
        assertEquals("MULTIPOINT ((1 1), (10 10))", result);
    }

}
