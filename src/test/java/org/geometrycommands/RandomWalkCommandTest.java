package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.io.WKTReader;
import org.geometrycommands.RandomWalkCommand.RandomWalkOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The RandomWalkCommand UnitTest
 * @author Jared Erickson
 */
public class RandomWalkCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POINT (100 100)";
        RandomWalkOptions options = new RandomWalkOptions();
        options.setGeometry(inputGeometry);
        options.setNumberOfWalks(10);
        options.setAngleIncrement(45);
        options.setDistance(10);
        options.setProbability(0.80);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        RandomWalkCommand command = new RandomWalkCommand();
        command.execute(options, reader, writer);
        Geometry g = new WKTReader().read(writer.getBuffer().toString());
        assertTrue(g instanceof LineString);
        assertEquals(11, g.getNumPoints());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "randomwalk",
                "-g", "POINT (100 100)",
                "-n", "10",
                "-d", "10",
                "-a", "45",
                "-p", "0.75"
        }, null);
        Geometry g = new WKTReader().read(result);
        assertTrue(g instanceof LineString);
        assertEquals(11, g.getNumPoints());

        // Geometry from input stream
        result = runApp(new String[]{
                "randomwalk",
                "-n", "10",
                "-d", "10",
                "-a", "45",
                "-p", "0.75"
        }, "POINT (100 100)");
        g = new WKTReader().read(result);
        assertTrue(g instanceof LineString);
        assertEquals(11, g.getNumPoints());
    }
}