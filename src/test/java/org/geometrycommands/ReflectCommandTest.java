package org.geometrycommands;

import org.geometrycommands.ReflectCommand.ReflectOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The ReflectCommand UnitTest
 * @author Jared Erickson
 */
public class ReflectCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        ReflectOptions options = new ReflectOptions();
        options.setGeometry(inputGeometry);
        options.setX0(5);
        options.setY0(2);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        ReflectCommand command = new ReflectCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, 6.8965517241379315 -7.241379310344829, "
                + "14.137931034482762 -0.3448275862068977, "
                + "7.241379310344829 6.8965517241379315, 0 0))", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "reflect",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-0", "5",
                "-1", "2"
        }, null);
        assertEquals("POLYGON ((0 0, 6.8965517241379315 -7.241379310344829, "
                + "14.137931034482762 -0.3448275862068977, "
                + "7.241379310344829 6.8965517241379315, 0 0))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "reflect",
                "--x0", "5",
                "--y0", "2"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((0 0, 6.8965517241379315 -7.241379310344829, "
                + "14.137931034482762 -0.3448275862068977, "
                + "7.241379310344829 6.8965517241379315, 0 0))", result);
    }
}
