package org.geometrycommands;

import org.geometrycommands.OffsetCurveCommand.OffsetCurveOptions;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The OffsetCurveCommand UnitTest
 *
 * @author Jared Erickson
 */
public class OffsetCurveCommandTest extends BaseTest {

    private final String offsetCurveWkt = "LINESTRING (-7.071067811865475 8.071067811865476, -3.0710678118654746 12.071067811865476)";

    @Test
    public void execute() throws Exception {
        // Geometry from options
        String inputGeometry = "LINESTRING (0 1, 2 3, 4 5)";
        OffsetCurveOptions options = new OffsetCurveOptions();
        options.setGeometry(inputGeometry);
        options.setEndCapStyle("round");
        options.setMitreLimit(5.0);
        options.setSimplifyFactor(0.01);
        options.setJoinStyle("round");
        options.setDistance(10);

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        OffsetCurveCommand command = new OffsetCurveCommand();
        command.execute(options, reader, writer);
        assertEquals(offsetCurveWkt, writer.getBuffer().toString());

        // Geometry from reader
        options = new OffsetCurveOptions();
        options.setDistance(10);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new OffsetCurveCommand();
        command.execute(options, reader, writer);
        assertEquals(offsetCurveWkt, writer.getBuffer().toString());

        // Single sided
        options = new OffsetCurveOptions();
        options.setDistance(10);
        options.setSingleSided(true);
        options.setEndCapStyle("square");
        options.setQuadrantSegements(6);

        reader = new StringReader("LINESTRING (0 1, 2 3, 4 5)");
        writer = new StringWriter();

        command = new OffsetCurveCommand();
        command.execute(options, reader, writer);
        assertEquals(offsetCurveWkt, writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "offsetCurve",
                "-g", "LINESTRING (0 1, 2 3, 4 5)",
                "-d", "10"
        }, null);
        assertEquals(offsetCurveWkt, result);

        // Geometry from input stream
        result = runApp(new String[]{
                "offsetCurve",
                "-d", "10"
        }, "LINESTRING (0 1, 2 3, 4 5)");
        assertEquals(offsetCurveWkt, result);

        // Butt
        result = runApp(new String[]{
                "offsetCurve",
                "-d", "10",
                "-c", "butt"
        }, "LINESTRING (0 1, 2 3, 4 5)");
        assertEquals(offsetCurveWkt, result);

        // Flat
        result = runApp(new String[]{
                "offsetCurve",
                "-d", "10",
                "-c", "flat"
        }, "LINESTRING (0 1, 2 3, 4 5)");
        assertEquals(offsetCurveWkt, result);

        // Square
        result = runApp(new String[]{
                "offsetCurve",
                "-d", "10",
                "-c", "square"
        }, "LINESTRING (0 1, 2 3, 4 5)");
        assertEquals(offsetCurveWkt, result);

        // Join Style Mitre
        result = runApp(new String[]{
                "offsetCurve",
                "-d", "10",
                "-j", "mitre"
        }, "LINESTRING (0 1, 2 3, 4 5)");
        assertGeometriesSimilar(offsetCurveWkt, result);

        // Join Style Bevel
        result = runApp(new String[]{
                "offsetCurve",
                "-d", "10",
                "-j", "bevel"
        }, "LINESTRING (0 1, 2 3, 4 5)");
        assertGeometriesSimilar(offsetCurveWkt, result);
    }

}
