package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.WKTReader;
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
public class RandomWalkCommandTest {

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
}