package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The ListCommand UnitTest
 * @author Jared Erickson
 */
public class ListCommandTest {

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
}
