package org.geometrycommands;

import org.geometrycommands.IsCounterClockwiseCommand.IsCounterClockwiseOptions;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The IsCounterClockwiseCommand Unit Test
 * @author Jared Erickson
 */
public class IsCounterClockwiseCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {
        // false
        String inputGeometry = "LINEARRING (15 20, 15 10, 10 10, 10 20, 15 20)";
        IsCounterClockwiseOptions options = new IsCounterClockwiseOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        IsCounterClockwiseCommand command = new IsCounterClockwiseCommand();
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());

        // true
        inputGeometry = "LINEARRING (15 20, 10 20, 10 10, 15 10, 15 20)";
        options = new IsCounterClockwiseOptions();
        options.setGeometry(inputGeometry);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new IsCounterClockwiseCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "isccw",
                "-g", "LINEARRING (15 20, 15 10, 10 10, 10 20, 15 20)"
        }, null);
        assertEquals("false", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "isccw"
        }, "LINEARRING (15 20, 10 20, 10 10, 15 10, 15 20)");
        assertEquals("true", result);
    }
}
