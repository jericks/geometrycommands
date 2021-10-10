package org.geometrycommands;

import org.geometrycommands.SimilarityCommand.SimilarityOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The SimilarityCommand UnitTest
 * @author Jared Erickson
 */
public class SimilarityCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        // area
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((2 2, 2 14, 14 14, 14 2, 2 2))";
        SimilarityOptions options = new SimilarityOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setAlgorithm("area");
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        SimilarityCommand command = new SimilarityCommand();
        command.execute(options, reader, writer);
        assertEquals("0.35555555555555557", writer.getBuffer().toString());
        
        // hausdorff
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POLYGON ((2 2, 2 14, 14 14, 14 2, 2 2))";
        options = new SimilarityOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setAlgorithm("hausdorff");
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("0.7142857142857142", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((2 2, 2 14, 14 14, 14 2, 2 2))";

        // Geometry from options
        String result = runApp(new String[]{
                "similarity",
                "-g", inputGeometry,
                "-o", otherGeometry,
                "-a", "area"
        }, null);
        assertEquals("0.35555555555555557", result);

        result = runApp(new String[]{
                "similarity",
                "-g", inputGeometry,
                "-o", otherGeometry,
                "-a", "a"
        }, null);
        assertEquals("0.35555555555555557", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "similarity",
                "-o", otherGeometry,
                "-a", "hausdorff"
        }, inputGeometry);
        assertEquals("0.7142857142857142", result);

        result = runApp(new String[]{
                "similarity",
                "-o", otherGeometry,
                "-a", "h"
        }, inputGeometry);
        assertEquals("0.7142857142857142", result);
    }

    @Test
    public void runWithInvalidAlgorithm() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "similarity",
                "-g", "LINESTRING (1 1, 10 10)",
                "-o", "POINT (1 1)",
                "-a", "ASDF"
        }, null);
        assertEquals("Unknown similarity measure algorithm!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help                   : Print help message (default: false)" + NEW_LINE +
                " --web-help               : Open help in a web browser (default: false)" + NEW_LINE +
                " -a (--algorithm) VAL     : The algorithm (area/a or hausdorff/h)" + NEW_LINE +
                " -g (--geometry) VAL      : The input geometry (default: LINESTRING (1 1, 10" + NEW_LINE +
                "                            10))" + NEW_LINE +
                " -o (--otherGeometry) VAL : The other geometry", result.get("err"));
    }
}
