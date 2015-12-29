package org.geometrycommands;

import org.geometrycommands.BufferCommand.BufferOptions;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * The BufferCommand UnitTest
 *
 * @author Jared Erickson
 */
public class BufferCommandTest extends BaseTest {

    private final String polygonWkt = "POLYGON ((110 100, 109.80785280403231 98.04909677983872, "
            + "109.23879532511287 96.1731656763491, 108.31469612302546 "
            + "94.44429766980397, 107.07106781186548 92.92893218813452, "
            + "105.55570233019603 91.68530387697454, 103.8268343236509 "
            + "90.76120467488713, 101.95090322016128 90.19214719596769, "
            + "100 90, 98.04909677983872 90.19214719596769, "
            + "96.1731656763491 90.76120467488713, 94.44429766980397 "
            + "91.68530387697454, 92.92893218813452 92.92893218813452, "
            + "91.68530387697454 94.44429766980397, 90.76120467488713 "
            + "96.1731656763491, 90.19214719596769 98.04909677983872, "
            + "90 100.00000000000001, 90.19214719596769 101.9509032201613, "
            + "90.76120467488714 103.82683432365091, 91.68530387697456 "
            + "105.55570233019603, 92.92893218813454 107.07106781186549, "
            + "94.44429766980399 108.31469612302547, 96.17316567634911 "
            + "109.23879532511287, 98.04909677983873 109.80785280403231, "
            + "100.00000000000003 110, 101.95090322016131 109.8078528040323, "
            + "103.82683432365093 109.23879532511286, 105.55570233019606 "
            + "108.31469612302544, 107.0710678118655 107.07106781186545, "
            + "108.31469612302547 105.555702330196, 109.23879532511287 "
            + "103.82683432365086, 109.80785280403231 101.95090322016124, "
            + "110 100))";

