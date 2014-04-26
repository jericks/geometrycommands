package org.geometrycommands;

import org.geometrycommands.ProjectCommand.ProjectOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The ProjectCommand UnitTest
 * @author Jared Erickson
 */
public class ProjectCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (1186683.01 641934.58)";
        ProjectOptions options = new ProjectOptions();
        options.setGeometry(inputGeometry);
        options.setSource("EPSG:2927");
        options.setTarget("EPSG:4326");
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        ProjectCommand command = new ProjectCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (-122.32131937934592 47.07927009358412)", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "project",
                "-g", "POINT (1186683.01 641934.58)",
                "-s", "EPSG:2927",
                "-t", "EPSG:4326"
        }, null);
        assertEquals("POINT (-122.32131937934592 47.07927009358412)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "project",
                "-s", "EPSG:2927",
                "-t", "EPSG:4326"
        }, "POINT (1186683.01 641934.58)");
        assertEquals("POINT (-122.32131937934592 47.07927009358412)", result);
    }
}
