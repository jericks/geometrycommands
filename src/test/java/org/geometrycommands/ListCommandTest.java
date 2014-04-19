package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The ListCommand UnitTest
 * @author Jared Erickson
 */
public class ListCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        Options options = new Options();

        ListCommand command = new ListCommand();
        command.execute(options, reader, writer);
        String output = writer.getBuffer().toString();
        assertFalse(output.isEmpty());
        String[] lines = output.split(System.getProperty("line.separator"));
        assertTrue(lines.length > 0);
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "list"
        }, null);
        assertFalse(result.isEmpty());
        String[] lines = result.split(System.getProperty("line.separator"));
        assertTrue(lines.length > 0);
        assertTrue(result.contains("buffer"));
        assertTrue(result.contains("centroid"));
    }
}
