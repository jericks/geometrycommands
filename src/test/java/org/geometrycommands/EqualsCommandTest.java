package org.geometrycommands;

import org.geometrycommands.EqualsCommand.EqualsOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The EqualsCommand UnitTest
 * @author Jared Erickson
 */
public class EqualsCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        // true
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        EqualsOptions options = new EqualsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        EqualsCommand command = new EqualsCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // false
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POLYGON ((1 1, 1 10, 10 10, 10 1, 1 1))";
        options = new EqualsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new EqualsCommand();
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());

        // exact
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        options = new EqualsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setType("exact");

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new EqualsCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // norm
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        options = new EqualsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setType("norm");

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new EqualsCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // topo
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        options = new EqualsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setType("topo");

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new EqualsCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // exact with tolerance
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        options = new EqualsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setType("exact");
        options.setTolerance(1.1);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new EqualsCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "equals",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-o", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertTrue(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "equals",
                "-o", "POLYGON ((1 1, 1 10, 10 10, 10 1, 1 1))"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertFalse(Boolean.parseBoolean(result));

        // topo
        result = runApp(new String[]{
                "equals",
                "-o", "POLYGON ((1 1, 1 10, 10 10, 10 1, 1 1))",
                "-t", "topo"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertFalse(Boolean.parseBoolean(result));
    }
}
