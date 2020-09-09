package org.geometrycommands;

import org.geometrycommands.PipeCommand.PipeOptions;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * The PipeCommand UnitTest
 * @author Jared Erickson
 */
public class PipeCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {
        PipeOptions options = new PipeOptions();
        options.setCommands("buffer -d 10 -g 'POINT(1 1)' | centroid");

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        PipeCommand command = new PipeCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (0.9999999999999994 1)\n", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        String result = runApp(new String[]{
                "pipe",
                "-c", "buffer -d 10 | centroid"
        }, "POINT(1 1)");
        assertEquals("POINT (0.9999999999999994 1)", result);
    }

}
