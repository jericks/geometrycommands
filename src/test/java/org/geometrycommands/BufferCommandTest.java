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
    }

}
