package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.geometrycommands.GetEndPointCommand.GetEndPointOptions;

/**
 * The GetEndPointCommand UnitTest
 * @author Jared Erickson
 */
public class GetEndPointCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (1 1, 5 5, 10 10)";
        GetEndPointOptions options = new GetEndPointOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        GetEndPointCommand command = new GetEndPointCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (10 10)", writer.getBuffer().toString());
        
        inputGeometry = "MULTILINESTRING ((1 1, 5 5, 10 10), (20 20, 30 30, 40 40))";
        options = new GetEndPointOptions();
        options.setGeometry(inputGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("POINT (40 40)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "endpoint",
                "-g", "LINESTRING (1 1, 5 5, 10 10)"
        }, null);
        assertEquals("POINT (10 10)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "endpoint"
        }, "MULTILINESTRING ((1 1, 5 5, 10 10), (20 20, 30 30, 40 40))");
        assertEquals("POINT (40 40)", result);
    }

    @Test
    public void runWithWrongGeometryType() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "endpoint",
                "-g", "POINT (1 1)"
        }, null);
        assertEquals("The input geometry must be a LineString or a MultiLineString!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help              : Print help message (default: false)" + NEW_LINE +
                " --web-help          : Open help in a web browser (default: false)" + NEW_LINE +
                " -g (--geometry) VAL : The input geometry (default: POINT (1 1))", result.get("err"));
    }

}
