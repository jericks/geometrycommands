package org.geometrycommands;

import org.geometrycommands.DensifyCommand.DensifyOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The DensifyCommand UnitTest
 * @author Jared Erickson
 */
public class DensifyCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "LINESTRING (1 1, 5 5, 12 12)";
        DensifyOptions options = new DensifyOptions();
        options.setGeometry(inputGeometry);
        options.setDistanceTolerance(2);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        DensifyCommand command = new DensifyCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (1 1, 2.3333333333333335 2.3333333333333335, "
                + "3.666666666666667 3.666666666666667, 5 5, 6.4 6.4, "
                + "7.800000000000001 7.800000000000001, 9.200000000000001 "
                + "9.200000000000001, 10.600000000000001 10.600000000000001, "
                + "12 12)", writer.getBuffer().toString());
    }
}
