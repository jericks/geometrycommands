package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.GetStartPointCommand.GetStartPointOptions;

/**
 * The GetStartPointCommand UnitTest
 * @author Jared Erickson
 */
public class GetStartPointCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (1 1, 5 5, 10 10)";
        GetStartPointOptions options = new GetStartPointOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        GetStartPointCommand command = new GetStartPointCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (1 1)", writer.getBuffer().toString());
        
        inputGeometry = "MULTILINESTRING ((1 1, 5 5, 10 10), (20 20, 30 30, 40 40))";
        options = new GetStartPointOptions();
        options.setGeometry(inputGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("POINT (1 1)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "startpoint",
                "-g", "LINESTRING (1 1, 5 5, 10 10)"
        }, null);
        assertEquals("POINT (1 1)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "startpoint"
        }, "MULTILINESTRING ((1 1, 5 5, 10 10), (20 20, 30 30, 40 40))");
        assertEquals("POINT (1 1)", result);
    }

    @Test
    public void runWithWrongGeometryType() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "startpoint",
                "-g", "POINT (1 1)"
        }, null);
        assertEquals("The input geometry must be a LineString or a MultiLineString!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help              : Print help message (default: false)" + NEW_LINE +
                " -g (--geometry) VAL : The input geometry (default: POINT (1 1))", result.get("err"));
    }
}
