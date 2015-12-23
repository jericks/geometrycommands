package org.geometrycommands;

import org.geometrycommands.KochSnowflakeCommand.KochSnowflakeOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The KochSnowflakeCommand UnitTest
 * @author Jared Erickson
 */
public class KochSnowflakeCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        KochSnowflakeOptions options = new KochSnowflakeOptions();
        options.setGeometry(inputGeometry);
        options.setNumberOfPoints(30);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        KochSnowflakeCommand command = new KochSnowflakeCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((1.6666666666666665 5.773502691896256, "
                + "0.0000000000000004 8.660254037844386, 3.333333333333333 "
                + "8.660254037844386, 5 11.547005383792515, 6.666666666666666 "
                + "8.660254037844386, 10 8.660254037844386, 8.333333333333332 "
                + "5.773502691896258, 10 2.8867513459481287, 6.666666666666667 "
                + "2.8867513459481287, 5 0, 3.333333333333334 2.8867513459481287, "
                + "0 2.8867513459481287, 1.6666666666666665 5.773502691896256))"
                , writer.getBuffer().toString());
                
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "kochsnowflake",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-n", "30"
        }, null);
        assertEquals("POLYGON ((1.6666666666666665 5.773502691896256, "
                + "0.0000000000000004 8.660254037844386, 3.333333333333333 "
                + "8.660254037844386, 5 11.547005383792515, 6.666666666666666 "
                + "8.660254037844386, 10 8.660254037844386, 8.333333333333332 "
                + "5.773502691896258, 10 2.8867513459481287, 6.666666666666667 "
                + "2.8867513459481287, 5 0, 3.333333333333334 2.8867513459481287, "
                + "0 2.8867513459481287, 1.6666666666666665 5.773502691896256))"
                , result);

        // Geometry from input stream
        result = runApp(new String[]{
                "kochsnowflake",
                "-n", "30"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((1.6666666666666665 5.773502691896256, "
                + "0.0000000000000004 8.660254037844386, 3.333333333333333 "
                + "8.660254037844386, 5 11.547005383792515, 6.666666666666666 "
                + "8.660254037844386, 10 8.660254037844386, 8.333333333333332 "
                + "5.773502691896258, 10 2.8867513459481287, 6.666666666666667 "
                + "2.8867513459481287, 5 0, 3.333333333333334 2.8867513459481287, "
                + "0 2.8867513459481287, 1.6666666666666665 5.773502691896256))"
                , result);
    }
}
