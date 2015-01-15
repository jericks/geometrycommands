package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.ListCommand.ListOptions;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The ListCommand UnitTest
 * @author Jared Erickson
 */
public class ListCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        ListOptions options = new ListOptions();
        ListCommand command = new ListCommand();
        command.execute(options, reader, writer);
        String output = writer.getBuffer().toString();
        assertFalse(output.isEmpty());
        String[] lines = output.split(System.getProperty("line.separator"));
        assertTrue(lines.length > 0);
        assertTrue(output.contains("buffer"));
        assertTrue(output.contains("centroid"));
    }

    @Test
    public void executeWithDescription() throws Exception {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        ListOptions options = new ListOptions();
        options.setIncludingDescription(true);
        ListCommand command = new ListCommand();
        command.execute(options, reader, writer);
        String output = writer.getBuffer().toString();
        assertFalse(output.isEmpty());
        String[] lines = output.split(System.getProperty("line.separator"));
        assertTrue(lines.length > 0);
        assertTrue(output.contains("buffer = Buffer a geometry by a distance."));
        assertTrue(output.contains("centroid = Calculate the centroid of a Geometry."));
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

    @Test
    public void runWithDescription() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "list", "-d"
        }, null);
        assertFalse(result.isEmpty());
        String[] lines = result.split(System.getProperty("line.separator"));
        assertTrue(lines.length > 0);
        assertTrue(result.contains("buffer = Buffer a geometry by a distance."));
        assertTrue(result.contains("centroid = Calculate the centroid of a Geometry."));
    }
}