    @Test
    public void execute() throws Exception {
        // Geometry from options
        String inputGeometry = "POINT (100 100)";
        BufferOptions options = new BufferOptions();
        options.setGeometry(inputGeometry);
        options.setEndCapStyle("round");
        options.setMitreLimit(5.0);
        options.setSimplifyFactor(0.01);
        options.setJoinStyle("round");
        options.setDistance(10);

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        BufferCommand command = new BufferCommand();
        command.execute(options, reader, writer);
        assertEquals(polygonWkt, writer.getBuffer().toString());

        // Geometry from reader
        options = new BufferOptions();
        options.setDistance(10);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new BufferCommand();
        command.execute(options, reader, writer);
        assertEquals(polygonWkt, writer.getBuffer().toString());

        // Single sided
        options = new BufferOptions();
        options.setDistance(10);
        options.setSingleSided(true);
        options.setEndCapStyle("square");
        options.setQuadrantSegements(6);

        reader = new StringReader("LINESTRING (0 1, 2 3, 4 5)");
        writer = new StringWriter();

        command = new BufferCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((4 5, 2 3, 0 1, -7.071067811865475 8.071067811865476, " +
                "-3.0710678118654746 12.071067811865476, 4 5))", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "buffer",
                "-g", "POINT (100 100)",
                "-d", "10"
        }, null);
        assertEquals(polygonWkt, result);

        // Geometry from input stream
        result = runApp(new String[]{
                "buffer",
                "-d", "10"
        }, "POINT (100 100)");
        assertEquals(polygonWkt, result);

        // Butt
        result = runApp(new String[]{
                "buffer",
                "-d", "10",
                "-c", "butt"
        }, "LINESTRING (0 1, 2 3, 4 5)");
        assertEquals("POLYGON ((-3.0710678118654746 12.071067811865476, 11.071067811865476 -2.0710678118654746, " +
                "7.071067811865475 -6.071067811865475, -7.071067811865475 8.071067811865476, " +
                "-3.0710678118654746 12.071067811865476))", result);

        // Flat
        result = runApp(new String[]{
                "buffer",
                "-d", "10",
                "-c", "flat"
        }, "LINESTRING (0 1, 2 3, 4 5)");
        assertEquals("POLYGON ((-3.0710678118654746 12.071067811865476, 11.071067811865476 -2.0710678118654746, " +
                "7.071067811865475 -6.071067811865475, -7.071067811865475 8.071067811865476, " +
                "-3.0710678118654746 12.071067811865476))", result);

        // Square
        result = runApp(new String[]{
                "buffer",
                "-d", "10",
                "-c", "square"
        }, "LINESTRING (0 1, 2 3, 4 5)");
        assertEquals("POLYGON ((-3.0710678118654746 12.071067811865476, 4.000000000000001 19.14213562373095, " +
                "18.14213562373095 5, 7.071067811865475 -6.071067811865475, 0 -13.142135623730951, " +
                "-14.14213562373095 1, -3.0710678118654746 12.071067811865476))", result);

        // Join Style Mitre
        result = runApp(new String[]{
                "buffer",
                "-d", "10",
                "-j", "mitre"
        }, "LINESTRING (0 1, 2 3, 4 5)");
        assertEquals("POLYGON ((-3.0710678118654746 12.071067811865476, -1.55570233019602 13.314696123025453, " +
                "0.173165676349103 14.238795325112868, 2.049096779838718 14.807852804032304, 4.000000000000001 15, " +
                "5.950903220161283 14.807852804032304, 7.826834323650898 14.238795325112868, " +
                "9.555702330196024 13.314696123025453, 11.071067811865476 12.071067811865476, " +
                "12.314696123025453 10.555702330196022, 13.238795325112868 8.826834323650898, " +
                "13.807852804032304 6.950903220161285, 14 5, 13.807852804032304 3.0490967798387154, " +
                "13.238795325112866 1.1731656763490985, 12.314696123025449 -0.5557023301960271, " +
                "11.071067811865476 -2.0710678118654746, 7.071067811865475 -6.071067811865475, " +
                "5.555702330196023 -7.314696123025453, 3.8268343236508984 -8.238795325112868, " +
                "1.9509032201612833 -8.807852804032304, 0.0000000000000006 -9, -1.950903220161282 -8.807852804032304, " +
                "-3.826834323650897 -8.238795325112868, -5.55570233019602 -7.314696123025453, -7.071067811865475 " +
                "-6.0710678118654755, -8.314696123025453 -4.555702330196022, -9.238795325112868 -2.826834323650899, " +
                "-9.807852804032304 -0.9509032201612861, -10 0.9999999999999988, -9.807852804032304 2.9509032201612837, " +
                "-9.238795325112866 4.826834323650901, -8.314696123025449 6.555702330196026, -7.071067811865475 " +
                "8.071067811865476, -3.0710678118654746 12.071067811865476))", result);

        // Join Style Bevel
        result = runApp(new String[]{
                "buffer",
                "-d", "10",
                "-j", "bevel"
        }, "LINESTRING (0 1, 2 3, 4 5)");
        assertEquals("POLYGON ((-3.0710678118654746 12.071067811865476, -1.55570233019602 13.314696123025453, " +
                "0.173165676349103 14.238795325112868, 2.049096779838718 14.807852804032304, 4.000000000000001 15, " +
                "5.950903220161283 14.807852804032304, 7.826834323650898 14.238795325112868, 9.555702330196024 " +
                "13.314696123025453, 11.071067811865476 12.071067811865476, 12.314696123025453 10.555702330196022, " +
                "13.238795325112868 8.826834323650898, 13.807852804032304 6.950903220161285, 14 5, 13.807852804032304 " +
                "3.0490967798387154, 13.238795325112866 1.1731656763490985, 12.314696123025449 -0.5557023301960271, " +
                "11.071067811865476 -2.0710678118654746, 7.071067811865475 -6.071067811865475, 5.555702330196023 " +
                "-7.314696123025453, 3.8268343236508984 -8.238795325112868, 1.9509032201612833 -8.807852804032304, " +
                "0.0000000000000006 -9, -1.950903220161282 -8.807852804032304, -3.826834323650897 -8.238795325112868, " +
                "-5.55570233019602 -7.314696123025453, -7.071067811865475 -6.0710678118654755, -8.314696123025453 " +
                "-4.555702330196022, -9.238795325112868 -2.826834323650899, -9.807852804032304 -0.9509032201612861, " +
                "-10 0.9999999999999988, -9.807852804032304 2.9509032201612837, -9.238795325112866 4.826834323650901, " +
                "-8.314696123025449 6.555702330196026, -7.071067811865475 8.071067811865476, -3.0710678118654746 " +
                "12.071067811865476))", result);
    }

}
