package org.geometrycommands;

import org.geometrycommands.SimplifyCommand.SimplifyOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The SimplifyCommand UnitTest
 * @author Jared Erickson
 */
public class SimplifyCommandTest {

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
}
