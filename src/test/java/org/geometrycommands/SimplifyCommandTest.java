package org.geometrycommands;

import org.geometrycommands.SimplifyCommand.SimplifyOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The SimplifyCommand UnitTest
 * @author Jared Erickson
 */
public class SimplifyCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        // douglaspeucker
        String inputGeometry = "LINESTRING (1 1, 2.3333333333333335 2.3333333333333335, "
                + "3.666666666666667 3.666666666666667, 5 5, 6.4 6.4, "
                + "7.800000000000001 7.800000000000001, 9.200000000000001 "
                + "9.200000000000001, 10.600000000000001 10.600000000000001, "
                + "12 12)";
        SimplifyOptions options = new SimplifyOptions();
        options.setGeometry(inputGeometry);
        options.setDistanceTolerance(2);
        options.setAlgorithm("douglaspeucker");
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        SimplifyCommand command = new SimplifyCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (1 1, 12 12)", writer.getBuffer().toString());
        
        // topologypreserving
        options = new SimplifyOptions();
        options.setGeometry(inputGeometry);
        options.setDistanceTolerance(2);
        options.setAlgorithm("topologypreserving");
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (1 1, 12 12)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        String inputGeometry = "LINESTRING (1 1, 2.3333333333333335 2.3333333333333335, "
                + "3.666666666666667 3.666666666666667, 5 5, 6.4 6.4, "
                + "7.800000000000001 7.800000000000001, 9.200000000000001 "
                + "9.200000000000001, 10.600000000000001 10.600000000000001, "
                + "12 12)";
        String otherGeometry = "POLYGON ((2 2, 2 14, 14 14, 14 2, 2 2))";

        // Geometry from options
        String result = runApp(new String[]{
                "simplify",
                "-g", inputGeometry,
                "-a", "douglaspeucker",
                "-d", "2"
        }, null);
        assertEquals("LINESTRING (1 1, 12 12)", result);

        result = runApp(new String[]{
                "simplify",
                "-g", inputGeometry,
                "-a", "dp",
                "-d", "2"
        }, null);
        assertEquals("LINESTRING (1 1, 12 12)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "simplify",
                "-a", "topologypreserving",
                "-d", "2"
        }, inputGeometry);
        assertEquals("LINESTRING (1 1, 12 12)", result);

        result = runApp(new String[]{
                "simplify",
                "-a", "tp",
                "-d", "2"
        }, inputGeometry);
        assertEquals("LINESTRING (1 1, 12 12)", result);
    }

    @Test
    public void runWithInvalidAlgorithm() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "simplify",
                "-g", "LINESTRING (1 1, 10 10)",
                "-a", "ASDF",
                "-d", "2"
        }, null);
        assertEquals("Unknown simplifier algorithm!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help               : Print help message (default: false)" + NEW_LINE +
                " -a (--algorithm) VAL : The distance tolerance (douglaspeucker/dp or" + NEW_LINE +
                "                        topologypreserving/tp)" + NEW_LINE +
                " -d (--distance) N    : The distance tolerance" + NEW_LINE +
                " -g (--geometry) VAL  : The input geometry (default: LINESTRING (1 1, 10 10))", result.get("err"));
    }
}
