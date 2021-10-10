package org.geometrycommands;

import org.geometrycommands.MortonCurveCommand.MortonCurveOptions;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The MortonCurveCommand UnitTest
 * @author Jared Erickson
 */
public class MortonCurveCommandTest extends BaseTest {

    private final String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";

    private final String resultGeometry = "LINESTRING (0 0, 1.4285714285714286 0, 0 1.4285714285714286, " +
        "1.4285714285714286 1.4285714285714286, 2.857142857142857 0, 4.285714285714286 0, 2.857142857142857 1.4285714285714286, " +
        "4.285714285714286 1.4285714285714286, 0 2.857142857142857, 1.4285714285714286 2.857142857142857, 0 4.285714285714286, " +
        "1.4285714285714286 4.285714285714286, 2.857142857142857 2.857142857142857, 4.285714285714286 2.857142857142857, " +
        "2.857142857142857 4.285714285714286, 4.285714285714286 4.285714285714286, 5.714285714285714 0, 7.142857142857143 0, " +
        "5.714285714285714 1.4285714285714286, 7.142857142857143 1.4285714285714286, 8.571428571428571 0, 10 0, " +
        "8.571428571428571 1.4285714285714286, 10 1.4285714285714286, 5.714285714285714 2.857142857142857, " +
        "7.142857142857143 2.857142857142857, 5.714285714285714 4.285714285714286, 7.142857142857143 4.285714285714286, " +
        "8.571428571428571 2.857142857142857, 10 2.857142857142857, 8.571428571428571 4.285714285714286, 10 4.285714285714286, " +
        "0 5.714285714285714, 1.4285714285714286 5.714285714285714, 0 7.142857142857143, 1.4285714285714286 7.142857142857143, " +
        "2.857142857142857 5.714285714285714, 4.285714285714286 5.714285714285714, 2.857142857142857 7.142857142857143, " +
        "4.285714285714286 7.142857142857143, 0 8.571428571428571, 1.4285714285714286 8.571428571428571, 0 10, " +
        "1.4285714285714286 10, 2.857142857142857 8.571428571428571, 4.285714285714286 8.571428571428571, " +
        "2.857142857142857 10, 4.285714285714286 10, 5.714285714285714 5.714285714285714, 7.142857142857143 5.714285714285714, " +
        "5.714285714285714 7.142857142857143, 7.142857142857143 7.142857142857143, 8.571428571428571 5.714285714285714, " +
        "10 5.714285714285714, 8.571428571428571 7.142857142857143, 10 7.142857142857143, 5.714285714285714 8.571428571428571, " +
        "7.142857142857143 8.571428571428571, 5.714285714285714 10, 7.142857142857143 10, 8.571428571428571 8.571428571428571, " +
        "10 8.571428571428571, 8.571428571428571 10, 10 10)";

    @Test 
    public void execute() throws Exception {
        
        MortonCurveOptions options = new MortonCurveOptions();
        options.setGeometry(inputGeometry);
        options.setNumberOfPoints(30);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        MortonCurveCommand command = new MortonCurveCommand();
        command.execute(options, reader, writer);
        assertEquals(resultGeometry, writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "mortoncurve",
                "-g", inputGeometry,
                "-n", "30"
        }, null);
        assertEquals(resultGeometry, result);

        // Geometry from input stream
        result = runApp(new String[]{
                "mortoncurve",
                "-n", "30"
        }, inputGeometry);
        assertEquals(resultGeometry, result);
    }
}
