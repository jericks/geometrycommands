package org.geometrycommands;

import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.geometrycommands.AngleCommand.AngleOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The AngleCommand Unit Test
 * @author Jared Erickson
 */
public class AngleCommandTest extends BaseTest {

    @Test
    public void executeDegrees() throws Exception {
        String point1 = "POINT (1 1)";
        String point2 = "POINT (10 10)";
        AngleOptions options = new AngleOptions();
        options.setGeometry(point1);
        options.setOtherGeometry(point2);
        AngleCommand cmd = new AngleCommand();
        Writer writer = new StringWriter();
        Reader reader = new StringReader("");
        cmd.execute(options, reader, writer);
        String result = writer.toString();
        assertEquals(45.0, Double.parseDouble(result), 0.1);
    }

    @Test
    public void executeRadians() throws Exception {
        String point1 = "POINT (1 1)";
        String point2 = "POINT (10 10)";
        AngleOptions options = new AngleOptions();
        options.setGeometry(point1);
        options.setOtherGeometry(point2);
        options.setType("radians");
        AngleCommand cmd = new AngleCommand();
        Writer writer = new StringWriter();
        Reader reader = new StringReader("");
        cmd.execute(options, reader, writer);
        String result = writer.toString();
        assertEquals(0.7853981633974483, Double.parseDouble(result), 0.001);
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "angle",
                "-g", "POINT (1 1)",
                "-o", "POINT (10 10)"
        }, null);
        assertEquals(45.0, Double.parseDouble(result), 0.001);

        // Geometry from input stream
        result = runApp(new String[]{
                "angle",
                "-o", "POINT (10 10)",
                "-t", "radians"
        }, "POINT (1 1)");
        assertEquals(0.7853981633974483, Double.parseDouble(result), 0.001);
    }

}
